package com.Teste.Aplication.model;

import java.io.Serializable;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.Teste.Aplication.Enuns.TipoPagamento;

@Entity
@Table(name = "pagamento")
public class Pagamento implements Serializable{

	/*private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idPagamento;	

	@Enumerated(EnumType.STRING)
	private TipoPagamento tipoPagamento;
	
    private double valor;
	
	private int quantidade;
	
	private LocalDate dataCadastro;
	
	
	@OneToOne
	@JoinColumn(name = "idCartao")
	private CartaoCredito cartao;
	
	@OneToOne
	@JoinColumn(name = "idBoleto")
	private Boleto boleto;
	
	
	public Pagamento(Long idPagamento, TipoPagamento tipoPagamento, double valor, int quantidade,
			LocalDate dataCadastro, CartaoCredito cartao, Boleto boleto) {
		super();
		this.idPagamento = idPagamento;
		this.tipoPagamento = tipoPagamento;
		this.valor = valor;
		this.quantidade = quantidade;
		this.dataCadastro = dataCadastro;
		this.cartao = cartao;
		this.boleto = boleto;
	}

	public Pagamento() {
		super();
		
	}	
	
	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento) {
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

	public Boleto getBoleto() {
		return boleto;
	}

	public void setBoleto(Boleto boleto) {
		this.boleto = boleto;
	}

	public Long getIdPagamento() {
		return idPagamento;
	}

	public void setIdPagamento(Long idPagamento) {
		this.idPagamento = idPagamento;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public CartaoCredito getCartao() {
		return cartao;
	}

	public void setCartao(CartaoCredito cartao) {
		this.cartao = cartao;
	}*/
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idPagamento;	

	@Enumerated(EnumType.STRING)
	private TipoPagamento tipoPagamento;
	
    private double valor;
	
	private LocalDate dataCadastro;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "compra_id")
	public Compra compra;
	
	public Pagamento() {
		super();
		
	}	
	
	public Pagamento(Long idPagamento, TipoPagamento tipoPagamento, double valor, int quantidade,
			LocalDate dataCadastro) {
		super();
		this.idPagamento = idPagamento;
		this.tipoPagamento = tipoPagamento;
		this.valor = valor;
		this.dataCadastro = dataCadastro;
	}

	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Long getIdPagamento() {
		return idPagamento;
	}

	public void setIdPagamento(Long idPagamento) {
		this.idPagamento = idPagamento;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

}
