package org.example.spring_ecommerce.domain.usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {

    Usuario salvar(Usuario usuario);

    Usuario procurarUsuarioPorEmail(String email);

    Usuario procurarUsuarioPorId(Long id);

    List<Usuario> todosOsUsuarios();

    void deleteById(Long id);
}
