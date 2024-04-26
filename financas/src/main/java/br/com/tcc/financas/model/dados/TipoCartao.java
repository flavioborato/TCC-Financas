/*
 *   TCC Engenharia de Software
 * Projeto : Cadastro de Finanças
 * Autor : Flávio Fernando Borato
 * Versão : 0.0
 * Revisão : 06/03/2024
 * Enum - Valores pre fixados para a tipo de cartao
 * */

package br.com.tcc.financas.model.dados;

public enum TipoCartao {
	CREDITO("Credito"),
	AMBOS("Debito E Credito");
	
	private final String displayValue;
    
    private TipoCartao(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }
}
