package org.example.spring_ecommerce.infrastructure.configuration.advices.exception.handler;

import org.example.spring_ecommerce.infrastructure.configuration.advices.exception.carrinho.CarrinhoException;
import org.example.spring_ecommerce.infrastructure.configuration.advices.exception.grupo.GrupoException;
import org.example.spring_ecommerce.infrastructure.configuration.advices.exception.itemCarrinho.ItemCarrinhoException;
import org.example.spring_ecommerce.infrastructure.configuration.advices.exception.itemVenda.ItemVendaException;
import org.example.spring_ecommerce.infrastructure.configuration.advices.exception.produto.ProdutoException;
import org.example.spring_ecommerce.infrastructure.configuration.advices.exception.usuario.UsuarioException;
import org.example.spring_ecommerce.infrastructure.configuration.advices.exception.venda.VendaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handlerexception(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(UsuarioException.class)
    public ResponseEntity<String> handlerUsuarioexception(UsuarioException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(ProdutoException.class)
    public ResponseEntity<String> handlerProdutoexception(ProdutoException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(GrupoException.class)
    public ResponseEntity<String> handlerGrupoexception(GrupoException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(CarrinhoException.class)
    public ResponseEntity<String> exception(CarrinhoException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(ItemVendaException.class)
    public ResponseEntity<String> handlerItemVendaexception(ItemVendaException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(ItemCarrinhoException.class)
    public ResponseEntity<String> handlerItemCarrinhoexception(ItemCarrinhoException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(VendaException.class)
    public ResponseEntity<String> handlerVendaexception(VendaException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }


}
