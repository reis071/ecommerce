package org.example.spring_ecommerce.adapters.outBound.entities.grupo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.example.spring_ecommerce.adapters.outBound.entities.usuario.UsuarioEntityJPA;
import org.example.spring_ecommerce.domain.usuario.Usuario;


import java.util.*;

@Data
@NoArgsConstructor
@Entity
public class GrupoEntityJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @ManyToMany(mappedBy = "grupos", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<UsuarioEntityJPA> usuarios = new HashSet<>();

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
