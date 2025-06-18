package org.example.spring_ecommerce.application.useCases.carrinho;

import org.example.spring_ecommerce.domain.carrinho.Carrinho;
import org.example.spring_ecommerce.domain.venda.Venda;

public interface CarrinhoUseCases {

    Venda compra(String nomeProd, int quantidade);

    Venda finalizarCompra();

    Carrinho adicionarAoCarrinho(String nomeProd, int quantidade);

//    void removerProdutoDoCarrinhoPorNome(String nomeProduto);

//    String precoTotalCarrinho();



}
