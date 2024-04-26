/*
 *   TCC Engenharia de Software
 * Projeto : Cadastro de Finanças
 * Autor : Flávio Fernando Borato
 * Versão : 0.0
 * Revisão : 20/04/2024
 * Classe - Teste Controller 
 * */

package br.com.tcc.financas.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
public class ControlerTeste {

	@Autowired
	private WebApplicationContext context;
	@Autowired
	private MockMvc mockMvc;
	
	
	/*
	* Libera Security antes dos Testes
	 * */
	@BeforeEach
	void setup() {
		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(SecurityMockMvcConfigurers.springSecurity())
				.build();
	}
	
	/*
	* Carrega Pagina de Login caso nada passado
	 * */
	@Test
	void deveRedirecionarParaLoginSeVazio() throws Exception {
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/"))
		.andExpect(MockMvcResultMatchers.view().name("login"));
	}
	
	/*
	* Carrega Pagina de Login caso passado login
	 * */
	@Test
	void deveRedirecionarParaLogin() throws Exception {
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/login"))
		.andExpect(MockMvcResultMatchers.view().name("login"));
	}
	
	/*
	* Carrega Pagina de Pessoas caso passado pessoas
	* Verifica atributos existentes
	 * */
	@Test
	@WithUserDetails("Flavio")
	void deveRedirecionarParaPessoas() throws Exception {
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/pessoas"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("pessoas"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("pessoacadastro"))
		.andExpect(MockMvcResultMatchers.view().name("pessoas"));
	}
	
	/*
	* Carrega Pagina de Consultas caso passado consultas
	* Verifica atributos existentes
	 * */
	@Test
	@WithUserDetails("Flavio")
	void deveRedirecionarParaConsultas() throws Exception {
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/consultas"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("consultaslista"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("pessoas"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("cartoes"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("rendadto"))
		.andExpect(MockMvcResultMatchers.view().name("consultas"));
	}
	

	/*
	* Carrega Pagina de GastosDeCartoes caso passado gastosdecartao
	* Verifica atributos existentes
	 * */
	@Test
	@WithUserDetails("Flavio")
	void deveRedirecionarParaGastosDeCartoes() throws Exception {
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/gastosdecartao"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("gastocartaocadastro"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("pessoas"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("cartoes"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("gastoscartoes"))
		.andExpect(MockMvcResultMatchers.view().name("gastosdecartao"));
	}
	
	/*
	* Carrega Pagina de GastosMensais caso passado gastosmensais
	* Verifica atributos existentes
	 * */
	@Test
	@WithUserDetails("Flavio")
	void deveRedirecionarParaGastosMensais() throws Exception {
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/gastosmensais"))	
		.andExpect(MockMvcResultMatchers.model().attributeExists("gastomensalcadastro"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("pessoas"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("gastosmensais"))
		.andExpect(MockMvcResultMatchers.view().name("gastosmensais"));
	}
	
	/*
	* Carrega Pagina de Cartões caso passado cartao
	* Verifica atributos existentes
	 * */
	@Test
	@WithUserDetails("Flavio")
	void deveRedirecionarParaCartoes() throws Exception {
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/cartao"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("pessoas"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("cartoes"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("cartaocadastro"))
		.andExpect(MockMvcResultMatchers.view().name("cartao"));
	}
	
	/*
	* Carrega Pagina de Renda caso passado renda
	* Verifica atributos existentes
	 * */
	@Test
	@WithUserDetails("Flavio")
	void deveRedirecionarParaRenda() throws Exception {
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/renda"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("rendacadastro"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("pessoas"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("rendas"))
		.andExpect(MockMvcResultMatchers.view().name("renda"));
	}
	
}
