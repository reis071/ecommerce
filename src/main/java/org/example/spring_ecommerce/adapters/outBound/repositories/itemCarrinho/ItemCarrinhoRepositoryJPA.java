package org.example.spring_ecommerce.adapters.outBound.repositories.itemCarrinho;

import org.example.spring_ecommerce.adapters.outBound.entities.itemCarrinho.ItemCarrinhoEntityJPA;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemCarrinhoRepositoryJPA extends JpaRepository<ItemCarrinhoEntityJPA, Long> {

}
