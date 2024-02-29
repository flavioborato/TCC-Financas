package br.com.tcc.financas.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.tcc.financas.model.Cartao;
import br.com.tcc.financas.repository.CartaoRepository;


@Controller
@RequestMapping("/cartao")
public class CartaoController {


	
	@Autowired
	private CartaoRepository cartaorepository;
	
		@GetMapping
		public String listarCartao(Model model) {
			
			PageRequest paginacao = PageRequest.of(0, 20, Sort.by("idcartao").descending()); 
			Page<Cartao> cartao = cartaorepository.findAll(paginacao);
			model.addAttribute("cartoes", cartao.getContent());
			return "cartao";
		}
		
		@PostMapping("/{codigo}/excluir")
		public ModelAndView excluirCartao(@PathVariable("codigo") Long codigo) {
			
			cartaorepository.deleteById(codigo);
			ModelAndView modelAndView = new ModelAndView("redirect:/cartao");
			return modelAndView;
		}
		
}
