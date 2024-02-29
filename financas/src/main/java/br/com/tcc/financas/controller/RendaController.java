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

import br.com.tcc.financas.model.Renda;
import br.com.tcc.financas.repository.RendaRepository;

@Controller
@RequestMapping("/renda")
public class RendaController {
	
	@Autowired
	private RendaRepository  rendarepository;
	
	@GetMapping
	public String listarRenda(Model model) {
		
		PageRequest paginacao = PageRequest.of(0, 200, Sort.by("idrenda").descending()); 
		Page<Renda> renda = rendarepository.findAll(paginacao);
		model.addAttribute("rendas", renda.getContent());
		return "renda";
	}
	
	@PostMapping("/{codigo}/excluir")
	public ModelAndView excluirRenda(@PathVariable("codigo") Long codigo) {
		
		rendarepository .deleteById(codigo);
		ModelAndView modelAndView = new ModelAndView("redirect:/renda");
		return modelAndView;
	}
}
