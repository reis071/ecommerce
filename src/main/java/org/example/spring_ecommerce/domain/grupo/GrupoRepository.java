package org.example.spring_ecommerce.domain.grupo;

import java.util.List;

public interface GrupoRepository {
     Grupo salvar(Grupo grupo);

     List<Grupo> findAll();

     Grupo findByNome(String nome);



}
