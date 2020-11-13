package com.Teste.Aplication.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Teste.Aplication.model.User;
import com.Teste.Aplication.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserResource {
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/findByNome/{name}")
	public ResponseEntity<User> findByNome(@PathVariable("name") String name){
		return userService.findByNome(name) != null ? ResponseEntity.ok(userService.findByNome(name)) : ResponseEntity.notFound().build(); 
	}
}
