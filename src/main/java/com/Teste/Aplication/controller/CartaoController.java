package com.Teste.Aplication.controller;




import java.math.RoundingMode;
import java.security.Principal;
import java.text.DecimalFormat;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Teste.Aplication.Enuns.Status;
import com.Teste.Aplication.model.Cartao;
import com.Teste.Aplication.model.Compra;
import com.Teste.Aplication.model.User;
import com.Teste.Aplication.repository.CompraRepository;
import com.Teste.Aplication.service.CartaoService;
import com.Teste.Aplication.service.UserService;

  

@Controller 	
@RequestMapping("/cartao")
public class CartaoController {

	@Autowired
	private CartaoService cartaoService;
	
	@Autowired
	private CompraRepository compraService;
	
	private Long id = null;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/cartao") 
	public String cartao(Cartao cartao, @RequestParam("id") String attr) { 
		id = Long.parseLong(attr); // recebe o id da compra
		Compra compra = compraService.getOne(id);
		//compra.setValor(compra.getValor());
		return "compra/cartao";           
	} 
	
	 private double calPMT(double pv, int n, String i){
		
	      	
	       // System.out.println( decimalFormat.format(valor) );
	        String porcent [] = i.split("%");
	        double taxa = Double.parseDouble(porcent[0]) / 100;
	        double resultOne = (Math.pow((1 + taxa), n) - 1);
	        double resultTwo = ((Math.pow(1 + taxa, n) * taxa));
	        double resultThree = resultOne / resultTwo;
	        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
		    decimalFormat.setRoundingMode(RoundingMode.DOWN);
		    
		    String valor[]=decimalFormat.format(pv/resultThree).split(",");
	        return Double.parseDouble(valor[0]+"."+ valor[1]);
	 }	
			 
	@PostMapping("/salvarCartao") 
	public String salvarCartao(@Valid Cartao cartao,BindingResult result, Principal principal, RedirectAttributes attr) { 
				
		//System.out.println(cartao.getQtd_parcelas());
		Compra compra = compraService.getOne(id); // retorna o objeto do banco para poder manipular
		  System.out.println(cartao.getQtd_parcelas());			
		  if(compra != null) { // se for diferente de nulo, continua o fluxo abaixo
			  User user = userService.getEmail(principal.getName());
			  if(user != null) {
				
				  double total = calPMT(compra.getValor(), cartao.getQtd_parcelas(), "2%");
				  
				  cartao.setValor_parcelado(total); //  seta o valor das parcelas
				  
				  System.out.println(user.getEmail());
				  cartaoService.salvarCartao(cartao);  // salva o cartão
				  compra.setUsuario(user);				  
				  cartao.setCompras(compra);
				  //compra.setQtd_parcelas(cartao);
				  compra.setCartao(cartao); // seta o cartão na compra
			      compra.setStatus(Status.CONCLUÍDA);
			      compraService.saveAndFlush(compra); // atualiza a compra
			      attr.addFlashAttribute("success", "Compra realizada com sucesso");
			      System.out.println("valor das parcelas");
			      //System.out.println(calPMT(compra.getValor(), cartao.getQtd_parcelas(), "2%"));
			      cartao.setValor_parcelado(calPMT(compra.getValor(), cartao.getQtd_parcelas(), "2%"));			      
			      			      
			  }  
		  }
		   		
		//return "redirect:/cartao/detalhes";		
		return "redirect:/cartao/detalhesCompraIdCartao/"+cartao.getId_Cartao();
	}	
	@GetMapping("/detalhesCompraIdCartao/{id_cartao}")  
	public ModelAndView detalhesCompraCartao(@PathVariable("id_cartao") Long id_cartao) {
		ModelAndView modelAndView = new ModelAndView("compra/detalhesCompraCartao");
		modelAndView.addObject("compras", compraService.findByIdCartao(id_cartao));
		return modelAndView;
	}
	
	    		
}

