package org.example.spring_ecommerce.application.useCases.usuarioGrupo;
import org.example.spring_ecommerce.domain.usuario.Usuario;

import java.util.List;


public interface UsuarioGrupoUseCases {
    public List<String> permissoesDoUsuario(Usuario usuario);
}
