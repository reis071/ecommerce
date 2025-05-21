package org.example.spring_ecommerce.application.services.usuario;

import lombok.AllArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.repositories.usuario.UsuarioImpl;
import org.example.spring_ecommerce.application.services.email.EmailService;
import org.example.spring_ecommerce.application.useCases.usuario.UsuarioUseCases;
import org.example.spring_ecommerce.infrastructure.configuration.advices.exceptionExclusives.ErroAutenticacao;
import org.example.spring_ecommerce.infrastructure.configuration.advices.exceptionExclusives.TokenInvalido;
import org.example.spring_ecommerce.adapters.inBound.dtos.EmailDto;
import org.example.spring_ecommerce.adapters.inBound.dtos.UsuarioDto;

import org.example.spring_ecommerce.domain.usuario.Usuario;
import org.example.spring_ecommerce.infrastructure.configuration.security.jwt.JwtService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class UsuarioService implements  UsuarioUseCases {

    private final UsuarioImpl usuarioImpl;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final JwtService jwtService;


    //Cadastra Usuario
    public Usuario salvar(Usuario usuario){
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);

        usuarioImpl.salvar(usuario);
        return usuario;
    }

    @Override
    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioImpl.procurarUsuarioPorEmail(email);
    }


    //envia token para alterar senha
//    public void enviarSolicitacaoDeResetarSenha(String email) {
//        Usuario user = usuarioImpl.procurarUsuarioPorEmail(email);
//
////        String token = jwtService.gerarToken    (new UsuarioDto(user,user.getPermissoes()));
//
//        EmailDto emailDtoDetails = new EmailDto(
//                user.getEmail(),
//                "Reset de Senha",
//                "Para redefinir sua senha:"
//                        + "token=" + token
//        );
//
//        emailService.sendEmail(emailDtoDetails); // Corrigido: Método correto
//    }

    //valida se é um token valido e reseta a senha
//    public void resetarSenha(String token, String newPassword) {
//        if (!jwtService.tokenValido(token)) {
//            throw new TokenInvalido();
//        }
//
//        String email = jwtService.obterLoginUsuario(token);
//
//        Usuario user = usuarioImpl.procurarUsuarioPorEmail(email);
//
//        user.setSenha(passwordEncoder.encode(newPassword));
//
//        usuarioImpl.salvar(user);
//    }

    public void depositar(double deposito){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Usuario usuario = usuarioImpl.procurarUsuarioPorEmail(email);

        if (deposito > 0) {
            usuario.setSaldo(usuario.getSaldo() + deposito);
            usuarioImpl.salvar(usuario);
        } else {
            throw new RuntimeException("Saldo não pode ser negativo");
        }
    }

}

