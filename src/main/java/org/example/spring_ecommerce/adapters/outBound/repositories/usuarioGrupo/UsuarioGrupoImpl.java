package org.example.spring_ecommerce.adapters.outBound.repositories.usuarioGrupo;

import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.entities.usuario.UsuarioEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.entities.usuarioGrupo.UsuarioGrupoEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.mappers.usuarioGrupo.UsuarioGrupoMapperJPA;
import org.example.spring_ecommerce.adapters.outBound.mappers.grupo.GrupoMapperJPA;
import org.example.spring_ecommerce.adapters.outBound.mappers.usuario.UsuarioMapperJPA;
import org.example.spring_ecommerce.domain.grupo.Grupo;
import org.example.spring_ecommerce.domain.usuario.Usuario;
import org.example.spring_ecommerce.domain.usuarioGrupo.UsuarioGrupo;
import org.example.spring_ecommerce.domain.usuarioGrupo.UsuarioGrupoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class UsuarioGrupoImpl implements UsuarioGrupoRepository {

    private final UsuarioGrupoRepositoryJPA repository;

    private final UsuarioGrupoMapperJPA mapper;

    @Override
    public List<String> findPermissoesByUsuario(Usuario usuario) {
        UsuarioEntityJPA usuarioEntityJPA = new UsuarioEntityJPA(usuario.getNome(), usuario.getSenha(), usuario.getEmail());
        return repository.findPermissoesByUsuario(usuarioEntityJPA);
    }

    @Override
    public void salvar(List<UsuarioGrupo> usuarioGrupos) {
        List<UsuarioGrupoEntityJPA> entities = usuarioGrupos.stream()
                .map(mapper::toEntity)
                .collect(Collectors.toList());

        repository.saveAll(entities);
    }

}
