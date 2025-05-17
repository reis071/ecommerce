package org.example.spring_ecommerce.domain.usuarioGrupo;

import org.example.spring_ecommerce.domain.grupo.Grupo;
import org.example.spring_ecommerce.domain.usuario.Usuario;

import java.util.List;

public interface UsuarioGrupoRepository {
    List<String> findPermissoesByUsuario(Usuario usuario);

    void salvar(List<UsuarioGrupo> usuarioGrupos);
}
