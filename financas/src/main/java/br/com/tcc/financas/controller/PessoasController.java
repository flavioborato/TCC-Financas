/*
 *   TCC Engenharia de Software
 * Projeto : Cadastro de Finanças
 * Autor : Flávio Fernando Borato
 * Versão : 0.0
 * Revisão : 06/03/2024
 * Classe - Controle do cadastro da pessoas e usuarios
 * */

package br.com.tcc.financas.controller;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	@Autowired
	private PasswordEncoder passwordEncoder;

	
	/*
	* Carregando os dados salvos na DB ao carregar a pagina
	 * */
	@GetMapping
	public String listarPessoa(Model model) {

		PageRequest paginacao = PageRequest.of(0, 20, Sort.by("idpessoa").descending()); 
		Page<Pessoa> pessoa =   pessoarepository.findAll(paginacao);
		model.addAttribute("pessoas", pessoa.getContent());
		model.addAttribute("pessoacadastro", new Pessoa());
		return "pessoas";
		
		}
	
	/*
	* Recebe os dados e efetua o cadastro dos dados na DB
	 * */
	@PostMapping("/cadastrar")
	public ModelAndView cadastrarPessoa(@ModelAttribute("pessoacadastro") Pessoa pessoa) {		
		String	senhaencode = "";
		if(pessoa.getSenha().getSenha().isEmpty()){
			senhaencode = this.passwordEncoder.encode("123456");
		}else {
			senhaencode = this.passwordEncoder.encode(pessoa.getSenha().getSenha());
		}			
		pessoa.getSenha().setSenha(senhaencode);
		pessoa.getSenha().setPessoa(pessoa);
		pessoarepository.save(pessoa);	
		ModelAndView modelAndView = new ModelAndView("redirect:/pessoas");
		return modelAndView;
	}
	
	/*
	* Recebe o numero do id e carrega os dados no campo para alteração na DB
	 * */
	@GetMapping("/{id}")
	public ModelAndView editarPessoa(@PathVariable("id") Long id, Model model) {
		
		ModelAndView modelAndView = new ModelAndView("pessoas");
		Optional<Pessoa> pessoa = pessoarepository.findById(id);
		model.addAttribute("pessoacadastro", pessoa);
		modelAndView.addObject("pessoacadastro", pessoa);
		return modelAndView;
	}
	
	/*
	* Recebe o numero do id e apaga o dado na DB
	 * */
	@PostMapping("/{codigo}/excluir")
	public ModelAndView excluirPessoa(@PathVariable("codigo") Long codigo) {

		pessoarepository.deleteById(codigo);
		ModelAndView modelAndView = new ModelAndView("redirect:/pessoas");
		return modelAndView;
	}

}
