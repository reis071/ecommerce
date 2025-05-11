package org.example.spring_ecommerce.adapters.outBound.entities.itemVenda;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.example.spring_ecommerce.adapters.outBound.entities.produto.ProdutoEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.entities.venda.VendaEntityJPA;

@Data
@Entity
public class ItemVendaEntityJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "id_Produto", nullable = false)
    private ProdutoEntityJPA produto;

    @ManyToOne
    @JoinColumn(name = "venda_id", nullable = false)
    @JsonBackReference // Adiciona esta anotação
    private VendaEntityJPA venda;

    @Column(nullable = false)
    private Integer quantidade;


    public ItemVendaEntityJPA() {}

    public ItemVendaEntityJPA(ProdutoEntityJPA produto, VendaEntityJPA venda, Integer quantidade) {
        this.produto = produto;
        this.venda = venda;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public ProdutoEntityJPA getProduto() {
        return produto;
    }

    public VendaEntityJPA getVenda() {
        return venda;
    }

    public Integer getQuantidade() {
        return quantidade;
    }




}
