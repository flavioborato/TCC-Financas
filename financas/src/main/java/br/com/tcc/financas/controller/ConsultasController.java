/*
 *   TCC Engenharia de Software
 * Projeto : Cadastro de Finanças
 * Autor : Flávio Fernando Borato
 * Versão : 0.2
 * Revisão : 18/07/2024
 * Classe - Controle das consultas gerais
 * */

package br.com.tcc.financas.controller;
import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import br.com.tcc.financas.dto.AlertasDTO;
import br.com.tcc.financas.dto.ConsultaDTO;
import br.com.tcc.financas.dto.ConsultaListaDTO;
import br.com.tcc.financas.dto.RendaDTO;
import br.com.tcc.financas.model.Cartao;
import br.com.tcc.financas.model.GastosCartao;
import br.com.tcc.financas.model.GastosMensais;
import br.com.tcc.financas.model.Pessoa;
import br.com.tcc.financas.repository.CartaoRepository;
import br.com.tcc.financas.repository.GastosCartaoRepository;
import br.com.tcc.financas.repository.GastosMensaisRepository;
import br.com.tcc.financas.repository.PessoaRepository;
import br.com.tcc.financas.repository.RendaRepository;

@Controller
@RequestMapping("/consultas")
public class ConsultasController {

	@Autowired
	private CartaoRepository cartaorepository;
	
	@Autowired
	private PessoaRepository pessoarepository;
	
	@Autowired
	private GastosMensaisRepository gastosmensaispository;
	
	@Autowired
	private GastosCartaoRepository gastoscartaorepository;
	
	@Autowired
	private RendaRepository rendarepository;
	
	private Integer verificaAlerta = 0;
	private Integer minuto = 0;

	/*
	* Classe de dados para fazer e inicialização dos atributos nas tela de consulta
	 * */
	public String inicializacao(Model model) {
	
		List<Pessoa> pessoa = pessoarepository.findAll();
		List<Cartao> cartao = cartaorepository.findAll();
		RendaDTO rendaDTO = new RendaDTO();
		model.addAttribute("rendadto", rendaDTO);
		model.addAttribute("consultaslista", new ConsultaDTO());
		model.addAttribute("pessoas", pessoa);
		model.addAttribute("cartoes", cartao);	
		return null;
		
	}
	/*
	* Classe verificar tempo de gerar alertas
	 * */
	public String verificaTempoDeAleta() {
		
		LocalTime horaAtual = LocalTime.now();
		if(this.verificaAlerta==0) {
			this.minuto = horaAtual.getMinute()+5;
			this.verificaAlerta=1;
		}	
		if(this.minuto <= horaAtual.getMinute()) {
			this.verificaAlerta=0;
		}		
		return null;	
	}
	
	/*
	* Classe consluta das parcelas a vencer dos Gastos Mensais, e exibir status na tela
	 * */
	public String verificacaoVencimentoGastoMensal(Model model) {

		AlertasDTO alertas = new AlertasDTO();
		alertas = alertas.alertaVencimentoGastoMensal(gastosmensaispository, this.verificaAlerta);
		verificaTempoDeAleta();
		model.addAttribute("alertas", alertas);
		return null;
		
	}	
		/*
		* Carregando os dados salvos na DB ao carregar a pagina
		 * */
		@GetMapping
		public String carregaDados(Model model) {
			inicializacao(model);
			verificacaoVencimentoGastoMensal(model);
			return "consultas";
		}

		/*
		* Efetua busca dos gastos da pessoa selecionada dentro de um determinado mes e ano
		 * */
		@PostMapping("/buscarpessoa")
		public ModelAndView buscaPorPessoa(@ModelAttribute("consultaslista") ConsultaDTO consuldaDados, Model model ) {
			inicializacao(model);
			ConsultaListaDTO consulta = new ConsultaListaDTO(consuldaDados);	
			List<GastosMensais> pessoasgasto = consulta.consultagaGastoMensalPessoa(gastosmensaispository);	
			List<GastosCartao> pessoasgastocartao = consulta.consultaGastocartaoPessoa(gastoscartaorepository);			
			model.addAttribute("pessoagasto", pessoasgasto);
			model.addAttribute("pessoagastocartao", pessoasgastocartao);
			model.addAttribute("totalpessoa", "R$"+ consulta.valorTotal());
			ModelAndView modelAndView = new ModelAndView("consulta/individualpessoa");									
			return modelAndView;
			
		}

		/*
		* Efetua busca dos gastos do cartão selecionada dentro de um determinado mes e ano
		 * */		
		@PostMapping("/buscarcartao")
		public ModelAndView buscaPorCartao(@ModelAttribute("consultaslista")  ConsultaDTO consuldaDados, Model model  ) {
			inicializacao(model);
			ConsultaListaDTO consulta = new ConsultaListaDTO(consuldaDados);
			List<GastosCartao> cartaogastocartao = consulta.consultaGastocartaoCartao(gastoscartaorepository);
			model.addAttribute("cartaogastocartao", cartaogastocartao);
			model.addAttribute("totalcartao", "R$"+ consulta.valorTotal());
			ModelAndView modelAndView = new ModelAndView("consulta/individualcartao");	
			return modelAndView;
	
}
	
		/*
		* Efetua busca dos gastos mensais dentro de um determinado mes e ano
		 * */
		@PostMapping("/buscargastosmensais")
		public ModelAndView buscaPorGastoMensal(@ModelAttribute("consultaslista") ConsultaDTO consuldaDados, Model model  ) {
			inicializacao(model);
			ConsultaListaDTO consulta = new ConsultaListaDTO(consuldaDados);
			List<GastosMensais> gastosmensais = consulta.consultaGastosMensais(gastosmensaispository);
			model.addAttribute("gastosmensais", gastosmensais);
			model.addAttribute("totalmensal", "R$"+ consulta.valorTotal());
			ModelAndView modelAndView = new ModelAndView("consulta/gastosmensais");	
			return modelAndView;
	
}
		
		/*
		* Efetua busca dos gastos de cartão dentro de um determinado mes e ano
		 * */
		@PostMapping("/buscargastoscartao")
		public ModelAndView buscaPorGastoCartao(@ModelAttribute("consultaslista") ConsultaDTO consuldaDados, Model model  ) {
			inicializacao(model);
			ConsultaListaDTO consulta = new ConsultaListaDTO(consuldaDados);
			List<GastosCartao> gastoscartao = consulta.consultaGastosCartao(gastoscartaorepository);
			model.addAttribute("gastoscartao", gastoscartao);
			model.addAttribute("totalgastocartao", "R$"+ consulta.valorTotal());	
			ModelAndView modelAndView = new ModelAndView("consulta/gastoscartao");
			return modelAndView;
	
}
		
		/*
		* Efetua busca dos gastos totais dentro de um determinado mes e ano trazendo a soma total
		 * */
		@PostMapping("/buscartotal")
		public ModelAndView buscaTotal(@ModelAttribute("consultaslista") ConsultaDTO consuldaDados, Model model  ) {
			inicializacao(model);
			ConsultaListaDTO consulta = new ConsultaListaDTO(consuldaDados);	
			RendaDTO rendaDTO = consulta.consultaTotal(gastoscartaorepository, gastosmensaispository, rendarepository);
			model.addAttribute("rendadto", rendaDTO);
			ModelAndView modelAndView = new ModelAndView("consulta/gastostotal");
			return modelAndView;
				
}
		
}
