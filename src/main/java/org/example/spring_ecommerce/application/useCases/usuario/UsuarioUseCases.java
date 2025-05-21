package org.example.spring_ecommerce.application.useCases.usuario;

import org.example.spring_ecommerce.domain.usuario.Usuario;

import java.util.List;

public interface UsuarioUseCases {
    public Usuario salvar(Usuario usuario);

    public Usuario buscarUsuarioPorEmail(String email);


    public void depositar(double deposito);
}
