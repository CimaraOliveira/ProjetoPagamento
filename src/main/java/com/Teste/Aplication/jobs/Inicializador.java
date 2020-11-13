package com.Teste.Aplication.jobs;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.Teste.Aplication.model.Role;
import com.Teste.Aplication.model.User;
import com.Teste.Aplication.service.RoleService;
import com.Teste.Aplication.service.UserService;

@Component
public class Inicializador implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		System.out.println("|*************************************|");
		System.out.println("	Jobs - Initializing User		");
		System.out.println("|*************************************|");
		
		Role roleUser = roleService.getNome("USER");	
		
		if(roleUser == null) {
			roleUser = new Role();
			roleUser.setNome("USER");
			roleService.save(roleUser);	
			
			User cliente = new User();
			cliente.setNome("Cleo");
			cliente.setEmail("cleo@gmail.com");
			cliente.setSenha("123123");
						
			cliente.getRole().add(roleUser);
			
			userService.salvarCadastro(cliente);
		}
		   
		Role roleAdmin = roleService.getNome("ADMIN");
		if(roleAdmin == null) {
			roleAdmin = new Role();
			roleAdmin.setNome("ADMIN");
			roleService.save(roleAdmin);	
			
			User administrador = new User();
			administrador.setNome("João");
			administrador.setEmail("joao@gmail.com");
			
			administrador.setSenha("12345");
			
			administrador.getRole().add(roleAdmin);
			
			userService.salvarCadastro(administrador);
		}
		
	}

}
