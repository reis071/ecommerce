package org.example.spring_ecommerce.application.useCases.grupo;

import org.example.spring_ecommerce.application.dtos.grupo.GrupoDTORequest;
import org.example.spring_ecommerce.domain.grupo.Grupo;
import org.example.spring_ecommerce.domain.usuario.Usuario;

import java.util.List;

public interface GrupoUseCases {
    Grupo salvarGrupo(GrupoDTORequest grupoDTORequest);

    List<Grupo> todosGrupos();

    Grupo procurarGrupoPorNome(String nome);

    void addGrupoAoUsuario(String tipoGrupo, String emailUsuario);
}
