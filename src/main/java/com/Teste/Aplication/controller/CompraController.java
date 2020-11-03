package com.Teste.Aplication.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Teste.Aplication.Enuns.TipoPagamento;
import com.Teste.Aplication.model.Cartao;
import com.Teste.Aplication.model.Compra;
import com.Teste.Aplication.repository.CompraRepository;
import com.Teste.Aplication.service.BoletoService;
import com.Teste.Aplication.service.CartaoService;

@Controller
@RequestMapping("/compras")
public class CompraController {

	@Autowired
	private CompraRepository compraService;

	@Autowired
	private CartaoService cartaoService;

	@Autowired
	private BoletoService boletoService;

	@GetMapping("/comprar")
	public String comprar(Compra compra) {
		return "compra/pagamento";

	}

	@GetMapping("/comp")
	public String comp(Compra compra) {
		return "compra/exemplo";

	}

	/*
	 * //CORRETO
	 * 
	 * @PostMapping("/salvar") public String salvar(Compra compra,RedirectAttributes
	 * attr, @RequestParam("tipoPagamento") TipoPagamento tipoPagamento) {
	 * 
	 * if(tipoPagamento.equals(TipoPagamento.CARTAO)) {
	 * 
	 * compraService.save(compra); return "compra/cartao";
	 * 
	 * 
	 * }else if(tipoPagamento.equals(TipoPagamento.BOLETO)) { //Boleto boleto =
	 * boletoService.gerarBoleto(); //attr.addFlashAttribute("success",
	 * "Boleto gerado com sucesso!"); compraService.save(compra); return
	 * "compra/boleto";
	 * 
	 * } //compraService.save(compra);
	 * //attr.addFlashAttribute("success","Operação realizada com sucesso!"); return
	 * "redirect:/home";
	 * 
	 * }
	 * 
	 * }
	 */

	public boolean validarNumeroCartao(String numero) {

		Integer numString;
		int soma = 0;

		if (numero.length() >= 16) {
			for (int i = 0; i < numero.length(); i++) {
				numString = Integer.parseInt(numero.substring(i, i + 1));
				if (i % 2 == 0) {

					if (numString * 2 > 9) {
						soma += (numString * 2 - 9);
					} else {
						soma += (numString * 2);
					}
				} else {
					soma += numString;
				}

			}

			return true;

		}
		return false;

	}

	// CORRETO
	@PostMapping("/salvar")
	public String salvar(Compra compra, RedirectAttributes attr, String numero,
			@RequestParam("tipoPagamento") TipoPagamento tipoPagamento) {

		if (tipoPagamento.equals(TipoPagamento.CARTAO)) {
			compraService.saveAndFlush(compra); // salva a compra para poder enviar o id
			attr.addAttribute("id", compra.getId()); // envia o id na requisição
			return "redirect:/cartao/cartao"; // redirect
		}

		else if (tipoPagamento.equals(TipoPagamento.BOLETO)) {
			// Boleto boleto = boletoService.gerarBoleto();
			// attr.addFlashAttribute("success", "Boleto gerado com sucesso!");
			//compraService.save(compra);
			return "redirect:/boleto";

		}

		// compraService.save(compra);
		attr.addFlashAttribute("success", "Operação realizada com sucesso!");
		return "redirect:/home";

	}

}
