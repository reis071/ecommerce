package org.example.spring_ecommerce.adapters.outBound.entities.itemCarrinho;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.example.spring_ecommerce.adapters.outBound.entities.carrinho.CarrinhoEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.entities.produto.ProdutoEntityJPA;

import java.util.Objects;

@Data
@Entity
public class ItemCarrinhoEntityJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "carrinho_id")
    @JsonBackReference
    private CarrinhoEntityJPA carrinho;

    @ManyToOne
    @JoinColumn(name = "produto_id")

    private ProdutoEntityJPA produto;

    @Min(1)
    private int quantidade;

    public ItemCarrinhoEntityJPA() {}

    public ItemCarrinhoEntityJPA(Carrinho carrinho, ProdutoEntityJPA produto, int quantidade) {
        this.carrinho = carrinho;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemCarrinhoEntityJPA that = (ItemCarrinhoEntityJPA) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
