package org.example.spring_ecommerce.adapters.outBound.repositories.itemCarrinho;

import org.example.spring_ecommerce.adapters.outBound.entities.carrinho.CarrinhoEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.entities.itemCarrinho.ItemCarrinhoEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.entities.produto.ProdutoEntityJPA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ItemCarrinhoRepositoryJPA extends JpaRepository<ItemCarrinhoEntityJPA, Long> {

    // Método personalizado para encontrar um item do carrinho com base no carrinho e no produto
    Optional<ItemCarrinhoEntityJPA> findByCarrinhoAndProduto(CarrinhoEntityJPA carrinhoEntityJPA, ProdutoEntityJPA produto);

}
