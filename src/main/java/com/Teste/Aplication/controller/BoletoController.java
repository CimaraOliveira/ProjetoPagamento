package com.Teste.Aplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Teste.Aplication.model.Compra;
import com.Teste.Aplication.service.BoletoService;

@Controller
@RequestMapping("/boletos") 
public class BoletoController {

	@Autowired
	private BoletoService boletoService;
	

	@GetMapping("/ex") 
	public String ex(Compra compra) { 
		return "compra/ex";           
	} 
}
