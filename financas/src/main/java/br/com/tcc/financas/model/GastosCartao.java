/*
 *   TCC Engenharia de Software
 * Projeto : Cadastro de Finanças
 * Autor : Flávio Fernando Borato
 * Versão : 0.0
 * Revisão : 03/06/2024
 * Classe - Entidade gastos_cartao - modelo do banco de dados
 * */

package br.com.tcc.financas.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import br.com.tcc.financas.model.dados.AreaGasto;
import br.com.tcc.financas.model.dados.TipoGasto;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="gastos_cartao")
public class GastosCartao {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idgastoscartao;
	private Integer parcelas;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate datacompra;
	private BigDecimal valor;
	private String descricao;
	@Enumerated(EnumType.STRING)
	private TipoGasto tipogasto;
	@Enumerated(EnumType.STRING)
	private AreaGasto area;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate mes;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Pessoa pessoa;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Cartao cartao;
	
	
	
	
	//Getters and Setters
	public Long getIdgastoscartao() {
		return idgastoscartao;
	}
	public void setIdgastoscartao(Long idgastoscartao) {
		this.idgastoscartao = idgastoscartao;
	}
	public Integer getParcelas() {
		return parcelas;
	}
	public void setParcelas(Integer parcelas) {
		this.parcelas = parcelas;
	}
	public LocalDate getDatacompra() {
		return datacompra;
	}
	public void setDatacompra(LocalDate datacompra) {
		this.datacompra = datacompra;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public TipoGasto getTipogasto() {
		return tipogasto;
	}
	public void setTipogasto(TipoGasto tipogasto) {
		this.tipogasto = tipogasto;
	}
	public AreaGasto getArea() {
		return area;
	}
	public void setArea(AreaGasto area) {
		this.area = area;
	}
	public LocalDate getMes() {
		return mes;
	}
	public void setMes(LocalDate mes) {
		this.mes = mes;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public Cartao getCartao() {
		return cartao;
	}
	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}
	
	
	
	
}
