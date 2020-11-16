package com.Teste.Aplication.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Teste.Aplication.service.UserService;


@Controller

public class LoginController {

	@GetMapping("/")
	public String login() { 
		return "login";
	}	
	
	@PostMapping("/home")
	public String test() {
		return "home";
	}
	
	//login invalido
	@GetMapping("/login-error")
	public String loginError(ModelMap model) {
		model.addAttribute("alerta", "erro");
		model.addAttribute("titulo", "Credenciais inválidas!");
		model.addAttribute("texto", "Login ou senha incorretos, tente novamente!");
		return "login";
	}
	
	
}
