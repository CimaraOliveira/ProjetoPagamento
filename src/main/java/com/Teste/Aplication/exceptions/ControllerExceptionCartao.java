package com.Teste.Aplication.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.Teste.Aplication.model.Cartao;


@ControllerAdvice
public class ControllerExceptionCartao {

	@ExceptionHandler(CartaoException.class)
	public ResponseEntity<Message<Cartao>> cartaoInvalidoException(CartaoException ex) {
		Message<Cartao> mensagem = new Message<>("Cartão de crédito inválido", null);
		return new ResponseEntity<Message<Cartao>>(mensagem, HttpStatus.BAD_REQUEST);
	}
}
