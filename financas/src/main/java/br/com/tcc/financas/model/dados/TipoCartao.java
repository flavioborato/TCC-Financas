package br.com.tcc.financas.model.dados;

public enum TipoCartao {
	DEBITO("Debito"),
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
