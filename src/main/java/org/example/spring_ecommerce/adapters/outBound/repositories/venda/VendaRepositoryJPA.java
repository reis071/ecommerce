package org.example.spring_ecommerce.adapters.outBound.repositories.venda;

import org.example.spring_ecommerce.adapters.outBound.entities.venda.VendaEntityJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VendaRepositoryJPA extends JpaRepository<VendaEntityJPA, Long> {
    List<VendaEntityJPA> findByDataVendaBetween(LocalDateTime start, LocalDateTime end);

}
