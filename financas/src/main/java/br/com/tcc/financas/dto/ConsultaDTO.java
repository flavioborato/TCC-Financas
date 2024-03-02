package br.com.tcc.financas.dto;

import java.time.LocalDate;
import br.com.tcc.financas.model.Cartao;
import br.com.tcc.financas.model.Pessoa;
import br.com.tcc.financas.model.dados.AreaGasto;
import br.com.tcc.financas.model.dados.TipoGasto;

public class ConsultaDTO {

	private LocalDate data;
	private TipoGasto tipogasto;
	private	AreaGasto area;
	private Cartao cartao;
	private Pessoa pessoa;
	
	
	//Getters and Setters
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
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
