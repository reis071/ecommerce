package org.example.spring_ecommerce.adapters.inBound.controllers.compra;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.application.useCases.carrinho.CarrinhoUseCases;
import org.example.spring_ecommerce.domain.carrinho.Carrinho;
import org.example.spring_ecommerce.domain.venda.Venda;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Compra", description = "Endpoints para realizar compras")
@RequiredArgsConstructor
@RestController
@RequestMapping("/compras")
public class CompraController {

    private final CarrinhoUseCases carrinhoUseCases;

    @Operation(summary = "Comprar um produto", description = "Endpoint para comprar um produto")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Produto comprado com sucesso", content = @Content(schema = @Schema(implementation = Venda.class))),
            @ApiResponse( responseCode = "404", description = "Produto nao encontrado")
    })
    @PostMapping("comprar-direto")
    public ResponseEntity<Venda> comprarProduto(@RequestParam String nomeProd, @RequestParam int quantidade) {
        Venda venda = carrinhoUseCases.compra(nomeProd, quantidade);
        return ResponseEntity.ok(venda);
    }

    @Operation(summary = "Adicionar um produto ao carrinho", description = "Endpoint para adicionar um produto ao carrinho")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "201", description = "Produto adicionado ao carrinho com sucesso", content = @Content(schema = @Schema(implementation = Carrinho.class))),
            @ApiResponse( responseCode = "404", description = "Produto nao encontrado")
    })
    // Endpoint para adicionar produto ao carrinho
    @PostMapping("adicionar-carrinho")
    public ResponseEntity<Carrinho> adicionarAoCarrinho(@RequestParam String nomeProd, @RequestParam int quantidade) {
        Carrinho carrinho = carrinhoUseCases.adicionarAoCarrinho(nomeProd, quantidade);
        return ResponseEntity.status(HttpStatus.CREATED).body(carrinho);
    }

    @Operation(summary = "Finalizar compra", description = "Endpoint para finalizar a compra de todos os itens do carrinho")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Compra finalizada com sucesso"),
            @ApiResponse( responseCode = "404", description = "Compra nao finalizada")
    })
    // Endpoint para finalizar a compra de todos os itens do carrinho
    @PostMapping("finalizar-compra")
    public ResponseEntity<String> finalizarCompra() {
        carrinhoUseCases.finalizarCompra();
        return ResponseEntity.status(HttpStatus.OK).body("compra feita com sucesso!");
    }

//    @DeleteMapping("remover-produto-carrinho")
//    public ResponseEntity<Void> removerProdutoDoCarrinhoPorNome(@RequestParam String nomeProduto) {
//        carrinhoUseCases.removerProdutoDoCarrinhoPorNome(nomeProduto);
//        return ResponseEntity.noContent().build();
//    }

//    @GetMapping("valor-total-carrinho")
//    public ResponseEntity<String> valorTotalCarrinho() {
//        return  ResponseEntity.status(HttpStatus.ACCEPTED).body(carrinhoUseCases.precoTotalCarrinho());
//    }

}
