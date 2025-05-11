package org.example.spring_ecommerce.adapters.outBound.repositories.usuario;

import lombok.AllArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.entities.usuario.UsuarioEntityJPA;
import org.example.spring_ecommerce.domain.usuario.Usuario;
import org.example.spring_ecommerce.domain.usuario.UsuarioRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Repository
public class UsuarioDAO implements UsuarioRepository {

    private final UsuarioRepositoryJPA repository;

    @Override
    public Usuario save(Usuario usuario) {
        UsuarioEntityJPA usuarioEntityJPA = new UsuarioEntityJPA(usuario);
        repository.save(usuarioEntityJPA);
        return usuario;
    }

    @Override
    public Usuario findByEmail(String email) {
        Optional<UsuarioEntityJPA> usuarioEntityJPA = repository.findByEmail(email);
        return usuarioEntityJPA.map( (entity) -> new Usuario( entity.getNome(), entity.getSaldo(), entity.getSenha(), entity.getEmail()))
                .orElseThrow(() -> new RuntimeException("Usuário nao encontrado"));
    }

    @Override
    public Usuario findById(Long id) {
        Optional<UsuarioEntityJPA> usuarioEntityJPA = repository.findById(id);
        return usuarioEntityJPA.map( (entity) -> new Usuario( entity.getNome(), entity.getSaldo(), entity.getSenha(), entity.getEmail()))
                .orElseThrow(() -> new RuntimeException("Usuário nao encontrado"));
    }

    @Override
    public List<Usuario> findAll() {
       return repository
               .findAll()
               .stream()
               .map( (entity) -> new Usuario( entity.getNome(), entity.getSaldo(), entity.getSenha(), entity.getEmail()))
               .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
