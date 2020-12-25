package com.Teste.Aplication.json;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Teste.Aplication.jwt.JwtComponent;
import com.Teste.Aplication.model.Role;
import com.Teste.Aplication.model.User;
import com.Teste.Aplication.service.RoleService;
import com.Teste.Aplication.service.UserService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin()
public class UsuarioJson {
	@Autowired
	private JwtComponent jwtComponent;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserService serviceUsuario;

	@Autowired
	private RoleService roleService;

	
	public ResponseEntity<?> login(@RequestBody User user) {
		if (user == null) {
			return ResponseEntity.status(400).build();
		}
		try {
			authenticate(user.getEmail(), user.getPassword());
			UserDetails userDB = serviceUsuario.loadUserByUsername(user.getUsername());
			
			if(userDB != null) { 
				String token = jwtComponent.generateToken(user);
				return ResponseEntity.ok(token);
			}
			 
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return ResponseEntity.notFound().build();
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<User> salvar(@Valid User user) {
		User u = serviceUsuario.getEmail(user.getEmail());
		String senha = user.getSenha();
		user.setSenha(new BCryptPasswordEncoder().encode(senha));
		serviceUsuario.salvar(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<User> detalhePorId(@PathVariable("id") Long id,
			@RequestHeader(value = "Authorization", required = false) String Authorization) {
        System.out.println(Authorization); 
		try {
			System.out.println(id);
			
			boolean isValid = jwtComponent.isTokenExpired(Authorization.substring(7));
			if (!isValid) { 
				User user = serviceUsuario.findById(id);
				if (user != null) {
					return ResponseEntity.ok(user);
				}
				return ResponseEntity.notFound().build();
			}
		} catch (ExpiredJwtException | SignatureException e) {
			return ResponseEntity.status(403).build();
		}
		return ResponseEntity.status(400).build();
	}

	@PutMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<User> update(User user, @PathVariable Long id) {
		user = serviceUsuario.findById(id);
		if (user != null) {
			String senha = user.getSenha();
			user.setSenha(new BCryptPasswordEncoder().encode(senha));
			serviceUsuario.editar(user);
			return ResponseEntity.ok(user);
		}
		return ResponseEntity.noContent().build();
	}

	@PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<User> salvarNovo(User user) {
		Role role = roleService.getNome("USER");
		if (role != null) {
			user.getRole().add(role);
		}
		serviceUsuario.salvarCadastro(user);

		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

}