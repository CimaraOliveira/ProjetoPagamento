package com.Teste.Aplication.controller;


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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Teste.Aplication.model.User;
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
	 
	/*@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("usuario", service.findAll());
		return "user/lista";  
	}  */	
		
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("user", service.findById(id));
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
	}
	
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
	
	//////////////////////////////
	
	/*@GetMapping("/cadastro/realizado")
	public String cadastroRealizado() {

	    return "fragments/mensagem";    
	 } */ 
   
	
	@GetMapping("/recuperar/senha")
	public String editarSenha() {
		return "user/editar-senha";
	}
	
	
	// form de pedido de recuperar senha
    /*@GetMapping("/p/recuperar/senha")
    public String redefinirSenha(String email, ModelMap model) throws MessagingException {
    	service.pedidoRedefinicaoDeSenha(email);
    	model.addAttribute("sucesso", "Em instantes você reberá um e-mail para "
    			+ "prosseguir com a redefinição de sua senha.");
    	model.addAttribute("usuario", new Usuario(email));
    	return "usuario/recuperar-senha";
    }*/
    
    // salvar a nova senha via recuperacao de senha
   /* @PostMapping("/p/nova/senha")
    public String confirmacaoDeRedefinicaoDeSenha(Usuario usuario, ModelMap model) {
    	Usuario u = service.buscarPorEmail(usuario.getEmail());
    	if (!usuario.getCodigoVerificador().equals(u.getCodigoVerificador())) {
    		model.addAttribute("falha", "Código verificador não confere.");
    		return "usuario/recuperar-senha";
    	}
    	u.setCodigoVerificador(null);
    	service.alterarSenha(u, usuario.getSenha());
    	model.addAttribute("alerta", "sucesso");
    	model.addAttribute("titulo", "Senha redefinida!");
    	model.addAttribute("texto", "Você já pode logar no sistema.");
    	return "login";
    }*/ 
}
