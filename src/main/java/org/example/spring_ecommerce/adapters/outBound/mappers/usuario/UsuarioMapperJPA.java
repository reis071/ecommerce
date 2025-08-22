package org.example.spring_ecommerce.adapters.outBound.mappers.usuario;

import org.example.spring_ecommerce.adapters.outBound.entities.usuario.UsuarioEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.mappers.lembreteDeCiclos.LembreteDeCiclosMapper;
import org.example.spring_ecommerce.domain.usuario.Usuario;
import org.mapstruct.Context;
import org.mapstruct.Mapper;



import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapperJPA {

    Usuario toDomain(UsuarioEntityJPA entity, @Context LembreteDeCiclosMapper contexto);

    UsuarioEntityJPA toEntity(Usuario domain, @Context LembreteDeCiclosMapper contexto);

    List<Usuario> toDomainList(List<UsuarioEntityJPA> entities);
    List<UsuarioEntityJPA> toEntityList(List<Usuario> domains);
}
