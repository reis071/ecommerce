package org.example.spring_ecommerce.domain.usuario;

import org.example.spring_ecommerce.domain.carrinho.Carrinho;
import org.example.spring_ecommerce.adapters.outBound.entities.venda.VendaEntityJPA;
import org.example.spring_ecommerce.domain.grupo.Grupo;

import java.util.*;

public class Usuario {

    private Long id;
    private String nome;
    private String senha;
    private String email;

    private Carrinho carrinho;
    private List<VendaEntityJPA> vendas = new ArrayList<>();
    private Set<Grupo> grupo = new HashSet<>();

    public Usuario() {}

    public Usuario(String nome,  String email, String senha) {

        this.nome = nome;
        this.senha = senha;
        this.email = email;
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


    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public List<VendaEntityJPA> getVendas() {
        return vendas;
    }

    public void setVendas(List<VendaEntityJPA> vendas) {
        this.vendas = vendas;
    }

    public Set<Grupo> getGrupo() {
        return grupo;
    }

    public void setGrupo(Set<Grupo> grupo) {
        this.grupo = grupo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
