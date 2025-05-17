package org.example.spring_ecommerce.adapters.inBound.controllers.produto;

import org.example.spring_ecommerce.adapters.outBound.entities.produto.ProdutoEntityJPA;
import org.example.spring_ecommerce.application.services.produto.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProdutoEntityJPA> addProduto(@RequestBody ProdutoEntityJPA produto) {
        ProdutoEntityJPA novoProduto = produtoService.save(produto);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    @GetMapping("/procurarProduto")
    public ResponseEntity<ProdutoEntityJPA> getProduto(@RequestParam String nomeProduto) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.procurarProdutoPorNome(nomeProduto));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoEntityJPA>> getAllProdutos() {

        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findAll());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProdutoEntityJPA> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoEntityJPA produtoAtualizado) {
        ProdutoEntityJPA produtoAtualizadoRetorno = produtoService.atualizarProduto(id, produtoAtualizado);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(produtoAtualizadoRetorno);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deletarProduto")
    public ResponseEntity<Void> deleteProduto(@RequestParam String nomeProduto) {
        produtoService.deleteById(nomeProduto);
        return ResponseEntity.noContent().build();
    }

}

