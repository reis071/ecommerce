package org.example.spring_ecommerce.application.services.grupo;

import lombok.AllArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.repositories.grupo.GrupoImpl;
import org.example.spring_ecommerce.adapters.outBound.repositories.usuario.UsuarioImpl;
import org.example.spring_ecommerce.application.useCases.grupo.GrupoUseCases;
import org.example.spring_ecommerce.domain.grupo.Grupo;
import org.example.spring_ecommerce.domain.usuario.Usuario;
import org.example.spring_ecommerce.infrastructure.configuration.advices.exception.grupo.GrupoException;
import org.example.spring_ecommerce.infrastructure.configuration.advices.exception.usuario.UsuarioException;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class GrupoService implements GrupoUseCases {

    private final GrupoImpl grupoImpl;
    private UsuarioImpl usuarioImpl;

    @Override
    public Grupo salvarGrupo(Grupo grupo) {
        if (grupo.getNome().isEmpty()) {
            throw new GrupoException("O nome do grupo nao pode ser vazio");
        }
        return grupoImpl.salvar(grupo);
    }

    @Override
    public List<Grupo> todosGrupos() {

        return grupoImpl.findAll();
    }

    @Override
    public Grupo procurarGrupoPorNome(String nome) {
        if (nome.isEmpty()) {
            throw new GrupoException("O nome do grupo nao pode ser vazio");
        }

        try {
           return grupoImpl.findByNome(nome);
        } catch (Exception e) {
            throw new GrupoException("Grupo nao encontrado");
        }
    }

    @Override
    public void addGrupoAoUsuario(String tipoGrupo, String emailUsuario) {
        if (tipoGrupo.isEmpty()) {
            throw new GrupoException("O nome do grupo nao pode ser vazio");
        }

        Usuario usuario = usuarioImpl.procurarUsuarioPorEmail(emailUsuario);
        if (usuario == null) {
            throw new UsuarioException("Usuario nao encontrado");
        }
        Grupo grupo = procurarGrupoPorNome(tipoGrupo);

        usuario.getGrupos().add(grupo);
        grupo.getUsuarios().add(usuario);

        usuarioImpl.salvar(usuario);

    }

}
