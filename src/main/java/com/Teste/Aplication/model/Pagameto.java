package com.Teste.Aplication.model;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.Teste.Aplication.Enuns.Status;
import com.Teste.Aplication.Enuns.TipoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Entity
@Table(name = "compras")
public class Pagameto implements Serializable{
	
		
	public ResponseEntity<Pagameto>apiPagamento (@RequestBody Pagameto pagamento,@RequestParam("tipoPagamento") TipoPagamento tipoPagamento,@RequestParam("quantidade") int quantidade,
			                        @RequestParam("id") long id,@RequestParam("valor") double valor ){
		RestTemplate restTemplate = new RestTemplate();  
		String fooResourceUrl = "https://api-projetopagamento.herokuapp.com/api/compras/salvar";
		HttpEntity<Pagameto> request = new HttpEntity<>(pagamento);
		ResponseEntity<Pagameto> responseEntity = restTemplate.postForEntity(fooResourceUrl ,request, Pagameto.class);
		pagamento.setDataCompra(new Date());
		pagamento.setValor(pagamento.getValor() * pagamento.getQuantidade());
		User user = new User();
		pagamento.setUsuario(user);
		
		if(tipoPagamento.equals(TipoPagamento.CARTAO)) {
			pagamento.setIdCompra(id);
			cartao();
		}
		
		else if (tipoPagamento.equals(TipoPagamento.BOLETO)) {
			pagamento.setIdCompra(id);
			Boleto();
		}
		
		if(responseEntity.getStatusCode().is2xxSuccessful()) {
			Pagameto pag = responseEntity.getBody();			
			System.out.println(pagamento);
		}
		
		return ResponseEntity.status(400).build();
	}
	
	
	private void cartao() {
		RestTemplate restTemplate = new RestTemplate();  
		String fooResourceUrl = "https://api-projetopagamento.herokuapp.com/api/cartao/salvarCartao";
		Cartao cartao = new Cartao();
		cartao.setNumero("5142391129300290");
		cartao.setCvv("123");
		cartao.setMes(02);
		cartao.setAno(2023);
		cartao.setQtd_parcelas(4);	
				
		ResponseEntity<Cartao> responseEntity = restTemplate.postForEntity(fooResourceUrl, cartao, Cartao.class);
		if(responseEntity.getStatusCode().is2xxSuccessful()) {
			System.out.println("Salvando cartao");
			Cartao cartao1 = responseEntity.getBody();
			System.out.println(cartao1);
		}
		
		
	}

	
	private void Boleto() {
		RestTemplate restTemplate = new RestTemplate();  
		String fooResourceUrl = "https://api-projetopagamento.herokuapp.com/api/boleto/saveBoleto";
		Boleto boleto = new Boleto();
		boleto.setDataCompra(new Date());
		boleto.setNumeroBoleto("3333333333");
				
		ResponseEntity<Boleto> responseEntity = restTemplate.postForEntity(fooResourceUrl ,boleto, Boleto.class);
		
		if(responseEntity.getStatusCode().is2xxSuccessful()) {
			System.out.println("Salvando compra boleto");
			Boleto boleto1 = responseEntity.getBody();
			System.out.println(boleto1);
		}
				
	}

	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idCompra;	

	
	@ManyToOne
	@JoinColumn(name = "idCartao")
	private Cartao cartao;
	
	@ManyToOne
	@JoinColumn(name = "idBoleto")
	private Boleto boleto;
	
	private double valor;
	
	private int quantidade;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataCompra;
	
	private TipoPagamento tipoPagamento;
	
	private Status status;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id")
	//@JsonIgnore
	public User usuario;
	
	@OneToOne(mappedBy="compra")
	private LogRegister logRegister;
    
	public Pagameto() {
		super();		
	}	
	
	public Pagameto(Long idCompra, Cartao cartao, Boleto boleto, double valor, int quantidade, Date dataCompra,
			TipoPagamento tipoPagamento, Status status, User usuario) {
		super();
		this.idCompra = idCompra;
		this.cartao = cartao;
		this.boleto = boleto;
		this.valor = valor;
		this.quantidade = quantidade;
		this.dataCompra = dataCompra;
		this.tipoPagamento = tipoPagamento;
		this.status = status;
		this.usuario = usuario;
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
	public User getUsuario() {
		return usuario;
	}
	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}
	public Date getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}	
	
				
}
