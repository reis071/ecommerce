package org.example.spring_ecommerce.infrastructure.configuration.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.adapters.inBound.dtos.UsuarioDto;
import org.example.spring_ecommerce.application.services.usuario.UsuarioService;
import org.example.spring_ecommerce.domain.usuario.Usuario;
import org.example.spring_ecommerce.infrastructure.configuration.security.ApiAplicationUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;


@RequiredArgsConstructor
@Component
public class JwtAuthFilter   {


}
