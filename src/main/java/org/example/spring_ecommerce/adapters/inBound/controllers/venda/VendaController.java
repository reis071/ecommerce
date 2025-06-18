package org.example.spring_ecommerce.adapters.inBound.controllers.venda;


import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.application.useCases.venda.VendaUseCases;
import org.example.spring_ecommerce.domain.venda.Venda;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("vendas")
public class VendaController {

    private final VendaUseCases vendaUseCases;

    @GetMapping
    public ResponseEntity<List<Venda>> pegarTodasVendas() {
        List<Venda> vendas = vendaUseCases.todasAsVendas();
        return ResponseEntity.status(HttpStatus.OK).body(vendas);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenda(@PathVariable Long id) {
        try {
            vendaUseCases.deletarVenda(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venda> updateVenda(@PathVariable Long id,
                                             @RequestParam String produtoNome,
                                             @RequestParam Integer quantidade,
                                             @RequestParam Long usuarioId) {
        try {
            Venda vendaAtualizada = vendaUseCases.atualizarVenda(id, produtoNome, quantidade, usuarioId);
            return ResponseEntity.ok(vendaAtualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/relatorio-por-data")
    public ResponseEntity<String> getVendasByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        String vendas = vendaUseCases.vendasPorDia(data);
        return ResponseEntity.status(HttpStatus.OK).body(vendas);
    }

    @GetMapping("/relatorio-por-mes")
    public ResponseEntity<String> getVendasByMonth(@RequestParam int ano, @RequestParam int mes) {
        String vendas = vendaUseCases.vendasPorMes(ano, mes);
        return ResponseEntity.status(HttpStatus.OK).body(vendas);
    }

    @GetMapping("/relatorio-por-semana")
    public ResponseEntity<String> getVendasThisWeek() {
        String vendas = vendaUseCases.vendasPorSemanaAtual();
        return ResponseEntity.status(HttpStatus.OK).body(vendas);
    }

}
