package org.example.spring_ecommerce.adapters.inBound.controllers.produto;

import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.application.useCases.produto.ProdutoUseCases;
import org.example.spring_ecommerce.domain.produto.Produto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoUseCases produtoUseCases;

    @PostMapping("/registrar-produto")
    public ResponseEntity<Produto> addProduto(@RequestBody Produto produto) {
        Produto produtoDomain = produtoUseCases.registrarProduto(produto);

        return ResponseEntity.status(HttpStatus.CREATED).body(produtoDomain);
    }

    @GetMapping("/procurar-produto")
    public ResponseEntity<Produto> getProduto(@RequestParam String nomeProduto) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoUseCases.procurarProdutoPorNome(nomeProduto));
    }

    @GetMapping("/todos-produtos")
    public ResponseEntity< List<Produto>> todosOsProdutos() {

        return ResponseEntity.status(HttpStatus.OK).body(produtoUseCases.listarTodosOsProdutos());
    }

    @DeleteMapping("/deletarProduto")
    public ResponseEntity<Void> deleteProduto(@RequestParam Long id) {
        produtoUseCases.removerProduto(id);
        return ResponseEntity.noContent().build();
    }

}

