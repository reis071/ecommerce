package org.example.spring_ecommerce.adapters.outBound.entities.venda;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.example.spring_ecommerce.adapters.outBound.entities.itemVenda.ItemVendaEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.entities.usuario.UsuarioEntityJPA;
import org.example.spring_ecommerce.domain.enums.StatusVenda;
import org.example.spring_ecommerce.domain.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class VendaEntityJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntityJPA usuario;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    @Column(nullable = false)
    private LocalDateTime dataVenda;

    @Column(nullable = false)
    private StatusVenda status;

    @Column(nullable = false)
    private double valorTotal;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ItemVendaEntityJPA> itensVenda = new ArrayList<>();

    public VendaEntityJPA() { }

    public VendaEntityJPA(UsuarioEntityJPA usuarioEntityJPA, LocalDateTime dataVenda, double valorTotal) {
        this.usuario = usuarioEntityJPA;
        this.dataVenda = dataVenda;
        this.valorTotal = valorTotal;
        this.status = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioEntityJPA getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntityJPA usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }

    public StatusVenda getStatus() {
        return status;
    }

    public void setStatus(StatusVenda status) {
        this.status = status;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItemVendaEntityJPA> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(List<ItemVendaEntityJPA> itensVenda) {
        this.itensVenda = itensVenda;
    }
}
