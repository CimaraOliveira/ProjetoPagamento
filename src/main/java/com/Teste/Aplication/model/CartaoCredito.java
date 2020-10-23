package com.Teste.Aplication.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cartao")
public class CartaoCredito implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_cartao;
	private String nome;
	private String numero;
	private Date dataValidade;
	
	@Column(length = 3)
	private String cvv;
	private String email;
	public CartaoCredito(Long id_cartao, String nome, String numero, Date dataValidade, String cvv) {
		super();
		this.id_cartao = id_cartao;
		this.nome = nome;
		this.numero = numero;
		this.dataValidade = dataValidade;
		this.cvv = cvv;
	}
	
	public CartaoCredito() {

	}

	public Long getId_Cartao() {
		return id_cartao;
	}

	public void setId_Cartao(Long id_Cartao) {
		this.id_cartao = id_Cartao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	
	

	public Long getId_cartao() {
		return id_cartao;
	}

	public void setId_cartao(Long id_cartao) {
		this.id_cartao = id_cartao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}