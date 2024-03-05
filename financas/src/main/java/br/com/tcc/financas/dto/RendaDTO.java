package br.com.tcc.financas.dto;

import java.time.LocalDate;

public class RendaDTO {
	
	private LocalDate data ;
	private Double valortotal ;
	private Double rendatotal;
	private Double disponivel;
	
	
	
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public Double getValortotal() {
		return valortotal;
	}
	public void setValortotal(Double valortotal) {
		this.valortotal = valortotal;
	}
	public Double getRendatotal() {
		return rendatotal;
	}
	public void setRendatotal(Double rendatotal) {
		this.rendatotal = rendatotal;
	}
	public Double getDisponivel() {
		return disponivel;
	}
	public void setDisponivel(Double disponivel) {
		this.disponivel = disponivel;
	}

	
	
	
	
	
	


}
