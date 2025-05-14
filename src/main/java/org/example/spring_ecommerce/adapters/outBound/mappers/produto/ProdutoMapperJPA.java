package org.example.spring_ecommerce.adapters.outBound.mappers.produto;

import org.example.spring_ecommerce.adapters.outBound.entities.produto.ProdutoEntityJPA;
import org.example.spring_ecommerce.domain.produto.Produto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoMapperJPA {
    Produto toDomain(ProdutoEntityJPA entity);
    ProdutoEntityJPA toEntity(Produto domain);

    List<Produto> toDomainList(List<ProdutoEntityJPA> entities);
    List<ProdutoEntityJPA> toEntityList(List<Produto> domains);
}
