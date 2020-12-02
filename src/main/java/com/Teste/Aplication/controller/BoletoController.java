package com.Teste.Aplication.controller;




import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.apache.tomcat.util.buf.UDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Teste.Aplication.Enuns.Status;
import com.Teste.Aplication.model.Boleto;
import com.Teste.Aplication.model.Compra;
import com.Teste.Aplication.model.User;
import com.Teste.Aplication.repository.CompraRepository;
import com.Teste.Aplication.service.BoletoService;
import com.Teste.Aplication.service.UserService;

@Controller
@RequestMapping("/boleto") 
public class BoletoController {

	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private CompraRepository compraService;
	
	@Autowired
	private UserService userService;
	
	private Long id = null;
	
	@GetMapping("/boleto")  
	public String boleto(@Valid Boleto boleto, @RequestParam("id") String attr,
			RedirectAttributes attrs, Principal principal) {
		id = Long.parseLong(attr);
		Compra compra = compraService.getOne(id);
		 if(compra != null) { 
			  User user = userService.getEmail(principal.getName());
			  if(user != null) {
				  System.out.println(user.getEmail());
				  boleto.setDataCompra(LocalDate.parse("2020-10-16"));
				  boletoService.salvarBoleto(boleto);
				  compra.setUsuario(user);
			      compra.setBoleto(boleto);    
			      compra.setStatus(Status.ANDAMENTO);
			      compraService.saveAndFlush(compra); 
			      attrs.addFlashAttribute("success", "Compra realizada com sucesso");
			      attrs.addAttribute("compras", compraService.findAllByIdUser(user.getId()));
			  }
		  }

		//return "compra/boleto";    
		return "redirect:/boleto/detalhesBoleto/{id_boleto}";
	} 
	
	@GetMapping("/detalhesCompraIdBoleto/{id_boleto}")  
	public ModelAndView detalhesBoleto(@PathVariable("id_boleto") Long id_boleto) {
		ModelAndView modelAndView = new ModelAndView("compra/boleto");
		modelAndView.addObject("compras", compraService.findByIdBoleto(id_boleto));
		return modelAndView;
	}
	
	
	
}
