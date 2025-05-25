package org.example.spring_ecommerce.adapters.outBound.repositories.carrinho;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.example.spring_ecommerce.adapters.outBound.mappers.carrino.CarrinhoMapperJPA;
import org.example.spring_ecommerce.domain.carrinho.Carrinho;
import org.example.spring_ecommerce.domain.carrinho.CarrinhoRepository;
import org.example.spring_ecommerce.domain.usuario.Usuario;
import org.springframework.stereotype.Repository;


@RequiredArgsConstructor
@Repository
public class CarrinhoImpl implements CarrinhoRepository {

    private final CarrinhoRepositoryJPA repository;
    private final CarrinhoMapperJPA mapper;

    @Transactional
    @Override
    public Carrinho salvar(Usuario usuario) {

        Carrinho carrinho = new Carrinho(usuario);
        repository.save(mapper.toEntity(carrinho));

        return carrinho;

    }
}
