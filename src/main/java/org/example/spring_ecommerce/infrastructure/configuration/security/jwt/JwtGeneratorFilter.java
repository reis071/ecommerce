package org.example.spring_ecommerce.infrastructure.configuration.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.infrastructure.configuration.security.ApiAplicationUserDetailsService;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

 @RequiredArgsConstructor
@Service
public class JwtGeneratorFilter{

    private final Environment env;
    private final ApiAplicationUserDetailsService apiAplicationUserDetailsService;

    public String gerarToken(Authentication authentication) {
        String segredo = env.getProperty("JWT_SECRET", "xYzT9#Bv78a%LpZ@5dFgR!kE^mNpQwRs");
        SecretKey chaveSecreta = Keys.hmacShaKeyFor(segredo.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .issuer("CompCommerce")
                .subject(authentication.getName())
                .claim("username", authentication.getName())
                .claim("roles", authentication.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(",")))
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + 4000000))
                .signWith(chaveSecreta)
                .compact();

    }

     public String tokenResetUser(String email) {
         String segredo = env.getProperty("JWT_SECRET", "xYzT9#Bv78a%LpZ@5dFgR!kE^mNpQwRs");
         SecretKey chaveSecreta = Keys.hmacShaKeyFor(segredo.getBytes(StandardCharsets.UTF_8));

         UserDetails usuario = apiAplicationUserDetailsService.loadUserByUsername(email);

         return Jwts.builder()
                 .issuer("CompCommerce")
                 .subject(usuario.getUsername())
                 .claim("username", usuario.getUsername())
                 .claim("roles", usuario.getAuthorities()
                         .stream()
                         .map(GrantedAuthority::getAuthority)
                         .collect(Collectors.joining(",")))
                 .issuedAt(new Date())
                 .expiration(new Date(new Date().getTime() + 4000000))
                 .signWith(chaveSecreta)
                 .compact();

     }
}
