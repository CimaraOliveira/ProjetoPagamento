package com.Teste.Aplication.json;


import java.math.RoundingMode;
import java.security.Principal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Teste.Aplication.Enuns.Status;
import com.Teste.Aplication.Enuns.TipoPagamento;
import com.Teste.Aplication.jwt.JwtComponent;
import com.Teste.Aplication.model.Boleto;
import com.Teste.Aplication.model.Cartao;
import com.Teste.Aplication.model.Pagameto;
import com.Teste.Aplication.model.LogRegister;
import com.Teste.Aplication.model.User;
import com.Teste.Aplication.service.BoletoService;
import com.Teste.Aplication.service.CartaoService;
import com.Teste.Aplication.service.LogRegisterService;
import com.Teste.Aplication.service.PagamentoService;
import com.Teste.Aplication.service.UserService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

class TestBoleto { // Classe Auxiliar
	public Boleto boleto;
	public Long idCompra;
}

class TestCartao{ // Classe Auxiliar
	public Cartao cartao;
	public Long idCompra;
}

@RestController
@RequestMapping("/api/compras")
@Api(value="API REST Pagamento")
@CrossOrigin(origins = {"*"})
public class PagamentoJson {
	@Autowired
	private JwtComponent jwtComponent;
	
	@Autowired
	private PagamentoService compraService;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private CartaoService cartaoService;
	
	@Autowired
	private UserService serviceUsuario;
	
	@Autowired
	private LogRegisterService logService;

	@PostMapping("/saveBoleto")
	public ResponseEntity<Boleto> salvarBoleto(HttpServletRequest request, @RequestBody TestBoleto test) {
		Pagameto compra = compraService.findByIdCompra(test.idCompra);
			

		String origin = request.getHeader("Origin");		
		LogRegister logRegister = new LogRegister();
		logRegister.setHostOrigin(origin);
		logRegister.setDate(new Date()); 
		logService.save(logRegister);	
		
		
		if (compra != null) {
			boletoService.salvarBoleto(test.boleto);
			test.boleto.setDataCompra(new Date());
			compra.setBoleto(test.boleto);
			compraService.salvarCompra(compra);
			return ResponseEntity.status(HttpStatus.CREATED).body(test.boleto);
		}

		return ResponseEntity.status(400).build();
	}

	@PostMapping("/saveCartao")
	public ResponseEntity<Cartao> salvarCartao(@RequestBody TestCartao test){
		Pagameto compra = compraService.findByIdCompra(test.idCompra);
		
        	
		if(compra != null) {
			cartaoService.salvarCartao(test.cartao);
			compra.setCartao(test.cartao);
			compraService.salvarCompra(compra);
			return ResponseEntity.status(HttpStatus.CREATED).body(test.cartao);
		}
		
		return ResponseEntity.status(400).build();
	}
	
	@GetMapping("/detalhesCompra/{id}")
	@ApiOperation(value="Retorna os Pagamentos pelo id do usuario")
	public ResponseEntity<Pagameto> detalhePorId(@PathVariable("id") Long id,
			@RequestHeader(value = "Authorization", required = false) String Authorization) {
        System.out.println(Authorization); 
		try {
			System.out.println(id);
			
			boolean isValid = jwtComponent.isTokenExpired(Authorization.substring(7));
			if (!isValid) { 
				Pagameto compras = compraService.findByIdCompra(id);
						
				if (compras != null) {
					return ResponseEntity.ok(compras);
				}
				return ResponseEntity.notFound().build();
			}
		} catch (ExpiredJwtException | SignatureException e) {
			return ResponseEntity.status(403).build();
		}
		return ResponseEntity.status(400).build();
	}
	
	@GetMapping("/detalhes")
	@ApiOperation(value="Retorna todos os Pagamentos do Usuário")
	public ResponseEntity<Pagameto> detalhes(@PathVariable("email") String email, @RequestBody  Principal principal,
			@RequestHeader(value = "Authorization", required = false) String Authorization) {
        System.out.println(Authorization); 
		try {
			System.out.println(email);
			
			boolean isValid = jwtComponent.isTokenExpired(Authorization.substring(7));
			if (!isValid) { 
				Long id_compra = serviceUsuario.getEmail(principal.getName()).getId();
				Pagameto compras = (Pagameto) compraService.findAllByIdUser(id_compra);	
						
				if (compras != null) {
					return ResponseEntity.ok(compras);
				}
				return ResponseEntity.notFound().build();
			}
		} catch (ExpiredJwtException | SignatureException e) {
			return ResponseEntity.status(403).build();
		}
		return ResponseEntity.status(400).build();
	}
	private double calPMT(double pv, int n, String i){///aquiiiiiiiiiiiiii
		
      	
	       // System.out.println( decimalFormat.format(valor) );
	        String porcent [] = i.split("%");
	        double taxa = Double.parseDouble(porcent[0]) / 100;
	        double resultOne = (Math.pow((1 + taxa), n) - 1);
	        double resultTwo = ((Math.pow(1 + taxa, n) * taxa));
	        double resultThree = resultOne / resultTwo;
	        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
		    decimalFormat.setRoundingMode(RoundingMode.DOWN);
		    
		    String valor[]=decimalFormat.format(pv/resultThree).split(",");
	        return Double.parseDouble(valor[0]+"."+ valor[1]);
	 }	
	@PostMapping("/saveCompra")
	@ApiOperation(value="Salva uma compra no cartao ou boleto se usuarip for autenticado")
	public ResponseEntity<Pagameto> salvarCompra(@RequestHeader(value="Origin", required = true) String origin,
			@RequestBody Pagameto pagamento,@RequestHeader(value = "Authorization", required =true) String Authorization) {
		
		LogRegister logRegister = new LogRegister();
		logRegister.setHostOrigin(origin);
		logRegister.setDate(new Date()); 
		logService.save(logRegister);
		
		if(Authorization == null) { // verifica se na requisição tem o token no header
			return ResponseEntity.status(400).build();
		}else if(Authorization.trim().isEmpty()) { // verifica se o token no header não é vazio
			return ResponseEntity.status(400).build();
		}
		
		try {
			boolean isValid = jwtComponent.isTokenExpired(Authorization.substring(7));
			if(!isValid) { // verifica se o token é valido e não expirou
				if(pagamento != null) { // verifica se o pagamento é diferente de nulo. Se for verdadeiro o fluxo continua...
					if(pagamento.getTipoPagamento() != null) { // verifica se o tipo de pagamento existe, caso seja verdadeiro ele continua o fluxo...
						User user = serviceUsuario.findByEmail(pagamento.getUsuario().getEmail());
						if(user != null) { // verifica se o usuario passado na requisição existe na base de dados. Se for verdadeiro o fluxo continua...
							if(pagamento.getTipoPagamento().equals(TipoPagamento.BOLETO)) {
								if(pagamento.getBoleto() != null) { // verifica se existe o boleto na requisição
									Boleto boleto = pagamento.getBoleto();
									pagamento.setStatus(Status.ANDAMENTO);
									boleto.setDataCompra(new Date());
									Random r = new Random();
									String codigo = String.valueOf(Math.abs(r.nextInt()*100000000));
									System.out.println(codigo);
									boleto.setNumeroBoleto(codigo);
									boleto.setDataVencimento(LocalDate.now().plusDays(5));
									boletoService.salvarBoleto(boleto);
								}else { // se não existe deve informar
									return ResponseEntity.status(400).build(); 
								}
							}else if(pagamento.getTipoPagamento().equals(TipoPagamento.CARTAO)) {
								if(pagamento.getCartao() != null) {// verifica se existe o cartao na requisição
									
									Cartao cartao = pagamento.getCartao();
									cartao.setValor_parcelado(calPMT(pagamento.getValor(), cartao.getQtd_parcelas(), "2%"));
									//cartao.setValor_parcelado(cartao.getValor_parcelado());
									pagamento.setStatus(Status.CONCLUÍDA);
									cartaoService.salvarCartao(cartao);
								}else {// se não existe deve informar
									return ResponseEntity.status(400).build();
								}
							}
							// caso existe o boleto ou cartão. 
							pagamento.setDataCompra(new Date());
							pagamento.setValor(pagamento.getValor() * pagamento.getQuantidade());
							pagamento.setUsuario(user);
							compraService.salvarCompra(pagamento);
							return new ResponseEntity<Pagameto>(pagamento, HttpStatus.CREATED);
						}
					}
				}
			}
		} catch (ExpiredJwtException | SignatureException e) {
			return ResponseEntity.status(403).build();// retorna caso o token não seja valido
		} catch (NoSuchElementException e) {// retorna caso o id não se encontra na base de dados
			return ResponseEntity.status(400).build();
		}
		return ResponseEntity.status(400).build(); // retorna caso alguma condição acima seja falso
	}
}