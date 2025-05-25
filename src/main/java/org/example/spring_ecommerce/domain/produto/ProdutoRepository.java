package org.example.spring_ecommerce.domain.produto;

import java.util.List;

public interface ProdutoRepository {
    Produto salvar(Produto produto);

    Produto procurarProdutoPorNome(String nomeProduto);

    Produto buscarPorId(Long id);

    List<Produto> todosOsProdutos();

    void deletarProduto(Long id);
}
