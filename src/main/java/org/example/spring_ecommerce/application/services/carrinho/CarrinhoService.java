package org.example.spring_ecommerce.application.services.carrinho;

import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.entities.itemCarrinho.ItemCarrinhoEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.repositories.carrinho.CarrinhoImpl;
import org.example.spring_ecommerce.adapters.outBound.repositories.itemCarrinho.ItemCarrinhoImpl;
import org.example.spring_ecommerce.adapters.outBound.repositories.itemVenda.ItemVendaImpl;
import org.example.spring_ecommerce.adapters.outBound.repositories.produto.ProdutoImpl;
import org.example.spring_ecommerce.adapters.outBound.repositories.usuario.UsuarioImpl;
import org.example.spring_ecommerce.adapters.outBound.repositories.venda.VendaImpl;
import org.example.spring_ecommerce.application.useCases.carrinho.CarrinhoUseCases;
import org.example.spring_ecommerce.domain.carrinho.Carrinho;
import org.example.spring_ecommerce.domain.itemCarrinho.ItemCarrinho;
import org.example.spring_ecommerce.domain.itemVenda.ItemVenda;
import org.example.spring_ecommerce.domain.produto.Produto;
import org.example.spring_ecommerce.domain.venda.Venda;
import org.example.spring_ecommerce.infrastructure.configuration.advices.exceptionExclusives.ProdutoInativo;
import org.example.spring_ecommerce.domain.enums.StatusVenda;
import org.example.spring_ecommerce.domain.usuario.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CarrinhoService implements CarrinhoUseCases {

    private final ProdutoImpl produtoImpl;
    private final VendaImpl vendaImpl;
    private final ItemVendaImpl itemVendaImpl;
    private final UsuarioImpl usuarioImpl;
    private final CarrinhoImpl carrinhoImpl;
    private final ItemCarrinhoImpl itemCarrinhoImpl;

    public Venda compra(String nomeProd, int quantidade){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Usuario usuario = usuarioImpl.procurarUsuarioPorEmail(email);
        Produto produtoAtual = produtoImpl.procurarProdutoPorNome(nomeProd);

        if (!produtoAtual.isAtivo()){
            throw new ProdutoInativo();
        }

        if (quantidade <= 0 || quantidade > produtoAtual.getEstoque()) {
            throw new IllegalArgumentException("Quantidade inválida, estoque insuficiente");
        }

        Venda venda = new Venda(usuario, LocalDateTime.now(), quantidade * produtoAtual.getPreco());

        if(usuario.getSaldo() >= (quantidade * produtoAtual.getPreco())){
            usuario.setSaldo(usuario.getSaldo() - quantidade * produtoAtual.getPreco());

            venda.setStatus(StatusVenda.VENDIDO);

            produtoAtual.setEstoque(produtoAtual.getEstoque() - quantidade);

            ItemVenda itemVenda = new ItemVenda(produtoAtual, venda, quantidade);
            venda.getItensVenda().add(itemVenda);

            produtoImpl.salvar(produtoAtual);
            usuarioImpl.salvar(usuario);
            vendaImpl.salva(venda);
            itemVendaImpl.salvar(itemVenda);

            return venda;
        }

        else {
            venda.setStatus(StatusVenda.CANCELADA);
            vendaImpl.salva(venda);
            throw new IllegalArgumentException("Saldo insuficiente");
        }

    }

    // Método para adicionar um item ao carrinho
    public Carrinho adicionarAoCarrinho(String nomeProd, int quantidade) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Usuario usuario = usuarioImpl.procurarUsuarioPorEmail(email);

        Produto produtoAtual = produtoImpl.procurarProdutoPorNome(nomeProd);

        if (!produtoAtual.isAtivo()) {
            throw new ProdutoInativo();
        }

        if (quantidade <= 0 || quantidade > produtoAtual.getEstoque()) {
            throw new IllegalArgumentException("Quantidade inválida, estoque insuficiente");
        }

        Carrinho carrinho = carrinhoImpl.salvar(usuario);

        itemCarrinhoImpl.salvar(carrinho, produtoAtual, quantidade);

        return carrinho;
    }

    // Método para comprar os itens do carrinho
    public Venda finalizarCompra() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Usuario usuario = usuarioImpl.procurarUsuarioPorEmail(email);

        List<ItemCarrinho> itensCarrinho = itemCarrinhoImpl.TodosOsItens(usuario);

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
        Venda venda = new Venda(usuario, LocalDateTime.now(), valorTotal);
        venda.setStatus(StatusVenda.VENDIDO);
        vendaImpl.salva(venda);

        // Processa cada item do carrinho
        for (ItemCarrinho itemCarrinho : itensCarrinho) {
            Produto produto = itemCarrinho.getProduto();

            int quantidade = itemCarrinho.getQuantidade();

            if (quantidade > produto.getEstoque()) {
                throw new IllegalArgumentException("Estoque insuficiente para o produto: " + produto.getNome());
            }

            produto.setEstoque(produto.getEstoque() - quantidade);
            produtoImpl.salvar(produto);

            ItemVenda itemVenda = new ItemVenda(produto, venda, quantidade);
            itemVendaImpl.salvar(itemVenda);
            venda.getItensVenda().add(itemVenda);
        }

        // Deduz o saldo do usuário e atualiza
        usuario.setSaldo(usuario.getSaldo() - valorTotal);
        usuarioImpl.salvar(usuario);

        // Limpa o carrinho após a compra
        usuario.getCarrinho().getItens().clear();
        carrinhoImpl.salvar(usuario.getCarrinho().getUsuario());

        return venda;
    }

    // Remover produto do carrinho pelo nome
    public void removerProdutoDoCarrinhoPorNome(String nomeProduto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Usuario usuario = usuarioImpl.procurarUsuarioPorEmail(email);

        Carrinho carrinho = new Carrinho(usuario);

        Produto produto = produtoImpl.procurarProdutoPorNome(nomeProduto);

        ItemCarrinho itemCarrinho = itemCarrinhoImpl.procurarCarrinhoEProduto(carrinho, produto);

        if (itemCarrinho != null) {
            itemCarrinhoImpl.removerItemCarrinho(itemCarrinho);
        } else {
            throw new IllegalArgumentException("Produto não encontrado no carrinho");
        }
    }

    public String  precoTotalCarrinho(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Usuario usuario = usuarioImpl.procurarUsuarioPorEmail(email);

        Carrinho carrinho = new Carrinho(usuario);
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
