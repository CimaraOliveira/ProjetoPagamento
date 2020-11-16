package com.Teste.Aplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import com.Teste.Aplication.model.Email;
import com.Teste.Aplication.model.User;


@Service
public class EmailService { 
		
	@Autowired
	private UserService serviceUsuario;
	
	
	public void sendNovaSenhaEmail(Email email, String novaSenha) {
		try {
			User usuario = serviceUsuario.getEmail(email.getTo());
			email.setFrom("suporte.projeto.siap@gmail.com");
			email.getMap().put("name", usuario.getNome());
			email.setSubject("Olá " + usuario.getNome() + " Confira sua nova senha!");
			
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(email.getTo());
			message.setSubject(email.getSubject());
			message.setText("Nova senha: " + novaSenha);
			message.setFrom(email.getFrom());
			
		   
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}