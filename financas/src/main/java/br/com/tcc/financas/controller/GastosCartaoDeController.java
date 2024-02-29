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

import br.com.tcc.financas.model.GastosCartao;
import br.com.tcc.financas.repository.GastosCartaoRepository;

@Controller
@RequestMapping("/gastosdecartao")
public class GastosCartaoDeController {

	@Autowired
	private GastosCartaoRepository gastoscartaorepository;
	
		@GetMapping
		public String listarGastosCartao(Model model) {
			
			PageRequest paginacao = PageRequest.of(0, 200, Sort.by("idgastoscartao").descending()); 
			Page<GastosCartao> gastoscartao = gastoscartaorepository.findAll(paginacao);
			model.addAttribute("gastoscartoes", gastoscartao.getContent());
			return "gastosdecartao";

		}

		@PostMapping("/{codigo}/excluir")
		public ModelAndView excluirGastosCartao(@PathVariable("codigo") Long codigo) {
			
			gastoscartaorepository.deleteById(codigo);
			ModelAndView modelAndView = new ModelAndView("redirect:/gastosdecartao");
			return modelAndView;
		}
		
}
