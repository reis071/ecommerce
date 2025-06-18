package org.example.spring_ecommerce.domain.carrinho;


import org.example.spring_ecommerce.domain.itemCarrinho.ItemCarrinho;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Carrinho {

    private Long id;

    private List<ItemCarrinho> itens = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public List<ItemCarrinho> getItens() {
        return itens;
    }

    public void setItens(List<ItemCarrinho> itens) {
        this.itens = itens;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carrinho carrinho = (Carrinho) o;
        return Objects.equals(id, carrinho.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
