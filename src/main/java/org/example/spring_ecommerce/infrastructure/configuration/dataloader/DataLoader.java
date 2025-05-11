package org.example.spring_ecommerce.infrastructure.configuration.dataloader;


import lombok.AllArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.repositories.grupo.GrupoDAO;
import org.example.spring_ecommerce.adapters.outBound.entities.produto.ProdutoEntityJPA;
import org.example.spring_ecommerce.domain.grupo.Grupo;
import org.example.spring_ecommerce.adapters.outBound.repositories.produto.ProdutoRepositoryJPA;
import org.example.spring_ecommerce.application.services.produto.ProdutoService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Random;

@AllArgsConstructor
@Component
public class DataLoader {

    private ProdutoRepositoryJPA produtoRepository;
    @Bean
    public ApplicationRunner initUsuario(GrupoDAO grupoDAO, ProdutoService produtoService ) {
        Random random = new Random();

        return args -> {
            if(grupoDAO.findAll().isEmpty()) {

            grupoDAO.salvar(new Grupo("ADMIN"));
            grupoDAO.salvar(new Grupo("USER"));
            }
            if(produtoRepository.findAll().isEmpty()) {
                produtoService.save(new ProdutoEntityJPA("Notebook Dell", "Notebook de alta performance", "Eletrônicos", 4500.0, random.nextInt(100)));
                produtoService.save(new ProdutoEntityJPA("Smartphone Samsung", "Celular com câmera de 64MP", "Eletrônicos", 2500.0, random.nextInt(100)));
                produtoService.save(new ProdutoEntityJPA("Teclado Mecânico", "Teclado gamer com switches mecânicos", "Acessórios", 350.0, random.nextInt(100)));
                produtoService.save(new ProdutoEntityJPA("Mouse Gamer", "Mouse com 16000 DPI", "Acessórios", 150.0, random.nextInt(100)));
                produtoService.save(new ProdutoEntityJPA("Monitor 27 polegadas", "Monitor Full HD", "Eletrônicos", 1200.0, random.nextInt(100)));
                produtoService.save(new ProdutoEntityJPA("Smartwatch Xiaomi", "Relógio inteligente com várias funcionalidades", "Eletrônicos", 800.0, random.nextInt(100)));
                produtoService.save(new ProdutoEntityJPA("Cadeira Gamer", "Cadeira ergonômica com ajuste de altura", "Móveis", 950.0, random.nextInt(100)));
                produtoService.save(new ProdutoEntityJPA("Caixa de Som Bluetooth", "Som portátil com alta qualidade", "Eletrônicos", 250.0, random.nextInt(100)));
                produtoService.save(new ProdutoEntityJPA("HD Externo 1TB", "HD portátil para armazenamento", "Eletrônicos", 400.0, random.nextInt(100)));
                produtoService.save(new ProdutoEntityJPA("SSD 500GB", "Armazenamento rápido", "Eletrônicos", 350.0, random.nextInt(100)));
                produtoService.save(new ProdutoEntityJPA("Tablet Apple", "iPad de última geração", "Eletrônicos", 3500.0, random.nextInt(100)));
                produtoService.save(new ProdutoEntityJPA("Roteador Wi-Fi", "Roteador com sinal de longo alcance", "Eletrônicos", 200.0, random.nextInt(100)));
                produtoService.save(new ProdutoEntityJPA("Fone de Ouvido Bluetooth", "Fone sem fio com cancelamento de ruído", "Acessórios", 300.0, random.nextInt(100)));
                produtoService.save(new ProdutoEntityJPA("Câmera DSLR", "Câmera profissional de alta qualidade", "Eletrônicos", 5000.0, random.nextInt(100)));
                produtoService.save(new ProdutoEntityJPA("Smart TV 55 polegadas", "TV 4K com inteligência artificial", "Eletrônicos", 4500.0, random.nextInt(100)));
                produtoService.save(new ProdutoEntityJPA("Air Fryer", "Fritadeira elétrica sem óleo", "Eletrodomésticos", 450.0, random.nextInt(100)));
                produtoService.save(new ProdutoEntityJPA("Geladeira Brastemp", "Geladeira Frost Free", "Eletrodomésticos", 3200.0, random.nextInt(100)));
                produtoService.save(new ProdutoEntityJPA("Fogão 4 bocas", "Fogão com acendimento automático", "Eletrodomésticos", 800.0, random.nextInt(100)));
                produtoService.save(new ProdutoEntityJPA("Máquina de Lavar", "Lava e seca roupas 12kg", "Eletrodomésticos", 2800.0, random.nextInt(100)));
                produtoService.save(new ProdutoEntityJPA("Ventilador Arno", "Ventilador de alta potência", "Eletrodomésticos", 200.0, random.nextInt(100)));
            }
            };

    }


}

