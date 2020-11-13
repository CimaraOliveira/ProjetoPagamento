package com.Teste.Aplication.controller;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.Teste.Aplication.model.User;
import com.Teste.Aplication.service.SessionService;
import com.Teste.Aplication.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private SessionService<User> serviceSession;
	
	@Autowired
	private UserService service;
	 
	
	@GetMapping("/home")
	public String home() {
	  User usuarioByEmail = service.getEmail(SecurityContextHolder.getContext().getAuthentication().getName());
	  serviceSession.criarSession("usuario", usuarioByEmail);
		return "home";
	}  
	
	
	
	

}
