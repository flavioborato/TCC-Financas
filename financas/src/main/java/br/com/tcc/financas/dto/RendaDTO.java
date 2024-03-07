/*
 *   TCC Engenharia de Software
 * Projeto : Cadastro de Finanças
 * Autor : Flávio Fernando Borato
 * Versão : 0.0
 * Revisão : 03/06/2024
 * Classe - Controle de entrada de dados para pesquisa da renda
 * */

package br.com.tcc.financas.dto;

import java.time.LocalDate;

public class RendaDTO {
	
	private LocalDate data ;
	private Double valortotal ;
	private Double rendatotal;
	private Double disponivel;
	
	
	
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public Double getValortotal() {
		return valortotal;
	}
	public void setValortotal(Double valortotal) {
		this.valortotal = valortotal;
	}
	public Double getRendatotal() {
		return rendatotal;
	}
	public void setRendatotal(Double rendatotal) {
		this.rendatotal = rendatotal;
	}
	public Double getDisponivel() {
		return disponivel;
	}
	public void setDisponivel(Double disponivel) {
		this.disponivel = disponivel;
	}

	
	
	
	
	
	


}
