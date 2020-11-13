package com.Teste.Aplication.exceptions;

import org.springframework.beans.factory.annotation.Autowired;

import com.Teste.Aplication.model.Cartao;

public class CartaoException extends RuntimeException {
	
	@Autowired
	private Cartao cartao;

	public CartaoException(Cartao cartao) {
		super();
		this.cartao = cartao;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}
	
	

}
