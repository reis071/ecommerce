package org.example.spring_ecommerce.adapters.outBound.repositories.usuario;

import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.entities.usuario.UsuarioEntityJPA;
import org.example.spring_ecommerce.adapters.outBound.mappers.usuario.UsuarioMapperJPA;
import org.example.spring_ecommerce.domain.usuario.Usuario;
import org.example.spring_ecommerce.domain.usuario.UsuarioRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class UsuarioImpl implements UsuarioRepository {

    private final UsuarioRepositoryJPA repository;
    UsuarioMapperJPA mapper;

    @Override
    public Usuario salvar(Usuario usuario) {
        UsuarioEntityJPA usuarioEntityJPA = new UsuarioEntityJPA(usuario.getNome(), usuario.getSenha(), usuario.getEmail());
        repository.save(usuarioEntityJPA);
        return mapper.toDomain(usuarioEntityJPA);
    }

    @Override
    public Usuario procurarUsuarioPorEmail(String email) {
        UsuarioEntityJPA usuarioEntityJPA = repository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuário nao encontrado"));
        return mapper.toDomain(usuarioEntityJPA);
    }

    @Override
    public Usuario procurarUsuarioPorId(Long id) {
        UsuarioEntityJPA usuarioEntityJPA = repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário nao encontrado"));
        return mapper.toDomain(usuarioEntityJPA);
    }

    @Override
    public List<Usuario> todosOsUsuarios() {
        List<UsuarioEntityJPA> entities = repository.findAll();
        return  mapper.toDomainList(entities);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
