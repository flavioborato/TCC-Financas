/*
 *   TCC Engenharia de Software
 * Projeto : Cadastro de Finanças
 * Autor : Flávio Fernando Borato
 * Versão : 0.1
 * Revisão : 19/07/2024
 * Classe - Controle de cada tipo de consulta efetuada e dados dos valores
 * */


package br.com.tcc.financas.dto;



import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import br.com.tcc.financas.model.Cartao;
import br.com.tcc.financas.model.GastosCartao;
import br.com.tcc.financas.model.GastosMensais;
import br.com.tcc.financas.model.Pessoa;
import br.com.tcc.financas.model.Renda;
import br.com.tcc.financas.model.dados.AreaGasto;
import br.com.tcc.financas.repository.GastosCartaoRepository;
import br.com.tcc.financas.repository.GastosMensaisRepository;
import br.com.tcc.financas.repository.RendaRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class ConsultaListaDTO {

	@NotBlank
	@NotNull
	private LocalDate data;
	private String tipogasto;
	private Cartao cartao;
	private Pessoa pessoa;
	private Double totalGastos;
	private Double somaGastoMensal;
	private Double somaGastoCartao;

	public ConsultaListaDTO(ConsultaDTO consulta) {
		this.data = consulta.getData();
		this.pessoa = consulta.getPessoa();
		this.cartao = consulta.getCartao();
		this.tipogasto = consulta.getTipogasto();
		
	}
	
	/*
	* Faz a busca na DB dos gastos mensais efetuado por uma determinada pessoa num determinado mes e ano
	* Calcula todos os gastos da busca
	 * */
	public List<GastosMensais> consultagaGastoMensalPessoa(GastosMensaisRepository gastosmensaispository){	
		List<GastosMensais> pessoasgasto = new ArrayList<>();
		this.somaGastoMensal = 0.0;
		if (this.data != null) {
			pessoasgasto = gastosmensaispository.findPessoaId(this.pessoa.getIdpessoa(),
											this.data.getMonthValue(), this.data.getYear(), this.tipogasto);
			for (GastosMensais gastosMensais : pessoasgasto) {
				this.somaGastoMensal += gastosMensais.getValor().doubleValue(); 
			} 
		}	
		
		return  pessoasgasto;
	}

	/*
	* Faz a busca na DB dos gastos de cartao efetuado por uma determinada pessoa num determinado mes e ano
	* Calcula todos os gastos da busca
	 * */
	public List<GastosCartao> consultaGastocartaoPessoa(GastosCartaoRepository gastoscartaorepository){	
		List<GastosCartao> pessoasgasto = new ArrayList<>();
		this.somaGastoCartao = 0.0;
		if (this.data != null) {
			pessoasgasto = gastoscartaorepository.findPessoaId(this.pessoa.getIdpessoa(),
					this.data.getMonthValue(), this.data.getYear(), this.tipogasto);
			for (GastosCartao gastosCartao : pessoasgasto) {
				this.somaGastoCartao += gastosCartao.getValor().doubleValue(); 
			} 
		}	
		return  pessoasgasto;
	}
	
	/*
	* Faz a busca na DB dos gastos de cartão efetuado por um determinado cartão num determinado mes e ano
	* Calcula todos os gastos da busca
	 * */
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
	
	/*
	* Faz a busca na DB dos gastos mensais efetuado num determinado mes e ano
	* Calcula todos os gastos da busca
	 * */
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
	
	/*
	* Faz a busca na DB dos gastos de cartão efetuado num determinado mes e ano
	* Calcula todos os gastos da busca
	 * */
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
	
	/*
	* Faz a busca na DB de todos gastos num determinado mes e ano e compara com a renda
	* Calcula todos os gastos da busca e mostra as diferencças
	 * */
	public RendaDTO consultaTotal(GastosCartaoRepository gastoscartaorepository, GastosMensaisRepository gastosmensaispository,
			RendaRepository rendarepository) {
		RendaDTO rendaDto = new RendaDTO();
		Double somaCartao = 0.0;
		Double somaMensal = 0.0;
		Double somaRenda = 0.0;
		Double diversao = 0.0;
		Double comida = 0.0;
		Double limpeza= 0.0;
		Double estudo= 0.0;
		Double saude= 0.0;
		Double locomocao= 0.0;
		Double presente= 0.0;
		Double bebida= 0.0;
		Double casa= 0.0;
		Double vestuario= 0.0;
		Double outros= 0.0;
		if (this.data != null) {	
			List<GastosCartao> gastocartao =  gastoscartaorepository.findGastosCartao(this.data.getMonthValue(), this.data.getYear());
			List<GastosMensais>	gastomensal = gastosmensaispository.findMensalData(this.data.getMonthValue(), this.data.getYear());
			List<Renda> renda = rendarepository.findAll();
			
			for (GastosCartao gastosCartao : gastocartao) {
				somaCartao += gastosCartao.getValor().doubleValue(); 
				
				if(gastosCartao.getArea() == AreaGasto.DIVERSAO) {
					diversao += gastosCartao.getValor().doubleValue();
				}
				if(gastosCartao.getArea() == AreaGasto.COMIDA) {
					comida += gastosCartao.getValor().doubleValue();
				}
				if(gastosCartao.getArea() == AreaGasto.LIMPEZA) {
					limpeza += gastosCartao.getValor().doubleValue();
				}
				if(gastosCartao.getArea() == AreaGasto.ESTUDO) {
					estudo += gastosCartao.getValor().doubleValue();
				}
				if(gastosCartao.getArea() == AreaGasto.SAUDE) {
					saude += gastosCartao.getValor().doubleValue();
				}
				if(gastosCartao.getArea() == AreaGasto.LOCOMOCAO) {
					locomocao += gastosCartao.getValor().doubleValue();
				}
				if(gastosCartao.getArea() == AreaGasto.PRESENTES) {
					presente += gastosCartao.getValor().doubleValue();
				}
				if(gastosCartao.getArea() == AreaGasto.BEBIDA) {
					bebida += gastosCartao.getValor().doubleValue();
				}
				if(gastosCartao.getArea() == AreaGasto.CASA) {
					casa += gastosCartao.getValor().doubleValue();
				}
				if(gastosCartao.getArea() == AreaGasto.VESTUARIO) {
					vestuario += gastosCartao.getValor().doubleValue();
				}
				if(gastosCartao.getArea() == AreaGasto.OUTROS) {
					outros += gastosCartao.getValor().doubleValue();
				}
			} 
			for (GastosMensais gastosMensal : gastomensal) {
				somaMensal += gastosMensal.getValor().doubleValue(); 
				
				if(gastosMensal.getArea() == AreaGasto.DIVERSAO) {
					diversao += gastosMensal.getValor().doubleValue();
				}
				if(gastosMensal.getArea() == AreaGasto.COMIDA) {
					comida += gastosMensal.getValor().doubleValue();
				}
				if(gastosMensal.getArea() == AreaGasto.LIMPEZA) {
					limpeza += gastosMensal.getValor().doubleValue();
				}
				if(gastosMensal.getArea() == AreaGasto.ESTUDO) {
					estudo += gastosMensal.getValor().doubleValue();
				}
				if(gastosMensal.getArea() == AreaGasto.SAUDE) {
					saude += gastosMensal.getValor().doubleValue();
				}
				if(gastosMensal.getArea() == AreaGasto.LOCOMOCAO) {
					locomocao += gastosMensal.getValor().doubleValue();
				}
				if(gastosMensal.getArea() == AreaGasto.PRESENTES) {
					presente += gastosMensal.getValor().doubleValue();
				}
				if(gastosMensal.getArea() == AreaGasto.BEBIDA) {
					bebida += gastosMensal.getValor().doubleValue();
				}
				if(gastosMensal.getArea() == AreaGasto.CASA) {
					casa += gastosMensal.getValor().doubleValue();
				}
				if(gastosMensal.getArea() == AreaGasto.VESTUARIO) {
					vestuario += gastosMensal.getValor().doubleValue();
				}
				if(gastosMensal.getArea() == AreaGasto.OUTROS) {
					outros += gastosMensal.getValor().doubleValue();
				}
			} 
			for (Renda gastosRenda : renda) {
				somaRenda += gastosRenda.getValor().doubleValue(); 
			} 
			
			rendaDto.setDiversao(diversao);
			rendaDto.setComida(comida);
			rendaDto.setLimpeza(limpeza);
			rendaDto.setEstudo(estudo);
			rendaDto.setSaude(saude);
			rendaDto.setLocomocao(locomocao);
			rendaDto.setPresente(presente);
			rendaDto.setBebida(bebida);
			rendaDto.setCasa(casa);
			rendaDto.setVestuario(vestuario);
			rendaDto.setOutros(outros);
			rendaDto.setValortotal(somaCartao+somaMensal);
			rendaDto.setData(this.data);
			rendaDto.setRendatotal(somaRenda);
			rendaDto.setDisponivel(somaRenda - (somaCartao+somaMensal) );
			
		}
		
		
		
		
		return rendaDto;
	}
	
	
	/*
	* Soma os gastos e retorna o valor total
	 * */
	public Double valorTotal () {
		this.totalGastos = this.somaGastoCartao+this.somaGastoMensal;
		return this.totalGastos;
	}





	
	

	
	




}
