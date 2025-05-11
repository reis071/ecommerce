package org.example.spring_ecommerce.adapters.outBound.entities.usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.spring_ecommerce.domain.usuario.Usuario;
import org.example.spring_ecommerce.adapters.outBound.entities.venda.VendaEntityJPA;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
public class UsuarioEntityJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String nome;

    @Column
    private double saldo = 0;

    @NotEmpty
    @Column(nullable = false)
    private String senha;



    @Email
    @NotEmpty
    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Carrinho carrinho;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // Prevent serialization issues
    private List<VendaEntityJPA> vendas = new ArrayList<>();

    @Transient
    private List<String> permissoes;

    public UsuarioEntityJPA(Usuario usuario) {
        this.nome = usuario.getNome();
        this.saldo = usuario.getSaldo();
        this.senha = usuario.getSenha();
        this.email = usuario.getEmail();
        this.carrinho = usuario.getCarrinho();
        this.vendas = usuario.getVendas();
        this.permissoes = usuario.getPermissoes();
    }

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
}
