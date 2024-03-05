package br.com.tcc.financas.dto;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import br.com.tcc.financas.model.Cartao;
import br.com.tcc.financas.model.GastosCartao;
import br.com.tcc.financas.model.GastosMensais;
import br.com.tcc.financas.model.Pessoa;
import br.com.tcc.financas.model.Renda;
import br.com.tcc.financas.model.dados.AreaGasto;
import br.com.tcc.financas.model.dados.TipoGasto;
import br.com.tcc.financas.repository.GastosCartaoRepository;
import br.com.tcc.financas.repository.GastosMensaisRepository;
import br.com.tcc.financas.repository.RendaRepository;


public class ConsultaListaDTO {

	private LocalDate data;
	private Cartao cartao;
	private Pessoa pessoa;
	private Double totalGastos;
	private Double somaGastoMensal;
	private Double somaGastoCartao;

	public ConsultaListaDTO(ConsultaDTO consulta) {
		this.data = consulta.getData();
		this.pessoa = consulta.getPessoa();
		this.cartao = consulta.getCartao();
		
	}
	
	public List<GastosMensais> consultagaGastoMensalPessoa(GastosMensaisRepository gastosmensaispository){	
		List<GastosMensais> pessoasgasto = new ArrayList<>();
		this.somaGastoMensal = 0.0;
		if (this.data != null) {
			pessoasgasto = gastosmensaispository.findPessoaId(this.pessoa.getIdpessoa(),
											this.data.getMonthValue(), this.data.getYear());
			for (GastosMensais gastosMensais : pessoasgasto) {
				this.somaGastoMensal += gastosMensais.getValor().doubleValue(); 
			} 
		}	
		
		return  pessoasgasto;
	}

	public List<GastosCartao> consultaGastocartaoPessoa(GastosCartaoRepository gastoscartaorepository){	
		List<GastosCartao> pessoasgasto = new ArrayList<>();
		this.somaGastoCartao = 0.0;
		if (this.data != null) {
			pessoasgasto = gastoscartaorepository.findPessoaId(this.pessoa.getIdpessoa(),
					this.data.getMonthValue(), this.data.getYear());
			for (GastosCartao gastosCartao : pessoasgasto) {
				this.somaGastoCartao += gastosCartao.getValor().doubleValue(); 
			} 
		}	
		return  pessoasgasto;
	}
	
	public List<GastosCartao> consultaGastocartaoCartao(GastosCartaoRepository gastoscartaorepository) {
		List<GastosCartao> cartaogasto = new ArrayList<>();
		this.somaGastoCartao = 0.0;
		this.somaGastoMensal = 0.0;
		if (this.data != null) {
			cartaogasto = gastoscartaorepository.findCartaoPeloCartao(this.cartao.getIdcartao(),
					this.data.getMonthValue(), this.data.getYear());
			for (GastosCartao gastosCartao : cartaogasto) {
				this.somaGastoCartao += gastosCartao.getValor().doubleValue(); 
			} 
		}	
		return  cartaogasto;
	}
	
	public List<GastosMensais> consultaGastosMensais(GastosMensaisRepository gastosmensaispository) {
		List<GastosMensais> gastosmensais = new ArrayList<>();
		this.somaGastoCartao = 0.0;
		this.somaGastoMensal = 0.0;
		if (this.data != null) {
			gastosmensais = gastosmensaispository.findMensalData(this.data.getMonthValue(), this.data.getYear());
			for (GastosMensais gastosMensais : gastosmensais) {
				this.somaGastoMensal += gastosMensais.getValor().doubleValue(); 
			} 
		}	
		
		return  gastosmensais;
	}
	
	public List<GastosCartao> consultaGastosCartao(GastosCartaoRepository gastoscartaorepository) {
		List<GastosCartao> gastocartao = new ArrayList<>();
		this.somaGastoCartao = 0.0;
		this.somaGastoMensal = 0.0;
		if (this.data != null) {
			gastocartao = gastoscartaorepository.findGastosCartao(this.data.getMonthValue(), this.data.getYear());
			for (GastosCartao gastosCartao : gastocartao) {
				this.somaGastoCartao += gastosCartao.getValor().doubleValue(); 
			} 
		}	
		return  gastocartao;
	}
	
	
	public RendaDTO consultaTotal(GastosCartaoRepository gastoscartaorepository, GastosMensaisRepository gastosmensaispository,
			RendaRepository rendarepository) {
		RendaDTO rendaDto = new RendaDTO();
		Double somaCartao = 0.0;
		Double somaMensal = 0.0;
		Double somaRenda = 0.0;
		if (this.data != null) {	
			List<GastosCartao> gastocartao =  gastoscartaorepository.findGastosCartao(this.data.getMonthValue(), this.data.getYear());
			List<GastosMensais>	gastomensal = gastosmensaispository.findMensalData(this.data.getMonthValue(), this.data.getYear());
			List<Renda> renda = rendarepository.findAll();
			
			for (GastosCartao gastosCartao : gastocartao) {
				somaCartao += gastosCartao.getValor().doubleValue(); 
			} 
			for (GastosMensais gastosMensal : gastomensal) {
				somaMensal += gastosMensal.getValor().doubleValue(); 
			} 
			for (Renda gastosRenda : renda) {
				somaRenda += gastosRenda.getValor().doubleValue(); 
			} 
			
			rendaDto.setValortotal(somaCartao+somaMensal);
			rendaDto.setData(this.data);
			rendaDto.setRendatotal(somaRenda);
			rendaDto.setDisponivel(somaRenda - (somaCartao+somaMensal) );
			
		}
		
		
		
		
		return rendaDto;
	}
	
	
	public Double valorTotal () {
		this.totalGastos = this.somaGastoCartao+this.somaGastoMensal;
		return this.totalGastos;
	}





	
	

	
	




}
