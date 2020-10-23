package com.Teste.Aplication.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.Teste.Aplication.service.UserService;
@Repository
@Transactional
public class UserDetailsRepository {

	@Autowired
	private UserService userService;

	/*@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.Teste.Aplication.model.User user = userService.findByNome(username);
		if(user == null)
			throw new UsernameNotFoundException("Usuario não encontrado");
		return new User(user.getNome(), user.getSenha(), true,true,true,true,user.getAuthority());

	}
	*/
	/*@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.Teste.Aplication.model.User user = userService.findByNome(username);
		if(user == null)
			throw new UsernameNotFoundException("Usuario não encontrado");
		//return new User(user.getNome(), user.getSenha(), true,true,true,true,user.getAuthorities());
		//return new User(user.getNome(), user.getSenha());
		//return user;
		//com.Teste.Aplication.model.User useFinal = new com.Teste.Aplication.model.User();
		return (UserDetails) user;
		/*org.springframework.security.core.userdetails.User userFinal = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getPermissoes(user));

		return userFinal;
		 * 
		 * 
	}*/

}
