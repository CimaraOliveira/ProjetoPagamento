package com.Teste.Aplication.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Teste.Aplication.model.Compra;

public interface CompraRepository extends JpaRepository<Compra, Long>{

	@Query(value="select * from compras c\n" + 
			"inner join usuario u on(c.usuario_id = u.id)\n" + 
			"where u.id = :id", nativeQuery = true)
	public List<Compra> findAllByIdUser(@Param("id") Long id);
	
	Compra findByIdCompra(Long id);

	@Query(value="select * from compras c\n" + 
			"inner join boleto b on(c.id_boleto = b.id_boleto) \n" + 
			"where b.id_boleto = :id_boleto", nativeQuery = true)
	public List<Compra> findByIdBoleto(@Param("id_boleto") Long id_boleto);
	
	@Query(value="select * from compras c\n" + 
			"inner join cartao b on(c.id_cartao = b.id_cartao) \n" + 
			"where b.id_cartao = :id_cartao", nativeQuery = true)
	public List<Compra> findByIdCartao(@Param("id_cartao") Long id_cartao);

}
