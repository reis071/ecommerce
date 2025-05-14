package org.example.spring_ecommerce.adapters.outBound.repositories.usuarioGrupo;

import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.entities.usuario.UsuarioEntityJPA;
import org.example.spring_ecommerce.domain.usuario.Usuario;
import org.example.spring_ecommerce.domain.usuarioGrupo.UsuarioGrupoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class UsuarioGrupoImpl implements UsuarioGrupoRepository {

    private final UsuarioGrupoRepositoryJPA repository;

    @Override
    public List<String> findPermissoesByUsuario(Usuario usuario) {
        UsuarioEntityJPA usuarioEntityJPA = new UsuarioEntityJPA(usuario.getNome(), usuario.getSenha(), usuario.getEmail());
        return repository.findPermissoesByUsuario(usuarioEntityJPA);
    }

}
