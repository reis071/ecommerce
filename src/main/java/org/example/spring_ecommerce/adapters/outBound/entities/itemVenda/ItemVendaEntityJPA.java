package org.example.spring_ecommerce.adapters.outBound.entities.itemVenda;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.entities.produto.ProdutoEntityJPA;

import java.util.Objects;

@NoArgsConstructor
@Data
@Entity
public class ItemVendaEntityJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_Produto", nullable = false)
    private ProdutoEntityJPA produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemVendaEntityJPA that = (ItemVendaEntityJPA) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
