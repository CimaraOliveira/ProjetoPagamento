package com.Teste.Aplication.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Teste.Aplication.model.Pagameto;
import com.Teste.Aplication.repository.PagamentoRepository;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository repository;
	
	@Transactional(readOnly = false)
	public void salvarCompra(Pagameto compra) {
	 
		 repository.save(compra);
	}
	
	public Pagameto findByIdCompra(Long id) {
		return repository.findByIdCompra(id);  
	}
	
	public List<Pagameto> findAllByIdUser(Long id){
		return repository.findAllByIdUser(id);
	}
	
	public List<Pagameto> findByIdBoleto(Long id_boleto) {
		return repository.findByIdBoleto(id_boleto);
	}
	
	public List<Pagameto> findByIdcartao(Long id_cartao){
		return repository.findByIdCartao(id_cartao);
	}
	public Pagameto findByEmail(String email) {
		return repository.findByEmail(email);  
	}
	
	
}
