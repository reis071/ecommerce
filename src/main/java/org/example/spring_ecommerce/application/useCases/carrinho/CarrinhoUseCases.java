package org.example.spring_ecommerce.application.useCases.carrinho;

import org.example.spring_ecommerce.application.dtos.carrinho.CarrinhoDTORequest;
import org.example.spring_ecommerce.domain.carrinho.Carrinho;
import org.example.spring_ecommerce.domain.venda.Venda;

public interface CarrinhoUseCases {

    Venda compra(CarrinhoDTORequest carrinhoDTORequest);

    Venda finalizarCompra();

    Carrinho adicionarAoCarrinho(CarrinhoDTORequest carrinhoDTORequest);

//    void removerProdutoDoCarrinhoPorNome(String nomeProduto);

//    String precoTotalCarrinho();



}
