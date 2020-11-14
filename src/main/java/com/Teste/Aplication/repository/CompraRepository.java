package com.Teste.Aplication.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Teste.Aplication.model.Compra;

public interface CompraRepository extends JpaRepository<Compra, Long>{

	@Query(value="select * from compras c\n" + 
			"inner join usuario u on(c.usuario_id = u.id)\n" + 
			"where u.id = id;", nativeQuery = true)
	List<Compra> findAllByIdUser(@Param("id") Long id);
	
	Compra findByIdCompra(Long id);

	

}
