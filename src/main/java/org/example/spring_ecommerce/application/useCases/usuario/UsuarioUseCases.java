package org.example.spring_ecommerce.application.useCases.usuario;

import org.example.spring_ecommerce.application.dtos.usuario.UsuarioDTORequest;
import org.example.spring_ecommerce.domain.usuario.Usuario;

public interface UsuarioUseCases {

    void resetarSenha(String token, String senhaNova);

    void enviarSolicitacaoDeResetarSenha(String email);

    Usuario salvar(UsuarioDTORequest usuarioDTORequest);

    Usuario buscarUsuarioPorEmail(String email);
}
