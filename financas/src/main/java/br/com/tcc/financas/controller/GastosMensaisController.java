/*
 *   TCC Engenharia de Software
 * Projeto : Cadastro de Finanças
 * Autor : Flávio Fernando Borato
 * Versão : 0.0
 * Revisão : 06/03/2024
 * Classe - Controle dos gastos a vista mensais
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
import br.com.tcc.financas.model.GastosMensais;
import br.com.tcc.financas.model.Pessoa;
import br.com.tcc.financas.repository.GastosMensaisRepository;
import br.com.tcc.financas.repository.PessoaRepository;

@Controller
@RequestMapping("/gastosmensais")
public class GastosMensaisController {
	
	@Autowired
	private GastosMensaisRepository gastosmensaisrepository;
	
	@Autowired
	private PessoaRepository pessoarepository;
	
	
	/*
	* Carregando os dados salvos na DB ao carregar a pagina
	 * */
	@GetMapping
	public String listarGastosMensais(Model model) {
		
		PageRequest paginacao = PageRequest.of(0, 200, Sort.by("idgastosmensais").descending());
		Page<GastosMensais> gastosmesal = gastosmensaisrepository.findAll(paginacao);
		List<Pessoa> pessoa = pessoarepository.findAll();
		model.addAttribute("gastosmensais", gastosmesal.getContent());
		model.addAttribute("gastomensalcadastro", new GastosMensais());
		model.addAttribute("pessoas", pessoa);
		return "gastosmensais";
	}
	
	/*
	* Recebe os dados e efetua o cadastro dos dados na DB
	 * */
	@PostMapping("/cadastrar")
	public ModelAndView cadastrarGastosMensais(@ModelAttribute("gastomensalcadastro") GastosMensais gastosmensais) {
		
		gastosmensaisrepository.save(gastosmensais);	
		ModelAndView modelAndView = new ModelAndView("redirect:/gastosmensais");
		return modelAndView;
	}
	
	/*
	* Recebe o numero do id e carrega os dados no campo para alteração na DB
	 * */
	@GetMapping("/{id}")
	public ModelAndView editarGastosMensais(@PathVariable("id") Long id, Model model) {
		
		ModelAndView modelAndView = new ModelAndView("gastosmensais");
		Optional<GastosMensais> gastosmensais = gastosmensaisrepository.findById(id);
		List<Pessoa> pessoa = pessoarepository.findAll();
		model.addAttribute("gastomensalcadastro", gastosmensais.get());
		model.addAttribute("pessoas", pessoa);
		return modelAndView;
	}
	
	/*
	* Recebe o numero do id e apaga o dado na DB
	 * */
	@PostMapping("/{codigo}/excluir")
	public ModelAndView excluirGastosMensais(@PathVariable("codigo") Long codigo) {
		
		gastosmensaisrepository.deleteById(codigo);
		ModelAndView modelAndView = new ModelAndView("redirect:/gastosmensais");
		return modelAndView;
	}
}
