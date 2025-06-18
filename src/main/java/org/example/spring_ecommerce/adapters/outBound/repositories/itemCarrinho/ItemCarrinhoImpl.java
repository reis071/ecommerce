package org.example.spring_ecommerce.adapters.outBound.repositories.itemCarrinho;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.entities.itemCarrinho.ItemCarrinhoEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.mappers.itemCarrinho.ItemCarrinhoMapperJPA;


import org.example.spring_ecommerce.domain.itemCarrinho.ItemCarrinho;
import org.example.spring_ecommerce.domain.itemCarrinho.ItemCarrinhoRepository;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ItemCarrinhoImpl implements ItemCarrinhoRepository {


    private final ItemCarrinhoRepositoryJPA repository;
    private final ItemCarrinhoMapperJPA mapper;

    @Transactional
    @Override
    public ItemCarrinho salvar(ItemCarrinho itemCarrinho) {


        ItemCarrinhoEntityJPA itemCarrinhoEntityJPA = mapper.toEntity(itemCarrinho);

        repository.save(itemCarrinhoEntityJPA);

        itemCarrinho.setId(itemCarrinhoEntityJPA.getId());

        return mapper.toDomain(itemCarrinhoEntityJPA);
    }

    @Transactional
    @Override
    public void removerItemCarrinho(ItemCarrinho itemCarrinho) {
        repository.delete(mapper.toEntity(itemCarrinho));
    }

}
