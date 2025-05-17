package org.example.spring_ecommerce.adapters.outBound.repositories.grupo;

import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.entities.grupo.GrupoEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.mappers.grupo.GrupoMapperJPA;
import org.example.spring_ecommerce.domain.grupo.Grupo;
import org.example.spring_ecommerce.domain.grupo.GrupoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@RequiredArgsConstructor
@Repository
public class GrupoImpl implements GrupoRepository {

    private final GrupoRepositoryJPA repository;
    GrupoMapperJPA mapper;

    @Override
    public Grupo salvar(Grupo grupo) {
        GrupoEntityJPA grupoEntityJPA = new GrupoEntityJPA(grupo.getNome());
        repository.save(grupoEntityJPA);
        return mapper.toDomain(grupoEntityJPA);
    }

    @Override
    public List<Grupo> findAll() {
        List<GrupoEntityJPA> entities = repository.findAll();
        return mapper.toDomainList(entities);
    }

    @Override
    public Grupo findByNome(String nome) {
        GrupoEntityJPA grupoEntityJPA = repository.findByNome(nome).orElseThrow(() -> new RuntimeException("Grupo nao encontrado"));
        return mapper.toDomain(grupoEntityJPA);
    }
}
