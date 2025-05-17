package org.example.spring_ecommerce.adapters.outBound.repositories.itemVenda;

import org.example.spring_ecommerce.adapters.outBound.entities.itemVenda.ItemVendaEntityJPA;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemVendaRepositoryJPA extends JpaRepository<ItemVendaEntityJPA, Long> {
}
