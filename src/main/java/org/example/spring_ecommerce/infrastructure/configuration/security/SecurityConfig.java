package org.example.spring_ecommerce.infrastructure.configuration.security;

import org.example.spring_ecommerce.infrastructure.configuration.security.jwt.JwtGeneratorFilter;
import org.example.spring_ecommerce.infrastructure.configuration.security.jwt.JwtValidatorFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .securityContext(context -> context.requireExplicitSave(false))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/usuarios/cadastrar-usuario",
                                "/usuarios/autenticar-usuario",
                                "/usuarios/solicitar-nova-senha",
                                "/usuarios/resetar-senha","/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/v3/api-docs.yaml",
                                "/webjars/**")
                        .permitAll()
                        .requestMatchers("/produtos/deletar-produto", "/produtos/atualizar-produto","produtos/todos-produtos", "/produtos/registrar-produto", "/produtos/procurar-produto",
                                "vendas/**",
                                "/grupos/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )

                .addFilterBefore(new JwtValidatorFilter(), UsernamePasswordAuthenticationFilter.class)
                .httpBasic(basic -> basic.authenticationEntryPoint(new CustomExceptionBasicAuth()))
                .exceptionHandling(ex -> ex.accessDeniedHandler(new CustomAcessDeniedHandler()));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }
}
