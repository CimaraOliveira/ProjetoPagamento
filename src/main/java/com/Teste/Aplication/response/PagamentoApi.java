package com.Teste.Aplication.response;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.Teste.Aplication.Enuns.TipoPagamento;
import com.Teste.Aplication.jwt.JwtComponent;
import com.Teste.Aplication.model.Boleto;
import com.Teste.Aplication.model.Cartao;
import com.Teste.Aplication.model.LogRegister;
import com.Teste.Aplication.model.Pagameto;
import com.Teste.Aplication.model.User;
import com.Teste.Aplication.service.CartaoService;
import com.Teste.Aplication.service.LogRegisterService;
import com.Teste.Aplication.service.PagamentoService;
import com.Teste.Aplication.service.UserService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

public class PagamentoApi {
	
	@Autowired
	private JwtComponent jwtComponent;

	@Autowired
    private UserService userSevice;
	
	@Autowired
    private CartaoService cartaoService;
	
	@Autowired
	private PagamentoService pagamentoService;
	
	
		
	public ResponseEntity<Pagameto>apiPagamento (HttpServletRequest req,@RequestBody Pagameto pagamento,@RequestBody Cartao cartao,@RequestBody Boleto boleto,@RequestParam("tipoPagamento") TipoPagamento tipoPagamento,@RequestParam("quantidade") int quantidade,
			                        @RequestParam("id") long id,@RequestParam("valor") double valor,
			                        @RequestHeader(value = "Authorization", required = false) String Authorization){	
		
        
		  System.out.println(Authorization); 
		  try {
		  System.out.println(id);
		  boolean isValid = jwtComponent.isTokenExpired(Authorization.substring(7));
		  if (!isValid) { 
			    RestTemplate restTemplate = new RestTemplate();  
				String fooResourceUrl = "https://api-projetopagamento.herokuapp.com/api/compras/salvar";
				HttpEntity<Pagameto> request = new HttpEntity<>(pagamento);
				ResponseEntity<Pagameto> responseEntity = restTemplate.postForEntity(fooResourceUrl ,request, Pagameto.class);
				pagamento.setDataCompra(new Date());
				pagamento.setValor(pagamento.getValor() * pagamento.getQuantidade());
			   
				User user = userSevice.findByEmail(pagamento.getUsuario().getEmail());
				pagamento.setUsuario(user);
				
				if(tipoPagamento.equals(TipoPagamento.CARTAO)) {
					pagamento.setIdCompra(id);		
					cartao(cartao);
				}
				
				else if (tipoPagamento.equals(TipoPagamento.BOLETO)) {
					pagamento.setIdCompra(id);
				    Boleto(boleto);
				}
				
				if(responseEntity.getStatusCode().is2xxSuccessful()) {
					Pagameto pag = responseEntity.getBody();			
					System.out.println(pagamento);
				}
				
		  }
	    }catch (ExpiredJwtException | SignatureException e) {
				return ResponseEntity.status(403).build();
			}	
		return ResponseEntity.status(400).build();
	}
	
	
	private void cartao(@RequestBody Cartao cartao) {
		RestTemplate restTemplate = new RestTemplate();  
		String fooResourceUrl = "https://api-projetopagamento.herokuapp.com/api/cartao/salvarCartao";
		cartao.setNumero("5142391129300290");
		cartao.setCvv("123");
		cartao.setMes(02);
		cartao.setAno(2023);
		cartao.setQtd_parcelas(4);	
		cartaoService.salvarCartao(cartao);
		cartao.setValor_parcelado(cartao.getValor_parcelado());
				
		ResponseEntity<Cartao> responseEntity = restTemplate.postForEntity(fooResourceUrl, cartao, Cartao.class);
		if(responseEntity.getStatusCode().is2xxSuccessful()) {
			System.out.println("Salvando cartao");
			Cartao cartao1 = responseEntity.getBody();
			System.out.println(cartao1);
		}	
	}

	
	private void Boleto(@RequestBody Boleto boleto) {
		RestTemplate restTemplate = new RestTemplate();  
		String fooResourceUrl = "https://api-projetopagamento.herokuapp.com/api/boleto/saveBoleto";
		boleto.setDataCompra(new Date());
		boleto.setNumeroBoleto("3333333333");
				
		ResponseEntity<Boleto> responseEntity = restTemplate.postForEntity(fooResourceUrl ,boleto, Boleto.class);
		
		if(responseEntity.getStatusCode().is2xxSuccessful()) {
			System.out.println("Salvando compra boleto");
			Boleto boleto1 = responseEntity.getBody();
			System.out.println(boleto1);
		}
				
	}
	
	public ResponseEntity<Pagameto> detalhePorId( @RequestParam("id") Long id,
			@RequestHeader(value = "Authorization", required = false) String Authorization) {
		
		
		System.out.println(Authorization); 
		try {
			System.out.println(id);
			
		boolean isValid = jwtComponent.isTokenExpired(Authorization.substring(7));
		if (!isValid) { 
		Pagameto pagamento = pagamentoService.findByIdCompra(id);
			if(pagamento != null) {
				RestTemplate restTemplate = new RestTemplate(); 
				String fooResourceUrl = "https://api-projetopagamento.herokuapp.com/api/compras/detalhesCompra";
				ResponseEntity<User[]> response = restTemplate.getForEntity(fooResourceUrl , User[].class);
			
			    if(response.getStatusCode().is2xxSuccessful()) {
			    	User[] user = response.getBody();
				    System.out.println(user);
			    }
			}
			    return ResponseEntity.notFound().build();
		
		
		  }
		    
		}catch (ExpiredJwtException | SignatureException e) {
			return ResponseEntity.status(403).build();
      }
		
		return ResponseEntity.status(400).build();
	
	}
	
	
}
