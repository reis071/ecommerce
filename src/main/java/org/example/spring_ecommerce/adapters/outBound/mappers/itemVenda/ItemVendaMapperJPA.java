package org.example.spring_ecommerce.adapters.outBound.mappers.itemVenda;

import org.example.spring_ecommerce.adapters.outBound.entities.itemVenda.ItemVendaEntityJPA;
import org.example.spring_ecommerce.domain.itemVenda.ItemVenda;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemVendaMapperJPA {
    ItemVenda toDomain(ItemVendaEntityJPA entity);
    ItemVendaEntityJPA toEntity(ItemVenda domain);

    List<ItemVenda> toDomainList(List<ItemVendaEntityJPA> entities);
    List<ItemVendaEntityJPA> toEntityList(List<ItemVenda> domains);
}
