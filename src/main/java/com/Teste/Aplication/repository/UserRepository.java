package com.Teste.Aplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Teste.Aplication.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	
	User findByEmail(String email);
	//pegando usuario do banco de dados
	//@Query("select u from usuario u where u.email like :email")
	//User findByEmail(@Param("email") String email);
	

	User findByNome(String username);
	
	

	
}
