package org.example.spring_ecommerce.infrastructure.configuration.dataloader;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.spring_ecommerce.application.dtos.grupo.GrupoDTORequest;
import org.example.spring_ecommerce.application.dtos.produto.ProdutoDTORequest;
import org.example.spring_ecommerce.application.dtos.usuario.UsuarioDTORequest;
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

            grupoUseCases.salvarGrupo(new GrupoDTORequest("ADMIN"));
            grupoUseCases.salvarGrupo(new GrupoDTORequest("USER"));

            usuarioUseCases.salvar(new UsuarioDTORequest("teste","teste@gmail.com","1234"));

            grupoUseCases.addGrupoAoUsuario("ADMIN", "teste@gmail.com");

            }
            if(produtoUseCases.listarTodosOsProdutos().isEmpty()) {
                produtoUseCases.registrarProduto(new ProdutoDTORequest("Notebook Dell", "Notebook de alta performance", "Eletrônicos", 4500.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new ProdutoDTORequest("Smartphone Samsung", "Celular com câmera de 64MP", "Eletrônicos", 2500.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new ProdutoDTORequest("Teclado Mecânico", "Teclado gamer com switches mecânicos", "Acessórios", 350.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new ProdutoDTORequest("Mouse Gamer", "Mouse com 16000 DPI", "Acessórios", 150.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new ProdutoDTORequest("Monitor 27 polegadas", "Monitor Full HD", "Eletrônicos", 1200.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new ProdutoDTORequest("Smartwatch Xiaomi", "Relógio inteligente com várias funcionalidades", "Eletrônicos", 800.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new ProdutoDTORequest("Cadeira Gamer", "Cadeira ergonômica com ajuste de altura", "Móveis", 950.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new ProdutoDTORequest("Caixa de Som Bluetooth", "Som portátil com alta qualidade", "Eletrônicos", 250.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new ProdutoDTORequest("HD Externo 1TB", "HD portátil para armazenamento", "Eletrônicos", 400.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new ProdutoDTORequest("SSD 500GB", "Armazenamento rápido", "Eletrônicos", 350.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new ProdutoDTORequest("Tablet Apple", "iPad de última geração", "Eletrônicos", 3500.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new ProdutoDTORequest("Roteador Wi-Fi", "Roteador com sinal de longo alcance", "Eletrônicos", 200.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new ProdutoDTORequest("Fone de Ouvido Bluetooth", "Fone sem fio com cancelamento de ruído", "Acessórios", 300.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new ProdutoDTORequest("Câmera DSLR", "Câmera profissional de alta qualidade", "Eletrônicos", 5000.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new ProdutoDTORequest("Smart TV 55 polegadas", "TV 4K com inteligência artificial", "Eletrônicos", 4500.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new ProdutoDTORequest("Air Fryer", "Fritadeira elétrica sem óleo", "Eletrodomésticos", 450.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new ProdutoDTORequest("Geladeira Brastemp", "Geladeira Frost Free", "Eletrodomésticos", 3200.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new ProdutoDTORequest("Fogão 4 bocas", "Fogão com acendimento automático", "Eletrodomésticos", 800.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new ProdutoDTORequest("Máquina de Lavar", "Lava e seca roupas 12kg", "Eletrodomésticos", 2800.0, random.nextInt(100)));
                produtoUseCases.registrarProduto(new ProdutoDTORequest("Ventilador Arno", "Ventilador de alta potência", "Eletrodomésticos", 200.0, random.nextInt(100)));
            }
            };

    }


}

