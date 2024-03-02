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
import br.com.tcc.financas.model.Cartao;
import br.com.tcc.financas.model.Pessoa;
import br.com.tcc.financas.repository.CartaoRepository;
import br.com.tcc.financas.repository.PessoaRepository;


@Controller
@RequestMapping("/cartao")
public class CartaoController {


	
	@Autowired
	private CartaoRepository cartaorepository;
	
	@Autowired
	private PessoaRepository pessoarepository;
	
		@GetMapping
		public String listarCartao(Model model) {
			
			PageRequest paginacao = PageRequest.of(0, 20, Sort.by("idcartao").descending()); 
			Page<Cartao> cartao = cartaorepository.findAll(paginacao);
			List<Pessoa> pessoa = pessoarepository.findAll();
			model.addAttribute("cartoes", cartao.getContent());
			model.addAttribute("cartaocadastro", new Cartao());
			model.addAttribute("pessoas", pessoa);
			return "cartao";
		}
		
		
		@PostMapping("/cadastrar")
		public ModelAndView cadastrarCartao(@ModelAttribute("cartaocadastro") Cartao cartao) {
			
			cartaorepository.save(cartao);	
			ModelAndView modelAndView = new ModelAndView("redirect:/cartao");
			return modelAndView;
		}
		
		@GetMapping("/{id}")
		public ModelAndView editarCartao(@PathVariable("id") Long id, Model model) {
			
			ModelAndView modelAndView = new ModelAndView("cartao");
			Optional<Cartao> cartao = cartaorepository.findById(id);
			List<Pessoa> pessoa = pessoarepository.findAll();
			model.addAttribute("cartaocadastro", cartao.get());
			model.addAttribute("pessoas", pessoa);
			return modelAndView;
		}
		
		@PostMapping("/{codigo}/excluir")
		public ModelAndView excluirCartao(@PathVariable("codigo") Long codigo) {
			
			cartaorepository.deleteById(codigo);
			ModelAndView modelAndView = new ModelAndView("redirect:/cartao");
			return modelAndView;
		}
		
}
