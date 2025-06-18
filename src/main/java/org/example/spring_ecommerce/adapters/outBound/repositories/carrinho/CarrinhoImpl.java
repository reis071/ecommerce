package org.example.spring_ecommerce.adapters.outBound.repositories.carrinho;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.entities.carrinho.CarrinhoEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.mappers.carrino.CarrinhoMapperJPA;
import org.example.spring_ecommerce.domain.carrinho.Carrinho;
import org.example.spring_ecommerce.domain.carrinho.CarrinhoRepository;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CarrinhoImpl implements CarrinhoRepository {

    private final CarrinhoRepositoryJPA repository;
    private final CarrinhoMapperJPA mapper;

    @Transactional
    @Override
    public Carrinho salvar() {
        CarrinhoEntityJPA carrinhoEntityJPA = new CarrinhoEntityJPA();

        repository.save(carrinhoEntityJPA);

        carrinhoEntityJPA.setId(carrinhoEntityJPA.getId());
        return mapper.toDomain(carrinhoEntityJPA);
    }

    @Override
    public Carrinho atualizarCarrinho(Carrinho carrinho) {
        CarrinhoEntityJPA carrinhoEntityJPA = mapper.toEntity(carrinho);

        repository.save(carrinhoEntityJPA);

        carrinhoEntityJPA.setId(carrinhoEntityJPA.getId());
        return mapper.toDomain(carrinhoEntityJPA);
    }


}
