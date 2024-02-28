package br.com.tcc.financas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.tcc.financas.model.Cartao;
import br.com.tcc.financas.repository.CartaoRepository;


@Controller
@RequestMapping("/cartao")
public class CartaoController {


	
	@Autowired
	private CartaoRepository cartaorepository;
	
		@GetMapping
		public String cartaoclass(Model model) {
			
			List<Cartao> cartao = cartaorepository.findAll();
			model.addAttribute("cartoes", cartao);
			return "cartao";
		}
		
		
}
