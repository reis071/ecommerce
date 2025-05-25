package org.example.spring_ecommerce.adapters.outBound.mappers.itemCarrinho;

import org.example.spring_ecommerce.adapters.outBound.entities.itemCarrinho.ItemCarrinhoEntityJPA;
import org.example.spring_ecommerce.domain.itemCarrinho.ItemCarrinho;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemCarrinhoMapperJPA {
    ItemCarrinho toDomain(ItemCarrinhoEntityJPA itemCarrinhoEntityJPA);
    ItemCarrinhoEntityJPA toEntity(ItemCarrinho itemCarrinho);

    List<ItemCarrinho> toDomainList(List<ItemCarrinhoEntityJPA> entities);
    List<ItemCarrinhoEntityJPA> toEntityList(List<ItemCarrinho> domain);
}
