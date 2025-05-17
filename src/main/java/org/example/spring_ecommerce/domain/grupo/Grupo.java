package org.example.spring_ecommerce.domain.grupo;


import org.example.spring_ecommerce.domain.usuario.Usuario;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Grupo {

    private Long id;

    private String nome;

    private Set<Usuario> usuarios = new HashSet<>();

    public Grupo() {}

    public Grupo( String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grupo grupo = (Grupo) o;
        return Objects.equals(id, grupo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
