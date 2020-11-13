package com.Teste.Aplication.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.Teste.Aplication.model.Compra;

public interface CompraRepository extends JpaRepository<Compra, Long>{

	Compra findByIdCompra(Long id);

	

}
