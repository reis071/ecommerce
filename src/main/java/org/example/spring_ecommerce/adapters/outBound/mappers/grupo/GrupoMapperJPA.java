package org.example.spring_ecommerce.adapters.outBound.mappers.grupo;

import org.example.spring_ecommerce.adapters.outBound.entities.grupo.GrupoEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.mappers.lembreteDeCiclos.LembreteDeCiclosMapper;
import org.example.spring_ecommerce.adapters.outBound.mappers.usuario.UsuarioMapperJPA;
import org.example.spring_ecommerce.domain.grupo.Grupo;
import org.mapstruct.Context;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface GrupoMapperJPA {

    GrupoEntityJPA toEntity(Grupo grupo, @Context LembreteDeCiclosMapper context);


    Grupo toDomain(GrupoEntityJPA entity, @Context LembreteDeCiclosMapper context);

    List<Grupo> toDomainList(List<GrupoEntityJPA> entities);
    List<GrupoEntityJPA> toEntityList(List<Grupo> domain);
}
