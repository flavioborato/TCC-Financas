package br.com.tcc.financas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RendaController {
	
	@GetMapping("/renda")
	public String teste(Model model) {
		return "renda";
	}

}
