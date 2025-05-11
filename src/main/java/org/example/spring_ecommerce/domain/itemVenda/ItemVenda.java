package org.example.spring_ecommerce.domain.itemVenda;
import org.example.spring_ecommerce.adapters.outBound.entities.produto.ProdutoEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.entities.venda.VendaEntityJPA;

import java.util.Objects;


public class ItemVenda {


    private Long id;

    private ProdutoEntityJPA produto;

    private VendaEntityJPA venda;

    private Integer quantidade;


    public ItemVenda() {}

    public ItemVenda(ProdutoEntityJPA produto, VendaEntityJPA venda, Integer quantidade) {
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

    public ProdutoEntityJPA getProduto() {
        return produto;
    }

    public void setProduto(ProdutoEntityJPA produto) {
        this.produto = produto;
    }

    public VendaEntityJPA getVenda() {
        return venda;
    }

    public void setVenda(VendaEntityJPA venda) {
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
