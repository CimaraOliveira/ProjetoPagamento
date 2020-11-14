package com.Teste.Aplication.service;

import java.util.List;

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
	
	public Compra findByIdCompra(Long id) {
		return repository.findByIdCompra(id);  
	}
	
	public List<Compra> findAllByIdUser(Long id){
		return repository.findAllByIdUser(id);
	}
	
}
