package org.example.spring_ecommerce.application.services.produto;


import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.entities.produto.ProdutoEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.repositories.produto.ProdutoImpl;
import org.example.spring_ecommerce.application.useCases.produto.ProdutoUseCases;
import org.example.spring_ecommerce.domain.produto.Produto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProdutoService implements ProdutoUseCases {


    private final ProdutoImpl produtoImpl;

    public Produto registrarProduto(Produto produto) {
            return produtoImpl.salvar(produto);
    }

    public Produto procurarProdutoPorNome(String nomeProduto) {
        return produtoImpl.procurarProdutoPorNome(nomeProduto);
    }

    //retorna todos os produtos

    public List<Produto> listarTodosOsProdutos() {
        return produtoImpl.todosOsProdutos();
    }

    //deleta ou inativa o produto

    public void removerProduto(Long id) {
        produtoImpl.deletarProduto(id);
    }

    // Atualiza um produto existente
    public Produto atualizarProduto(Long id, ProdutoEntityJPA produtoAtualizado) {
        Produto produtoExistente = produtoImpl.buscarPorId(id);

        produtoExistente.setNome(produtoAtualizado.getNome());
        produtoExistente.setDescricao(produtoAtualizado.getDescricao());
        produtoExistente.setPreco(produtoAtualizado.getPreco());
        produtoExistente.setEstoque(produtoAtualizado.getEstoque());
        produtoExistente.setAtualizadoEm(LocalDateTime.now());

        return produtoImpl.salvar(produtoExistente);
    }


}
