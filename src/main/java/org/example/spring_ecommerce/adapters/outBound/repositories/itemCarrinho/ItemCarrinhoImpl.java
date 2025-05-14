package org.example.spring_ecommerce.adapters.outBound.repositories.itemCarrinho;

import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.entities.carrinho.CarrinhoEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.entities.itemCarrinho.ItemCarrinhoEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.mappers.carrino.CarrinhoMapperJPA;
import org.example.spring_ecommerce.adapters.outBound.mappers.itemCarrinho.ItemCarrinhoMapperJPA;
import org.example.spring_ecommerce.adapters.outBound.mappers.produto.ProdutoMapperJPA;
import org.example.spring_ecommerce.adapters.outBound.mappers.usuario.UsuarioMapperJPA;
import org.example.spring_ecommerce.domain.carrinho.Carrinho;
import org.example.spring_ecommerce.domain.itemCarrinho.ItemCarrinho;
import org.example.spring_ecommerce.domain.itemCarrinho.ItemCarrinhoRepository;
import org.example.spring_ecommerce.domain.produto.Produto;
import org.example.spring_ecommerce.domain.usuario.Usuario;
import org.springframework.stereotype.Repository;

import java.util.List;


@RequiredArgsConstructor
@Repository
public class ItemCarrinhoImpl implements ItemCarrinhoRepository {
    private final ItemCarrinhoRepositoryJPA itemCarrinhoRepository;
    private final ItemCarrinhoMapperJPA mapper;

    UsuarioMapperJPA usuarioMapper;
    ProdutoMapperJPA produtoMapperJPA;
    CarrinhoMapperJPA carrinhoMapperJPA;


    @Override
    public ItemCarrinho salvar(Carrinho carrinho, Produto produto, int quantidade) {

        ItemCarrinho itemCarrinho = new ItemCarrinho(carrinho, produto, quantidade);

        ItemCarrinhoEntityJPA itemCarrinhoEntityJPA = itemCarrinhoRepository.save(mapper.toEntity(itemCarrinho));

        return mapper.toDomain(itemCarrinhoEntityJPA);
    }

    @Override
    public List<ItemCarrinho> TodosOsItens(Usuario usuario) {

        CarrinhoEntityJPA carrinhoEntityJPA = new CarrinhoEntityJPA(usuarioMapper.toEntity(usuario));

        List<ItemCarrinhoEntityJPA> itemCarrinhoEntityJPA = carrinhoEntityJPA.getItens();
        return mapper.toDomainList(itemCarrinhoEntityJPA);
    }

    @Override
    public ItemCarrinho procurarCarrinhoEProduto(Carrinho carrinho, Produto produto) {
        ItemCarrinhoEntityJPA itemCarrinhoEntityJPA = itemCarrinhoRepository.
                findByCarrinhoAndProduto(carrinhoMapperJPA.toEntity(carrinho), produtoMapperJPA.toEntity(produto))
                .orElseThrow(() -> new RuntimeException("Item nao encontrado"));

        return mapper.toDomain(itemCarrinhoEntityJPA);
    }

    @Override
    public void removerItemCarrinho(ItemCarrinho itemCarrinho) {

        itemCarrinhoRepository.delete(mapper.toEntity(itemCarrinho));
    }
}
