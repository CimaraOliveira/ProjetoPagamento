package com.Teste.Aplication.controller;



import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.context.Context;

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
		if(result.hasErrors()) {
			attr.addFlashAttribute("fail", "Dados Inválidos tente novamente!");
		}
		/*if((validarNumeroCartao(numero)==false) || (validarCvvCartao(cvv) == false)) {
			attr.addFlashAttribute("fail", "Dados Inválidos tente novamente!");
			return "redirect:/cartao/cartao";
			
			
		}*/
		    Compra compra = compraService.getOne(id); // retorna o objeto do banco para poder manipular
		    if(compra != null) { // se for diferente de nulo, continua o fluxo abaixo
			    cartaoService.salvarCartao(cartao);  // salva o cartão
		    	compra.setCartao(cartao); // seta o cartão na compra
		    	compraService.saveAndFlush(compra); // atualiza a compra
		    	attr.addFlashAttribute("success", "Compra realizada com sucesso");
		    }
		
		
		return "redirect:/";		
	} 
	
	
	@GetMapping("/comprar") 
	public Compra comprar(Compra compra) { 
	     return compraService.save(compra);          
	} 
	
	
	public boolean validarNumeroCartao(String numero) {
		
		Integer numString;
		int soma = 0;
		
		
		if (numero.length() >= 16) {
			for (int i = 0; i < numero.length(); i++) {
				numString = Integer.parseInt(numero.substring(i, i + 1));
				if (i % 2 == 0) {
					
					if (numString * 2 > 9) {
						soma += (numString * 2 - 9);
					} else {
						soma += (numString * 2);
					}
				} else {
					soma += numString;
				}
								
			}
			
			return true;
			
			
		}
		return false;
		
		
		/*Integer numString;
		int soma = 0;
		
		
		if (numero.length() >= 16) {
			for (int i = 0; i < numero.length(); i++) {
				numString = Integer.parseInt(numero.substring(i, i + 1));
				if (i % 2 == 0) {
					
					if (numString * 2 > 9) {
						soma += (numString * 2 - 9);
					} else {
						soma += (numString * 2);
					}
				} else {
					soma += numString;
				}
								
			}
			
			return true;
			
			
		}
		
		return false;*/
	    	
	}
	
	public boolean validarCvvCartao(String cvv) {
		if (Objects.isNull(cvv)) {
			return false;
		}

		try {
			cvv = cvv.trim();
			if (Integer.parseInt(cvv) < 1 || cvv.length() != 3) {
				return false;
			}
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
    		
}
