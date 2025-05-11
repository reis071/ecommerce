package org.example.spring_ecommerce.adapters.outBound.entities.grupo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.spring_ecommerce.domain.grupo.Grupo;

import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
public class GrupoEntityJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    public GrupoEntityJPA(Grupo grupo) {
        this.id = grupo.getId();
        this.nome = grupo.getNome();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrupoEntityJPA that = (GrupoEntityJPA) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
