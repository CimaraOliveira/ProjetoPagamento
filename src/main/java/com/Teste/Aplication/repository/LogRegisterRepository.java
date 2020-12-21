package com.Teste.Aplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Teste.Aplication.model.LogRegister;


@Repository
public interface LogRegisterRepository extends JpaRepository<LogRegister, Long> {

	@Query
	public LogRegister findByHostOrigin(String hostOrigin);
}
