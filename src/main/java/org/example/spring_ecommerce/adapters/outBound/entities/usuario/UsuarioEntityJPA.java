package org.example.spring_ecommerce.adapters.outBound.entities.usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.NoArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.entities.carrinho.CarrinhoEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.entities.grupo.GrupoEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.entities.venda.VendaEntityJPA;

import java.util.*;

@NoArgsConstructor
@Entity
public class UsuarioEntityJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nome;

    @NotEmpty
    private String senha;

    @Email
    @NotEmpty
    private String email;

    @OneToOne
    private CarrinhoEntityJPA carrinho;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<VendaEntityJPA> vendas = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "usuario_grupo_rel",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "grupo_id")
    )
    @JsonIgnore
    private Set<GrupoEntityJPA> grupos = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioEntityJPA usuario = (UsuarioEntityJPA) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return this.id;
    }

    public @NotEmpty String getNome() {
        return this.nome;
    }

    public @NotEmpty String getSenha() {
        return this.senha;
    }

    public @Email @NotEmpty String getEmail() {
        return this.email;
    }

    public CarrinhoEntityJPA getCarrinho() {
        return this.carrinho;
    }

    public List<VendaEntityJPA> getVendas() {
        return this.vendas;
    }

    public Set<GrupoEntityJPA> getGrupos() {
        return this.grupos;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(@NotEmpty String nome) {
        this.nome = nome;
    }

    public void setSenha(@NotEmpty String senha) {
        this.senha = senha;
    }

    public void setEmail(@Email @NotEmpty String email) {
        this.email = email;
    }

    public void setCarrinho(CarrinhoEntityJPA carrinho) {
        this.carrinho = carrinho;
    }

    @JsonIgnore
    public void setVendas(List<VendaEntityJPA> vendas) {
        this.vendas = vendas;
    }

    @JsonIgnore
    public void setGrupos(Set<GrupoEntityJPA> grupos) {
        this.grupos = grupos;
    }

    public String toString() {
        return "UsuarioEntityJPA(id=" + this.getId() + ", nome=" + this.getNome() + ", senha=" + this.getSenha() + ", email=" + this.getEmail() + ", carrinho=" + this.getCarrinho() + ", vendas=" + this.getVendas() + ", grupos=" + this.getGrupos() + ")";
    }
}
