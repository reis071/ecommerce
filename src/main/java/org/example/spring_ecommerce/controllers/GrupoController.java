package org.example.spring_ecommerce.controllers;

import org.example.spring_ecommerce.model.usuario.Grupo;
import org.example.spring_ecommerce.services.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @PostMapping(path = "/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Grupo> addGrupo(@RequestBody Grupo grupo){
        return ResponseEntity.status(HttpStatus.CREATED).body(grupoService.save(grupo));
    }

    @GetMapping(path = "/todos")
    public ResponseEntity<List<Grupo>> getAll(){

        return ResponseEntity.status(HttpStatus.OK).body(grupoService.findAll());
    }
}
