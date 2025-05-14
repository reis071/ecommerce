package org.example.spring_ecommerce.adapters.outBound.repositories.venda;

import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.entities.venda.VendaEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.mappers.usuario.UsuarioMapperJPA;
import org.example.spring_ecommerce.adapters.outBound.mappers.venda.VendaMapperJPA;

import org.example.spring_ecommerce.domain.venda.Venda;
import org.example.spring_ecommerce.domain.venda.VendaRepository;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class VendaImpl implements VendaRepository {
     private final VendaRepositoryJPA vendaRepositoryJPA;
     VendaMapperJPA mapper;
     UsuarioMapperJPA usuarioMapper;

    @Override
    public Venda salva(Venda venda) {
        VendaEntityJPA vendaSalva = new VendaEntityJPA(usuarioMapper.toEntity(venda.getUsuario()), venda.getDataVenda(), venda.getValorTotal());
        return mapper.toDomain(vendaRepositoryJPA.save(vendaSalva));
    }
}
