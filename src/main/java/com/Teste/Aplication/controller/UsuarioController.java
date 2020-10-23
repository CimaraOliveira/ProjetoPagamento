package com.Teste.Aplication.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Teste.Aplication.model.Usuario;
import com.Teste.Aplication.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired 
	private UsuarioService service; 
	
	@GetMapping("/cadastrar")   
	public String cadastrar(Usuario usuario) {
		return "usuario/cadastro";    
	} 
	
	@GetMapping("/ex")   
	public String ex(Usuario usuario) {
		return "usuario/ex";    
	} 
	
	
	/*@PostMapping("/salvar") 
	public String salvar(Usuario usuario, RedirectAttributes attr) {
		service.salvar(usuario);
		attr.addFlashAttribute("success", "Usuário Cadastrado com sucesso!");
		return "usuario/cadastro";
	}*/
	
	
	@PostMapping("/salvar") 
	public String salvar(@Valid Usuario usuario,BindingResult result, RedirectAttributes attr) {
		if(result.hasErrors()) { 
			return "usuario/cadastro";
		}
		service.salvar(usuario);
		attr.addFlashAttribute("success", "Usuário Cadastrado com sucesso!");
		return "redirect:/usuarios/cadastrar";  
	}
	  
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("usuarios", service.listar());
		return "usuario/lista";
	}  
	 
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("usuario", service.buscarPorId(id));
		return "usuario/cadastro";
		
	}
	
	/*@PostMapping("/editar")
	public String editar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "usuario/cadastro";
		}
		service.editar(usuario);
		attr.addFlashAttribute("success", "Dados alterados com sucesso!");
		return "redirect:/usuarios/cadastrar";
	}*/
	
	/*@GetMapping("/excluir/{id}") 
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		service.excluir(id);    
		attr.addFlashAttribute("success", "Exclusão realizada com sucesso!");
		//return "redirect:/usuarios/listar";
		return "usuario/lista"; 
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id) {
		
		service.excluir(id);
		
		return "usuario/lista";
	}        
		*/
	
}
