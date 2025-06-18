package org.example.spring_ecommerce.application.services.grupo;

import lombok.AllArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.repositories.grupo.GrupoImpl;
import org.example.spring_ecommerce.adapters.outBound.repositories.usuario.UsuarioImpl;
import org.example.spring_ecommerce.application.useCases.grupo.GrupoUseCases;
import org.example.spring_ecommerce.domain.grupo.Grupo;
import org.example.spring_ecommerce.domain.usuario.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class GrupoService implements GrupoUseCases {

    private final GrupoImpl grupoImpl;
    private UsuarioImpl usuarioImpl;

    @Override
    public Grupo salvarGrupo(Grupo grupo) {
        return grupoImpl.salvar(grupo);
    }

    @Override
    public List<Grupo> todosGrupos() {
        return grupoImpl.findAll();
    }

    @Override
    public Grupo procurarGrupoPorNome(String nome) {
        return grupoImpl.findByNome(nome);
    }

    @Override
    public void addGrupoAoUsuario(String tipoGrupo, String emailUsuario) {
        Usuario usuario = usuarioImpl.procurarUsuarioPorEmail(emailUsuario);
        Grupo grupo = procurarGrupoPorNome(tipoGrupo);

        usuario.getGrupos().add(grupo);
        grupo.getUsuarios().add(usuario);

        usuarioImpl.salvar(usuario);

    }

}
