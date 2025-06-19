package org.example.spring_ecommerce.adapters.inBound.controllers.venda;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.application.useCases.venda.VendaUseCases;
import org.example.spring_ecommerce.domain.venda.Venda;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Venda", description = "Endpoint para vendas")
@RequiredArgsConstructor
@RestController
@RequestMapping("vendas")
public class VendaController {

    private final VendaUseCases vendaUseCases;

    @Operation(summary = "Retornar todas as vendas", description = "Endpoint para retornar todas as vendas")
    @ApiResponse( responseCode = "200", description = "Vendas encontradas com sucesso",
            content = @Content(schema = @Schema(implementation = Venda.class)))
    @GetMapping
    public ResponseEntity<List<Venda>> pegarTodasVendas() {
        List<Venda> vendas = vendaUseCases.todasAsVendas();
        return ResponseEntity.status(HttpStatus.OK).body(vendas);
    }


    @Operation(summary = "Deletar uma venda", description = "Endpoint para deletar uma venda pelo id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204", description = "Venda deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Venda nao encontrada")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenda(@PathVariable Long id) {
        try {
            vendaUseCases.deletarVenda(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Atualizar uma venda", description = "Endpoint para atualizar uma venda pelo id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Venda atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Venda nao encontrada")
    })
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

    @Operation(summary = "Retornar vendas por data", description = "Endpoint para retornar vendas por data")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Vendas encontradas com sucesso"),
            @ApiResponse(responseCode = "404", description = "Vendas nao encontradas")
    })
    @GetMapping("/relatorio-por-data")
    public ResponseEntity<String> getVendasByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        String vendas = vendaUseCases.vendasPorDia(data);
        return ResponseEntity.status(HttpStatus.OK).body(vendas);
    }

    @Operation(summary = "Retornar vendas por mes", description = "Endpoint para retornar vendas por mes")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Vendas encontradas com sucesso"),
            @ApiResponse(responseCode = "404", description = "Vendas nao encontradas")
    })
    @GetMapping("/relatorio-por-mes")
    public ResponseEntity<String> getVendasByMonth(@RequestParam int ano, @RequestParam int mes) {
        String vendas = vendaUseCases.vendasPorMes(ano, mes);
        return ResponseEntity.status(HttpStatus.OK).body(vendas);
    }

    @Operation(summary = "Retornar vendas por semana", description = "Endpoint para retornar vendas por semana")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Vendas encontradas com sucesso"),
            @ApiResponse(responseCode = "404", description = "Vendas nao encontradas")
    })
    @GetMapping("/relatorio-por-semana")
    public ResponseEntity<String> getVendasThisWeek() {
        String vendas = vendaUseCases.vendasPorSemanaAtual();
        return ResponseEntity.status(HttpStatus.OK).body(vendas);
    }

}
