package org.example.spring_ecommerce.application.useCases.produto;

import org.example.spring_ecommerce.domain.produto.Produto;

import java.util.List;

public interface ProdutoUseCases {
    Produto registrarProduto(Produto produto);

    Produto procurarProdutoPorNome(String nomeProduto);

    void removerProduto(Long id);

    List<Produto> listarTodosOsProdutos();

    Produto atualizarProduto(Produto produtoAtualizado);

}
