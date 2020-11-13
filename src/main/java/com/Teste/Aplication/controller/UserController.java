package com.Teste.Aplication.controller;


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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Teste.Aplication.model.Role;
import com.Teste.Aplication.model.User;
import com.Teste.Aplication.service.RoleService;
import com.Teste.Aplication.service.UserService;

@Controller 	
@RequestMapping("/user")
public class UserController{

	@Autowired
	private UserService service;
	@Autowired
	private RoleService roleService;
	  
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
	

   
}
