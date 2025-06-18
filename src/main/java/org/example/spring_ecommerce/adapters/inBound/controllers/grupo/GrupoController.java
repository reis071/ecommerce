package org.example.spring_ecommerce.adapters.inBound.controllers.grupo;

import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.application.useCases.grupo.GrupoUseCases;
import org.example.spring_ecommerce.domain.grupo.Grupo;
import org.example.spring_ecommerce.application.services.grupo.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/grupos")
public class GrupoController {

    private final GrupoUseCases grupoUseCases;

    @PostMapping(path = "/registrar-grupo")
    public ResponseEntity<Grupo> cadastrarGrupo(@RequestBody Grupo grupo){
        return ResponseEntity.status(HttpStatus.CREATED).body(grupoUseCases.salvarGrupo(grupo));
    }

    @GetMapping(path = "/listar-grupos")
    public ResponseEntity<List<Grupo>> todosOsGrupos(){

        return ResponseEntity.status(HttpStatus.OK).body(grupoUseCases.todosGrupos());
    }

    @PostMapping(path = "/add-grupo-ao-usuario")
    public ResponseEntity<Grupo> addGrupoAoUsuario(@RequestParam String emailUsuario, @RequestParam String tipoGrupo){
        grupoUseCases.addGrupoAoUsuario(tipoGrupo, emailUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(grupoUseCases.procurarGrupoPorNome(tipoGrupo));
    }

}
