package com.Teste.Aplication.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Teste.Aplication.Enuns.TipoPagamento;
import com.Teste.Aplication.model.Boleto;
import com.Teste.Aplication.model.CartaoCredito;
import com.Teste.Aplication.model.Compra;
import com.Teste.Aplication.repository.CompraRepository;
import com.Teste.Aplication.service.BoletoService;
import com.Teste.Aplication.service.CartaoService;
import com.Teste.Aplication.service.CompraService;

@Controller
@RequestMapping("/compras") 
public class CompraController {
 
	@Autowired
	private CartaoService cartaoService;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private CompraRepository compraService;
	
	
	@GetMapping("/ex") 
	public String ex(Compra compra) { 
		return "compra/ex";           
	} 
	
	@GetMapping("/comprar") 
	public String comprar(Compra compra) { 
		return "compra/pagamento";           
	} 
	
	/*@GetMapping("/formaPagamento")             
	public String formaPagamento(Compra compra, BindingResult result) {
		 return "compra/forma";           
	} */
	
	
	@GetMapping("/cartao") 
	public String cartao(Compra compra) { 
		
		return "compra/cartao";           
	} 
	
	@GetMapping("/boleto") 
	public String boleto(Compra compra) { 
		return "compra/exemplo";           
	} 
	
	
	/*@PostMapping("/salvarExemplo") 
	public String salvarExemplo(Compra compra,RedirectAttributes attr, @RequestParam("tipoPagamento") TipoPagamento tipoPagamento) {
		compraService.save(compra);
		attr.addFlashAttribute("success","Operação realizada com sucesso!");
		return "redirect:/compras/ex";
		
	}*/
	
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
	
	
	
	
	@PostMapping("/salvar")   
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
		//compraService.save(compra);
		//attr.addFlashAttribute("success","Operação realizada com sucesso!");
		return "redirect:/compras/ex";
		
	}
	
	
	
	@PostMapping("/salvarCartao") 
	public String salvarCartao(@Valid CartaoCredito cartao) { 
		//CartaoCredito c = new CartaoCredito();
		cartaoService.salvarCartao(cartao);
		return "/home";           
	} 
	
	
	//@PostMapping("/formaPagamento") 
	/*public String salvarFormaPagamento(@Valid Compra compra, @RequestParam("tipoPagamento") TipoPagamento tipoPagamento,Long id,RedirectAttributes attr) {
		
		Compra pag = compraService.findByIdCompra(id);
		 
		if(TipoPagamento.CARTAO.equals(compra.getTipoPagamento())) {
	    	Cartao cc = pag.getCartao();
	    	cartaoService.validarCartao(cc);
	    	pag.setCartao(cartaoService.salvarCartao(cc));
	    	attr.addFlashAttribute("success", "Teste1!");
	    	return "redirect:/compras/cartao";
		 
				    	
	    }
	
		else if(TipoPagamento.BOLETO.equals(compra.getTipoPagamento())) {
			Boleto boleto = boletoService.gerarBoleto();
			pag.setBoleto(boletoService.salvarBoleto(boleto));
			attr.addFlashAttribute("success", "Boleto gerado com sucesso!");
			return "compra/exemplo";
		}
		
		Compra compra1 = compraService.save(pag); 
		attr.addFlashAttribute("success", "Compra realizada com sucessoooo TESTE!");
		 return "redirect:/compras/comprar";           
	} *//////////////////////////////////////
	
     
	
	
	
	
	
	
	
	/*@PostMapping("/pagamento")
	public String realizarCompra(Compra compra,  @RequestParam("tipoPagamento") TipoPagamento tipoPagamento , Long id,RedirectAttributes attr) {
	    //Compra pag = compra.getCompra();
		
		Compra pag = compraService.findByIdCompra(id);
		 
		if(TipoPagamento.CARTAO.equals(compra.getTipoPagamento())) {
	    	Cartao cc = pag.getCartao();
	    	cartaoService.validarCartao(cc);
	    	pag.setCartao(cartaoService.salvarCartao(cc));
	    	attr.addFlashAttribute("success", "Teste1!");
	    	return "redirect:/compras/cartao";
	    	
	    }
	
		else if(TipoPagamento.BOLETO.equals(compra.getTipoPagamento())) {
			Boleto boleto = boletoService.gerarBoleto();
			pag.setBoleto(boletoService.salvarBoleto(boleto));
			attr.addFlashAttribute("success", "Boleto gerado com sucesso!");
			return "compra/exemplo";
		}
		
		Compra compra1 = compraService.save(pag);
		attr.addFlashAttribute("success", "Compra realizada com sucessoooo TESTE!");
		 return "redirect:/compras/comprar";
			
		
	}*/
	
	
	
	
	
	
	                    
}
