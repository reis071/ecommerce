package org.example.spring_ecommerce.adapters.inBound.dtos.produto;

public record ProdutoDTOResponse(String nome, String descricao, String categoria, Double preco, Integer estoque) {
}
