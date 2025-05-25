package org.example.spring_ecommerce.domain.itemVenda;
import org.example.spring_ecommerce.adapters.outBound.entities.produto.ProdutoEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.entities.venda.VendaEntityJPA;
import org.example.spring_ecommerce.domain.produto.Produto;
import org.example.spring_ecommerce.domain.venda.Venda;

import java.util.Objects;


public class ItemVenda {


    private Long id;

    private Produto produto;

    private Venda venda;

    private Integer quantidade;


    public ItemVenda() {}

    public ItemVenda( Produto produto, Venda venda, Integer quantidade) {
        this.produto = produto;
        this.venda = venda;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemVenda itemVenda = (ItemVenda) o;
        return Objects.equals(id, itemVenda.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
