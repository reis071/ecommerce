package org.example.spring_ecommerce.adapters.outBound.repositories.venda;

import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.entities.venda.VendaEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.mappers.usuario.UsuarioMapperJPA;
import org.example.spring_ecommerce.adapters.outBound.mappers.venda.VendaMapperJPA;

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
     private final UsuarioMapperJPA usuarioMapper;
     private final VendaMapperJPA mapper;

    @Override
    public Venda salva(Venda venda) {
        VendaEntityJPA vendaSalva = new VendaEntityJPA(usuarioMapper.toEntity(venda.getUsuario()), venda.getValorTotal());
        return mapper.toDomain(repository.save(vendaSalva));
    }

    @Cacheable("vendaCache")
    @Override
    public List<Venda> todasAsVendas() {
        return mapper.toDomainList(repository.findAll());
    }

    @Cacheable("vendaCache")
    @Override
    public Venda buscaPorId(Long id) {
        return mapper.toDomain(repository.findById(id).orElseThrow(() -> new RuntimeException("Venda nao encontrada")));
    }

    @Cacheable("vendaCache")
    @Override
    public List<Venda> buscaPorDataVendaBetween(LocalDateTime start, LocalDateTime end) {
        return mapper.toDomainList(repository.findByDataVendaBetween(start,end));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
