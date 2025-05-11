package org.example.spring_ecommerce.infrastructure.configuration.advices.exceptionExclusives;

public class TokenInvalido extends RuntimeException{

    public TokenInvalido(){
        super("Token invalido");
    }
}
