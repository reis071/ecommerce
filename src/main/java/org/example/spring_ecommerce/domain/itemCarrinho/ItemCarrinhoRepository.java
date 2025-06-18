package org.example.spring_ecommerce.domain.itemCarrinho;

import org.example.spring_ecommerce.domain.carrinho.Carrinho;
import org.example.spring_ecommerce.domain.produto.Produto;
import org.example.spring_ecommerce.domain.usuario.Usuario;

import java.util.List;
import java.util.Optional;

public interface ItemCarrinhoRepository {

    ItemCarrinho salvar(ItemCarrinho itemCarrinho);

//    ItemCarrinho procurarCarrinhoEProduto(Carrinho carrinho, Produto produto);

    void removerItemCarrinho(ItemCarrinho itemCarrinho);
}
