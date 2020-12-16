package com.Teste.Aplication.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Teste.Aplication.Enuns.TipoPagamento;
import com.Teste.Aplication.model.Boleto;
import com.Teste.Aplication.model.Compra;
import com.Teste.Aplication.model.User;
import com.Teste.Aplication.repository.CompraRepository;
import com.Teste.Aplication.service.BoletoService;
import com.Teste.Aplication.service.UserService;

@Controller
@RequestMapping("/compras")
public class CompraController {
	
	@Autowired
	private CompraRepository compraService;
	
	@Autowired
	private UserService userService;
	
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
	public String salvar(@Valid Compra compra,User user, Boleto boleto, RedirectAttributes attr,
			@RequestParam("tipoPagamento") TipoPagamento tipoPagamento) {
    	
    	compra.setDataCompra(new Date());
		
		
		/*Date data = new Date();
    	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    	String dataCompra = formato.format(data);
    	compra.setDataCompra(data);*/
    	        
    	compra.setValor(compra.getValor() * compra.getQuantidade());
    	 	
    	if (tipoPagamento.equals(TipoPagamento.CARTAO)){  	  		
			
			compraService.saveAndFlush(compra); // salva a compra para poder enviar o id
			attr.addAttribute("id", compra.getId()); // envia o id na requisição
			return "redirect:/cartao/cartao"; 
		}

		else if (tipoPagamento.equals(TipoPagamento.BOLETO)) {
			
			compraService.saveAndFlush(compra);   
			attr.addAttribute("id", compra.getId());
			return "redirect:/boleto/boleto";
		}
			
		attr.addFlashAttribute("success", "Operação realizada com sucesso!");
		return "/home";

	}
	
	//parcelamento com juros,  compra de R$ 1.000, parcelada em 10x, com juros de 2% ao mês, 
	//por exemplo. Pagará em cada parcela, R$ 111,33, resultando no total de R$ 1.113,27. 
	//O valor de R$ 113,27, foi o total que você pagou de juros só por parcelar a compra.
}
