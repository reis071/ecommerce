package org.example.spring_ecommerce.application.useCases.usuario;

import org.example.spring_ecommerce.domain.usuario.Usuario;

import java.util.List;

public interface UsuarioUseCases {

    void resetarSenha(String token, String senhaNova);

    void enviarSolicitacaoDeResetarSenha(String email);

    Usuario salvar(Usuario usuario);

    Usuario buscarUsuarioPorEmail(String email);
}
