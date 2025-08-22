package org.example.spring_ecommerce.application.services.produto;

import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.adapters.inBound.dtos.produto.ProdutoDTOResponse;
import org.example.spring_ecommerce.adapters.outBound.repositories.produto.ProdutoImpl;
import org.example.spring_ecommerce.application.dtos.produto.ProdutoDTORequest;
import org.example.spring_ecommerce.application.dtos.produto.ProdutoDTORequestComId;
import org.example.spring_ecommerce.application.useCases.produto.ProdutoUseCases;
import org.example.spring_ecommerce.domain.produto.Produto;
import org.example.spring_ecommerce.infrastructure.configuration.advices.exception.produto.ProdutoException;
import org.springframework.stereotype.Service;


import java.util.List;

@RequiredArgsConstructor
@Service
public class ProdutoService implements ProdutoUseCases {

    private final ProdutoImpl produtoImpl;

    @Override
    public Produto registrarProduto(ProdutoDTORequest produtoDTO) {

            Produto produto = new Produto(produtoDTO.nome(), produtoDTO.descricao(), produtoDTO.categoria(), produtoDTO.preco(), produtoDTO.estoque());
            return produtoImpl.salvar(produto);
    }

    @Override
    public Produto procurarProdutoPorNome(String nomeProduto) {
        try {
            return produtoImpl.procurarProdutoPorNome(nomeProduto);
        }
        catch (ProdutoException e) {
            throw new ProdutoException("Produto nao encontrado");
        }
    }

    @Override
    public List<Produto> listarTodosOsProdutos() {
            return produtoImpl.todosOsProdutos();
    }

    @Override
    public void removerProduto(Long id) {
        try {
            produtoImpl.deletarProduto(id);
        }catch (Exception e) {
            throw new ProdutoException("Produto nao encontrado");
        }
    }

    @Override
    public Produto atualizarProduto(ProdutoDTORequestComId produtoDTO) {
        Produto produtoExistente = produtoImpl.buscarPorId(produtoDTO.id());


        if(!produtoDTO.nome().isEmpty()){
            produtoExistente.setNome(produtoExistente.getNome());
        }
        else if (!produtoDTO.descricao().isEmpty()) {
            produtoExistente.setDescricao(produtoDTO.descricao());
        }
        else if (produtoDTO.preco() != produtoExistente.getPreco() && produtoDTO.preco() > 0) {
            produtoExistente.setPreco(produtoDTO.preco());
        }
        else if (produtoDTO.estoque() != produtoExistente.getEstoque() && produtoDTO.estoque() > 0) {
            produtoExistente.setEstoque(produtoDTO.estoque());
        }
        else if (produtoDTO.ativo() != produtoExistente.isAtivo()) {
            produtoExistente.setAtivo(produtoDTO.ativo());
        }
        else if (!produtoDTO.categoria().isEmpty()) {
            produtoExistente.setCategoria(produtoDTO.categoria());
        }

        return produtoImpl.salvar(produtoExistente);
    }

}
