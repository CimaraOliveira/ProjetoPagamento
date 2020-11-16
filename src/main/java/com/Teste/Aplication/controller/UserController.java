package com.Teste.Aplication.controller;


import java.util.Random;



import javax.validation.Valid;



import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
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

import com.Teste.Aplication.model.Email;
import com.Teste.Aplication.model.Role;
import com.Teste.Aplication.model.User;
import com.Teste.Aplication.service.EmailService;
import com.Teste.Aplication.service.RoleService;
import com.Teste.Aplication.service.UserService;



@Controller 	
@RequestMapping("/user")
public class UserController{

	@Autowired
	private UserService service;
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private EmailService sendEmail;
	  
	@GetMapping("/cadastrar")    
	public String cadastrar(User user) {
		return "user/cadastro";   
	}  	  
	  
	@PostMapping("/salvar") 
	public String salvar(@Valid User user,BindingResult result, RedirectAttributes attr) {
		if(result.hasErrors()) { 
			return "user/cadastro";
		}
		    User u = service.getEmail(user.getEmail());
		
			String senha = user.getSenha();
			user.setSenha(new BCryptPasswordEncoder().encode(senha));
			service.salvar(user);
			attr.addFlashAttribute("success", "Usuário Cadastrado com sucesso!");
			 
		
		return "redirect:/usuario/cadastrar";
	} 
	 
	@GetMapping("/detalhes")
	public ModelAndView detalhePorId() {
		User user = service.getEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		ModelAndView mv = new ModelAndView("user/detalhes");
		mv.addObject("usuario", service.findById(user.getId()));
		mv.addObject("usuario", user);
		return mv;
		
	}
	
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
			Role role = roleService.getNome("USER");
			if(role != null) {
				user.getRole().add(role);
			}
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
	
	@GetMapping("/editarSenha")
	public String editarSenha() {
		return "user/editarSenha";
	}
	
	
	@PostMapping("/trocarSenha")
	public ModelAndView trocarSenha(@RequestParam("email") String email) {
		
		User user2 = service.getEmail(email);
		ModelAndView view = new ModelAndView("login");
		if(user2 == null) {
			
				view.addObject("error", "Email não está cadastrado no sistema!");
		}else {
			Random r = new Random();
			String novaSenhaGerada = Integer.toString(Math.abs(r.nextInt()), 36).substring(0, 6);
			System.out.println(novaSenhaGerada);
			user2.setSenha(novaSenhaGerada);
			service.salvar(user2);
			Email email2 = new Email();
			email2.setTo(user2.getEmail());
			sendEmail.sendNovaSenhaEmail(email2, novaSenhaGerada);
			view.addObject("mensagem", "Nova senha gerada!!!");
		}
		return view;		
	}

   
}
