package br.com.tcc.financas.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import br.com.tcc.financas.model.Pessoa;
import br.com.tcc.financas.model.Renda;
import br.com.tcc.financas.repository.PessoaRepository;
import br.com.tcc.financas.repository.RendaRepository;

@Controller
@RequestMapping("/renda")
public class RendaController {
	
	@Autowired
	private RendaRepository  rendarepository;
	
	@Autowired
	private PessoaRepository pessoarepository;
	
	@GetMapping
	public String listarRenda(Model model) {
		
		PageRequest paginacao = PageRequest.of(0, 200, Sort.by("idrenda").descending()); 
		Page<Renda> renda = rendarepository.findAll(paginacao);
		List<Pessoa> pessoa = pessoarepository.findAll();
		model.addAttribute("rendas", renda.getContent());
		model.addAttribute("rendacadastro", new Renda());
		model.addAttribute("pessoas", pessoa);
		return "renda";
	}
	
	@PostMapping("/cadastrar")
	public ModelAndView cadastrarPessoa(@ModelAttribute("rendacadastro") Renda renda) {
		
		rendarepository.save(renda);	
		ModelAndView modelAndView = new ModelAndView("redirect:/renda");
		return modelAndView;
	}
	
	@PostMapping("/{codigo}/excluir")
	public ModelAndView excluirRenda(@PathVariable("codigo") Long codigo) {
		
		rendarepository .deleteById(codigo);
		ModelAndView modelAndView = new ModelAndView("redirect:/renda");
		return modelAndView;
	}
}
