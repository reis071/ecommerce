package org.example.spring_ecommerce.infrastructure.configuration.advices.exceptionExclusives;

public class ProdutoInativo extends RuntimeException {
    public ProdutoInativo() {
        super("Produto Indisponivel para venda");
    }
}
