package org.example.spring_ecommerce.domain.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.spring_ecommerce.domain.services.UsuarioService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;



@Component
public class JwtAuthFilter  extends OncePerRequestFilter {

    private JwtService jwtService;

    private UsuarioService usuarioService;

    public JwtAuthFilter( JwtService jwtService, UsuarioService usuarioService ) {
        this.jwtService = jwtService;
        this.usuarioService = usuarioService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {

        String authorization = httpServletRequest.getHeader("Authorization");

        if( authorization != null && authorization.startsWith("Bearer")){
            String token = authorization.split(" ")[1];
            boolean isValid = jwtService.tokenValido(token);

            if(isValid){
                String loginUsuario = jwtService.obterLoginUsuario(token);
                UserDetails usuario = usuarioService.loadUserByUsername(loginUsuario);
                UsernamePasswordAuthenticationToken user = new
                        UsernamePasswordAuthenticationToken(usuario,null,
                        usuario.getAuthorities());
                user.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(user);
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}
