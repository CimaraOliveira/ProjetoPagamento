package com.Teste.Aplication.controller;


import java.util.Random;

import javax.validation.Valid;

import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Teste.Aplication.model.User;
import com.Teste.Aplication.model.Email;
import com.Teste.Aplication.service.UserService;

@Controller 	
@RequestMapping("/usuario")
public class UserController{

	@Autowired
	private UserService service;
	  
	@GetMapping("/cadastrar")    
	public String cadastrar(User user) {
		return "user/cadastro";   
	}  	  
	  
	@PostMapping("/salvar") 
	public String salvar(@Valid User user,BindingResult result, RedirectAttributes attr) {
		if(result.hasErrors()) { 
			return "user/cadastro";
		}
		
			String senha = user.getSenha();
			user.setSenha(new BCryptPasswordEncoder().encode(senha));
			service.salvar(user);
			attr.addFlashAttribute("success", "Usuário Cadastrado com sucesso!");
			 
		
		return "redirect:/usuario/cadastrar";
	} 
	 
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("usuario", service.findAll());
		return "user/lista";  
	}  
	
	@GetMapping("/listar/id")
	public String listarPorId(ModelMap model, @RequestParam("id") Long id) {
		model.addAttribute("usuario", service.findById(id));
		return "user/lista";
	}
	
	
	/*@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("usuario", service.findById(id));
		return "user/cadastro";
		
	}
	
	@PostMapping("/editar")
	public String editar(@Valid User user, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "user/cadastro";
		}
		service.editar(user);
		attr.addFlashAttribute("success", "Dados alterados com sucesso!");
		return "redirect:/usuario/cadastrar";
	}*/
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("usuario", service.findById(id));
		return "user/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(User user, RedirectAttributes attr) {
		String senha = user.getSenha();
		user.setSenha(new BCryptPasswordEncoder().encode(senha));
		service.editar(user);
		attr.addFlashAttribute("success", "Seus dados foram alterados com sucesso!");
		return "redirect:/usuario/cadastrar";
	}	
	
	//não vamos excluir
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		service.excluir(id);
		attr.addFlashAttribute("success", "Funcionário removido com sucesso.");
		return "redirect:/usuario/listar";
	}	
	
	@GetMapping("/novo/cadastro")
	public String novoCadastro(User user) {

        return "cadastrar-se";
    } 
	
	// rebece o form da página cadastrar-se
	@PostMapping("/salvar/novo")   
	public String salvarCadastro(User user, BindingResult result, RedirectAttributes attr) throws MessageDescriptorFormatException {
        try {
        	service.salvarCadastro(user);
        }catch (DataIntegrityViolationException ex) {
			result.reject("email", "Ops... Este e-mail já existe!");
    		return "cadastrar-se";
		}
        attr.addFlashAttribute("success", "Usuário Cadastrado com sucesso!");
         return "login"; 
         
	}
	
	/*@GetMapping("/cadastro/realizado")
	public String cadastroRealizado() {

	    return "fragments/mensagem";    
	 } */ 
	
	
	@GetMapping("/recuperar/senha")
	public String editarSenha() {
		return "user/editar-senha";
	}
	
	
	@PostMapping("/update")
	public ModelAndView update(@RequestParam("email") String email) {
		
		User u = service.getEmail(email);
		ModelAndView view = new ModelAndView("login");
		if(u == null) {
			
				view.addObject("error", "Email não está cadastrado no sistema!");
		}else {
			Random r = new Random();
			String novaSenhaGerada = Integer.toString(Math.abs(r.nextInt()), 36).substring(0, 6);
			System.out.println(novaSenhaGerada);
			u.setSenha(novaSenhaGerada);
			service.add(u);
			Email email2 = new Email();//Email email = new Email();
			email2.setTo(u.getEmail());
			//sendEmail.sendNovaSenhaEmail(email2, novaSenhaGerada);///dando um erro aqui
			view.addObject("mensagem", "Nova senha gerada!!!");
		}
		return view;		
	}
	
	
}
