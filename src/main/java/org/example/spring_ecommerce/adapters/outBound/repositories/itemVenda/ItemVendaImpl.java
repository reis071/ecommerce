package org.example.spring_ecommerce.adapters.outBound.repositories.itemVenda;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.entities.itemVenda.ItemVendaEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.mappers.itemVenda.ItemVendaMapperJPA;
import org.example.spring_ecommerce.adapters.outBound.repositories.produto.ProdutoRepositoryJPA;
import org.example.spring_ecommerce.domain.itemVenda.ItemVenda;
import org.example.spring_ecommerce.domain.itemVenda.ItemVendaRepository;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository

public class ItemVendaImpl implements ItemVendaRepository {
    private final ItemVendaMapperJPA mapper;
    private final ItemVendaRepositoryJPA itemVendaRepositoryJPA;

    @Transactional
    @Override
    public ItemVenda salvar(ItemVenda itemVenda) {

        ItemVenda domain = new ItemVenda(itemVenda.getProduto(), itemVenda.getVenda(), itemVenda.getQuantidade());

        ItemVendaEntityJPA entity = itemVendaRepositoryJPA.save(mapper.toEntity(domain));

        return mapper.toDomain(entity);
    }

}
