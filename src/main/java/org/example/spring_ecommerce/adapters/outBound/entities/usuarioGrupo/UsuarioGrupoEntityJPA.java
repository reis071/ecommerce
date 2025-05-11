package org.example.spring_ecommerce.adapters.outBound.entities.usuarioGrupo;

import jakarta.persistence.*;
import org.example.spring_ecommerce.adapters.outBound.entities.grupo.GrupoEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.entities.usuario.UsuarioEntityJPA;
import org.example.spring_ecommerce.domain.grupo.Grupo;
import org.example.spring_ecommerce.domain.usuario.Usuario;

import java.util.Objects;


@Entity
public class UsuarioGrupoEntityJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UsuarioEntityJPA usuario;

    @ManyToOne
    @JoinColumn(name = "id_grupo")
    private GrupoEntityJPA grupo;

    public UsuarioGrupoEntityJPA() {}

    public UsuarioGrupoEntityJPA(UsuarioEntityJPA usuario, GrupoEntityJPA grupo) {
        this.usuario = usuario;
        this.grupo = grupo;
    }

    public Long getId() {
        return id;
    }

    public UsuarioEntityJPA getUsuario() {
        return usuario;
    }

    public GrupoEntityJPA getGrupo() {
        return grupo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioGrupoEntityJPA that = (UsuarioGrupoEntityJPA) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
