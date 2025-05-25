package org.example.spring_ecommerce.infrastructure.configuration.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AutenticacaoEvento {

    @EventListener
    public void autenticacaoSucesso(AuthenticationSuccessEvent acesso) {
        log.info("Usuário autenticado: {}", acesso.getAuthentication().getName());
    }

    @EventListener
    public void autenticacaoErro(AuthenticationSuccessEvent acesso) {
        log.info("Usuário não autenticado: {}", acesso.getAuthentication().getName());
    }

}
