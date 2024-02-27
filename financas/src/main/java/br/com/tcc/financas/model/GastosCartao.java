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
	private Long idgastos_cartao;
	private Integer parcelas;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate data_compra;
	private BigDecimal valor;
	private String descricao;
	@Enumerated(EnumType.STRING)
	private TipoGasto tipo_gasto;
	@Enumerated(EnumType.STRING)
	private AreaGasto area;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate mes;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Pessoa pessoa;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Cartao cartao;
	
	
	
	
	//Getters and Setters
	public Long getIdgastos_cartao() {
		return idgastos_cartao;
	}
	public void setIdgastos_cartao(Long idgastos_cartao) {
		this.idgastos_cartao = idgastos_cartao;
	}
	public Integer getParcelas() {
		return parcelas;
	}
	public void setParcelas(Integer parcelas) {
		this.parcelas = parcelas;
	}
	public LocalDate getData_compra() {
		return data_compra;
	}
	public void setData_compra(LocalDate data_compra) {
		this.data_compra = data_compra;
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
	public TipoGasto getTipo_gasto() {
		return tipo_gasto;
	}
	public void setTipo_gasto(TipoGasto tipo_gasto) {
		this.tipo_gasto = tipo_gasto;
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
