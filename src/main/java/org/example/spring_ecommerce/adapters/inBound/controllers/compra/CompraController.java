package org.example.spring_ecommerce.adapters.inBound.controllers.compra;

import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.application.useCases.carrinho.CarrinhoUseCases;
import org.example.spring_ecommerce.domain.carrinho.Carrinho;
import org.example.spring_ecommerce.domain.venda.Venda;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/compras")
public class CompraController {

    private final CarrinhoUseCases carrinhoUseCases;

    @PostMapping("comprar-direto")
    public ResponseEntity<Venda> comprarProduto(@RequestParam String nomeProd, @RequestParam int quantidade) {
        Venda venda = carrinhoUseCases.compra(nomeProd, quantidade);
        return ResponseEntity.ok(venda);
    }

    // Endpoint para adicionar produto ao carrinho
    @PostMapping("adicionar-carrinho")
    public ResponseEntity<Carrinho> adicionarAoCarrinho(@RequestParam String nomeProd, @RequestParam int quantidade) {
        Carrinho carrinho = carrinhoUseCases.adicionarAoCarrinho(nomeProd, quantidade);
        return ResponseEntity.status(HttpStatus.CREATED).body(carrinho);
    }

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
