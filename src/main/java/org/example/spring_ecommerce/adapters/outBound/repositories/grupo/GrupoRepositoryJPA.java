package org.example.spring_ecommerce.adapters.outBound.repositories.grupo;

import org.example.spring_ecommerce.adapters.outBound.entities.grupo.GrupoEntityJPA;
import org.example.spring_ecommerce.domain.grupo.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GrupoRepositoryJPA extends JpaRepository<GrupoEntityJPA, Long> {
    Optional<GrupoEntityJPA> findByNome(String nome);
}
