package com.Teste.Aplication.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "boleto")
public class Boleto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idBoleto;
	private Integer numeroBoleto;
	private LocalDate dataCompra;
	
	public Long getIdBoleto() {
		return idBoleto;
	}
	public void setIdBoleto(Long idBoleto) {
		this.idBoleto = idBoleto;
	}
	public Integer getNumeroBoleto() {
		return numeroBoleto;
	}
	public void setNumeroBoleto(Integer numeroBoleto) {
		this.numeroBoleto = numeroBoleto;
	}
	public LocalDate getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(LocalDate dataCompra) {
		this.dataCompra = dataCompra;
	}
	@Override
	public String toString() {
		return "Boleto [idBoleto=" + idBoleto + ", numeroBoleto=" + numeroBoleto + ", dataVencimento=" + dataCompra
				+ "]";
	}
	
	
}

