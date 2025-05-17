package org.example.spring_ecommerce.adapters.outBound.mappers.venda;

import org.example.spring_ecommerce.adapters.outBound.entities.venda.VendaEntityJPA;
import org.example.spring_ecommerce.domain.venda.Venda;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VendaMapperJPA {
    VendaEntityJPA toEntityJPA(Venda venda);
    Venda toDomain(VendaEntityJPA vendaEntityJPA);

    List<VendaEntityJPA> toEntityList(List<Venda> domains);
    List<Venda> toDomainList(List<VendaEntityJPA> entities);
}
