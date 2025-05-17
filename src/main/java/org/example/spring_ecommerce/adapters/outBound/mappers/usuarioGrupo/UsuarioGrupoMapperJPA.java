package org.example.spring_ecommerce.adapters.outBound.mappers.usuarioGrupo;

import org.example.spring_ecommerce.adapters.outBound.entities.usuarioGrupo.UsuarioGrupoEntityJPA;

import org.example.spring_ecommerce.domain.usuarioGrupo.UsuarioGrupo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioGrupoMapperJPA {
    UsuarioGrupoEntityJPA toEntity(UsuarioGrupo domain);
    UsuarioGrupo toDomain(UsuarioGrupoEntityJPA entity);
}
