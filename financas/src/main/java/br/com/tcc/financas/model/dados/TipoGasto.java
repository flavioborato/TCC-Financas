/*
 *   TCC Engenharia de Software
 * Projeto : Cadastro de Finanças
 * Autor : Flávio Fernando Borato
 * Versão : 0.0
 * Revisão : 06/03/2024
 * Enum - Valores pre fixados para a tipos de gasto
 * */

package br.com.tcc.financas.model.dados;

public enum TipoGasto {
	PESSOAL("Pessoal"),
	FAMILIAR("Familiar");
	
	private final String displayValue;
    
    private TipoGasto(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }
}
