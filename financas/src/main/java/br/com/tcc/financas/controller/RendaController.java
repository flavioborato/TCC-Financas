/*
 *   TCC Engenharia de Software
 * Projeto : Cadastro de Finanças
 * Autor : Flávio Fernando Borato
 * Versão : 0.0
 * Revisão : 03/06/2024
 * Classe - Controle da renda
 * */

package br.com.tcc.financas.controller;


import java.util.List;
import java.util.Optional;

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
	
	/*
	* Carregando os dados salvos na DB ao carregar a pagina
	 * */
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
	
	/*
	* Recebe os dados e efetua o cadastro dos dados na DB
	 * */
	@PostMapping("/cadastrar")
	public ModelAndView cadastrarRenda(@ModelAttribute("rendacadastro") Renda renda) {
		
		rendarepository.save(renda);	
		ModelAndView modelAndView = new ModelAndView("redirect:/renda");
		return modelAndView;
	}
	
	/*
	* Recebe o numero do id e carrega os dados no campo para alteração na DB
	 * */
	@GetMapping("/{id}")
	public ModelAndView editarRenda(@PathVariable("id") Long id, Model model) {
		
		ModelAndView modelAndView = new ModelAndView("renda");
		Optional<Renda> renda = rendarepository.findById(id);
		List<Pessoa> pessoa = pessoarepository.findAll();
		model.addAttribute("rendacadastro", renda.get());
		model.addAttribute("pessoas", pessoa);
		return modelAndView;
	}
	
	/*
	* Recebe o numero do id e apaga o dado na DB
	 * */
	@PostMapping("/{codigo}/excluir")
	public ModelAndView excluirRenda(@PathVariable("codigo") Long codigo) {
		
		rendarepository .deleteById(codigo);
		ModelAndView modelAndView = new ModelAndView("redirect:/renda");
		return modelAndView;
	}
}
