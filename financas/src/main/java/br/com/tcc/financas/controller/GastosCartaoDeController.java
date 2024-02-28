package br.com.tcc.financas.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.tcc.financas.model.GastosCartao;
import br.com.tcc.financas.repository.GastosCartaoRepository;

@Controller
@RequestMapping("/gastosdecartao")
public class GastosCartaoDeController {

	@Autowired
	private GastosCartaoRepository gastoscartaorepository;
	
		@GetMapping
		public String gastoscartaoclass(Model model) {
			
			List<GastosCartao> gastoscartao = gastoscartaorepository.findAll();
			model.addAttribute("gastoscartoes", gastoscartao);
			return "gastosdecartao";

		}

}
