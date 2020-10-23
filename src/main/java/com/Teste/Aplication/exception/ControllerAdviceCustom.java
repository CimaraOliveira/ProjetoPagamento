package com.Teste.Aplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.Teste.Aplication.model.CartaoCredito;



@ControllerAdvice
public class ControllerAdviceCustom {

	@ExceptionHandler(CartaoException.class)
	public ResponseEntity<Message<CartaoCredito>> cartaoInvalidoException(CartaoException ex) {
		Message<CartaoCredito> mensagem = new Message<>("Cartão de crédito inválido", null);
		return new ResponseEntity<Message<CartaoCredito>>(mensagem, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CartaoNuloException.class)
	public ResponseEntity<Message<Void>> cartaoNuloException() {
		Message<Void> mensagem = new Message<>("Informe os dados do cartão", null);
		return new ResponseEntity<Message<Void>>(mensagem, HttpStatus.BAD_REQUEST);
	}
}
