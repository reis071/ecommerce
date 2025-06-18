package org.example.spring_ecommerce.adapters.outBound.mappers.carrino;

import org.example.spring_ecommerce.adapters.outBound.entities.carrinho.CarrinhoEntityJPA;
import org.example.spring_ecommerce.domain.carrinho.Carrinho;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarrinhoMapperJPA {

    Carrinho toDomain(CarrinhoEntityJPA carrinhoEntityJPA);

    CarrinhoEntityJPA toEntity(Carrinho carrinho);
}
