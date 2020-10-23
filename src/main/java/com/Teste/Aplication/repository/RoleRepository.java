package com.Teste.Aplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Teste.Aplication.model.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	@Query("select r from Role r where r.nome = ?1")
	public Role findByNome(String nome);
}
