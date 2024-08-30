package org.example.spring_ecommerce.domain.controllers;

import lombok.RequiredArgsConstructor;

import org.example.spring_ecommerce.configuration.errorGlobal.advices.expectionExclusives.UsuarioNaoPodeCriarAdmin;
import org.example.spring_ecommerce.domain.controllers.dto.CredenciaisDto;
import org.example.spring_ecommerce.domain.controllers.dto.TokenDto;
import org.example.spring_ecommerce.domain.controllers.dto.UsuarioDto;
import org.example.spring_ecommerce.domain.entities.usuario.Usuario;
import org.example.spring_ecommerce.domain.security.jwt.JwtService;
import org.example.spring_ecommerce.domain.services.UsuarioService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final JwtService jwtService;

    @PostMapping(path = "/add")
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody UsuarioDto body){
        if (body.getPermissoes().contains("ADMIN")) {
            throw new UsuarioNaoPodeCriarAdmin("Usuario não pode criar administrador");
        }
            Usuario usuarioSalvo = usuarioService.salvar(body.getUsuario(), body.getPermissoes());
            return ResponseEntity.ok(usuarioSalvo);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "/add/adm")
    public ResponseEntity<Usuario> salvarAdm(@RequestBody UsuarioDto body){
        Usuario usuarioSalvo = usuarioService.salvar(body.getUsuario(), body.getPermissoes());
        return ResponseEntity.ok(usuarioSalvo);
    }

    @GetMapping(path = "/todos")
    public ResponseEntity<List<Usuario>> listarTodos(){
        return ResponseEntity.ok().body(usuarioService.obterTodosUsuarios());
    }

    @PostMapping("/autenticar")
    public TokenDto autenticar(@RequestBody CredenciaisDto credenciais){
            Usuario usuario = Usuario.builder()
                    .email(credenciais.getEmail())
                    .senha(credenciais.getSenha()).build();
            UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDto(usuario.getEmail(), token);
 }

    @PostMapping("/resetar-senha-request")
    public ResponseEntity<String> requestPasswordReset(@RequestParam String email) {
        usuarioService.sendPasswordResetEmail(email);
        return ResponseEntity.ok("Link de reset de senha enviado para o email.");
    }

    @PostMapping("/resetar-senha")
    public ResponseEntity<String> resetPassword(@RequestParam String token,
                                                @RequestParam String newPassword) {
        usuarioService.resetPassword(token, newPassword);
        return ResponseEntity.ok("Senha alterada com sucesso.");
    }
}
