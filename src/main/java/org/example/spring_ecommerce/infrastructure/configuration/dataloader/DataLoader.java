package org.example.spring_ecommerce.infrastructure.configuration.dataloader;


import lombok.AllArgsConstructor;
import org.example.spring_ecommerce.adapters.outBound.repositories.grupo.GrupoImpl;
import org.example.spring_ecommerce.adapters.outBound.repositories.produto.ProdutoImpl;
import org.example.spring_ecommerce.domain.grupo.Grupo;
import org.example.spring_ecommerce.domain.produto.Produto;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Random;

@AllArgsConstructor
@Component
public class DataLoader {

    private ProdutoImpl produtoImpl;

    @Bean
    public ApplicationRunner initUsuario(GrupoImpl grupoImpl ) {
        Random random = new Random();

        return args -> {
            if(grupoImpl.findAll().isEmpty()) {

            grupoImpl.salvar(new Grupo("ADMIN"));
            grupoImpl.salvar(new Grupo("USER"));
            }
            if(produtoImpl.todosOsProdutos().isEmpty()) {
                produtoImpl.salvar(new Produto("Notebook Dell", "Notebook de alta performance", "Eletrônicos", 4500.0, random.nextInt(100)));
                produtoImpl.salvar(new Produto("Smartphone Samsung", "Celular com câmera de 64MP", "Eletrônicos", 2500.0, random.nextInt(100)));
                produtoImpl.salvar(new Produto("Teclado Mecânico", "Teclado gamer com switches mecânicos", "Acessórios", 350.0, random.nextInt(100)));
                produtoImpl.salvar(new Produto("Mouse Gamer", "Mouse com 16000 DPI", "Acessórios", 150.0, random.nextInt(100)));
                produtoImpl.salvar(new Produto("Monitor 27 polegadas", "Monitor Full HD", "Eletrônicos", 1200.0, random.nextInt(100)));
                produtoImpl.salvar(new Produto("Smartwatch Xiaomi", "Relógio inteligente com várias funcionalidades", "Eletrônicos", 800.0, random.nextInt(100)));
                produtoImpl.salvar(new Produto("Cadeira Gamer", "Cadeira ergonômica com ajuste de altura", "Móveis", 950.0, random.nextInt(100)));
                produtoImpl.salvar(new Produto("Caixa de Som Bluetooth", "Som portátil com alta qualidade", "Eletrônicos", 250.0, random.nextInt(100)));
                produtoImpl.salvar(new Produto("HD Externo 1TB", "HD portátil para armazenamento", "Eletrônicos", 400.0, random.nextInt(100)));
                produtoImpl.salvar(new Produto("SSD 500GB", "Armazenamento rápido", "Eletrônicos", 350.0, random.nextInt(100)));
                produtoImpl.salvar(new Produto("Tablet Apple", "iPad de última geração", "Eletrônicos", 3500.0, random.nextInt(100)));
                produtoImpl.salvar(new Produto("Roteador Wi-Fi", "Roteador com sinal de longo alcance", "Eletrônicos", 200.0, random.nextInt(100)));
                produtoImpl.salvar(new Produto("Fone de Ouvido Bluetooth", "Fone sem fio com cancelamento de ruído", "Acessórios", 300.0, random.nextInt(100)));
                produtoImpl.salvar(new Produto("Câmera DSLR", "Câmera profissional de alta qualidade", "Eletrônicos", 5000.0, random.nextInt(100)));
                produtoImpl.salvar(new Produto("Smart TV 55 polegadas", "TV 4K com inteligência artificial", "Eletrônicos", 4500.0, random.nextInt(100)));
                produtoImpl.salvar(new Produto("Air Fryer", "Fritadeira elétrica sem óleo", "Eletrodomésticos", 450.0, random.nextInt(100)));
                produtoImpl.salvar(new Produto("Geladeira Brastemp", "Geladeira Frost Free", "Eletrodomésticos", 3200.0, random.nextInt(100)));
                produtoImpl.salvar(new Produto("Fogão 4 bocas", "Fogão com acendimento automático", "Eletrodomésticos", 800.0, random.nextInt(100)));
                produtoImpl.salvar(new Produto("Máquina de Lavar", "Lava e seca roupas 12kg", "Eletrodomésticos", 2800.0, random.nextInt(100)));
                produtoImpl.salvar(new Produto("Ventilador Arno", "Ventilador de alta potência", "Eletrodomésticos", 200.0, random.nextInt(100)));
            }
            };

    }


}

