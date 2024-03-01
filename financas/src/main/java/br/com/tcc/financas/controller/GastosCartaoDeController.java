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

import br.com.tcc.financas.model.Cartao;
import br.com.tcc.financas.model.GastosCartao;
import br.com.tcc.financas.model.Pessoa;
import br.com.tcc.financas.repository.CartaoRepository;
import br.com.tcc.financas.repository.GastosCartaoRepository;
import br.com.tcc.financas.repository.PessoaRepository;

@Controller
@RequestMapping("/gastosdecartao")
public class GastosCartaoDeController {

	@Autowired
	private GastosCartaoRepository gastoscartaorepository;
	
	@Autowired
	private CartaoRepository cartaorepository;
	
	@Autowired
	private PessoaRepository pessoarepository;
	
		@GetMapping
		public String listarGastosCartao(Model model) {
			
			PageRequest paginacao = PageRequest.of(0, 200, Sort.by("idgastoscartao").descending()); 
			Page<GastosCartao> gastoscartao = gastoscartaorepository.findAll(paginacao);
			List<Pessoa> pessoa = pessoarepository.findAll();
			List<Cartao> cartao = cartaorepository.findAll();
			model.addAttribute("gastoscartoes", gastoscartao.getContent());
			model.addAttribute("gastocartaocadastro", new GastosCartao());
			model.addAttribute("pessoas", pessoa);
			model.addAttribute("cartoes", cartao);
			return "gastosdecartao";

		}

		@PostMapping("/cadastrar")
		public ModelAndView cadastrarPessoa(@ModelAttribute("gastocartaocadastro") GastosCartao gastoscartao) {
			
			gastoscartaorepository.save(gastoscartao);	
			ModelAndView modelAndView = new ModelAndView("redirect:/gastosdecartao");
			return modelAndView;
		}
		
		@PostMapping("/{codigo}/excluir")
		public ModelAndView excluirGastosCartao(@PathVariable("codigo") Long codigo) {
			
			gastoscartaorepository.deleteById(codigo);
			ModelAndView modelAndView = new ModelAndView("redirect:/gastosdecartao");
			return modelAndView;
		}
		
}
