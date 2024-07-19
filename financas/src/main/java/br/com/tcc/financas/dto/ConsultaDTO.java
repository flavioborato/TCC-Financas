/*
 *   TCC Engenharia de Software
 * Projeto : Cadastro de Finanças
 * Autor : Flávio Fernando Borato
 * Versão : 0.1
 * Revisão : 18/07/2024
 * Classe - Controle de entrada de dados para as consultas
 * */

package br.com.tcc.financas.dto;

import java.time.LocalDate;
import br.com.tcc.financas.model.Cartao;
import br.com.tcc.financas.model.Pessoa;
import br.com.tcc.financas.model.dados.TipoGasto;

public class ConsultaDTO {


	private LocalDate data;
	private Cartao cartao;
	private Pessoa pessoa;
	private String tipogasto;
	
	
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
	public String getTipogasto() {
		return tipogasto;
	}
	public void setTipogasto(String tipogasto) {
		this.tipogasto = tipogasto;
	}

	
	
	
	
	
}
