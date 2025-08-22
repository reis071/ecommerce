package org.example.spring_ecommerce.application.services.usuario;

import lombok.AllArgsConstructor;
import org.example.spring_ecommerce.adapters.inBound.dtos.email.EmailDto;
import org.example.spring_ecommerce.adapters.outBound.repositories.carrinho.CarrinhoImpl;
import org.example.spring_ecommerce.adapters.outBound.repositories.usuario.UsuarioImpl;
import org.example.spring_ecommerce.application.services.email.EmailService;
import org.example.spring_ecommerce.application.useCases.usuario.UsuarioUseCases;

import org.example.spring_ecommerce.domain.carrinho.Carrinho;
import org.example.spring_ecommerce.domain.usuario.Usuario;
import org.example.spring_ecommerce.infrastructure.configuration.advices.exception.handler.GlobalExceptionHandler;
import org.example.spring_ecommerce.infrastructure.configuration.advices.exception.usuario.UsuarioException;
import org.example.spring_ecommerce.infrastructure.configuration.security.jwt.JwtGeneratorFilter;
import org.example.spring_ecommerce.infrastructure.configuration.security.jwt.JwtValidatorFilter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class UsuarioService implements  UsuarioUseCases {

    private final GlobalExceptionHandler globalExceptionHandler;
    private final CarrinhoImpl carrinhoImpl;
    private final UsuarioImpl usuarioImpl;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final JwtGeneratorFilter jwtGeneratorFilter;
    private final JwtValidatorFilter jwtValidatorFilter;

    @Override
    public Usuario salvar(Usuario usuario){
        if(usuario.getEmail().isEmpty()){
            throw new UsuarioException("O e-mail do usuário deve ser preenchido.");
        }
        else if(usuario.getSenha().isEmpty()){
            throw new UsuarioException("A senha do usuário deve ser preenchida.");
        }
        else if (usuario.getNome().isEmpty()) {
            throw new UsuarioException("O nome do usuário deve ser preenchido.");
        }

        Carrinho carrinho = carrinhoImpl.salvar();

        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);

        usuario.setCarrinho(carrinho);

        usuarioImpl.salvar(usuario);

        return usuario;
    }

    @Override
    public Usuario buscarUsuarioPorEmail(String email) {
        try {
            return usuarioImpl.procurarUsuarioPorEmail(email);
        } catch (Exception e) {
            throw new UsuarioException("Usuário nao encontrado");
        }
    }


    @Override
    public void enviarSolicitacaoDeResetarSenha(String email) {
        String token = jwtGeneratorFilter.tokenResetUser(email);

        EmailDto emailDtoDetails = new EmailDto(
                email,
                "Reset de Senha",
                "Para redefinir sua senha:"
                        + "token=" + token
        );

        emailService.sendEmail(emailDtoDetails);
    }

    //valida se é um token valido e reseta a senha
    public void resetarSenha(String token, String novaSenha) {

        String email = jwtValidatorFilter.validarToken(token);

        Usuario user = usuarioImpl.procurarUsuarioPorEmail(email);

        user.setSenha(passwordEncoder.encode(novaSenha));

        usuarioImpl.salvar(user);
    }


}

