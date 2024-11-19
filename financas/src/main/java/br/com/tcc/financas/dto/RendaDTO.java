/*
 *   TCC Engenharia de Software
 * Projeto : Cadastro de Finanças
 * Autor : Flávio Fernando Borato
 * Versão : 0.0
 * Revisão : 19/07/2024
 * Classe - Controle de entrada de dados para pesquisa da renda
 * */

package br.com.tcc.financas.dto;

import java.time.LocalDate;

public class RendaDTO {
	
	private LocalDate data ;
	private Double valortotal ;
	private Double rendatotal;
	private Double disponivel;
	private Double diversao;
	private Double comida;
	private Double limpeza;
	private Double estudo;
	private Double saude;
	private Double locomocao;
	private Double presente;
	private Double bebida;
	private Double casa;
	private Double vestuario;
	private Double outros;
	private Double terceiros;
	private Double trabalho;
	
	
	

	public Double getTerceiros() {
		return terceiros;
	}

	public void setTerceiros(Double terceiros) {
		this.terceiros = terceiros;
	}

	public Double getTrabalho() {
		return trabalho;
	}

	public void setTrabalho(Double trabalho) {
		this.trabalho = trabalho;
	}

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

	public Double getDiversao() {
		return diversao;
	}

	public void setDiversao(Double diversao) {
		this.diversao = diversao;
	}

	public Double getComida() {
		return comida;
	}

	public void setComida(Double comida) {
		this.comida = comida;
	}

	public Double getLimpeza() {
		return limpeza;
	}

	public void setLimpeza(Double limpeza) {
		this.limpeza = limpeza;
	}

	public Double getEstudo() {
		return estudo;
	}

	public void setEstudo(Double estudo) {
		this.estudo = estudo;
	}

	public Double getSaude() {
		return saude;
	}

	public void setSaude(Double saude) {
		this.saude = saude;
	}

	public Double getLocomocao() {
		return locomocao;
	}

	public void setLocomocao(Double locomocao) {
		this.locomocao = locomocao;
	}

	public Double getPresente() {
		return presente;
	}

	public void setPresente(Double presente) {
		this.presente = presente;
	}

	public Double getBebida() {
		return bebida;
	}

	public void setBebida(Double bebida) {
		this.bebida = bebida;
	}

	public Double getCasa() {
		return casa;
	}

	public void setCasa(Double casa) {
		this.casa = casa;
	}

	public Double getVestuario() {
		return vestuario;
	}

	public void setVestuario(Double vestuario) {
		this.vestuario = vestuario;
	}

	public Double getOutros() {
		return outros;
	}

	public void setOutros(Double outros) {
		this.outros = outros;
	}
	
	
	
	
	
	


}
