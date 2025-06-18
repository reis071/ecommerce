package org.example.spring_ecommerce.domain.carrinho;



public interface CarrinhoRepository {

    Carrinho salvar();

    Carrinho atualizarCarrinho(Carrinho carrinho);

}
