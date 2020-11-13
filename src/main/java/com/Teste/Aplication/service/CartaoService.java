package com.Teste.Aplication.service;





import java.time.LocalDate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Teste.Aplication.Enuns.TipoBandeira;
import com.Teste.Aplication.model.Cartao;
import com.Teste.Aplication.repository.CartaoRepository;

@Service
public class CartaoService {

	@Autowired
	private CartaoRepository repository;
	
	public Cartao salvarCartao(Cartao cartao) {
	    return repository.save(cartao);
	}
	
	public  boolean validarCartao(String numero) {
		int s1 = 0, s2 = 0;
		String reverse = new StringBuffer(numero).reverse().toString();
		for (int i = 0 ;i < reverse.length();i++) {
			int digit = Character.digit(reverse.charAt(i), 10);
			if(i % 2 == 0) { s1 += digit; }
			else {
				s2 += 2 * digit;
				if (digit >= 5) { s2 -= 9; }
			}
			
		}
		return (s1 + s2) % 10 == 0;
		
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

	
	
	
	/*
	    	
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
	*/
	/*public boolean validarNumeroCartao(String numero) {
	 * 
	 * /*int s1 = 0, s2 = 0;
		
		String reverse = new StringBuffer(numero).reverse().toString();
		for (int i = 0 ;i < reverse.length();i++) {
			int digit = Character.digit(reverse.charAt(i), 10);
			if(i % 2 == 0) { 
				s1 += digit; 
			}
			else {
				s2 += 2 * digit;
				if (digit >= 5) { 
					s2 -= 9;
				}
			}
			
		}
		
		
		return (s1 + s2) % 10 == 0;
		
       
	}*/
	


	/*public boolean validarDataValidade(LocalDate dataValidade) {//LocalDate
		if (Objects.isNull(dataValidade) || LocalDate.now().isAfter(dataValidade)) {
			return false;
		}
		if(dataValidade.equals(null)) {
			return false;
		}
		
		return true;
	}
	
	*/
}
