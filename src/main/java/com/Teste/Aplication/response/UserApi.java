package com.Teste.Aplication.response;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.Teste.Aplication.model.User;

public class UserApi {

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
		
}
