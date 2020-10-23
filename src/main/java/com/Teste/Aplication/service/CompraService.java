package com.Teste.Aplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Teste.Aplication.model.Compra;
import com.Teste.Aplication.repository.CompraRepository;

@Service
public class CompraService {

	@Autowired
	private CompraRepository repository;
	
	@Transactional(readOnly = false)
	public void salvarCompra(Compra compra) {
	 
		 repository.save(compra);
	}
	
	@Transactional(readOnly = true)
	public Compra findByIdCompra(Long id) {
		return repository.findByIdCompra(id);  
	}
	
	/*@Transactional(readOnly = false)
	public void salvarCompra(Compra compra){
		//String crypt = new BCryptPasswordEncoder().encode(user.getSenha());
		//compra.setSenha(crypt);
		compra.setQuantidade(compra.getQuantidade());
		compra.setValor(compra.getValor());
		
		
		//repository.save(compra);*/	
		
		//emailDeConfirmacaoDeCadastro(user.getEmail());
	//}
}
