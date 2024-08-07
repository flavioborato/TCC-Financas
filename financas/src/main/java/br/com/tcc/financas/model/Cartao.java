/*
 *   TCC Engenharia de Software
 * Projeto : Cadastro de Finanças
 * Autor : Flávio Fernando Borato
 * Versão : 0.0
 * Revisão : 06/03/2024
 * Classe - Entidade cartao - modelo do banco de dados
 * */

package br.com.tcc.financas.model;


import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.tcc.financas.model.dados.TipoCartao;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="cartao")
public class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idcartao;
	private Integer numero;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate validade;
	@Enumerated(EnumType.STRING)
	private TipoCartao tipo;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Pessoa pessoa;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cartao",fetch = FetchType.LAZY)
	@JsonIgnore
	private List<GastosCartao> gastoscartao;
	
	
	//Getters and Setters
	public Long getIdcartao() {
		return idcartao;
	}
	public void setIdcartao(Long idcartao) {
		this.idcartao = idcartao;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public LocalDate getValidade() {
		return validade;
	}
	public void setValidade(LocalDate validade) {
		this.validade = validade;
	}
	public TipoCartao getTipo() {
		return tipo;
	}
	public void setTipo(TipoCartao tipo) {
		this.tipo = tipo;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public List<GastosCartao> getGastoscartao() {
		return gastoscartao;
	}
	public void setGastoscartao(List<GastosCartao> gastoscartao) {
		this.gastoscartao = gastoscartao;
	}
	
	
	
	
	
	
	
	
}
