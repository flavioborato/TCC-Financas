/*
 *   TCC Engenharia de Software
 * Projeto : Cadastro de Finanças
 * Autor : Flávio Fernando Borato
 * Versão : 0.0
 * Revisão : 25/04/2024
 * Classe - Teste Repository - Renda
 * */
package br.com.tcc.financas.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import br.com.tcc.financas.model.Pessoa;
import br.com.tcc.financas.model.Renda;
import br.com.tcc.financas.model.Senha;


@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RendaRepositoryTeste {

	
	
	@Autowired
	PessoaRepository pessoaRepository;
	@Autowired
	RendaRepository rendaRepository;
	
	private BigInteger cpf = BigInteger.valueOf(9999);
	private BigDecimal valor1 =  BigDecimal.valueOf(1234.12);
	private BigDecimal valor2 =  BigDecimal.valueOf(4321.15);
	private Renda rendaNovo;
	private Long rendaId;
	
	/*
	* Inicialização antes dos testes
	 * */
	@BeforeAll
	void incial() {
		Pessoa pessoaCerate = new Pessoa();	
		Senha senha = new Senha();
		senha.setSenha("123456");
		pessoaCerate.setNome("PessoaTeste");
		pessoaCerate.setCpf(cpf);
		pessoaCerate.setNivel(2);
		pessoaCerate.setSenha(senha);
		pessoaCerate.getSenha().setPessoa(pessoaCerate);
		pessoaRepository.save(pessoaCerate);
	}
	
	/*
	* Teste de criação de dados
	 * */
	@Test
	@Order(1)
	void criaRendaTeste() {
		Renda rendaCreate = new Renda();
		Pessoa pessoaTeste = pessoaRepository.findByNome("PessoaTeste");
		rendaCreate.setValor(valor1);
		rendaCreate.setPessoa(pessoaTeste);
		rendaCreate.setDescricao("Teste de Renda");
		rendaNovo = rendaRepository.save(rendaCreate);
		rendaId = rendaNovo.getIdrenda();
	}
	
	/*
	* Teste de procura de dados
	 * */
	@Test
	@Order(2)
	void findByIdTeste() {
		Optional<Renda> rendaTeste = rendaRepository.findById(rendaId);
		Pessoa pessoaTeste = pessoaRepository.findByNome("PessoaTeste");
		assertEquals(rendaTeste.get().getValor(),  BigDecimal.valueOf(1234.12));
		assertEquals(rendaTeste.get().getDescricao(),"Teste de Renda");
		assertEquals(rendaTeste.get().getPessoa().getIdpessoa(), pessoaTeste.getIdpessoa());
	
	}
	
	/*
	* Teste de atualização dos dados
	 * */
	@Test
	@Order(3)	
	void atualizaTeste() {
		Optional<Renda> rendaProcura = rendaRepository.findById(rendaId);
		Renda rendaAtualiza = rendaProcura.get();
		rendaAtualiza.setValor(valor2);
		rendaAtualiza.setDescricao("Atualiza Teste de Renda");
		rendaRepository.save(rendaAtualiza);
		
	}
	
	/*
	* Teste de verificação da atualização dos dados
	 * */
	@Test
	@Order(4)	
	void atualizaTesteVerifica() {
		Optional<Renda> rendaAtualiza = rendaRepository.findById(rendaId);
		assertEquals(rendaAtualiza.get().getValor(), BigDecimal.valueOf(4321.15) );
		assertEquals(rendaAtualiza.get().getDescricao(),"Atualiza Teste de Renda");

	}
	
	/*
	* Teste de deletar os dados
	 * */
	@Test
	@Order(5)	
	void deleteTeste() {
		
		rendaRepository.deleteById(rendaId);
	    Optional<Renda> rendaDel = rendaRepository.findById(rendaId);
	    assertEquals(rendaDel,Optional.empty());
	}
	
	/*
	* Finalização após os testes
	 * */
	@AfterAll
	void finaliza() {
		Pessoa pessoa = pessoaRepository.findByNome("PessoaTeste");
		pessoaRepository.deleteById(pessoa.getIdpessoa());
	}
	
}
