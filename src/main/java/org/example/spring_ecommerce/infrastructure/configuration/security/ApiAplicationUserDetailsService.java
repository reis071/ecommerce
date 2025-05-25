package org.example.spring_ecommerce.infrastructure.configuration.security;

import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.repositories.usuario.UsuarioImpl;
import org.example.spring_ecommerce.domain.usuario.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApiAplicationUserDetailsService implements UserDetailsService {

    private final UsuarioImpl usuarioImpl;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioImpl.procurarUsuarioPorEmail(username);

        List<GrantedAuthority> permissoes = usuario
                .getGrupo()
                .stream()
                .map( permissaoConfig -> new SimpleGrantedAuthority(permissaoConfig.getNome()))
                .collect(Collectors.toList());

        return  new User(usuario.getEmail(), usuario.getSenha(), permissoes);
    }
}
