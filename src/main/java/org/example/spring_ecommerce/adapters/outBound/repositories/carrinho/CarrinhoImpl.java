package org.example.spring_ecommerce.adapters.outBound.repositories.carrinho;

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
    CarrinhoMapperJPA mapper;

    @Override
    public Carrinho salvar(Usuario usuario) {

        Carrinho carrinho = new Carrinho(usuario);
        repository.save(mapper.toEntity(carrinho));

        return carrinho;

    }
}
