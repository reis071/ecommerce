package org.example.spring_ecommerce.infrastructure.configuration.security.jwt;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;



@Component
public class JwtGeneratorFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null) {
            Environment env = getEnvironment();

            String segredo = env.getProperty("JWT_SECRET", "xYzT9#Bv78a%LpZ@5dFgR!kE^mNpQwRs");

            SecretKey chaveSecreta = Keys.hmacShaKeyFor(segredo.getBytes(StandardCharsets.UTF_8));

            String token = Jwts.builder()
                    .issuer("CompComerce")
                    .subject("JWT TOKEN")
                    .claim("username", authentication.getName())
                    .claim("roles", authentication.getAuthorities()
                            .stream()
                            .map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                    .issuedAt(new Date())
                    .expiration(new Date(new Date().getTime() + 4000000))
                    .signWith(chaveSecreta).compact();

            response.setHeader("Authorization",  token);
        }
        filterChain.doFilter(request, response);
    }

    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/usuarios/autenticar");
    }
}
