package org.example.spring_ecommerce.domain.produto;

public interface ProdutoRepository {
    public Produto salvar(Produto produto);

    public Produto procurarProdutoPorNome(String nomeProduto);

}
