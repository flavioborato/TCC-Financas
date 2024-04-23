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
	
	@Test
	@Order(1)
	void criaPessoaTeste() {
		Cartao cartaoCreate = new Cartao();
		Pessoa pessoaTeste = pessoaRepository.findByNome("PessoaTeste");
		cartaoCreate.setNumero(9999);
		cartaoCreate.setPessoa(pessoaTeste);
		cartaoCreate.setTipo(TipoCartao.CREDITO);
		cartaoCreate.setValidade(LocalDate.now());
		cartaoNovo = cartaoRepository.save(cartaoCreate);
		cartaoId = cartaoNovo.getIdcartao();
	}
	
	@Test
	@Order(2)
	void findByNumeroTeste() {
		
	}
	
	@Test
	@Order(3)	
	void atualizaTeste() {
		
	}
	
	@Test
	@Order(4)	
	void deleteTeste() {
		
		cartaoRepository.deleteById(cartaoId);
	    Optional<Cartao> cartaoDel = cartaoRepository.findById(cartaoId);
	    assertEquals(cartaoDel,Optional.empty());
	}
	
	
	@AfterAll
	void finaliza() {
		Pessoa pessoa = pessoaRepository.findByNome("PessoaTeste");
		pessoaRepository.deleteById(pessoa.getIdpessoa());
	}
	
}
