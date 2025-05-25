package org.example.spring_ecommerce.domain.venda;

import java.time.LocalDateTime;
import java.util.List;

public interface VendaRepository {
    Venda  salva(Venda venda);
    List<Venda> todasAsVendas();

    Venda buscaPorId(Long id);

    List<Venda> buscaPorDataVendaBetween(LocalDateTime start, LocalDateTime end);

    void delete(Long id);
}
