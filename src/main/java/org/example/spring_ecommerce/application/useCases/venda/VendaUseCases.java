package org.example.spring_ecommerce.application.useCases.venda;

import org.example.spring_ecommerce.domain.venda.Venda;

import java.time.LocalDate;
import java.util.List;

public interface VendaUseCases {

    List<Venda> todasAsVendas();

    Venda atualizarVenda(Long id, String produtoNome, Integer quantidade, Long usuarioId);

    void deletarVenda(Long id);

    String vendasPorDia(LocalDate date);

    String vendasPorMes(int ano, int mes);

    String vendasPorSemanaAtual();
}
