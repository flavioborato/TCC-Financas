/*
 *   TCC Engenharia de Software
 * Projeto : Cadastro de Finanças
 * Autor : Flávio Fernando Borato
 * Versão : 0.1
 * Revisão : 01/06/2024
 * Classe - Controle de dados para alertas de sistema
 * */

package br.com.tcc.financas.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import br.com.tcc.financas.model.GastosMensais;
import br.com.tcc.financas.repository.GastosMensaisRepository;

public class AlertasDTO {

	private Integer alertaMesAtual;
	private Integer alertaMesProximo;
	private List<Long> idMesAtual;
	private List<Long> idMesProximo;
	private List<Integer> dataMesAtual;
	private List<Integer> dataMesProximo;
	
	
	public List<Integer> getDataMesAtual() {
		return dataMesAtual;
	}
	public void setDataMesAtual(List<Integer> dataMesAtual) {
		this.dataMesAtual = dataMesAtual;
	}
	public List<Integer> getDataMesProximo() {
		return dataMesProximo;
	}
	public void setDataMesProximo(List<Integer> dataMesProximo) {
		this.dataMesProximo = dataMesProximo;
	}
	public List<Long> getIdMesProximo() {
		return idMesProximo;
	}
	public void setIdMesProximo(List<Long> idMesProximo) {
		this.idMesProximo = idMesProximo;
	}
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
	
	
	
	/*
	* Classe consluta das parcelas a vencer dos Gastos Mensais, e exibir status na tela
	 * */
	public AlertasDTO alertaVencimentoGastoMensal(GastosMensaisRepository gastosmensaispository, Integer verificaAlerta) {
		
		LocalDate data = LocalDate.now();
		AlertasDTO alertas = new AlertasDTO();
		
		List<Long> idGastosMensaisAtual = new ArrayList<>();
		List<Integer> dataGastosMensaisAtual = new ArrayList<>();
		if(verificaAlerta==0) {
			List<GastosMensais> gastosMensaisAtual = gastosmensaispository.findMensalData(data.getMonth().getValue(),data.getYear());
			//Percorre a lista para identificar as datas de vencimento do mes corrente
			for (GastosMensais gastosFor : gastosMensaisAtual) {
				if (gastosFor.getDatacompra().getDayOfMonth() <= (data.getDayOfMonth() + 3) && 
						gastosFor.getDatacompra().getDayOfMonth() >= data.getDayOfMonth() ) 
				{alertas.setAlertaMesAtual(1);
				dataGastosMensaisAtual.add(gastosFor.getDatacompra().getDayOfMonth());
				idGastosMensaisAtual.add(gastosFor.getIdgastosmensais());
				}			
			}
		}
		List<Long> idGastosMensaisProximo = new ArrayList<>();
		List<Integer> dataGastosMensaisProximo = new ArrayList<>();
		if(verificaAlerta==0) {
			List<GastosMensais> gastosMensaisProximo = gastosmensaispository.findMensalData((data.getMonth().getValue()+1),data.getYear());
			//Percorre a lista para identificar as datas de vencimento do proximo mes
			if( (data.getDayOfMonth() >= 27 && data.getMonth().getValue() == 2) ||
				(data.getDayOfMonth() >= 30 && data.getMonth().getValue() % 2 == 1 )||	
				(data.getDayOfMonth() >= 30 && data.getMonth().getValue() % 2 == 0 )	
					) {
				for (GastosMensais gastosFor : gastosMensaisProximo) {
					if (gastosFor.getDatacompra().getDayOfMonth() >= 1 && 
							gastosFor.getDatacompra().getDayOfMonth() <= 2 ) 
					{alertas.setAlertaMesProximo(1);
					dataGastosMensaisProximo.add(gastosFor.getDatacompra().getDayOfMonth());
					idGastosMensaisProximo.add(gastosFor.getIdgastosmensais());
					}			
				}
			}	
		}		
		alertas.setIdMesAtual(idGastosMensaisAtual);
		alertas.setIdMesProximo(idGastosMensaisProximo);
		alertas.setDataMesAtual(dataGastosMensaisAtual);
		alertas.setDataMesProximo(dataGastosMensaisProximo);
		return alertas;
		
	}	
	
	
}
