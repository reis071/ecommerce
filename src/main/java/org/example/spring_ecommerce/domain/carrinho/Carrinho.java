package org.example.spring_ecommerce.domain.carrinho;


import org.example.spring_ecommerce.domain.usuario.Usuario;
import org.example.spring_ecommerce.adapters.outBound.entities.itemCarrinho.ItemCarrinhoEntityJPA;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Carrinho {

    private Long id;

    private Usuario usuario;

    private List<ItemCarrinhoEntityJPA> itens = new ArrayList<>();

    public Carrinho() {
    }

    // Construtor com usuário
    public Carrinho(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<ItemCarrinhoEntityJPA> getItens() {
        return itens;
    }

    public void setItens(List<ItemCarrinhoEntityJPA> itens) {
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
