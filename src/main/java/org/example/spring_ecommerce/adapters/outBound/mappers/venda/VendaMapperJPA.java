package org.example.spring_ecommerce.adapters.outBound.mappers.venda;

import org.example.spring_ecommerce.adapters.outBound.entities.venda.VendaEntityJPA;
import org.example.spring_ecommerce.domain.venda.Venda;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VendaMapperJPA {
    VendaEntityJPA toEntityJPA(Venda venda);
    Venda toDomain(VendaEntityJPA vendaEntityJPA);
}
