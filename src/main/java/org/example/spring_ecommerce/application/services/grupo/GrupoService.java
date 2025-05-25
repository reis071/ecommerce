package org.example.spring_ecommerce.application.services.grupo;

import lombok.AllArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.repositories.grupo.GrupoImpl;
import org.example.spring_ecommerce.application.useCases.grupo.GrupoUseCases;
import org.example.spring_ecommerce.application.useCases.usuario.UsuarioUseCases;
import org.example.spring_ecommerce.domain.grupo.Grupo;
import org.example.spring_ecommerce.domain.usuario.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class GrupoService implements GrupoUseCases {

    private final GrupoImpl grupoImpl;
    private UsuarioUseCases usuarioUseCases;

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

    public void addGrupoAoUsuario(String tipoGrupo, String emailUsuario) {
        Usuario usuario = usuarioUseCases.buscarUsuarioPorEmail(emailUsuario);
        Grupo grupo = procurarGrupoPorNome(tipoGrupo);

        usuario.getGrupo().add(grupo);
        usuarioUseCases.salvar(usuario);
    }

}
