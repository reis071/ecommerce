package org.example.spring_ecommerce.domain.itemVenda;

import org.example.spring_ecommerce.adapters.outBound.entities.itemVenda.ItemVendaEntityJPA;
import org.example.spring_ecommerce.domain.produto.Produto;
import org.example.spring_ecommerce.domain.venda.Venda;

public interface ItemVendaRepository {
    ItemVenda salvar(ItemVenda itemVenda);

}
