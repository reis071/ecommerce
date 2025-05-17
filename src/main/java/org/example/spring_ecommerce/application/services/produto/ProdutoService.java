package org.example.spring_ecommerce.application.services.produto;


import org.example.spring_ecommerce.adapters.outBound.entities.produto.ProdutoEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.repositories.produto.ProdutoRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepositoryJPA produtoRepository;

    public ProdutoService(ProdutoRepositoryJPA produtoRepositoryJPA) {
        this.produtoRepository = produtoRepositoryJPA;
    }

    //adiciona produto
    @CacheEvict(value = "produtoCache", allEntries = true)
    public ProdutoEntityJPA save(ProdutoEntityJPA produto) {
            if (produtoRepository.findByNome(produto.getNome()).isPresent()) {
                throw new IllegalArgumentException("Já existe um produto com esse nome.");
            }

            produto.setCriadoEm(LocalDateTime.now());
            produto.setAtualizadoEm(LocalDateTime.now());
            return produtoRepository.save(produto);
    }

    //retorna produto por id
    @Cacheable("produtoCache")
    public ProdutoEntityJPA procurarProdutoPorNome(String nomeProduto) {
        return produtoRepository.findByNome(nomeProduto).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    //retorna todos os produtos
    @Cacheable("produtoCache")
    public List<ProdutoEntityJPA> findAll() {
        return produtoRepository.findAll();
    }

    //deleta ou inativa o produto
    @Cacheable("produtoCache")
    public void deleteById(String nomeProduto) {
        ProdutoEntityJPA produto = procurarProdutoPorNome(nomeProduto);
        if (!produto.getItensVenda().isEmpty()) {

            produto.setAtivo(false);
            produtoRepository.save(produto);
        } else {
            produtoRepository.deleteById(produto.getId());
        }
    }

    // Atualiza um produto existente
    @CacheEvict(value = "produtoCache", key = "#produto.id")
    public ProdutoEntityJPA atualizarProduto(Long id, ProdutoEntityJPA produtoAtualizado) {
        ProdutoEntityJPA produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produtoExistente.setNome(produtoAtualizado.getNome());
        produtoExistente.setDescricao(produtoAtualizado.getDescricao());
        produtoExistente.setPreco(produtoAtualizado.getPreco());
        produtoExistente.setEstoque(produtoAtualizado.getEstoque());
        produtoExistente.setAtualizadoEm(LocalDateTime.now());

        return produtoRepository.save(produtoExistente);
    }


}
