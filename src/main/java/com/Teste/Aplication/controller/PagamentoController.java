package com.Teste.Aplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Teste.Aplication.model.Pagamento;

@Controller
@RequestMapping("/pagamento_final")
public class PagamentoController {

	@GetMapping("/finalizar")
	public String finalizar(Pagamento finalizar) {
		return "pagamento/ex";
	}
	
	@GetMapping("/formapagamento")       
	public String formapagamento(Pagamento forma) {
		return "pagamento/forma"; 
	}
	
	
}
