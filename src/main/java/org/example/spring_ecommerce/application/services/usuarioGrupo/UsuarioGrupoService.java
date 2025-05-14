package org.example.spring_ecommerce.application.services.usuarioGrupo;

import lombok.AllArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.repositories.usuarioGrupo.UsuarioGrupoImpl;
import org.example.spring_ecommerce.application.useCases.usuarioGrupo.UsuarioGrupoUseCases;
import org.example.spring_ecommerce.domain.usuario.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UsuarioGrupoService implements UsuarioGrupoUseCases {

    private final UsuarioGrupoImpl usuarioGrupoImpl;

    @Override
    public List<String> permissoesDoUsuario(Usuario usuario) {
        return usuarioGrupoImpl.findPermissoesByUsuario(usuario);
    }

}
