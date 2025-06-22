package org.example.spring_ecommerce.application.services.carrinho;

import lombok.RequiredArgsConstructor;

import org.example.spring_ecommerce.adapters.outBound.repositories.carrinho.CarrinhoImpl;
import org.example.spring_ecommerce.infrastructure.configuration.advices.exception.produto.ProdutoException;
import org.springframework.stereotype.Service;
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

import org.example.spring_ecommerce.domain.usuario.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CarrinhoService implements CarrinhoUseCases {
    
    private final CarrinhoImpl carrinhoImpl;
    private final ProdutoImpl produtoImpl;
    private final VendaImpl vendaImpl;
    private final ItemVendaImpl itemVendaImpl;
    private final UsuarioImpl usuarioImpl;
    private final ItemCarrinhoImpl itemCarrinhoImpl;


    public Venda compra(String nomeProd, int quantidade){

        Produto produto = produtoImpl.procurarProdutoPorNome(nomeProd);

        if (!produto.isAtivo()) {
            throw new ProdutoException("Produto inativo");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Usuario usuario = usuarioImpl.procurarUsuarioPorEmail(email);

        Venda venda = vendaImpl.salva(new Venda(usuario, quantidade * produto.getPreco()));

        produto.setEstoque(produto.getEstoque() - quantidade);

        ItemVenda itemVenda = new ItemVenda(produto, quantidade);
        venda.getItensVenda().add(itemVenda);

        produtoImpl.salvar(produto);
        usuarioImpl.salvar(usuario);
        vendaImpl.salva(venda);
        itemVendaImpl.salvar(itemVenda);

        return venda;
    }

    // Método para adicionar um item ao carrinho
    public Carrinho adicionarAoCarrinho(String nomeProd, int quantidade) {
        Produto produto = produtoImpl.procurarProdutoPorNome(nomeProd);

        if (!produto.isAtivo()) {
            throw new ProdutoException("Produto inativo");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        String email = authentication.getName();

        Usuario usuario = usuarioImpl.procurarUsuarioPorEmail(email);

        Carrinho carrinho = usuario.getCarrinho();

        ItemCarrinho itemCarrinho = itemCarrinhoImpl.salvar(new ItemCarrinho(produto, quantidade));

        carrinho.getItens().add(itemCarrinho);

        carrinhoImpl.atualizarCarrinho(carrinho);

        return carrinho;
    }

    // Método para comprar os itens do carrinho
    public Venda finalizarCompra() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Usuario usuario = usuarioImpl.procurarUsuarioPorEmail(email);

        List<ItemCarrinho> itensCarrinho = usuario.getCarrinho().getItens();

        if (itensCarrinho.isEmpty()) {
            throw new IllegalArgumentException("O carrinho está vazio.");
        }

        double valorTotal = itensCarrinho.stream()
                .mapToDouble( item -> item.getProduto().getPreco() * item.getQuantidade())
                .sum();

        // Cria a venda
        Venda venda = vendaImpl.salva(new Venda(usuario, valorTotal));

        // Processa cada item do carrinho
        for (ItemCarrinho itemCarrinho : itensCarrinho) {
            Produto produto = itemCarrinho.getProduto();

            int quantidade = itemCarrinho.getQuantidade();

            if (quantidade > produto.getEstoque()) {
                throw new IllegalArgumentException("Estoque insuficiente para o produto: " + produto.getNome());
            }

            produto.setEstoque(produto.getEstoque() - quantidade);
            produtoImpl.salvar(produto);

            ItemVenda itemVenda = new ItemVenda(produto, quantidade);

            itemVendaImpl.salvar(itemVenda);

            venda.getItensVenda().add(itemVenda);
        }

        // Limpa o carrinho após a compra
        usuario.getCarrinho().getItens().clear();
        usuarioImpl.salvar(usuario);
        vendaImpl.salva(venda);

        return venda;
    }

// Remover produto do carrinho pelo nome
    public void removerProdutoDoCarrinhoPorNome(String nomeProduto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Usuario usuario = usuarioImpl.procurarUsuarioPorEmail(email);

        Carrinho carrinho = usuario.getCarrinho();
        for (ItemCarrinho itemCarrinho : carrinho.getItens()) {
            if (itemCarrinho.getProduto().getNome().equals(nomeProduto)) {
                itemCarrinhoImpl.removerItemCarrinho(itemCarrinho);
            }
            throw new IllegalArgumentException("Produto não encontrado no carrinho");
        }
    }

//    public String  precoTotalCarrinho(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = authentication.getName();
//
//        Usuario usuario = usuarioImpl.procurarUsuarioPorEmail(email);
//
//        List<ItemCarrinho> itensCarrinho = itemCarrinhoImpl.TodosOsItens(usuario);
//
//        if (itensCarrinho.isEmpty()) {
//            throw new IllegalArgumentException("O carrinho está vazio.");
//        }
//
//        double valorTotal = itensCarrinho.stream()
//                .mapToDouble(item -> item.getProduto().getPreco() * item.getQuantidade())
//                .sum();
//
//        return "{valorTotal: " + valorTotal + "}";
//    }
}
