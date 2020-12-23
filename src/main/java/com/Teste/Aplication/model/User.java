package com.Teste.Aplication.model;

import java.util.Collection;






import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Entity
@Table(name = "usuario")
public class User implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8798096104982666697L;

	private void consume() {
	RestTemplate restTemplate = new RestTemplate();
	String fooResourceUrl = "https://api-projetopagamento.herokuapp.com/api/user/findAll";
	ResponseEntity<User[]> response = restTemplate.getForEntity(fooResourceUrl + "/1", User[].class);
	
	    for(User user : response.getBody()) {
		    System.out.println(user);
	    }
	}
	
	private void consumeOne() {
		RestTemplate restTemplate = new RestTemplate(); //passa  o nome no broswer 
		String fooResourceUrl = "https://api-projetopagamento.herokuapp.com/api/user/findByNome/";
		ResponseEntity<User[]> response = restTemplate.getForEntity(fooResourceUrl , User[].class);
		
		    if(response.getStatusCode().is2xxSuccessful()) {
		    	User[] user = response.getBody();
			    System.out.println(user);
		    }
	}
	
	private void save() {
		
		RestTemplate restTemplate = new RestTemplate();  
		String fooResourceUrl = "https://api-projetopagamento.herokuapp.com/api/user/save";
		User user = new User();
		user.setNome("Mara");
		user.setEmail("cimarinhaoliveira1@gmail.com");
		user.setSenha("12345678");
		
		try {
		HttpEntity<User> request = new 	HttpEntity<>(user);	
		ResponseEntity<User> responseEntity = restTemplate.postForEntity(fooResourceUrl ,request, User.class);
		
		if(responseEntity.getStatusCode().is2xxSuccessful()) {
			System.out.println("Criando Novo User");
			User user2 = responseEntity.getBody();
			System.out.println(user2);
		}
		
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public User() {
		this.enabled = true;
		this.accountNonExpired = true;
		this.accountNonLocked = true;
		this.credentialsNonExpired = true;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, length = 40)
	private String nome;
	
	@Column(nullable = false, unique = true, length = 60)
	private String email;
	
	@Column(nullable = false, length = 255)
	private String senha;
	
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
	public List<Compra> compras;
	
    private boolean accountNonExpired;
	
	private boolean accountNonLocked;
	
	private boolean credentialsNonExpired;
	
	private boolean enabled;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany
	private Set<Role> role = new HashSet<Role>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.addAll(getRole());
		return authorities;
	}
	
	public Set<Role> getRole() {
		return role;
	}
	
	public void setRole(Set<Role> role) {
		this.role = role;
	}

	public void setUsername(String username) {
		this.email= username;
	}

	public void setPassword(String password) {
		this.senha = password;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	
	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;

	}   

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	/*public String getRoleString() {
		String txt="";
		if(role!=null) {
			for(Role r: role) {
				txt+= r.getNome() + ", "; 
			}
		}
		return txt;
	}*/

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}	
}
