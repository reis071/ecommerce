package org.example.spring_ecommerce.adapters.outBound.repositories.usuario;

import org.example.spring_ecommerce.adapters.outBound.entities.usuario.UsuarioEntityJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UsuarioRepositoryJPA extends JpaRepository<UsuarioEntityJPA, Long> {
    Optional<UsuarioEntityJPA> findByEmail(String email);
}
