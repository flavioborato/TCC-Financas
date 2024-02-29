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

import br.com.tcc.financas.model.Pessoa;
import br.com.tcc.financas.repository.PessoaRepository;

@Controller
@RequestMapping("/pessoas")
public class PessoasController {
	
	@Autowired
	private PessoaRepository pessoarepository;
	
	@GetMapping
	public String listarPessoa(Model model) {

		PageRequest paginacao = PageRequest.of(0, 20, Sort.by("idpessoa").descending()); 
		Page<Pessoa> pessoa =   pessoarepository.findAll(paginacao);
		model.addAttribute("pessoas", pessoa.getContent());
		return "pessoas";
		
		}
	
	
	@PostMapping("/{codigo}/excluir")
	public ModelAndView excluirPessoa(@PathVariable("codigo") Long codigo) {
		
		pessoarepository.deleteById(codigo);
		ModelAndView modelAndView = new ModelAndView("redirect:/pessoas");
		return modelAndView;
	}

}
