package com.commitOuDesiste.pixverificador.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratamentoDeErros {

  @ExceptionHandler(UsuarioNaoEncontradoException.class)
  public ResponseEntity<String> handleUsuarioNaoEncontrado(UsuarioNaoEncontradoException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }
}
