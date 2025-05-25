package org.example.spring_ecommerce.domain.carrinho;

import org.example.spring_ecommerce.domain.usuario.Usuario;

import java.util.Optional;

public interface CarrinhoRepository {
    Carrinho salvar(Usuario usuario);

}
