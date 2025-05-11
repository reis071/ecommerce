package org.example.spring_ecommerce.adapters.outBound.repositories.grupo;

import lombok.AllArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.entities.grupo.GrupoEntityJPA;
import org.example.spring_ecommerce.domain.grupo.Grupo;
import org.example.spring_ecommerce.domain.grupo.GrupoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Repository
public class GrupoDAO implements GrupoRepository {

    private final GrupoRepositoryJPA repository;

    @Override
    public Grupo salvar(Grupo grupo) {
        GrupoEntityJPA grupoEntityJPA = new GrupoEntityJPA(grupo);
        repository.save(grupoEntityJPA);
        return grupo;
    }

    @Override
    public List<Grupo> findAll() {
        return repository
                .findAll()
                .stream()
                .map(grupo -> new Grupo( grupo.getNome()))
                .collect(Collectors.toList());
    }

    @Override
    public Grupo findByNome(String nome) {
        Optional<GrupoEntityJPA> grupoEntityJPA = repository.findByNome(nome);
        return grupoEntityJPA.map( (entity) -> new Grupo( entity.getNome())).orElseThrow(() -> new RuntimeException("Grupo nao encontrado"));
    }
}
