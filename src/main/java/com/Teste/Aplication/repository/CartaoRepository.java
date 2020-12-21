package com.Teste.Aplication.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Teste.Aplication.model.Cartao;

	@Repository
	public interface CartaoRepository extends JpaRepository<Cartao, Long> {
	
		@Query(value="select * from compras cs \n" + 
				"inner join cartao c on(cs.id_cartao = c.id_cartao) \n" + 
				"where c.id_cartao = :id_cartao", nativeQuery = true)
		public Cartao idCartao(@Param("id_cartao")Long id);
	}



