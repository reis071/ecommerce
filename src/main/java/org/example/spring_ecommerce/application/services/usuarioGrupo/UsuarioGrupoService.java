package org.example.spring_ecommerce.application.services.usuarioGrupo;

import lombok.AllArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.repositories.usuarioGrupo.UsuarioGrupoDAO;
import org.example.spring_ecommerce.application.useCases.usuarioGrupo.UsuarioGrupoUseCases;
import org.example.spring_ecommerce.domain.usuario.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UsuarioGrupoService implements UsuarioGrupoUseCases {

    private final UsuarioGrupoDAO usuarioGrupoDAO;

    @Override
    public List<String> permissoesDoUsuario(Usuario usuario) {
        return usuarioGrupoDAO.findPermissoesByUsuario(usuario);
    }

}
