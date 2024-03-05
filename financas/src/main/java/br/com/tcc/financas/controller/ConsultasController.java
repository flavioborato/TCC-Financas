package br.com.tcc.financas.controller;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
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
	
	
	
		@GetMapping
		public String carregaDados(Model model) {
						
			inicializacao(model);
			
			return "consultas";
		}

		
		@PostMapping("/buscarpessoa")
		public ModelAndView buscaPorPessoa(@ModelAttribute("consultaslista") ConsultaDTO consuldaDados, Model model ) {
			inicializacao(model);
			ConsultaListaDTO consulta = new ConsultaListaDTO(consuldaDados);	
			List<GastosMensais> pessoasgasto = consulta.consultagaGastoMensalPessoa(gastosmensaispository);	
			List<GastosCartao> pessoasgastocartao = consulta.consultaGastocartaoPessoa(gastoscartaorepository);			
			model.addAttribute("pessoagasto", pessoasgasto);
			model.addAttribute("pessoagastocartao", pessoasgastocartao);
			model.addAttribute("totalpessoa", "R$"+ consulta.valorTotal());
			ModelAndView modelAndView = new ModelAndView("/consultas");									
			return modelAndView;
			
		}
		
		@PostMapping("/buscarcartao")
		public ModelAndView buscaPorCartao(@ModelAttribute("consultaslista")  ConsultaDTO consuldaDados, Model model  ) {
			inicializacao(model);
			ConsultaListaDTO consulta = new ConsultaListaDTO(consuldaDados);
			List<GastosCartao> cartaogastocartao = consulta.consultaGastocartaoCartao(gastoscartaorepository);
			model.addAttribute("cartaogastocartao", cartaogastocartao);
			model.addAttribute("totalcartao", "R$"+ consulta.valorTotal());
			ModelAndView modelAndView = new ModelAndView("consultas");	
			return modelAndView;
	
}
		
		@PostMapping("/buscargastosmensais")
		public ModelAndView buscaPorGastoMensal(@ModelAttribute("consultaslista") ConsultaDTO consuldaDados, Model model  ) {
			inicializacao(model);
			ConsultaListaDTO consulta = new ConsultaListaDTO(consuldaDados);
			List<GastosMensais> gastosmensais = consulta.consultaGastosMensais(gastosmensaispository);
			model.addAttribute("gastosmensais", gastosmensais);
			model.addAttribute("totalmensal", "R$"+ consulta.valorTotal());
			ModelAndView modelAndView = new ModelAndView("consultas");	
			return modelAndView;
	
}
		
		@PostMapping("/buscargastoscartao")
		public ModelAndView buscaPorGastoCartao(@ModelAttribute("consultaslista") ConsultaDTO consuldaDados, Model model  ) {
			inicializacao(model);
			ConsultaListaDTO consulta = new ConsultaListaDTO(consuldaDados);
			List<GastosCartao> gastoscartao = consulta.consultaGastosCartao(gastoscartaorepository);
			model.addAttribute("gastoscartao", gastoscartao);
			model.addAttribute("totalgastocartao", "R$"+ consulta.valorTotal());	
			ModelAndView modelAndView = new ModelAndView("consultas");
			return modelAndView;
	
}
		
		@PostMapping("/buscartotal")
		public ModelAndView buscaTotal(@ModelAttribute("consultaslista") ConsultaDTO consuldaDados, Model model  ) {
			inicializacao(model);
			ConsultaListaDTO consulta = new ConsultaListaDTO(consuldaDados);		
			RendaDTO rendaDTO = consulta.consultaTotal(gastoscartaorepository, gastosmensaispository, rendarepository);
			model.addAttribute("rendadto", rendaDTO);
			ModelAndView modelAndView = new ModelAndView("consultas");
			return modelAndView;
	
}
		
}
