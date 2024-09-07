package org.example.spring_ecommerce.repositories;

import org.example.spring_ecommerce.model.Carrinho;
import org.example.spring_ecommerce.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
    Optional<Carrinho> findByUsuario(Usuario usuario);

}
