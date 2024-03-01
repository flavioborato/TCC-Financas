package br.com.tcc.financas.model;

import java.math.BigDecimal;
import java.util.Calendar;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import br.com.tcc.financas.model.dados.AreaGasto;
import br.com.tcc.financas.model.dados.TipoGasto;
import br.com.tcc.financas.model.dados.TipoPagamento;
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
@Table(name="gastos_mensais")
public class GastosMensais {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idgastosmensais;
	@Enumerated(EnumType.STRING)
	private TipoPagamento tipopagamento;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Calendar datacompra;
	private BigDecimal valor;
	private String descricao;
	@Enumerated(EnumType.STRING)
	private TipoGasto tipogasto;
	@Enumerated(EnumType.STRING)
	private AreaGasto area;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Calendar mes;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Pessoa pessoa;
	
	
	
	//Getters and Setters
	public Long getIdgastosmensais() {
		return idgastosmensais;
	}
	public void setIdgastosmensais(Long idgastosmensais) {
		this.idgastosmensais = idgastosmensais;
	}
	public TipoPagamento getTipopagamento() {
		return tipopagamento;
	}
	public void setTipopagamento(TipoPagamento tipopagamento) {
		this.tipopagamento = tipopagamento;
	}
	public Calendar getDatacompra() {
		return datacompra;
	}
	public void setDatacompra(Calendar datacompra) {
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
	public Calendar getMes() {
		return mes;
	}
	public void setMes(Calendar mes) {
		this.mes = mes;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	
	
	

}
