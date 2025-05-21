package org.example.spring_ecommerce.application.useCases.grupo;

import org.example.spring_ecommerce.domain.grupo.Grupo;

import java.util.List;

public interface GrupoUseCases {
    public Grupo salvarGrupo(Grupo grupo);

    public List<Grupo> todosGrupos();

    public Grupo procurarGrupoPorNome(String nome);

    public void addGrupoAoUsuario(String tipoGrupo, String emailUsuario);
}
