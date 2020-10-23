package com.Teste.Aplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Teste.Aplication.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	// @Query("select u from usuarios u where u.email = :email")
	//// Usuario findByEmail(String email);

	//@Query("select u from usuario u where u.email = ?1")
	//public Usuario findByEmail(String email);
		
	/*@Query("select u from usuarios u where u.id = :id")
	void editar(Usuario usuario);*/

	/*@Query(value="select * from usuarios u where u.id = ?")
    public Optional<Usuario> findById(Long id);
	

	@Query("select u from Usuario u where u.id like :id\"")
	Usuario findById(@Param("email") Long id);*/

	//@Query
	//void editar(Long id);

	/*@Query
	void update(Usuario u);
	

	
	

*/
}
