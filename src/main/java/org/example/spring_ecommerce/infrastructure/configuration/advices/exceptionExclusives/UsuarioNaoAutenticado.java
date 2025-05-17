package org.example.spring_ecommerce.infrastructure.configuration.advices.exceptionExclusives;

public class UsuarioNaoAutenticado extends RuntimeException {
    public UsuarioNaoAutenticado() {
        super("Usuario nao autenticado");
    }
}
