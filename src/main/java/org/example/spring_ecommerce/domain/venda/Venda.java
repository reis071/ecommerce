package org.example.spring_ecommerce.domain.venda;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.example.spring_ecommerce.adapters.outBound.entities.itemVenda.ItemVendaEntityJPA;
import org.example.spring_ecommerce.domain.enums.StatusVenda;
import org.example.spring_ecommerce.domain.itemVenda.ItemVenda;
import org.example.spring_ecommerce.domain.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Venda {


    private Long id;


    private Usuario usuario;

    private LocalDateTime dataVenda;

    private StatusVenda status;

    private double valorTotal;

    private List<ItemVenda> itensVenda = new ArrayList<>();

    public Venda() { }

    public Venda(Usuario usuario, double valorTotal) {
        this.usuario = usuario;

        this.valorTotal = valorTotal;
        this.status = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
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

    public List<ItemVenda> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(List<ItemVenda> itensVenda) {
        this.itensVenda = itensVenda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venda venda = (Venda) o;
        return Objects.equals(id, venda.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
