package com.Teste.Aplication.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.Teste.Aplication.jwt.JwtComponent;
import com.Teste.Aplication.model.User;
import com.Teste.Aplication.service.PagamentoService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

public class UserApi {
	
	@Autowired
	private JwtComponent jwtComponent;
	
	@Autowired
	private PagamentoService pagamentService;
	
	public ResponseEntity<User> detalhePorIdUsuario(@RequestParam("id") Long id, 
			@RequestHeader(value = "Authorization", required = false) String Authorization) {
		System.out.println(Authorization);
		try {
			System.out.println(id);
			
			boolean isValid = jwtComponent.isTokenExpired(Authorization.substring(7));
			if (!isValid) { 
				RestTemplate restTemplate = new RestTemplate();  
				String fooResourceUrl = "https://api-projetopagamento.herokuapp.com/api/user/findById/{id}";
				ResponseEntity<User[]> response = restTemplate.getForEntity(fooResourceUrl , User[].class);
				
				    if(response.getStatusCode().is2xxSuccessful()) {
				    	User[] user = response.getBody();
					    System.out.println(user);
					   
				    }
				    return ResponseEntity.notFound().build();
			}
			
		}catch (ExpiredJwtException | SignatureException e) {
				return ResponseEntity.status(403).build();
			}
		
		    return ResponseEntity.status(400).build();
	}
	
	private void save() {		
		RestTemplate restTemplate = new RestTemplate();  
		String fooResourceUrl = "https://api-projetopagamento.herokuapp.com/api/user/save";
		User user = new User();
		user.setNome("José");
		user.setEmail("jose@gmail.com");
		user.setSenha("12345678");
		
		try {
		HttpEntity<User> request = new 	HttpEntity<>(user);	
		ResponseEntity<User> responseEntity = restTemplate.postForEntity(fooResourceUrl ,request, User.class);
		
		if(responseEntity.getStatusCode().is2xxSuccessful()) {
			System.out.println("Criando Novo User");
			User user2 = responseEntity.getBody();
			System.out.println(user2);
		}
		
		}catch (Exception e) {
			// TODO: handle exception
		}		
	}
	
	    ///só para os primeiros testes 
	    private void consume() {
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = "https://api-projetopagamento.herokuapp.com/api/user/findAll";
		ResponseEntity<User[]> response = restTemplate.getForEntity(fooResourceUrl + "/1", User[].class);
		
		    for(User user : response.getBody()) {
			    System.out.println(user);
		    }
		}
		
		private void consumeOne() {
			RestTemplate restTemplate = new RestTemplate(); //passa  o nome no broswer 
			String fooResourceUrl = "https://api-projetopagamento.herokuapp.com/api/user/findByNome/";
			ResponseEntity<User[]> response = restTemplate.getForEntity(fooResourceUrl , User[].class);
			
			    if(response.getStatusCode().is2xxSuccessful()) {
			    	User[] user = response.getBody();
				    System.out.println(user);
			    }
		}		
		
		
}
