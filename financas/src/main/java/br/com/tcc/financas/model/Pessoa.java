package br.com.tcc.financas.model;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="pessoa")
public class Pessoa implements UserDetails {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idpessoa;
	private String nome;
	private BigInteger cpf;
	private Integer nivel;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "pessoa",fetch = FetchType.LAZY)
	@JsonIgnore
	private Senha senha;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa",fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Renda> renda;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa",fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Cartao> cartao;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa",fetch = FetchType.LAZY)
	@JsonIgnore
	private List<GastosMensais> gastosmensais;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa",fetch = FetchType.LAZY)
	@JsonIgnore
	private List<GastosCartao> gastoscartao;
	
	//Getters and Setters
	public Long getIdpessoa() {
		return idpessoa;
	}
	public void setIdpessoa(Long idpessoa) {
		this.idpessoa = idpessoa;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigInteger getCpf() {
		return cpf;
	}
	public void setCpf(BigInteger cpf) {
		this.cpf = cpf;
	}
	public Integer getNivel() {
		return nivel;
	}
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
	public Senha getSenha() {
		return senha;
	}
	public void setSenha(Senha senha) {
		this.senha = senha;
	}
	public List<Renda> getRenda() {
		return renda;
	}
	public void setRenda(List<Renda> renda) {
		this.renda = renda;
	}
	public List<Cartao> getCartao() {
		return cartao;
	}
	public void setCartao(List<Cartao> cartao) {
		this.cartao = cartao;
	}
	public List<GastosMensais> getGastosmensais() {
		return gastosmensais;
	}
	public void setGastosmensais(List<GastosMensais> gastosmensais) {
		this.gastosmensais = gastosmensais;
	}
	public List<GastosCartao> getGastoscartao() {
		return gastoscartao;
	}
	public void setGastoscartao(List<GastosCartao> gastoscartao) {
		this.gastoscartao = gastoscartao;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}
	@Override
	public String getPassword() {
		return this.getSenha().getSenha();
	}
	@Override
	public String getUsername() {
		return this.getNome();
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
	

}
