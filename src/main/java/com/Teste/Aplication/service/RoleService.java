package com.Teste.Aplication.service;

import java.util.List;







import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Teste.Aplication.model.Role;
import com.Teste.Aplication.repository.RoleRepository;


@Service
public class RoleService {
	
	@Autowired
	private RoleRepository repository;
	
	public void save(Role role) {
		 repository.saveAndFlush(role);
	}
	
	public Role getNome(String nome) {
		return repository.findByNome(nome);
	}
	
	public List<Role> buscarTodos(){
		return repository.findAll();
	}

}
