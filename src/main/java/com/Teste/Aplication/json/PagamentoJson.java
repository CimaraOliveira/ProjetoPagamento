package com.Teste.Aplication.json;


import java.util.Date;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Teste.Aplication.jwt.JwtComponent;
import com.Teste.Aplication.model.Boleto;
import com.Teste.Aplication.model.Cartao;
import com.Teste.Aplication.model.Pagameto;
import com.Teste.Aplication.model.LogRegister;
import com.Teste.Aplication.model.User;
import com.Teste.Aplication.service.BoletoService;
import com.Teste.Aplication.service.CartaoService;
import com.Teste.Aplication.service.PagamentoService;
import com.Teste.Aplication.service.UserService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

class TestBoleto { // Classe Auxiliar
	public Boleto boleto;
	public Long idCompra;
}

class TestCartao{ // Classe Auxiliar
	public Cartao cartao;
	public Long idCompra;
}

@RestController
@RequestMapping("/api/compras")
@CrossOrigin(origins = {"*"})
public class PagamentoJson {
	@Autowired
	private JwtComponent jwtComponent;
	
	@Autowired
	private PagamentoService compraService;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private CartaoService cartaoService;
	
	@Autowired
	private UserService serviceUsuario;

	@PostMapping("/saveBoleto")
	public ResponseEntity<Boleto> salvarBoleto(HttpServletRequest request, @RequestBody TestBoleto test) {
		Pagameto compra = compraService.findByIdCompra(test.idCompra);

		String origin = request.getHeader("Origin");
		
		LogRegister logRegister = new LogRegister();
		logRegister.setHostOrigin(origin);
		logRegister.setDate(new Date()); 
		
		if (compra != null) {
			boletoService.salvarBoleto(test.boleto);
			test.boleto.setDataCompra(new Date());
			compra.setBoleto(test.boleto);
			compraService.salvarCompra(compra);
			return ResponseEntity.status(HttpStatus.CREATED).body(test.boleto);
		}

		return ResponseEntity.status(400).build();
	}

	@PostMapping("/saveCartao")
	public ResponseEntity<Cartao> salvarCartao(@RequestBody TestCartao test){
		Pagameto compra = compraService.findByIdCompra(test.idCompra);
		
        	
		if(compra != null) {
			cartaoService.salvarCartao(test.cartao);
			compra.setCartao(test.cartao);
			compraService.salvarCompra(compra);
			return ResponseEntity.status(HttpStatus.CREATED).body(test.cartao);
		}
		
		return ResponseEntity.status(400).build();
	}
	
	@GetMapping("/detalhesCompra/{id}")
	public ResponseEntity<Pagameto> detalhePorId(@PathVariable("id") Long id,
			@RequestHeader(value = "Authorization", required = false) String Authorization) {
        System.out.println(Authorization); 
		try {
			System.out.println(id);
			
			boolean isValid = jwtComponent.isTokenExpired(Authorization.substring(7));
			if (!isValid) { 
				Pagameto compras = compraService.findByIdCompra(id);
						
				if (compras != null) {
					return ResponseEntity.ok(compras);
				}
				return ResponseEntity.notFound().build();
			}
		} catch (ExpiredJwtException | SignatureException e) {
			return ResponseEntity.status(403).build();
		}
		return ResponseEntity.status(400).build();
	}
	
	@PostMapping("/saveCompra")
	public ResponseEntity<Pagameto> salvarCompra(@RequestBody Pagameto compra){
		compra.setDataCompra(new Date());
		compra.setValor(compra.getValor() * compra.getQuantidade());
		System.out.println(compra.getUsuario());
		User user = serviceUsuario.findById(compra.getUsuario().getId());
		compra.setUsuario(user);
		compraService.salvarCompra(compra);
		return new ResponseEntity<Pagameto>(compra, HttpStatus.CREATED);
		
	}
	
}