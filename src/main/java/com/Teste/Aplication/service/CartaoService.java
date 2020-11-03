package com.Teste.Aplication.service;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Teste.Aplication.model.Cartao;
import com.Teste.Aplication.repository.CartaoRepository;

@Service
public class CartaoService {

	@Autowired
	private CartaoRepository repository;
	
	public Cartao salvarCartao(Cartao cartao) {
	    return repository.save(cartao);
	}
	/*public boolean validarNumeroCartao(String numero) {
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
