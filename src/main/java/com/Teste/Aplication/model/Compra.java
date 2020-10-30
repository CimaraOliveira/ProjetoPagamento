package com.Teste.Aplication.model;

import java.io.Serializable;



import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.Teste.Aplication.Enuns.TipoPagamento;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "compras")
public class Compra implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idCompra;	

	
	@ManyToOne
	@JoinColumn(name = "idCartao")
	private Cartao cartao;
	
	@OneToOne
	@JoinColumn(name = "idBoleto")
	private Boleto boleto;
	
	
	private double valor;
	
	private int quantidade;
	
	/*@ManyToOne
	@JoinColumn(name = "compra_id")
	@JsonIgnore
	public Compra compra; */
	
	
	//@Enumerated(EnumType.STRING)
	private TipoPagamento tipoPagamento;
	
    
	public Compra() {
		super();
		
	}
	
	

	
	public Compra(Long idCompra, Cartao cartao, Boleto boleto, double valor, int quantidade,
			TipoPagamento tipoPagamento) {
		super();
		this.idCompra = idCompra;
		this.cartao = cartao;
		this.boleto = boleto;
		this.valor = valor;
		this.quantidade = quantidade;
		this.tipoPagamento = tipoPagamento;
	}




	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Long getId() {
		return idCompra ;
	}

	public void setId(Long idCompra ) {
		this.idCompra  = idCompra ;
	}

	

	public Long getIdCompra() {
		return idCompra;
	}




	public void setIdCompra(Long idCompra) {
		this.idCompra = idCompra;
	}




	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public Boleto getBoleto() {
		return boleto;
	}

	public void setBoleto(Boleto boleto) {
		this.boleto = boleto;
	}

	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

		
}
