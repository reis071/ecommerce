package org.example.spring_ecommerce.adapters.outBound.repositories.usuario;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.entities.usuario.UsuarioEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.mappers.lembreteDeCiclos.LembreteDeCiclosMapper;
import org.example.spring_ecommerce.adapters.outBound.mappers.usuario.UsuarioMapperJPA;
import org.example.spring_ecommerce.domain.usuario.Usuario;
import org.example.spring_ecommerce.domain.usuario.UsuarioRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class UsuarioImpl implements UsuarioRepository {

    private final UsuarioRepositoryJPA repository;
    private final UsuarioMapperJPA mapper;

    @Transactional
    @Override
    public Usuario salvar(Usuario usuario) {
        UsuarioEntityJPA usuarioEntityJPA = mapper.toEntity(usuario, new LembreteDeCiclosMapper());

        repository.save(usuarioEntityJPA);

        usuarioEntityJPA.setId(usuarioEntityJPA.getId());

        return mapper.toDomain(usuarioEntityJPA, new LembreteDeCiclosMapper());
    }

    @Transactional
    @Override
    public Usuario procurarUsuarioPorEmail(String email) {
        UsuarioEntityJPA usuarioEntityJPA = repository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuário nao encontrado"));
        return mapper.toDomain(usuarioEntityJPA,new LembreteDeCiclosMapper());
    }

    @Transactional
    @Override
    public List<Usuario> todosOsUsuarios() {
        List<UsuarioEntityJPA> entities = repository.findAll();
        return  mapper.toDomainList(entities);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
