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
