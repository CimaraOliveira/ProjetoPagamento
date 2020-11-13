package com.Teste.Aplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Teste.Aplication.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	
	User findByEmail(String email);
	
	User findByNome(String username);
	
	

	
}
