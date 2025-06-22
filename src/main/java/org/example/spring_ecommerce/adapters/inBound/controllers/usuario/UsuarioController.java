package org.example.spring_ecommerce.adapters.inBound.controllers.usuario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.example.spring_ecommerce.adapters.inBound.dtos.usuario.UsuarioDto;
import org.example.spring_ecommerce.application.useCases.usuario.UsuarioUseCases;
import org.example.spring_ecommerce.domain.usuario.Usuario;

import org.example.spring_ecommerce.infrastructure.configuration.security.AuthenticationUsuarioCustom;
import org.example.spring_ecommerce.infrastructure.configuration.security.jwt.JwtGeneratorFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@Tag(name = "Usuarios", description = "Endpoints para manipulação de usuários")
@RequiredArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioUseCases usuarioUseCases;
    private final AuthenticationUsuarioCustom autenticacao;
    private final JwtGeneratorFilter jwtGeneratorFilter;

    @Operation(summary = "Cadastrar um novo usuario")
    @ApiResponse( responseCode = "201", description = "Usuario cadastrado com sucesso",
            content = @Content(schema = @Schema(implementation = Usuario.class)))
    @PostMapping(path = "/cadastrar-usuario")
    public ResponseEntity<UsuarioDto> cadastrarUsuario(@RequestBody Usuario usuario) {
        Usuario domain = usuarioUseCases.salvar(usuario);

        return ResponseEntity.ok(new UsuarioDto(domain.getNome(), domain.getEmail()));
    }

    @Operation(summary = "Autenticar um usuario", description = "Endpoint para autenticar um usuario")
    @ApiResponse( responseCode = "200", description = "Usuario autenticado com sucesso",
            content = @Content(schema = @Schema(implementation = String.class)))
    @PostMapping(path = "/autenticar-usuario")
    private ResponseEntity<String> autenticar(@RequestBody HashMap<String, String> credencials) {
        String email = credencials.get("email");
        String senha = credencials.get("senha");

        Authentication authentication = autenticacao.authenticate(new UsernamePasswordAuthenticationToken(email, senha));

        String token = jwtGeneratorFilter.gerarToken(authentication);

        return ResponseEntity.ok().header("Authorization", token).body("Autenticado com sucesso");
    }

    @Operation(summary = "Solicitar resetar senha", description = "Endpoint para solicitar resetar senha")
    @ApiResponse( responseCode = "200", description = "Token de resetar senha enviado com sucesso",
            content = @Content(schema = @Schema(implementation = String.class)))
    @GetMapping("/solicitar-nova-senha")
    public ResponseEntity<String> solicitarResetarSenha(@RequestParam String email) {

        usuarioUseCases.enviarSolicitacaoDeResetarSenha(email);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("token de senha enviado para o email.");
    }

    @Operation(summary = "Resetar senha", description = "Endpoint para resetar senha")
    @ApiResponse( responseCode = "200", description = "Senha alterada com sucesso",
            content = @Content(schema = @Schema(implementation = String.class)))
    @PostMapping("/resetar-senha")
    public ResponseEntity<String> resetarSenhaUsuario(
                                                @RequestParam String novaSenha,
                                                @RequestParam String token) {

        usuarioUseCases.resetarSenha(token, novaSenha);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Senha alterada com sucesso.");
    }

}
