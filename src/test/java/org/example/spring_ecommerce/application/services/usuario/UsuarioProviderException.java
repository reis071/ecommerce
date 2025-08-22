package org.example.spring_ecommerce.application.services.usuario;

import org.example.spring_ecommerce.application.dtos.usuario.UsuarioDTORequest;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class UsuarioProviderException implements ArgumentsProvider {


    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        UsuarioDTORequest case1 = new UsuarioDTORequest("", "fulano@ig.com.br", "123456");
        UsuarioDTORequest case2 = new UsuarioDTORequest("fulano", "", "123456");
        UsuarioDTORequest case3 = new UsuarioDTORequest("fulano", "fulano@ig.com.br", "");
        UsuarioDTORequest case4 = new UsuarioDTORequest("", "", "");

        return Stream.of(
                Arguments.of(case1, "campo(s) vazio(s)"),
                Arguments.of(case2, "campo(s) vazio(s)"),
                Arguments.of(case3, "campo(s) vazio(s)"),
                Arguments.of(case4, "campo(s) vazio(s)")
        );
    }
}
