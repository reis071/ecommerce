package org.example.spring_ecommerce.adapters.outBound.repositories.carrinho;

import org.example.spring_ecommerce.adapters.outBound.entities.carrinho.CarrinhoEntityJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



public interface CarrinhoRepositoryJPA extends JpaRepository<CarrinhoEntityJPA, Long> {
}
