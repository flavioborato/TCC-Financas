package br.com.tcc.financas.dto;

import java.time.LocalDate;
import br.com.tcc.financas.model.Cartao;
import br.com.tcc.financas.model.Pessoa;

public class ConsultaDTO {


	private LocalDate data;
	private Cartao cartao;
	private Pessoa pessoa;
	
	
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
