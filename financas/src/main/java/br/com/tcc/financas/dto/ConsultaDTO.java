/*
 *   TCC Engenharia de Software
 * Projeto : Cadastro de Finanças
 * Autor : Flávio Fernando Borato
 * Versão : 0.0
 * Revisão : 03/06/2024
 * Classe - Controle de entrada de dados para as consultas
 * */

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
