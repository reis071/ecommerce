package org.example.spring_ecommerce.adapters.outBound.repositories.usuarioGrupo;

import org.example.spring_ecommerce.domain.usuario.Usuario;
import org.example.spring_ecommerce.adapters.outBound.entities.usuarioGrupo.UsuarioGrupoEntityJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioGrupoRepositoryJPA extends JpaRepository<UsuarioGrupoEntityJPA, Long> {
    @Query("""
            select distinct g.nome
            from UsuarioGrupoEntityJPA ug
            join ug.grupo g
            join ug.usuario u
            where u = ?1
    """)
    List<String> findPermissoesByUsuario(Usuario usuario);
}
