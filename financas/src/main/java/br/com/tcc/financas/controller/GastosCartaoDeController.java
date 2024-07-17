/*
 *   TCC Engenharia de Software
 * Projeto : Cadastro de Finanças
 * Autor : Flávio Fernando Borato
 * Versão : 0.1
 * Revisão : 06/03/2024
 * Classe - Controle dos gastos efetuados pelo cartão
 * */

package br.com.tcc.financas.controller;


import java.time.LocalDate;
import java.util.ArrayList;
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
	
		/*
		* Carregando os dados salvos na DB ao carregar a pagina
		 * */
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

		/*
		* Recebe os dados e efetua o cadastro dos dados na DB
		 * */
		@PostMapping("/cadastrar")
		public ModelAndView cadastrarGastosCartao(@ModelAttribute("gastocartaocadastro") GastosCartao gastoscartao) {
			// Verifica se é alteração ou cadastro pelo ID
			if(gastoscartao.getIdgastoscartao() == null || gastoscartao.getIdgastoscartao() == 0 ) {
					LocalDate mes = gastoscartao.getMes();
					List<GastosCartao> novaParcela = new ArrayList<>();
					// Cria lista das parcelas alterando data para ser salva
				for (int i = 0; i < gastoscartao.getParcelas(); i++) {	
					GastosCartao parcela = new GastosCartao();
					parcela.setArea(gastoscartao.getArea());
					parcela.setCartao(gastoscartao.getCartao());
					parcela.setDatacompra(gastoscartao.getDatacompra());
					parcela.setDescricao(gastoscartao.getDescricao());
					parcela.setIdgastoscartao(null);
					parcela.setMes(mes);
					parcela.setParcelas(i+1);
					parcela.setPessoa(gastoscartao.getPessoa());
					parcela.setTipogasto(gastoscartao.getTipogasto());
					parcela.setValor(gastoscartao.getValor());	
					novaParcela.add(parcela);
					mes = mes.plusMonths(1);	
				}
				gastoscartaorepository.saveAll(novaParcela);

			}else {
				System.out.println("Edição Efetuada"); 
				gastoscartaorepository.save(gastoscartao);	
			}		
			ModelAndView modelAndView = new ModelAndView("redirect:/gastosdecartao");
			return modelAndView;
		}
		
		/*
		* Recebe o numero do id e carrega os dados no campo para alteração na DB
		 * */
		@GetMapping("/{id}")
		public ModelAndView editarGastosCartao(@PathVariable("id") Long id, Model model) {
			
			ModelAndView modelAndView = new ModelAndView("gastosdecartao");
			Optional<GastosCartao> gastosdecartao = gastoscartaorepository.findById(id);
			List<Pessoa> pessoa = pessoarepository.findAll();
			List<Cartao> cartao = cartaorepository.findAll();
			model.addAttribute("gastocartaocadastro", gastosdecartao.get());
			model.addAttribute("pessoas", pessoa);
			model.addAttribute("cartoes", cartao);
			return modelAndView;
		}
		
		/*
		* Recebe o numero do id e apaga o dado na DB
		 * */
		@PostMapping("/{codigo}/excluir")
		public ModelAndView excluirGastosCartao(@PathVariable("codigo") Long codigo) {
			
			gastoscartaorepository.deleteById(codigo);
			ModelAndView modelAndView = new ModelAndView("redirect:/gastosdecartao");
			return modelAndView;
		}
		
}
