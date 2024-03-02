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
import br.com.tcc.financas.dto.ConsultaPessoaDTO;
import br.com.tcc.financas.model.Cartao;
import br.com.tcc.financas.model.GastosCartao;
import br.com.tcc.financas.model.GastosMensais;
import br.com.tcc.financas.model.Pessoa;
import br.com.tcc.financas.repository.CartaoRepository;
import br.com.tcc.financas.repository.GastosCartaoRepository;
import br.com.tcc.financas.repository.GastosMensaisRepository;
import br.com.tcc.financas.repository.PessoaRepository;

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
	
		@GetMapping
		public String carregaDados(Model model) {
						
			List<Pessoa> pessoa = pessoarepository.findAll();
			List<Cartao> cartao = cartaorepository.findAll();
			model.addAttribute("consultaslista", new ConsultaDTO());
			model.addAttribute("pessoas", pessoa);
			model.addAttribute("cartoes", cartao);
			model.addAttribute("totalpessoa", "R$0");
			
			return "consultas";
		}

		
		@PostMapping("/buscarpessoa")
		public ModelAndView buscaPorPessoa(@ModelAttribute("consultaslista") ConsultaDTO consuldaDados, Model model ) {
	
		  	
		
			ConsultaPessoaDTO consulta = new ConsultaPessoaDTO(consuldaDados);	
			List<GastosMensais> pessoasgasto = consulta.consultagaGastoMensal(gastosmensaispository);	
			List<GastosCartao> pessoasgastocartao = consulta.consultaGastocartao(gastoscartaorepository);
			Double  total = consulta.valorTotal();
			model.addAttribute("totalpessoa", "R$"+ total);
			model.addAttribute("pessoagasto", pessoasgasto);
			model.addAttribute("pessoagastocartao", pessoasgastocartao);
			ModelAndView modelAndView = new ModelAndView("/consultas");
			
			
			List<Pessoa> pessoa = pessoarepository.findAll();
			List<Cartao> cartao = cartaorepository.findAll();
			model.addAttribute("consultaslista", new ConsultaDTO());
			model.addAttribute("pessoas", pessoa);
			model.addAttribute("cartoes", cartao);
			return modelAndView;
			
		}
		
		@PostMapping("/buscarcartao")
		public ModelAndView buscaPorCartao(@ModelAttribute("consultaslista") ConsultaDTO consuldaDados, Model model  ) {
	
			ModelAndView modelAndView = new ModelAndView("redirect:/consultas");
			model.addAttribute("totalpessoa", "R$"+"500");
			return modelAndView;
	
}
		
		@PostMapping("/buscargastosmensais")
		public ModelAndView buscaPorGastoMensal(@ModelAttribute("consultaslista") ConsultaDTO consuldaDados, Model model  ) {
	
			ModelAndView modelAndView = new ModelAndView("redirect:/consultas");
			return modelAndView;
	
}
		
		@PostMapping("/buscargastoscartao")
		public ModelAndView buscaPorGastoCartao(@ModelAttribute("consultaslista") ConsultaDTO consuldaDados, Model model  ) {
	
			ModelAndView modelAndView = new ModelAndView("redirect:/consultas");
			model.addAttribute("totalpessoa", "R$"+"500");
			return modelAndView;
	
}
		
		@PostMapping("/buscartotal")
		public ModelAndView buscaTotal(@ModelAttribute("consultaslista") ConsultaDTO consuldaDados, Model model  ) {
	
			ModelAndView modelAndView = new ModelAndView("redirect:/consultas");
			model.addAttribute("totalpessoa", "R$"+"500");
		return modelAndView;
	
}
		
}
