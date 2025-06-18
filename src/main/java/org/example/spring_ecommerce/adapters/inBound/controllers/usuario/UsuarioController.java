package org.example.spring_ecommerce.adapters.inBound.controllers.usuario;

import lombok.RequiredArgsConstructor;

import org.example.spring_ecommerce.adapters.inBound.dtos.UsuarioDto;
import org.example.spring_ecommerce.application.useCases.usuario.UsuarioUseCases;
import org.example.spring_ecommerce.domain.usuario.Usuario;
import org.example.spring_ecommerce.application.services.usuario.UsuarioService;

import org.example.spring_ecommerce.infrastructure.configuration.security.AuthenticationUsuarioCustom;
import org.example.spring_ecommerce.infrastructure.configuration.security.jwt.JwtGeneratorFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioUseCases usuarioUseCases;
    private final AuthenticationUsuarioCustom autenticacao;
    private final JwtGeneratorFilter jwtGeneratorFilter;

    @PostMapping(path = "/cadastrar-usuario")
    public ResponseEntity<UsuarioDto> cadastrarUsuario(@RequestBody Usuario usuario) {
        Usuario domain = usuarioUseCases.salvar(usuario);

        return ResponseEntity.ok(new UsuarioDto(domain.getNome(), domain.getEmail()));
    }

    @PostMapping(path = "/autenticar-usuario")
    private ResponseEntity<String> autenticar(@RequestBody HashMap<String, String> credencials) {
        String email = credencials.get("email");
        String senha = credencials.get("senha");

        Authentication authentication = autenticacao.authenticate(new UsernamePasswordAuthenticationToken(email, senha));

        String token = jwtGeneratorFilter.gerarToken(authentication);

        return ResponseEntity.ok().header("Authorization", token).body("Autenticado com sucesso");
    }

    @GetMapping("/solicitar-nova-senha")
    public ResponseEntity<String> solicitarResetarSenha(@RequestParam String email) {

        usuarioUseCases.enviarSolicitacaoDeResetarSenha(email);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("token de senha enviado para o email.");
    }

    @PostMapping("/resetar-senha")
    public ResponseEntity<String> resetarSenhaUsuario(
                                                @RequestParam String novaSenha,
                                                @RequestParam String token) {

        usuarioUseCases.resetarSenha(token, novaSenha);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Senha alterada com sucesso.");
    }



}
