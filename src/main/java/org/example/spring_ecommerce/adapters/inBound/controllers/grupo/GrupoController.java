package org.example.spring_ecommerce.adapters.inBound.controllers.grupo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.adapters.inBound.dtos.grupo.GrupoDTOResponse;
import org.example.spring_ecommerce.application.dtos.grupo.GrupoDTORequest;
import org.example.spring_ecommerce.application.useCases.grupo.GrupoUseCases;
import org.example.spring_ecommerce.domain.grupo.Grupo;
import org.example.spring_ecommerce.application.services.grupo.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Grupo", description = "Endpoint para gerenciar grupos")
@RequiredArgsConstructor
@RestController
@RequestMapping("/grupos")
public class GrupoController {

    private final GrupoUseCases grupoUseCases;

    @Operation(summary = "Registra um novo grupo", description = "Endpoint para registrar um novo grupo")
    @ApiResponse( responseCode = "201", description = "Grupo registrado com sucesso",
            content = @Content(schema = @Schema(implementation = Grupo.class)))
    @PostMapping(path = "/registrar-grupo")
    public ResponseEntity<GrupoDTOResponse> cadastrarGrupo(@RequestBody GrupoDTORequest request){
        Grupo grupoDomain = grupoUseCases.salvarGrupo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new GrupoDTOResponse(grupoDomain.getNome()));
    }

    @Operation(summary = "Retorna todos os grupos", description = "Endpoint para retornar todos os grupos")
    @ApiResponse( responseCode = "200", description = "Grupos encontrados com sucesso",
            content = @Content(schema = @Schema(implementation = Grupo.class)))
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
