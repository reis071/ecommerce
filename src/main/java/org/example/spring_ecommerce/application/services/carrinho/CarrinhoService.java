package org.example.spring_ecommerce.application.services.carrinho;

import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.entities.carrinho.CarrinhoEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.entities.itemCarrinho.ItemCarrinhoEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.entities.itemVenda.ItemVendaEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.entities.produto.ProdutoEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.entities.venda.VendaEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.repositories.carrinho.CarrinhoDAO;
import org.example.spring_ecommerce.adapters.outBound.repositories.itemCarrinho.ItemCarrinhoRepositoryJPA;
import org.example.spring_ecommerce.adapters.outBound.repositories.itemVenda.ItemVendaRepositoryJPA;
import org.example.spring_ecommerce.adapters.outBound.repositories.produto.ProdutoRepositoryJPA;
import org.example.spring_ecommerce.adapters.outBound.repositories.usuario.UsuarioDAO;
import org.example.spring_ecommerce.adapters.outBound.repositories.venda.VendaRepositoryJPA;
import org.example.spring_ecommerce.domain.carrinho.Carrinho;
import org.example.spring_ecommerce.infrastructure.configuration.advices.exceptionExclusives.ProdutoInativo;
import org.example.spring_ecommerce.domain.enums.StatusVenda;
import org.example.spring_ecommerce.domain.usuario.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CarrinhoService {

    private final ProdutoRepositoryJPA produtoRepositoryJPA;
    private final VendaRepositoryJPA vendaRepositoryJPA;
    private final ItemVendaRepositoryJPA itemVendaRepository;
    private final UsuarioDAO usuarioDAO;
    private final CarrinhoDAO carrinhoDAO;
    private final ItemCarrinhoRepositoryJPA itemCarrinhoRepository;



    public VendaEntityJPA compra(String nomeProd, int quantidade){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Usuario usuario = usuarioDAO.findByEmail(email);

        ProdutoEntityJPA produtoAtual = produtoRepositoryJPA.findByNome(nomeProd)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        if (!produtoAtual.isAtivo()){
            throw new ProdutoInativo();
        }

        if (quantidade <= 0 || quantidade > produtoAtual.getEstoque()) {
            throw new IllegalArgumentException("Quantidade inválida, estoque insuficiente");
        }


        VendaEntityJPA venda = new VendaEntityJPA(usuario, LocalDateTime.now(), quantidade * produtoAtual.getPreco());
        venda.setStatus(StatusVenda.VENDIDO);

        if(usuario.getSaldo() >= (quantidade * produtoAtual.getPreco())){
            usuario.setSaldo(usuario.getSaldo() - quantidade * produtoAtual.getPreco());
            usuarioDAO.save(usuario);
            vendaRepositoryJPA.save(venda);
        }
        else {
            venda.setStatus(StatusVenda.CANCELADA);
            vendaRepositoryJPA.save(venda);
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        produtoAtual.setEstoque(produtoAtual.getEstoque() - quantidade);
        produtoRepositoryJPA.save(produtoAtual);

        ItemVendaEntityJPA itemVendaEntityJPA = new ItemVendaEntityJPA(produtoAtual, venda, quantidade);
        venda.getItensVenda().add(itemVendaEntityJPA);

        itemVendaRepository.save(itemVendaEntityJPA);

        return venda;
    }

    // Método para adicionar um item ao carrinho
    public CarrinhoEntityJPA adicionarAoCarrinho(String nomeProd, int quantidade) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Usuario usuario = usuarioDAO.findByEmail(email);

        ProdutoEntityJPA produtoAtual = produtoRepositoryJPA.findByNome(nomeProd)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        if (!produtoAtual.isAtivo()) {
            throw new ProdutoInativo();
        }

        if (quantidade <= 0 || quantidade > produtoAtual.getEstoque()) {
            throw new IllegalArgumentException("Quantidade inválida, estoque insuficiente");
        }

        Carrinho  carrinho = carrinhoDAO.procurarUsuario(usuario);
        carrinhoDAO.salvar(usuario);

        // Adiciona o item ao carrinho
        ItemCarrinhoEntityJPA itemCarrinho = new ItemCarrinhoEntityJPA(carrinho, produtoAtual, quantidade);
        itemCarrinhoRepository.save(itemCarrinho);
        carrinho.getItens().add(itemCarrinho);

        return carrinho;
    }

    // Método para comprar os itens do carrinho
    public VendaEntityJPA finalizarCompra() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Usuario usuario = usuarioDAO.findByEmail(email);

        Carrinho carrinho = carrinhoDAO.procurarUsuario(usuario);

        List<ItemCarrinhoEntityJPA> itensCarrinho = carrinho.getItens();
        if (itensCarrinho.isEmpty()) {
            throw new IllegalArgumentException("O carrinho está vazio.");
        }

        double valorTotal = itensCarrinho.stream()
                .mapToDouble(item -> item.getProduto().getPreco() * item.getQuantidade())
                .sum();

        if (usuario.getSaldo() < valorTotal) {
            throw new IllegalArgumentException("Saldo insuficiente para completar a compra.");
        }

        // Cria a venda
        VendaEntityJPA venda = new VendaEntityJPA(usuario, LocalDateTime.now(), valorTotal);
        venda.setStatus(StatusVenda.VENDIDO);
        vendaRepositoryJPA.save(venda);

        // Processa cada item do carrinho
        for (ItemCarrinhoEntityJPA itemCarrinho : itensCarrinho) {
            ProdutoEntityJPA produto = itemCarrinho.getProduto();
            int quantidade = itemCarrinho.getQuantidade();

            if (quantidade > produto.getEstoque()) {
                throw new IllegalArgumentException("Estoque insuficiente para o produto: " + produto.getNome());
            }

            produto.setEstoque(produto.getEstoque() - quantidade);
            produtoRepositoryJPA.save(produto);

            ItemVendaEntityJPA itemVendaEntityJPA = new ItemVendaEntityJPA(produto, venda, quantidade);
            itemVendaRepository.save(itemVendaEntityJPA);
            venda.getItensVenda().add(itemVendaEntityJPA);
        }

        // Deduz o saldo do usuário e atualiza
        usuario.setSaldo(usuario.getSaldo() - valorTotal);
        usuarioDAO.save(usuario);

        // Limpa o carrinho após a compra
        carrinho.getItens().clear();
        carrinhoDAO.salvar(carrinho.getUsuario());

        return venda;
    }

    // Remover produto do carrinho pelo nome
    public void removerProdutoDoCarrinhoPorNome(String nomeProduto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Usuario usuario = usuarioDAO.findByEmail(email);

        Carrinho carrinho = carrinhoDAO.procurarUsuario(usuario);

        ProdutoEntityJPA produto = produtoRepositoryJPA.findByNome(nomeProduto)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado com o nome: " + nomeProduto));

        Optional<ItemCarrinhoEntityJPA> itemCarrinho = itemCarrinhoRepository.findByCarrinhoAndProduto(carrinho, produto);

        if (itemCarrinho.isPresent()) {
            itemCarrinhoRepository.delete(itemCarrinho.get());
        } else {
            throw new IllegalArgumentException("Produto não encontrado no carrinho");
        }
    }

    public String precoTotalCarrinho(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Usuario usuario = usuarioDAO.findByEmail(email);

        Carrinho carrinho = carrinhoDAO.procurarUsuario(usuario);

        List<ItemCarrinhoEntityJPA> itensCarrinho = carrinho.getItens();
        if (itensCarrinho.isEmpty()) {
            throw new IllegalArgumentException("O carrinho está vazio.");
        }

        double valorTotal = itensCarrinho.stream()
                .mapToDouble(item -> item.getProduto().getPreco() * item.getQuantidade())
                .sum();

        return "{valorTotal: " + valorTotal + "}";
    }

}
