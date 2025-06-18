package org.example.spring_ecommerce.adapters.outBound.repositories.produto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.entities.produto.ProdutoEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.mappers.produto.ProdutoMapperJPA;
import org.example.spring_ecommerce.domain.produto.Produto;
import org.example.spring_ecommerce.domain.produto.ProdutoRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ProdutoImpl implements ProdutoRepository {

    private final ProdutoRepositoryJPA repository;
    private final ProdutoMapperJPA mapper;

    @Transactional
    @Override
    public Produto salvar(Produto produto) {
        ProdutoEntityJPA produtoEntityJPA = mapper.toEntity(produto);

        repository.save(produtoEntityJPA);

        produtoEntityJPA.setId(produtoEntityJPA.getId());
        return mapper.toDomain(produtoEntityJPA);
    }

    @Cacheable("produtoCache")
    @Transactional
    @Override
    public Produto procurarProdutoPorNome(String nomeProduto) {
        ProdutoEntityJPA produtoEntityJPA = repository.findByNome(nomeProduto).orElseThrow(() -> new RuntimeException("Produto nao encontrado"));

        produtoEntityJPA.setId(produtoEntityJPA.getId());
        return mapper.toDomain(produtoEntityJPA);
    }

    @Cacheable("produtoCache")
    @Override
    public Produto buscarPorId(Long id) {
        ProdutoEntityJPA produtoEntityJPA = repository.findById(id).orElseThrow(() -> new RuntimeException("Produto nao encontrado"));
        return mapper.toDomain(produtoEntityJPA);
    }

    @Cacheable("produtoCache")
    @Transactional
    @Override
    public List<Produto> todosOsProdutos() {
        List<ProdutoEntityJPA> entities = repository.findAll();
        return mapper.toDomainList(entities);
    }

    @Override
    public void deletarProduto(Long id) {
        repository.deleteById(id);
    }

}
