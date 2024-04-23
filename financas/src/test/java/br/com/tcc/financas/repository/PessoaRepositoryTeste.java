package br.com.tcc.financas.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.math.BigInteger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import br.com.tcc.financas.model.Pessoa;
import br.com.tcc.financas.model.Senha;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PessoaRepositoryTeste {

	@Autowired
	PessoaRepository pessoaRepository;

	
	private BigInteger cpf = BigInteger.valueOf(9999);
	private BigInteger cpf2 = BigInteger.valueOf(9999);
	
	
	@BeforeAll
	void incial() {
		
	}
	
	@Test
	@Order(1)
	void criaPessoaTeste() {
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
	
	@Test
	@Order(2)
	void findByNomeTeste() {
		Pessoa pessoaTeste = pessoaRepository.findByNome("PessoaTeste");
		assertEquals(pessoaTeste.getNome(),"PessoaTeste");
		assertEquals(pessoaTeste.getCpf(), cpf);
		assertEquals(pessoaTeste.getNivel(),2);
		assertNotNull(pessoaTeste.getSenha());
	}
	
	@Test
	@Order(3)	
	void atualizaTeste() {
		Pessoa pessoaAtualiza = pessoaRepository.findByNome("PessoaTeste");
		pessoaAtualiza.setNome("PessoaTesteAlteracao");
		pessoaAtualiza.setCpf(cpf2);
		pessoaAtualiza.setNivel(1);
		pessoaRepository.save(pessoaAtualiza);
		assertEquals(pessoaAtualiza.getNome(),"PessoaTesteAlteracao");
		assertEquals(pessoaAtualiza.getCpf(), cpf2);
		assertEquals(pessoaAtualiza.getNivel(),1);
	}
	
	@Test
	@Order(4)	
	void deleteTeste() {
		Pessoa pessoa = pessoaRepository.findByNome("PessoaTesteAlteracao");
		pessoaRepository.deleteById(pessoa.getIdpessoa());
		
		Pessoa pessoaDel = pessoaRepository.findByNome("PessoaTesteAlteracao");
		assertEquals(pessoaDel,null);
	}
	
	
	@AfterAll
	void finaliza() {
		
	}
	
}
