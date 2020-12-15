package com.Teste.Aplication.model;

import java.io.Serializable;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Entity
@Table(name = "cartao")
public class Cartao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_cartao;
	private String nome;
	private String numero;
	@Column(length = 3)
	private String cvv;
	
	private int qtd_parcelas;
	
	double juros;
	
	private int mes;
	
	private int ano;
		
	@OneToMany(mappedBy = "cartao")
	@JsonIgnore
	private List<Compra>compras;
	
	public Cartao(Long id_cartao, String nome, String numero, String cvv, int mes, int ano, List<Compra> compras,
			           int qtd_parcelas, double juros) {
		super();
		this.id_cartao = id_cartao;
		this.nome = nome;
		this.numero = numero;
		this.cvv = cvv;
		this.mes = mes;
		this.ano = ano;
		this.compras = compras;
		this.qtd_parcelas = qtd_parcelas;
		this.juros = juros;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Cartao() {

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

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public Long getId_cartao() {
		return id_cartao;
	}

	public void setId_cartao(Long id_cartao) {
		this.id_cartao = id_cartao;
	}

	public int getQtd_parcelas() {
		return qtd_parcelas;
	}

	public void setQtd_parcelas(int qtd_parcelas) {
		this.qtd_parcelas = qtd_parcelas;
	}

	public double getJuros() {
		return juros;
	}

	public void setJuros(double juros) {
		this.juros = juros;
	}

	@Override
	public String toString() {
		return "Cartao [id_cartao=" + id_cartao + ", nome=" + nome + ", numero=" + numero + ", cvv=" + cvv
				+ ", qtd_parcelas=" + qtd_parcelas + ", mes=" + mes + ", ano=" + ano + ", compras=" + compras + "]";
	}

	
	
	
}