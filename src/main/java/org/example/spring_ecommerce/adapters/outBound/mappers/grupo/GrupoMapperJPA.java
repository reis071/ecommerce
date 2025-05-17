package org.example.spring_ecommerce.adapters.outBound.mappers.grupo;

import org.example.spring_ecommerce.adapters.outBound.entities.grupo.GrupoEntityJPA;
import org.example.spring_ecommerce.domain.grupo.Grupo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GrupoMapperJPA {
    Grupo toDomain(GrupoEntityJPA grupoEntityJPA);
    GrupoEntityJPA toEntity(Grupo grupo);

    List<Grupo> toDomainList(List<GrupoEntityJPA> entities);
    List<GrupoEntityJPA> toEntityList(List<Grupo> domain);
}
