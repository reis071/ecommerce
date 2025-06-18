package org.example.spring_ecommerce.adapters.outBound.repositories.itemVenda;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.entities.itemVenda.ItemVendaEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.mappers.carrino.CarrinhoMapperJPA;
import org.example.spring_ecommerce.adapters.outBound.mappers.itemVenda.ItemVendaMapperJPA;
import org.example.spring_ecommerce.adapters.outBound.mappers.produto.ProdutoMapperJPA;
import org.example.spring_ecommerce.adapters.outBound.mappers.usuario.UsuarioMapperJPA;
import org.example.spring_ecommerce.adapters.outBound.mappers.venda.VendaMapperJPA;
import org.example.spring_ecommerce.adapters.outBound.repositories.produto.ProdutoRepositoryJPA;
import org.example.spring_ecommerce.domain.itemVenda.ItemVenda;
import org.example.spring_ecommerce.domain.itemVenda.ItemVendaRepository;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository

public class ItemVendaImpl implements ItemVendaRepository {

    private final ItemVendaMapperJPA mapper;
    private final ItemVendaRepositoryJPA repository;

    @Transactional
    @Override
    public ItemVenda salvar(ItemVenda itemVenda) {

        ItemVendaEntityJPA entity = mapper.toEntity(itemVenda);

        repository.save(entity);

        entity.setId(entity.getId());

        return mapper.toDomain(entity);
    }

}
