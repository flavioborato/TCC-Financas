package br.com.tcc.financas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.tcc.financas.model.Pessoa;
import br.com.tcc.financas.repository.PessoaRepository;

@Controller
@RequestMapping("/pessoas")
public class PessoasController {
	
	@Autowired
	private PessoaRepository pessoarepository;
	
	
	@GetMapping
	public String pessoaclass(Model model) {

		List<Pessoa> pessoa = pessoarepository.findAll();
		model.addAttribute("pessoas", pessoa);
		return "pessoas";
		
	}

}
