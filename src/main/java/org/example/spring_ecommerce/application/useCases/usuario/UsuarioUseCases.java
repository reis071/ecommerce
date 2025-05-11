package org.example.spring_ecommerce.application.useCases.usuario;

import org.example.spring_ecommerce.adapters.inBound.controllers.dto.UsuarioDto;
import org.example.spring_ecommerce.domain.usuario.Usuario;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UsuarioUseCases {
    public Usuario salvar(Usuario usuario, List<String> grupos);

    public UsuarioDto obterUsuarioComPermissoes(String email);

    public void enviarSolicitacaoDeResetarSenha(String email);

    public void resetarSenha(String token, String newPassword);

    public void depositar(double deposito);
}
