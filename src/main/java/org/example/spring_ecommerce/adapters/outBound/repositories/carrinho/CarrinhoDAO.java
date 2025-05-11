package org.example.spring_ecommerce.adapters.outBound.repositories.carrinho;

import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.entities.carrinho.CarrinhoEntityJPA;

import org.example.spring_ecommerce.adapters.outBound.entities.usuario.UsuarioEntityJPA;

import org.example.spring_ecommerce.adapters.outBound.repositories.usuario.UsuarioRepositoryJPA;
import org.example.spring_ecommerce.domain.carrinho.Carrinho;
import org.example.spring_ecommerce.domain.carrinho.CarrinhoRepository;
import org.example.spring_ecommerce.domain.usuario.Usuario;
import org.springframework.stereotype.Repository;


@RequiredArgsConstructor
@Repository
public class CarrinhoDAO implements CarrinhoRepository {

    private final CarrinhoRepositoryJPA repositoryJPA;
    private final UsuarioRepositoryJPA usuarioRepositoryJPA;


    @Override
    public Carrinho salvar(Usuario usuario) {
        // Busca o UsuarioEntityJPA pelo ID
        UsuarioEntityJPA entity = usuarioRepositoryJPA.findById(usuario.getId()).orElseThrow(() -> new RuntimeException("Usuário nao encontrado"));


        CarrinhoEntityJPA carrinho = new CarrinhoEntityJPA(entity);

        repositoryJPA.save(carrinho);
        // Converte de volta para o domínio (Carrinho)
        Carrinho carrinhoObj = new Carrinho();
        carrinhoObj.setId(carrinho.getId());
        carrinhoObj.setUsuario(usuario); // apenas referência

        return carrinhoObj;

    }

    @Override
    public Carrinho procurarUsuario(Usuario usuario) {

        UsuarioEntityJPA usuarioEntity = usuarioRepositoryJPA.findByEmail(usuario.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        CarrinhoEntityJPA carrinhoEntity = repositoryJPA.findByUsuario(usuarioEntity)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

        Carrinho carrinho = new Carrinho();
        carrinho.setId(carrinhoEntity.getId());
        carrinho.setUsuario(usuario); // Aqui você assume que o usuário já está convertido

        return carrinho;
    }
}
