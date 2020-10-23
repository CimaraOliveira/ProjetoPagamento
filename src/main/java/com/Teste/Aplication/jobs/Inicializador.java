package com.Teste.Aplication.jobs;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.Teste.Aplication.model.User;
import com.Teste.Aplication.service.UserService;

@Component
public class Inicializador implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private UserService userService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println("|*************************************|");
		System.out.println("	Jobs - Initializing User		");
		System.out.println("|*************************************|");
		
		
		User user = new User();
		user.setNome("Cleo");
		user.setEmail("cleo@gmail.com");
		user.setSenha(new BCryptPasswordEncoder().encode("123123"));
		user.getRole();
		userService.salvarCadastro(user);
		
		User user2 = new User();
		user2.setNome("Mario Santos");
		user2.setEmail("santoso@gmail.com");
		user2.setSenha(new BCryptPasswordEncoder().encode("12345"));
		userService.salvarCadastro(user2);
		
		
		
	}

}
