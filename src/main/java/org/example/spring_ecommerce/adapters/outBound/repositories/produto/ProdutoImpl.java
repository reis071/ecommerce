package org.example.spring_ecommerce.adapters.outBound.repositories.produto;

import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.entities.produto.ProdutoEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.mappers.produto.ProdutoMapperJPA;
import org.example.spring_ecommerce.domain.produto.Produto;
import org.example.spring_ecommerce.domain.produto.ProdutoRepository;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ProdutoImpl implements ProdutoRepository {

    private final ProdutoRepositoryJPA repository;
    private final ProdutoMapperJPA mapper;

    @Override
    public Produto salvar(Produto produto) {
        ProdutoEntityJPA produtoEntityJPA = new ProdutoEntityJPA(produto.getNome(),
                                                                produto.getDescricao(),
                                                                produto.getCategoria(),
                                                                produto.getPreco(),
                                                                produto.getEstoque());
        repository.save(produtoEntityJPA);
        return mapper.toDomain(produtoEntityJPA);
    }

    @Override
    public Produto procurarProdutoPorNome(String nomeProduto) {
        ProdutoEntityJPA produtoEntityJPA = repository.findByNome(nomeProduto).orElseThrow(() -> new RuntimeException("Produto nao encontrado"));
        return mapper.toDomain(produtoEntityJPA);
    }


}
