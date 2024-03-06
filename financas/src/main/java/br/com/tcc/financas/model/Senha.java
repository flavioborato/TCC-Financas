package br.com.tcc.financas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="senha")
public class Senha  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idsenha;
	private String senha;
	@OneToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Pessoa pessoa;
	
	
	//Getters and Setters
	public Long getIdsenha() {
		return idsenha;
	}
	public void setIdsenha(Long idsenha) {
		this.idsenha = idsenha;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	

	


	
	

}
