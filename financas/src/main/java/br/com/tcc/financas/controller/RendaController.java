package br.com.tcc.financas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.tcc.financas.model.Renda;
import br.com.tcc.financas.repository.RendaRepository;

@Controller
@RequestMapping("/renda")
public class RendaController {
	
	@Autowired
	private RendaRepository  rendarepository;
	
	@GetMapping
	public String rendaclass(Model model) {
		
		List<Renda> renda = rendarepository.findAll();
		model.addAttribute("rendas", renda);
		return "renda";
	}

}
