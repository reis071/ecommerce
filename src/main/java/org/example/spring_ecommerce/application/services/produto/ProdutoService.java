package org.example.spring_ecommerce.application.services.produto;

import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.repositories.produto.ProdutoImpl;
import org.example.spring_ecommerce.application.useCases.produto.ProdutoUseCases;
import org.example.spring_ecommerce.domain.produto.Produto;
import org.example.spring_ecommerce.infrastructure.configuration.advices.exception.produto.ProdutoException;
import org.springframework.stereotype.Service;


import java.util.List;

@RequiredArgsConstructor
@Service
public class ProdutoService implements ProdutoUseCases {

    private final ProdutoImpl produtoImpl;

    public Produto registrarProduto(Produto produto) {
            return produtoImpl.salvar(produto);
    }

    public Produto procurarProdutoPorNome(String nomeProduto) {
        try {
            return produtoImpl.procurarProdutoPorNome(nomeProduto);
        }
        catch (ProdutoException e) {
            throw new ProdutoException("Produto nao encontrado");
        }
    }

    public List<Produto> listarTodosOsProdutos() {
            return produtoImpl.todosOsProdutos();
    }

    public void removerProduto(Long id) {
        try {
            produtoImpl.deletarProduto(id);
        }catch (Exception e) {
            throw new ProdutoException("Produto nao encontrado");
        }
    }

    public Produto atualizarProduto(Produto produtoAtualizado) {
        Produto produtoExistente = produtoImpl.buscarPorId(produtoAtualizado.getId());

        if(!produtoAtualizado.getNome().isEmpty()){
            produtoExistente.setNome(produtoExistente.getNome());
        }
        else if (!produtoAtualizado.getDescricao().isEmpty()) {
            produtoExistente.setDescricao(produtoAtualizado.getDescricao());
        }
        else if (produtoAtualizado.getPreco() != produtoExistente.getPreco()) {
            produtoExistente.setPreco(produtoAtualizado.getPreco());
        }
        else if (!produtoAtualizado.getCategoria().isEmpty()) {
            produtoExistente.setEstoque(produtoAtualizado.getEstoque());
        }
        else if (produtoAtualizado.isAtivo() != produtoExistente.isAtivo()) {
            produtoExistente.setAtivo(produtoAtualizado.isAtivo());
        }

        return produtoImpl.salvar(produtoExistente);
    }

}
