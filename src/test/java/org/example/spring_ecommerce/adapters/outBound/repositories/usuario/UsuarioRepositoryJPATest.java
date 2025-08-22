package org.example.spring_ecommerce.adapters.outBound.repositories.usuario;

import jakarta.persistence.EntityManager;
import org.example.spring_ecommerce.adapters.outBound.entities.usuario.UsuarioEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.mappers.lembreteDeCiclos.LembreteDeCiclosMapper;
import org.example.spring_ecommerce.adapters.outBound.mappers.usuario.UsuarioMapperJPA;
import org.example.spring_ecommerce.adapters.outBound.mappers.usuario.UsuarioMapperJPAImpl;
import org.example.spring_ecommerce.application.dtos.usuario.UsuarioDTORequest;
import org.example.spring_ecommerce.domain.usuario.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

import static org.assertj.core.api.Assertions.assertThat;

@Import({UsuarioMapperJPAImpl.class, LembreteDeCiclosMapper.class})
@Profile("test")
@DataJpaTest
class UsuarioRepositoryJPATest {


    @Autowired
    UsuarioMapperJPA mapper;

    @Autowired
    UsuarioRepositoryJPA repository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    @DisplayName("retornar um usuário por email em caso de sucesso")
    void findByEmailSuccessCase1() {
        UsuarioDTORequest data = new UsuarioDTORequest("Fulano", "fulano@ig.com.br", "123456");
        this.criarUsuario(data);


        UsuarioEntityJPA entity = this.repository.findByEmail(data.email()).orElse(null);

        assertThat(entity).isNotNull();
        assertThat(entity.getEmail()).isEqualTo(data.email());
    }

    @Test
    @DisplayName("Retornar que o usuário nao foi encontrado caso não exista")
    void findByEmailSuccessCase2() {
        UsuarioDTORequest data = new UsuarioDTORequest("Fulano", "fulano@ig.com.br", "123456");

        UsuarioEntityJPA entity = this.repository.findByEmail(data.email()).orElse(null);

        assertThat(entity).isNull();
    }


    private UsuarioEntityJPA criarUsuario(UsuarioDTORequest data) {

        UsuarioEntityJPA entity = this.mapper.toEntity(new Usuario(data.nome(), data.email(), data.senha()), new LembreteDeCiclosMapper());

        this.testEntityManager.persistAndFlush(entity);
        return entity;
    }
}