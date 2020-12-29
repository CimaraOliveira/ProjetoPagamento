package com.Teste.Aplication.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Teste.Aplication.model.Pagameto;

public interface PagamentoRepository extends JpaRepository<Pagameto, Long>{

	@Query(value="select * from compras c\n" + 
			"inner join usuario u on(c.usuario_id = u.id)\n" + 
			"where u.id = :id", nativeQuery = true)
	public List<Pagameto> findAllByIdUser(@Param("id") Long id);
	
	Pagameto findByIdCompra(Long id);

	@Query(value="select * from compras c\n" + 
			"inner join boleto b on(c.id_boleto = b.id_boleto) \n" + 
			"where b.id_boleto = :id_boleto", nativeQuery = true)
	public List<Pagameto> findByIdBoleto(@Param("id_boleto") Long id_boleto);
	
	@Query(value="select * from compras c\n" + 
			"inner join cartao b on(c.id_cartao = b.id_cartao) \n" + 
			"where b.id_cartao = :id_cartao", nativeQuery = true)
	public List<Pagameto> findByIdCartao(@Param("id_cartao") Long id_cartao);
	

	@Query(value="select * from compras c\n" + 
			"inner join usuario u on(c.usuario_id = u.id)\n" + 
			"where u.email = :email", nativeQuery = true)
	public List<Pagameto> findByEmail(@Param("email") String email);

}
