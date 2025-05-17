package org.example.spring_ecommerce.adapters.outBound.repositories.venda;

import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.entities.venda.VendaEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.mappers.usuario.UsuarioMapperJPA;
import org.example.spring_ecommerce.adapters.outBound.mappers.venda.VendaMapperJPA;

import org.example.spring_ecommerce.domain.venda.Venda;
import org.example.spring_ecommerce.domain.venda.VendaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class VendaImpl implements VendaRepository {
     private final VendaRepositoryJPA vendaRepositoryJPA;

     private final VendaMapperJPA mapper;
     private final UsuarioMapperJPA usuarioMapper;

    @Override
    public Venda salva(Venda venda) {
        VendaEntityJPA vendaSalva = new VendaEntityJPA(usuarioMapper.toEntity(venda.getUsuario()), venda.getDataVenda(), venda.getValorTotal());
        return mapper.toDomain(vendaRepositoryJPA.save(vendaSalva));
    }

    @Override
    public List<Venda> todasAsVendas() {
        return mapper.toDomainList(vendaRepositoryJPA.findAll());
    }


    @Override
    public Venda buscaPorId(Long id) {
        return mapper.toDomain(vendaRepositoryJPA.findById(id).orElseThrow(() -> new RuntimeException("Venda nao encontrada")));
    }

    @Override
    public List<Venda> buscaPorDataVendaBetween(LocalDateTime start, LocalDateTime end) {
        return mapper.toDomainList(vendaRepositoryJPA.findByDataVendaBetween(start,end));
    }

    @Override
    public void delete(Long id) {
        vendaRepositoryJPA.deleteById(id);
    }
}
