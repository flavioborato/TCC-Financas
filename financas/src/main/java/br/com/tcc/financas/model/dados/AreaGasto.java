/*
 *   TCC Engenharia de Software
 * Projeto : Cadastro de Finanças
 * Autor : Flávio Fernando Borato
 * Versão : 0.0
 * Revisão : 06/03/2024
 * Enum - Valores pre fixados para a area de gasto
 * */

package br.com.tcc.financas.model.dados;

public enum AreaGasto {
	DIVERSAO("Diversão"),
	COMIDA("Comida"),
	LIMPEZA("Limpeza"),
	ESTUDO("Estudo"),
	SAUDE("Saude"),
	LOCOMOCAO("Locomoção"),
	PRESENTES("Presentes"),
	BEBIDA("Bebida"),
	CASA("Casa"),
	VESTUARIO("Vestuario"),	
	OUTROS("Outros diversos"),
	TERCEIROS("Terceiros"),
	TRABALHO("Trabalho");
	
	
	private final String displayValue;
    
    private AreaGasto(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }
}
