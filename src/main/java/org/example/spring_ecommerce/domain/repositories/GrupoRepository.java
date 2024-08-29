package org.example.spring_ecommerce.domain.repositories;

import org.example.spring_ecommerce.domain.entities.usuario.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {
    Optional<Grupo> findByNome(String nombre);
}
