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

import br.com.tcc.financas.model.GastosMensais;
import br.com.tcc.financas.repository.GastosMensaisRepository;

@Controller
@RequestMapping("/gastosmensais")
public class GastosMensaisController {
	
	@Autowired
	private GastosMensaisRepository gastosmensaisrepository;
	
	@GetMapping
	public String listarGastosMensais(Model model) {
		
		PageRequest paginacao = PageRequest.of(0, 200, Sort.by("idgastosmensais").descending());
		Page<GastosMensais> gastosmesal = gastosmensaisrepository.findAll(paginacao);
		model.addAttribute("gastosmensais", gastosmesal.getContent());
		return "gastosmensais";
	}
	
	@PostMapping("/{codigo}/excluir")
	public ModelAndView excluirGastosMensais(@PathVariable("codigo") Long codigo) {
		
		gastosmensaisrepository.deleteById(codigo);
		ModelAndView modelAndView = new ModelAndView("redirect:/gastosmensais");
		return modelAndView;
	}
}
