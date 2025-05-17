package org.example.spring_ecommerce.application.services.grupo;

import lombok.AllArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.repositories.grupo.GrupoImpl;
import org.example.spring_ecommerce.application.useCases.grupo.GrupoUseCases;
import org.example.spring_ecommerce.domain.grupo.Grupo;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class GrupoService implements GrupoUseCases {

    private final GrupoImpl grupoImpl;

    @Override
    public Grupo salvarGrupoService(Grupo grupo) {
        return grupoImpl.salvar(grupo);
    }

    @Override
    public List<Grupo> todosGruposService() {
        return grupoImpl.findAll();
    }

    @Override
    public Grupo procurarGruPorNomeService(String nome) {
        return grupoImpl.findByNome(nome);
    }

}
