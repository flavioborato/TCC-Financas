/*
 *   TCC Engenharia de Software
 * Projeto : Cadastro de Finanças
 * Autor : Flávio Fernando Borato
 * Versão : 0.0
 * Revisão : 25/04/2024
 * Classe - Teste Repository - Cartão
 * */
package br.com.tcc.financas.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigInteger;
import java.time.LocalDate;
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
import br.com.tcc.financas.model.Cartao;
import br.com.tcc.financas.model.Pessoa;
import br.com.tcc.financas.model.Senha;
import br.com.tcc.financas.model.dados.TipoCartao;


@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CartaoRepositoryTeste {

	
	
	@Autowired
	PessoaRepository pessoaRepository;
	@Autowired
	CartaoRepository cartaoRepository;
	
	private BigInteger cpf = BigInteger.valueOf(9999);
	private Cartao cartaoNovo;
	private Long cartaoId;
	
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
	void criaCartaoTeste() {
		Cartao cartaoCreate = new Cartao();
		Pessoa pessoaTeste = pessoaRepository.findByNome("PessoaTeste");
		cartaoCreate.setNumero(9999);
		cartaoCreate.setPessoa(pessoaTeste);
		cartaoCreate.setTipo(TipoCartao.CREDITO);
		cartaoCreate.setValidade(LocalDate.now());
		cartaoNovo = cartaoRepository.save(cartaoCreate);
		cartaoId = cartaoNovo.getIdcartao();
	}
	
	/*
	* Teste de procura de dados
	 * */
	@Test
	@Order(2)
	void findByIdTeste() {
		Optional<Cartao> cartaoTeste = cartaoRepository.findById(cartaoId);
		Pessoa pessoaTeste = pessoaRepository.findByNome("PessoaTeste");
		assertEquals(cartaoTeste.get().getNumero(), 9999);
		assertEquals(cartaoTeste.get().getTipo(),TipoCartao.CREDITO);
		assertEquals(cartaoTeste.get().getValidade(),LocalDate.now());
		assertEquals(cartaoTeste.get().getPessoa().getIdpessoa(), pessoaTeste.getIdpessoa());
	
	}
	
	/*
	* Teste de atualização dos dados
	 * */
	@Test
	@Order(3)	
	void atualizaTeste() {
		Optional<Cartao> cartaoProcura = cartaoRepository.findById(cartaoId);
		Cartao cartaoAtualiza = cartaoProcura.get();
		cartaoAtualiza.setNumero(9988);
		cartaoAtualiza.setTipo(TipoCartao.AMBOS);
		cartaoRepository.save(cartaoAtualiza);
	}
	
	/*
	* Teste de verificação da atualização dos dados
	 * */
	@Test
	@Order(4)	
	void atualizaTesteVerifica() {
		Optional<Cartao> cartaoAtualiza = cartaoRepository.findById(cartaoId);
		assertEquals(cartaoAtualiza.get().getNumero(), 9988);
		assertEquals(cartaoAtualiza.get().getTipo(),TipoCartao.AMBOS);

	}
	
	/*
	* Teste de deletar os dados
	 * */
	@Test
	@Order(5)	
	void deleteTeste() {
		
		cartaoRepository.deleteById(cartaoId);
	    Optional<Cartao> cartaoDel = cartaoRepository.findById(cartaoId);
	    assertEquals(cartaoDel,Optional.empty());
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
