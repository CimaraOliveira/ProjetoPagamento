package com.Teste.Aplication.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Teste.Aplication.model.User;
import com.Teste.Aplication.model.Email;
@Service
	public class EmailService { 
		//@Autowired
		//private JavaMailSender emailSender;
		
		@Autowired
		private UserService serviceUsuario;
		
			
		public void sendEmailBemVindo(Email email) {
			try {
				User usuario = serviceUsuario.getEmail(email.getTo());
				email.setFrom("emailll q iremos criar");
				email.getMap().put("name", usuario.getNome());
				email.setSubject("Olá " + usuario.getNome() + ", seja bem-vindo ao sistema de Pagamento!");
				
				/*SimpleMailMessage message = new SimpleMailMessage();
				message.setTo(email.getTo());
				message.setSubject(email.getSubject());
				message.setText("Seja bem vindo ao Sistema de Pagamento");
				message.setFrom(email.getFrom());
				
				//emailSender.send(message);*/
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void sendNovaSenhaEmail(Email email, String novaSenha) {
			try {
				User usuario = serviceUsuario.getEmail(email.getTo());
				email.setFrom("gestaoescolaronline1.0@gmail.com");
				email.getMap().put("name", usuario.getNome());
				email.setSubject("Olá " + usuario.getNome() + " Confira sua nova senha!");
				
				/*SimpleMailMessage message = new SimpleMailMessage();
				message.setTo(email.getTo());
				message.setSubject(email.getSubject());
				message.setText("Nova senha: " + novaSenha);
				message.setFrom(email.getFrom());
				
				emailSender.send(message);*/
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void sendMail(String texto, String email){
			
			/*SimpleMailMessage message = new SimpleMailMessage();
			
			User usuario = serviceUsuario.getEmail(email);
			
	        message.setText(texto);
	        message.setSubject("Olá " + usuario.getNome() + " Sua compra foi realizada com Sucesso!");
	        message.setTo(email);
	        message.setFrom("gestaoescolaronline1.0@gmail.com");*/
	        
	        try {
	        	//emailSender.send(message);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}
}
