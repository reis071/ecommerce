package org.example.spring_ecommerce.application.services.vendas;

import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.repositories.itemVenda.ItemVendaImpl;
import org.example.spring_ecommerce.adapters.outBound.repositories.produto.ProdutoImpl;
import org.example.spring_ecommerce.adapters.outBound.repositories.venda.VendaImpl;
import org.example.spring_ecommerce.application.useCases.venda.VendaUseCases;
import org.example.spring_ecommerce.domain.itemVenda.ItemVenda;
import org.example.spring_ecommerce.domain.produto.Produto;
import org.example.spring_ecommerce.domain.venda.Venda;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class VendaService implements VendaUseCases {

    private final ProdutoImpl produtoImpl;
    private final VendaImpl vendaImpl;
    private final ItemVendaImpl itemVendaImpl;


    //retorna todas as vendas
    public List<Venda> todasAsVendas() {
        return  vendaImpl.todasAsVendas();
    }

    //atualiza uma venda
    @CacheEvict(value = "vendaCache", allEntries = true)
    public Venda atualizarVenda(Long id, String produtoNome, Integer quantidade, Long usuarioId) {
        Venda venda = vendaImpl.buscaPorId(id);

        Produto produto = produtoImpl.procurarProdutoPorNome(produtoNome);

        if (quantidade == null || quantidade <= 0 || quantidade > produto.getEstoque()) {
            throw new IllegalArgumentException("Quantidade inválida ou estoque insuficiente");
        }

        venda.setDataVenda(LocalDateTime.now());
        venda.setValorTotal(quantidade * produto.getPreco());


        ItemVenda itemVendaEntityJPA = venda.getItensVenda().stream()
                .filter(item -> item.getProduto().equals(produto))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado na venda"));

        itemVendaEntityJPA.setQuantidade(quantidade);

        produto.setEstoque(produto.getEstoque() - quantidade);

        produtoImpl.salvar(produto);
        itemVendaImpl.salvar(itemVendaEntityJPA);

        return vendaImpl.salva(venda);
    }

    //relatorio vendas por data

    public String vendasPorDia(LocalDate date) {
        LocalDateTime dataDeInicio = date.atStartOfDay();
        LocalDateTime dataFinal = dataDeInicio.plusDays(1).minusNanos(1);

        List<Venda> vendaRel = vendaImpl.buscaPorDataVendaBetween(dataDeInicio, dataFinal);

        int totalVendas = vendaRel.size();
        double rendaTotal = 0.0;

        for (Venda venda : vendaRel) {
            rendaTotal += venda.getValorTotal();
        }
        return  "{total vendas: " + totalVendas  +", renda total: " + rendaTotal + "}";
    }

    //relatorio vendas por mes

    public String vendasPorMes(int year, int month) {

        LocalDateTime startOfMonth = LocalDate.of(year, month, 1).atStartOfDay();
        LocalDateTime endOfMonth = startOfMonth.plusMonths(1).minusNanos(1);
        List<Venda> vendaRel =  vendaImpl.buscaPorDataVendaBetween(startOfMonth, endOfMonth);

        int totalVendas = vendaRel.size();
        double rendaTotal = 0.0;

        for (Venda venda : vendaRel) {
            rendaTotal += venda.getValorTotal();
        }
        return  "{total vendas: " + totalVendas  +", renda total: " + rendaTotal + "}";
    }

    // relatorio venda por semana
    public String vendasPorSemanaAtual() {
        LocalDate now = LocalDate.now();
        LocalDate startOfWeek = now.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = now.with(DayOfWeek.SUNDAY);

        LocalDateTime startOfWeekDateTime = startOfWeek.atStartOfDay();
        LocalDateTime endOfWeekDateTime = endOfWeek.atTime(LocalTime.MAX);

        List<Venda> vendaRel = vendaImpl.buscaPorDataVendaBetween(startOfWeekDateTime, endOfWeekDateTime).stream()
                .filter(venda -> {
                    LocalDate vendaDate = venda.getDataVenda().toLocalDate();
                    return !vendaDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) && !vendaDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
                })
                .toList();

        int totalVendas = vendaRel.size();
        double rendaTotal = 0.0;

        for (Venda venda : vendaRel) {
            rendaTotal += venda.getValorTotal();
        }

        return  "{total vendas: " + totalVendas  +", renda total: " + rendaTotal + "}";
    }

    //deletar venda
    @CacheEvict(value = "vendaCache", allEntries = true)
    public void deletarVenda(Long id) {
        vendaImpl.delete(id);
    }
}
