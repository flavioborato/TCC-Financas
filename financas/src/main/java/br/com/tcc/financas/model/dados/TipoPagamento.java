/*
 *   TCC Engenharia de Software
 * Projeto : Cadastro de Finanças
 * Autor : Flávio Fernando Borato
 * Versão : 0.0
 * Revisão : 06/03/2024
 * Enum - Valores pre fixados para a tipos de pagamento
 * */

package br.com.tcc.financas.model.dados;

public enum TipoPagamento {
	PIX("PIX"),
	BOLETO("BOLETO"),
	DEBITO("Debito");
	
	private final String displayValue;
    
    private TipoPagamento(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }

}
