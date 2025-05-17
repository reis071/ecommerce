package org.example.spring_ecommerce.adapters.outBound.mappers.usuario;

import org.example.spring_ecommerce.adapters.outBound.entities.usuario.UsuarioEntityJPA;
import org.example.spring_ecommerce.domain.usuario.Usuario;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapperJPA {

    Usuario toDomain(UsuarioEntityJPA entity);

    UsuarioEntityJPA toEntity(Usuario domain);

    // Mapeamento de listas (MapStruct faz automaticamente!)
    List<Usuario> toDomainList(List<UsuarioEntityJPA> entities);
    List<UsuarioEntityJPA> toEntityList(List<Usuario> domains);
}
