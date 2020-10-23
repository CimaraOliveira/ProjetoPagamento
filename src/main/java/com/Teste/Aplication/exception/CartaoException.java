package com.Teste.Aplication.exception;

import com.Teste.Aplication.model.CartaoCredito;

public class CartaoException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CartaoCredito cartao;

	public CartaoException(CartaoCredito cartao) {
		super();
		this.cartao = cartao;
	}

	public CartaoCredito getCartao() {
		return cartao;
	}

	public void setCartao(CartaoCredito cartao) {
		this.cartao = cartao;
	}

}
