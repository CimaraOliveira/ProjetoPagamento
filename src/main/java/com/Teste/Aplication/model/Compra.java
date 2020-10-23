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

	
	@OneToOne
	@JoinColumn(name = "idCartao")
	private CartaoCredito cartao;
	
	@OneToOne
	@JoinColumn(name = "idBoleto")
	private Boleto boleto;
	
	
	private double valor;
	
	private int quantidade;
	
	@ManyToOne
	@JoinColumn(name = "compra_id")
	@JsonIgnore
	public Compra compra; 
	
	
	//@Enumerated(EnumType.STRING)
	private TipoPagamento tipoPagamento;
	
    
	public Compra() {
		super();
		
	}
	
	public Compra(Long idCompra , TipoPagamento tipoPagamento, CartaoCredito cartao, Boleto boleto, double valor,
			int quantidade, Compra compra) {
		super();
		this.idCompra = idCompra ;
		this.tipoPagamento = tipoPagamento;
		this.cartao = cartao;
		this.boleto = boleto;
		this.valor = valor;
		this.quantidade = quantidade;
		this.compra = compra;
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

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public CartaoCredito getCartao() {
		return cartao;
	}

	public void setCartao(CartaoCredito cartao) {
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

	
	
	
	/*private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_compra;
	
    private int quantidade;
	
	@OneToMany(mappedBy = "compra")
	@JsonIgnore
	public List<Pagamento> pagamentos;
	
	
	
	public Compra() {
		super();
	}

	public Compra(Long id_compra, int quantidade, List<Pagamento> pagamentos) {
		super();
		this.id_compra = id_compra;
		this.quantidade = quantidade;
		this.pagamentos = pagamentos;
	}

	public Long getId_compra() {
		return id_compra;
	}

	public void setId_compra(Long id_compra) {
		this.id_compra = id_compra;
	}

	/*public double getPreco() { 
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
    
	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}*/

	
	
}
