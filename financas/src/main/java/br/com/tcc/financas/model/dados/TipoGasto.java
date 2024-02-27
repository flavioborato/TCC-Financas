package br.com.tcc.financas.model.dados;

public enum TipoGasto {
	PESSOAL("Pessoal"),
	FAMILIAR("familiar");
	
	private final String displayValue;
    
    private TipoGasto(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }
}
