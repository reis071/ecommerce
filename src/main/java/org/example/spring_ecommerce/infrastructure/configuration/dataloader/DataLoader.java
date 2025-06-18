package org.example.spring_ecommerce.infrastructure.configuration.dataloader;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.application.useCases.grupo.GrupoUseCases;
import org.example.spring_ecommerce.application.useCases.produto.ProdutoUseCases;
import org.example.spring_ecommerce.application.useCases.usuario.UsuarioUseCases;
import org.example.spring_ecommerce.domain.grupo.Grupo;
import org.example.spring_ecommerce.domain.produto.Produto;
import org.example.spring_ecommerce.domain.usuario.Usuario;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Random;

@RequiredArgsConstructor
@Component
public class DataLoader {

    private final ProdutoUseCases produtoUseCases;
    private final GrupoUseCases grupoUseCases;
    private final UsuarioUseCases usuarioUseCases;
    @Bean
    public ApplicationRunner initUsuario() {
        Random random = new Random();

        return args -> {
            if(grupoUseCases.todosGrupos().isEmpty()) {

            grupoUseCases.salvarGrupo(new Grupo("ADMIN"));
            grupoUseCases.salvarGrupo(new Grupo("USER"));

            usuarioUseCases.salvar(new Usuario("teste","teste@gmail.com","1234"));

            grupoUseCases.addGrupoAoUsuario("ADMIN", "teste@gmail.com");

            }
            if(produtoUseCases.listarTodosOsProdutos().isEmpty()) {
                produtoUseCases.registrarProduto(new Produto("Notebook Dell", "Notebook de alta performance", "Eletrônicos", 4500.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new Produto("Smartphone Samsung", "Celular com câmera de 64MP", "Eletrônicos", 2500.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new Produto("Teclado Mecânico", "Teclado gamer com switches mecânicos", "Acessórios", 350.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new Produto("Mouse Gamer", "Mouse com 16000 DPI", "Acessórios", 150.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new Produto("Monitor 27 polegadas", "Monitor Full HD", "Eletrônicos", 1200.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new Produto("Smartwatch Xiaomi", "Relógio inteligente com várias funcionalidades", "Eletrônicos", 800.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new Produto("Cadeira Gamer", "Cadeira ergonômica com ajuste de altura", "Móveis", 950.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new Produto("Caixa de Som Bluetooth", "Som portátil com alta qualidade", "Eletrônicos", 250.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new Produto("HD Externo 1TB", "HD portátil para armazenamento", "Eletrônicos", 400.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new Produto("SSD 500GB", "Armazenamento rápido", "Eletrônicos", 350.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new Produto("Tablet Apple", "iPad de última geração", "Eletrônicos", 3500.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new Produto("Roteador Wi-Fi", "Roteador com sinal de longo alcance", "Eletrônicos", 200.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new Produto("Fone de Ouvido Bluetooth", "Fone sem fio com cancelamento de ruído", "Acessórios", 300.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new Produto("Câmera DSLR", "Câmera profissional de alta qualidade", "Eletrônicos", 5000.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new Produto("Smart TV 55 polegadas", "TV 4K com inteligência artificial", "Eletrônicos", 4500.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new Produto("Air Fryer", "Fritadeira elétrica sem óleo", "Eletrodomésticos", 450.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new Produto("Geladeira Brastemp", "Geladeira Frost Free", "Eletrodomésticos", 3200.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new Produto("Fogão 4 bocas", "Fogão com acendimento automático", "Eletrodomésticos", 800.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new Produto("Máquina de Lavar", "Lava e seca roupas 12kg", "Eletrodomésticos", 2800.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new Produto("Ventilador Arno", "Ventilador de alta potência", "Eletrodomésticos", 200.0, random.nextInt(100)));
            }
            };

    }


}

