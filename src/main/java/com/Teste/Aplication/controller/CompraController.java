package com.Teste.Aplication.controller;

import java.security.Principal;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Teste.Aplication.Enuns.TipoPagamento;
import com.Teste.Aplication.model.Compra;
import com.Teste.Aplication.model.User;
import com.Teste.Aplication.repository.CompraRepository;
import com.Teste.Aplication.service.SessionService;
import com.Teste.Aplication.service.UserService;

@Controller
@RequestMapping("/compras")
public class CompraController {
	
	@Autowired
	private CompraRepository compraService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SessionService<User> serverSession;
	
	@GetMapping("/comprar")
	public String comprar(Compra compra) {
		return "compra/pagamento";

	}
		
    @GetMapping("/detalhes")  
    public ModelAndView detalhes(Principal principal) {
    	Long id_compra = userService.getEmail(principal.getName()).getId();
    	ModelAndView modelAndView = new ModelAndView("compra/detalhes");		
	    modelAndView.addObject("compras", compraService.findAllByIdUser(id_compra));
    	return modelAndView;
	}
				
    @PostMapping("/salvar")
	public String salvar(@Valid Compra compra, RedirectAttributes attr,
			@RequestParam("tipoPagamento") TipoPagamento tipoPagamento) {
    	  
		if (tipoPagamento.equals(TipoPagamento.CARTAO)) {  			
			
			compraService.saveAndFlush(compra); // salva a compra para poder enviar o id
			attr.addAttribute("id", compra.getId()); // envia o id na requisição
			return "redirect:/cartao/cartao"; // redirect
		}

		else if (tipoPagamento.equals(TipoPagamento.BOLETO)) {
			// Boleto boleto = boletoService.gerarBoleto();
			// attr.addFlashAttribute("success", "Boleto gerado com sucesso!");
			//compraService.save(compra);
			return "redirect:/boleto";

		}

		attr.addFlashAttribute("success", "Operação realizada com sucesso!");
		return "/home";

	}
	
}
