package org.example.spring_ecommerce.adapters.outBound.repositories.produto;

import org.example.spring_ecommerce.adapters.outBound.entities.produto.ProdutoEntityJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepositoryJPA extends JpaRepository<ProdutoEntityJPA, Long> {

    Optional<ProdutoEntityJPA> findByNome(String nome);
}
