package com.Teste.Aplication.json;

import java.security.Principal;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Teste.Aplication.Enuns.TipoPagamento;
import com.Teste.Aplication.model.Compra;
import com.Teste.Aplication.model.User;
import com.Teste.Aplication.service.CompraService;
import com.Teste.Aplication.service.UserService;

@RestController
@RequestMapping("/api/compras")
@CrossOrigin()
public class CompraResource {
	
	@Autowired
	private CompraService compraService;
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Compra> salvar(@Valid Compra compra,User user,
			@RequestParam("tipoPagamento") TipoPagamento tipoPagamento){
		
		compra.setDataCompra(new Date());
    	compra.setValor(compra.getValor() * compra.getQuantidade());
		
		if(tipoPagamento.equals(TipoPagamento.CARTAO)){
			
			compra.setId(compra.getId());
			compraService.salvarCompra(compra);
			return new ResponseEntity<Compra>(compra,HttpStatus.OK);
						
		}else if(tipoPagamento.equals(TipoPagamento.BOLETO)){
			
			compra.setId(compra.getId());
			compraService.salvarCompra(compra);
			return new ResponseEntity<Compra>(compra,HttpStatus.OK);
		}
		
		return new ResponseEntity<Compra>(compra,HttpStatus.CONFLICT);
		
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Compra> detalhes(Principal principal){
		Long id_compra = userService.getEmail(principal.getName()).getId();
		Compra compra = compraService.findByIdCompra(id_compra);
		if(compra != null) {
			return ResponseEntity.ok(compra);
		}
		return ResponseEntity.notFound().build();
	}
}
