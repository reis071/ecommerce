package org.example.spring_ecommerce.infrastructure.configuration.security.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class JwtValidatorFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader("Authorization");

        if(jwt != null) {
            try {
                Environment env = getEnvironment();

                String segredo = env.getProperty("JWT_SECRET", "xYzT9#Bv78a%LpZ@5dFgR!kE^mNpQwRs");

                SecretKey chaveSecreta = Keys.hmacShaKeyFor(segredo.getBytes(StandardCharsets.UTF_8));

                Claims claims = Jwts
                        .parser().verifyWith(chaveSecreta)
                        .build()
                        .parseSignedClaims(jwt)
                        .getPayload();

                String email = claims.get("username", String.class);
                String roles = claims.get("roles", String.class);

                Authentication authentication = new UsernamePasswordAuthenticationToken(email,
                        null,
                        AuthorityUtils.commaSeparatedStringToAuthorityList(roles));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }catch (Exception e) {
                throw new BadCredentialsException("Token inválido");
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/usuarios/autenticar-usuario") || request.getServletPath().equals("/usuarios/cadastrar-usuario") ||
                request.getServletPath().equals("/usuarios/solicitar-nova-senha") || request.getServletPath().equals("/usuarios/resetar-senha");
    }

    public String validarToken(String token) {
        try {

        Environment env = getEnvironment();

        String segredo = env.getProperty("JWT_SECRET", "xYzT9#Bv78a%LpZ@5dFgR!kE^mNpQwRs");

        SecretKey chaveSecreta = Keys.hmacShaKeyFor(segredo.getBytes(StandardCharsets.UTF_8));

        Claims claims = Jwts
                .parser().verifyWith(chaveSecreta)
                .build()
                .parseSignedClaims(token)
                .getPayload();

            return claims.get("username", String.class);
        }
        catch (Exception e) {
            throw new BadCredentialsException("Token inválido");}
    }
}
