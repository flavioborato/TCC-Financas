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
	OUTROS("Outros diversos");
	
	
	private final String displayValue;
    
    private AreaGasto(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }
}
