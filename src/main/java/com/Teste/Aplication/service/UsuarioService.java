package com.Teste.Aplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Teste.Aplication.model.Usuario;
import com.Teste.Aplication.repository.UsuarioRepository;

@Service
public class UsuarioService{

	@Autowired
	private UsuarioRepository repository;

	@Transactional(readOnly = false)
	public void salvar(Usuario usuario) {
		repository.save(usuario);
	}

	@Transactional(readOnly = true)
	public List<Usuario> listar() {
		
		return repository.findAll();
	}

	@Transactional(readOnly = true)
	public Usuario buscarPorId(Long id) {
		
		return repository.findById(id).get();
				 
	}
	
	//@Transactional(readOnly = false)
	//public void editar(Usuario usuario) {
    // id.setId(id.getId());
    // repository.saveAndFlush(id);
    
		/*Usuario p2 = repository.findById(usuario.getId()).get();
		p2.setNome(usuario.getNome());
		p2.setEmail(usuario.getEmail());*/	
     
     
	

		/*@Transactional(readOnly = true)
	public Usuario buscarPorEmail(String email) {
		
		return repository.findByEmail(email);
	}
	

		
	@Transactional(readOnly = false)
	public void excluir(Long id) {
        repository.deleteById(id);
		
	}*/

	
	}
