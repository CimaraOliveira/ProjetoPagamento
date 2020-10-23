package com.Teste.Aplication.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class AbstractEntity{
//public abstract class AbstractEntity implements Serializable  {

	/*@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	public AbstractEntity() {
		super();
	}
	
	public AbstractEntity(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

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

	public boolean hasNotId() {
		return id == null;
	}

	public boolean hasId() {
		return id != null;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractEntity other = (AbstractEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Entidade %s id: %s", this.getClass().getName(), getId());
	}*/
	
	
	/*public Collection<? extends GrantedAuthority> getAuthority() {
		return this.nome;
	}*/
	
	
	
	
	
}
