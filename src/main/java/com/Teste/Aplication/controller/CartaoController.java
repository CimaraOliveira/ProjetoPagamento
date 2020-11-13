package com.Teste.Aplication.controller;




import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Teste.Aplication.model.Cartao;
import com.Teste.Aplication.model.Compra;
import com.Teste.Aplication.repository.CompraRepository;
import com.Teste.Aplication.service.CartaoService;

  

@Controller 	
@RequestMapping("/cartao")
public class CartaoController {

	@Autowired
	private CartaoService cartaoService;
	
	@Autowired
	private CompraRepository compraService;
	
	private Long id = null;
	
	@GetMapping("/cartao") 
	public String cartao(Cartao cartao, @RequestParam("id") String attr) { 
		id = Long.parseLong(attr); // recebe o id da compra
		return "compra/cartao";           
	} 
	
			
	@PostMapping("/salvarCartao") 
	public String salvarCartao(@Valid Cartao cartao,BindingResult result, RedirectAttributes attr,String numero, String cvv) { 
		  Compra compra = compraService.getOne(id); // retorna o objeto do banco para poder manipular
		  if(compra != null) { // se for diferente de nulo, continua o fluxo abaixo
			   	cartaoService.salvarCartao(cartao);  // salva o cartão
		    	compra.setCartao(cartao); // seta o cartão na compra
		    	compraService.saveAndFlush(compra); // atualiza a compra
		          
		    	attr.addFlashAttribute("success", "Compra realizada com sucesso");
		  }
		   		
		return "redirect:/home";		
	} 
	
	
	    		
}
