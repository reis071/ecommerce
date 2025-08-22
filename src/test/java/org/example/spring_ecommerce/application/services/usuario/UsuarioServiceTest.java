package org.example.spring_ecommerce.application.services.usuario;

import org.example.spring_ecommerce.adapters.outBound.repositories.carrinho.CarrinhoImpl;
import org.example.spring_ecommerce.adapters.outBound.repositories.usuario.UsuarioImpl;
import org.example.spring_ecommerce.application.dtos.usuario.UsuarioDTORequest;
import org.example.spring_ecommerce.application.services.email.EmailService;
import org.example.spring_ecommerce.domain.carrinho.Carrinho;
import org.example.spring_ecommerce.domain.usuario.Usuario;
import org.example.spring_ecommerce.infrastructure.configuration.advices.exception.usuario.UsuarioException;
import org.example.spring_ecommerce.infrastructure.configuration.security.jwt.JwtGeneratorFilter;
import org.example.spring_ecommerce.infrastructure.configuration.security.jwt.JwtValidatorFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class UsuarioServiceTest {


    @Mock
    private CarrinhoImpl carrinhoImpl;

    @Mock
    private UsuarioImpl usuarioImpl;

    @Mock
    private  PasswordEncoder passwordEncoder;

    @Mock
    private EmailService emailService;

    @Mock
    private  JwtGeneratorFilter jwtGeneratorFilter;

    @Mock
    private  JwtValidatorFilter jwtValidatorFilter;

    @InjectMocks
    private  UsuarioService usuarioService;

    @ParameterizedTest(name = "[{index}] - {0}")
    @ArgumentsSource(UsuarioProviderException.class)
    void saveUserExceptions(UsuarioDTORequest dtoInvalido, String mensagemEsperada) {
        UsuarioException exception = assertThrows(UsuarioException.class, () -> {
            usuarioService.salvar(dtoInvalido);
        });

        assertEquals(mensagemEsperada, exception.getMessage());

        verify(usuarioImpl, never()).salvar(any(Usuario.class));
        verify(carrinhoImpl, never()).salvar();
        verify(passwordEncoder, never()).encode(anyString());
    }

    @Test
    @DisplayName("Salvar um usuário com sucesso")
    void saveUserSucess(){

     }
}
