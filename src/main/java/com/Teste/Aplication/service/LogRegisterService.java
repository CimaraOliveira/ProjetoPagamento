package com.Teste.Aplication.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Teste.Aplication.model.LogRegister;
import com.Teste.Aplication.repository.LogRegisterRepository;

@Service
public class LogRegisterService {

	@Autowired
	private LogRegisterRepository repository;
	
	public void save(LogRegister logRegister ) {
		 repository.saveAndFlush(logRegister);
	}
	
	public LogRegister getHostOrigin(String hostOrigin) {
		return repository.findByHostOrigin(hostOrigin);
	}
	
	
}
