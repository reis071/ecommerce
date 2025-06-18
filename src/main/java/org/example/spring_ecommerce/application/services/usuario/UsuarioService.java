package org.example.spring_ecommerce.application.services.usuario;

import lombok.AllArgsConstructor;
import org.example.spring_ecommerce.adapters.inBound.dtos.EmailDto;
import org.example.spring_ecommerce.adapters.outBound.repositories.carrinho.CarrinhoImpl;
import org.example.spring_ecommerce.adapters.outBound.repositories.usuario.UsuarioImpl;
import org.example.spring_ecommerce.application.services.email.EmailService;
import org.example.spring_ecommerce.application.useCases.usuario.UsuarioUseCases;

import org.example.spring_ecommerce.domain.carrinho.Carrinho;
import org.example.spring_ecommerce.domain.usuario.Usuario;
import org.example.spring_ecommerce.infrastructure.configuration.security.jwt.JwtGeneratorFilter;
import org.example.spring_ecommerce.infrastructure.configuration.security.jwt.JwtValidatorFilter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class UsuarioService implements  UsuarioUseCases {


    private final CarrinhoImpl carrinhoImpl;
    private final UsuarioImpl usuarioImpl;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final JwtGeneratorFilter jwtGeneratorFilter;
    private final JwtValidatorFilter jwtValidatorFilter;

    @Override
    public Usuario salvar(Usuario usuario){

        Carrinho carrinho = carrinhoImpl.salvar();

        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);

        usuario.setCarrinho(carrinho);

        usuarioImpl.salvar(usuario);

        return usuario;
    }

    @Override
    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioImpl.procurarUsuarioPorEmail(email);
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

