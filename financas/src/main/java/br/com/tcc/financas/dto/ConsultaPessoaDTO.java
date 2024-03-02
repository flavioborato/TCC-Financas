package br.com.tcc.financas.dto;



import java.time.LocalDate;
import java.util.List;
import br.com.tcc.financas.model.Cartao;
import br.com.tcc.financas.model.GastosCartao;
import br.com.tcc.financas.model.GastosMensais;
import br.com.tcc.financas.model.Pessoa;
import br.com.tcc.financas.repository.GastosCartaoRepository;
import br.com.tcc.financas.repository.GastosMensaisRepository;

public class ConsultaPessoaDTO {

	private LocalDate data;
	private Cartao cartao;
	private Pessoa pessoa;
	private Double total;
	private Double valor1;
	private Double valor2;

	public ConsultaPessoaDTO(ConsultaDTO consulta) {
		this.data = consulta.getData();
		this.pessoa = consulta.getPessoa();
		this.cartao = consulta.getCartao();
		
	}
	
	public List<GastosMensais> consultagaGastoMensal(GastosMensaisRepository gastosmensaispository){	
		Integer mes = 0;
		Integer ano = 0;
		if (this.data != null) {
			mes = this.data.getMonthValue();
			ano = this.data.getYear();
		}	
		List<GastosMensais> pessoasgasto = gastosmensaispository.findPessoaId(this.pessoa.getIdpessoa(),mes, ano);
		this.valor1 = 0.0;
		for (GastosMensais gastosMensais : pessoasgasto) {
			this.valor1 += gastosMensais.getValor().doubleValue(); 
		} 
		return  pessoasgasto;
	}

	public List<GastosCartao> consultaGastocartao(GastosCartaoRepository gastoscartaorepository){	
		Integer mes = 0;
		Integer ano = 0;
		if (this.data != null) {
			mes = this.data.getMonthValue();
			ano = this.data.getYear();
		}	
		List<GastosCartao> pessoasgasto = gastoscartaorepository.findPessoaId(this.pessoa.getIdpessoa(),mes, ano);
		this.valor2 = 0.0;

		for (GastosCartao gastosCartao : pessoasgasto) {
			this.valor2 += gastosCartao.getValor().doubleValue(); 
		} 

		
		return  pessoasgasto;
	}
	
	public Double valorTotal () {
		this.total = this.valor2+this.valor1;
		return this.total;
	}
	
	
	//Getters and Setters
	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}



}
