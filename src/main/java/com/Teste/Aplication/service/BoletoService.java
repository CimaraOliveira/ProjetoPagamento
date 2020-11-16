package com.Teste.Aplication.service;

import java.time.LocalDate;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Teste.Aplication.model.Boleto;
import com.Teste.Aplication.repository.BoletoRepository;


@Service
public class BoletoService {
 
	@Autowired   
	private  BoletoRepository repository;
	
	@Transactional(readOnly = false)
	public Boleto salvarBoleto(Boleto boleto) {
	 
		return repository.save(boleto);
		 
	}
	
	@Transactional(readOnly = false)
	public Boleto gerarBoleto() {
		Boleto boleto = new Boleto();
		Random gerador = new Random();
		boleto.setNumeroBoleto(gerador.nextInt(101) * 100);
		boleto.setDataCompra(LocalDate.now().plusDays(5));
	    return boleto;
		
	}
	
		
	
}
