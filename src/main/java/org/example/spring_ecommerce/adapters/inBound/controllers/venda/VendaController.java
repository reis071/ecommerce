package org.example.spring_ecommerce.adapters.inBound.controllers.venda;


import org.example.spring_ecommerce.application.services.vendas.VendaService;
import org.example.spring_ecommerce.domain.venda.Venda;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("vendas")
public class VendaController {


    private VendaService vendaService;


    @GetMapping
    public ResponseEntity<List<Venda>> pegarTodasVendas(Authentication authentication) {
        List<Venda> vendas = vendaService.todasAsVendas();
        return ResponseEntity.status(HttpStatus.OK).body(vendas);
    }



    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteVenda(@PathVariable Long id) {
        try {
            vendaService.deletarVenda(id);
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
            Venda vendaAtualizada = vendaService.atualizarVenda(id, produtoNome, quantidade, usuarioId);
            return ResponseEntity.ok(vendaAtualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/relatorioPorData")
    public ResponseEntity<String> getVendasByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        String vendas = vendaService.vendasPorDia(data);
        return ResponseEntity.status(HttpStatus.OK).body(vendas);
    }

    @GetMapping("/relatorioPorMesAno")
    public ResponseEntity<String> getVendasByMonth(@RequestParam int ano, @RequestParam int mes) {
        String vendas = vendaService.vendasPorMes(ano, mes);
        return ResponseEntity.status(HttpStatus.OK).body(vendas);
    }

    @GetMapping("/relatorioPorSemanaAtual")
    public ResponseEntity<String> getVendasThisWeek() {
        String vendas = vendaService.vendasPorSemanaAtual();
        return ResponseEntity.status(HttpStatus.OK).body(vendas);
    }

}
