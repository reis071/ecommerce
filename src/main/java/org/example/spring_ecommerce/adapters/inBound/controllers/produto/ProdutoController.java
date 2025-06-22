package org.example.spring_ecommerce.adapters.inBound.controllers.produto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.application.useCases.produto.ProdutoUseCases;
import org.example.spring_ecommerce.domain.produto.Produto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Produtos", description = "Endpoints para gerenciamento de produtos")
@RequiredArgsConstructor
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoUseCases produtoUseCases;

    @Operation(summary = "Registra um novo produto", description = "Endpoint para registrar um novo produto")
    @ApiResponse( responseCode = "201", description = "Produto registrado com sucesso",
    content = @Content(schema = @Schema(implementation = Produto.class)))
    @PostMapping("/registrar-produto")
    public ResponseEntity<Produto> addProduto(@RequestBody Produto produto) {
        Produto produtoDomain = produtoUseCases.registrarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoDomain);
    }

    @Operation(summary = "Procura um produto pelo nome", description = "Endpoint para procurar um produto pelo nome")
    @ApiResponse( responseCode = "200", description = "Produto encontrado com sucesso",
            content = @Content(schema = @Schema(implementation = Produto.class)))
    @ApiResponse( responseCode = "404", description = "Produto nao encontrado")
    @GetMapping("/procurar-produto")
    public ResponseEntity<Produto> getProduto(@RequestParam String nomeProduto) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoUseCases.procurarProdutoPorNome(nomeProduto));
    }

    @Operation(summary = "Retornar todos os produtos", description = "Endpoint para retornar todos os produtos")
    @ApiResponse( responseCode = "200", description = "Produtos encontrados com sucesso",
            content = @Content(schema = @Schema(implementation = Produto.class)))
    @GetMapping("/todos-produtos")
    public ResponseEntity< List<Produto>> todosOsProdutos() {
        return ResponseEntity.status(HttpStatus.OK).body(produtoUseCases.listarTodosOsProdutos());
    }

    @Operation(summary = "Deleta um produto pelo id", description = "Endpoint para deletar um produto pelo id")
    @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Produto nao encontrado")
    @DeleteMapping("/deletar-produto")
    public ResponseEntity<Void> deleteProduto(@RequestParam Long id) {
        produtoUseCases.removerProduto(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualiza um produto", description = "Endpoint para atualizar um produto pelo id")
    @ApiResponse( responseCode = "200", description = "Produto atualizado com sucesso",
            content = @Content(schema = @Schema(implementation = Produto.class)))
    @PatchMapping("/atualizar-produto")
    public ResponseEntity<Produto> updateProduto( @RequestBody Produto produto) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoUseCases.atualizarProduto(produto));
    }
}

