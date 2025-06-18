package org.example.spring_ecommerce.adapters.outBound.entities.itemCarrinho;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.example.spring_ecommerce.adapters.outBound.entities.produto.ProdutoEntityJPA;

import java.util.Objects;

@NoArgsConstructor
@Data
@Entity
public class ItemCarrinhoEntityJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ProdutoEntityJPA produto;

    @Min(1)
    private int quantidade;

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
