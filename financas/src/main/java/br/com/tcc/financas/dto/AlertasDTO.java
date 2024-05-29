/*
 *   TCC Engenharia de Software
 * Projeto : Cadastro de Finanças
 * Autor : Flávio Fernando Borato
 * Versão : 0.1
 * Revisão : 29/05/2024
 * Classe - Controle de dados para alertas de sistema
 * */

package br.com.tcc.financas.dto;

import java.util.List;

public class AlertasDTO {

	private Integer alertaMesAtual;
	private Integer alertaMesProximo;
	private List<Long> idMesAtual;
	
	
	
	public List<Long> getIdMesAtual() {
		return idMesAtual;
	}
	public void setIdMesAtual(List<Long> idMesAtual) {
		this.idMesAtual = idMesAtual;
	}
	public Integer getAlertaMesAtual() {
		return alertaMesAtual;
	}
	public void setAlertaMesAtual(Integer alertaMesAtual) {
		this.alertaMesAtual = alertaMesAtual;
	}
	public Integer getAlertaMesProximo() {
		return alertaMesProximo;
	}
	public void setAlertaMesProximo(Integer alertaMesProximo) {
		this.alertaMesProximo = alertaMesProximo;
	}
	
	
	
}
