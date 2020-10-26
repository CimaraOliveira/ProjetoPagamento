package com.Teste.Aplication.service;

import java.time.LocalDate;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Teste.Aplication.exception.CartaoException;
import com.Teste.Aplication.model.CartaoCredito;
import com.Teste.Aplication.repository.CartaoRepository;

@Service
public class CartaoService {

	@Autowired
	private CartaoRepository repository;
	
	public boolean validarCartao(CartaoCredito cartao) {

		if (!validarNumeroCartao(cartao.getNumero())) {
			throw new CartaoException(cartao);
		}
		/*if (!validarDataValidade(cartao.getDataValidade())) {
			throw new CartaoException(cartao);
		}*/
		if (!validarCvvCartao(cartao.getCvv())) {
			throw new CartaoException(cartao);
		}
		return true;
	}

	
	public boolean validarNumeroCartao(String numero) {
		Integer numString;
		int soma = 0;
		if (numero.length() <= 15) {
			for (int i = 0; i < numero.length(); i++) {
				numString = Integer.parseInt(numero.substring(i, i + 1));
				if (i % 2 == 0) {
					soma += numString;
				} else {
					if ((numString * 2) > 9) {
						soma += ((numString * 2) - 9);
					} else {
						soma += (numString * 2);
					}
				}
			}
		}
		
		if (numero.length() >= 16) {
			for (int i = 0; i < numero.length(); i++) {
				numString = Integer.parseInt(numero.substring(i, i + 1));
				if (i % 2 == 0) {
					if (numString * 2 > 9) {
						soma += (numString * 2 - 9);
					} else {
						soma += (numString * 2);
					}
				} else {
					soma += numString;
				}
			}
		}
		return soma % 10 == 0;
	}
	
	public boolean validarCvvCartao(String cvv) {
		if (Objects.isNull(cvv)) {
			return false;
		}

		try {
			cvv = cvv.trim();
			if (Integer.parseInt(cvv) < 1 || cvv.length() != 3) {
				return false;
			}
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	public boolean validarDataValidade(LocalDate dataValidade) {
		if (Objects.isNull(dataValidade) || LocalDate.now().isAfter(dataValidade)) {
			return false;
		}
		return true;
	}
	
	public CartaoCredito salvarCartao(CartaoCredito cartao) {
	    return repository.save(cartao);
	}
}
