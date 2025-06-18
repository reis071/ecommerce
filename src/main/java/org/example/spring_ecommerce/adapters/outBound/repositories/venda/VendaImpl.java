package org.example.spring_ecommerce.adapters.outBound.repositories.venda;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.entities.venda.VendaEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.mappers.venda.VendaMapperJPA;

import org.example.spring_ecommerce.domain.enums.StatusVenda;
import org.example.spring_ecommerce.domain.venda.Venda;
import org.example.spring_ecommerce.domain.venda.VendaRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class VendaImpl implements VendaRepository {

     private final VendaRepositoryJPA repository;
     private final VendaMapperJPA mapper;

    @Transactional
    @Override
    public Venda salva(Venda venda) {

        VendaEntityJPA entity = mapper.toEntityJPA(venda);

        entity.setStatus(StatusVenda.VENDIDO);
        repository.save(entity);

        entity.setId(entity.getId());

        return mapper.toDomain(entity);
    }

    @Transactional
    @Cacheable("vendaCache")
    @Override
    public List<Venda> todasAsVendas() {
        return mapper.toDomainList(repository.findAll());
    }

    @Transactional
    @Cacheable("vendaCache")
    @Override
    public Venda buscaPorId(Long id) {
        return mapper.toDomain(repository.findById(id).orElseThrow(() -> new RuntimeException("Venda nao encontrada")));
    }

    @Transactional
    @Cacheable("vendaCache")
    @Override
    public List<Venda> buscaPorDataVendaBetween(LocalDateTime start, LocalDateTime end) {
        return mapper.toDomainList(repository.findByDataVendaBetween(start,end));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
