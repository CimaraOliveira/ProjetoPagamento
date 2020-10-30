package com.Teste.Aplication.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Teste.Aplication.Enuns.TipoPagamento;
import com.Teste.Aplication.model.Compra;
import com.Teste.Aplication.repository.CompraRepository;
import com.Teste.Aplication.service.BoletoService;
import com.Teste.Aplication.service.CartaoService;

@Controller
@RequestMapping("/compras") 
public class CompraController {
 
	@Autowired
	private CompraRepository compraService;
	
	@Autowired
	private CartaoService cartaoService;
	
	@Autowired
	private BoletoService boletoService;
	
	@GetMapping("/comprar") 
	public String comprar(Compra compra) { 
		return "compra/pagamento";           
	} 
	
		
	//Funcionando
	/*@PostMapping("/salvarExemplo") 
	public String salvarExemplo(Compra compra,RedirectAttributes attr, @RequestParam("tipoPagamento") TipoPagamento tipoPagamento) {
		if(tipoPagamento.equals(TipoPagamento.CARTAO)) {
			
			compraService.save(compra);
			//attr.addFlashAttribute("success", "Teste1!");
			return "redirect:/compras/cartao";
			
			
		}else if(tipoPagamento.equals(TipoPagamento.BOLETO)) {
			Boleto boleto = boletoService.gerarBoleto();
			attr.addFlashAttribute("success", "Boleto gerado com sucesso!");
			compraService.save(compra);
			return "compra/boleto";
			
		}
		compraService.save(compra);
		attr.addFlashAttribute("success","Operação realizada com sucesso!");
		return "redirect:/compras/ex";
		
	}*/
	
	
	//CORRETO
	@PostMapping("/salvar")   
	public String salvar(Compra compra,RedirectAttributes attr, @RequestParam("tipoPagamento") TipoPagamento tipoPagamento) {
		
		if(tipoPagamento.equals(TipoPagamento.CARTAO)) {
			
			compraService.save(compra);
	        return "compra/cartao";
			
			
		}else if(tipoPagamento.equals(TipoPagamento.BOLETO)) {
			//Boleto boleto = boletoService.gerarBoleto();
			//attr.addFlashAttribute("success", "Boleto gerado com sucesso!");
			compraService.save(compra);
			return "compra/boleto";
			
		}
		//compraService.save(compra);
		//attr.addFlashAttribute("success","Operação realizada com sucesso!");
		return "redirect:/home";
		
	}
	             
}
