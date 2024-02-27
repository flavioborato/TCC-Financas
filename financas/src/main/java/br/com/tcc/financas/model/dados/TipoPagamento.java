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
