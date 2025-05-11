package org.example.spring_ecommerce.application.useCases.grupo;

import org.example.spring_ecommerce.domain.grupo.Grupo;

import java.util.List;

public interface GrupoUseCases {
    Grupo salvarGrupoService(Grupo grupo);

    List<Grupo> todosGruposService();

    Grupo procurarGruPorNomeService(String nome);
}
