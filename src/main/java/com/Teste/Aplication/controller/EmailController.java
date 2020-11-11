package com.Teste.Aplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.Teste.Aplication.model.Email;

@Controller
@RequestMapping("/email")
public class EmailController {
	
	//@Autowired
	//private EmailService emailServicee;
	//@Autowired
	//private UsuarioService usuarioService;
	
	@PostMapping("/sendEmail")
	public ModelAndView enviarEmail(Email email) {
	//	emailServicee.sendEmailText(email, email.getTexto());
		return success();
	}
	
	public ModelAndView success() {
		ModelAndView view = new ModelAndView("/usuario/portal-user");
		view.addObject("success", "Email Enviado com Sucesso!");
		return view;
	}

}