package org.example.spring_ecommerce.application.dtos.produto;

public record ProdutoDTORequest(String nome,String descricao,String categoria, Double preco, Integer estoque) {
}
