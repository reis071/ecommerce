package org.example.spring_ecommerce.adapters.inBound.controllers.usuario;

import lombok.RequiredArgsConstructor;

import org.example.spring_ecommerce.infrastructure.configuration.advices.exceptionExclusives.UsuarioNaoPodeCriarAdmin;

import org.example.spring_ecommerce.adapters.inBound.dtos.UsuarioDto;
import org.example.spring_ecommerce.domain.usuario.Usuario;
import org.example.spring_ecommerce.infrastructure.configuration.security.jwt.JwtService;
import org.example.spring_ecommerce.application.services.usuario.UsuarioService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping(path = "/cadastrar-usuario")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        usuarioService.salvar(usuario);
        return ResponseEntity.ok(usuario);
    }

//    @PostMapping("/solicitar-nova-senha")
//    public ResponseEntity<String> requestPasswordReset(@RequestParam String email) {
//        usuarioService.enviarSolicitacaoDeResetarSenha(email);
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Link de reset de senha enviado para o email.");
//    }
//
//    @PostMapping("/resetar-senha")
//    public ResponseEntity<String> resetarSenhaUsuario(@RequestParam String token,
//                                                @RequestParam String novaSenha) {
//        usuarioService.resetarSenha(token, novaSenha);
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Senha alterada com sucesso.");
//    }

    @PutMapping("depositar")
    public ResponseEntity<String> depositar(@RequestParam double valor) {
        usuarioService.depositar(valor);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Depositado com sucesso.");
    }

}
