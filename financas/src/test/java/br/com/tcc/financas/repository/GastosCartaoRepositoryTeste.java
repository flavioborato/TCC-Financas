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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
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
import br.com.tcc.financas.model.GastosCartao;
import br.com.tcc.financas.model.Pessoa;
import br.com.tcc.financas.model.Senha;
import br.com.tcc.financas.model.dados.AreaGasto;
import br.com.tcc.financas.model.dados.TipoCartao;
import br.com.tcc.financas.model.dados.TipoGasto;


@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GastosCartaoRepositoryTeste {

	
	
	@Autowired
	PessoaRepository pessoaRepository;
	@Autowired
	CartaoRepository cartaoRepository;
	@Autowired
	GastosCartaoRepository gastosCartaoRepository;
	
	private BigInteger cpf = BigInteger.valueOf(9999);
	private Cartao cartaoNovo;
	private Pessoa pessoaTeste;
	private Long cartaoId;
	private GastosCartao gastoCartaoNovo;
	private Long gastoCartaoId;
	
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
		pessoaTeste = pessoaRepository.save(pessoaCerate);
		Cartao cartaoCreate = new Cartao();
		cartaoCreate.setNumero(9999);
		cartaoCreate.setPessoa(pessoaTeste);
		cartaoCreate.setTipo(TipoCartao.CREDITO);
		cartaoCreate.setValidade(LocalDate.now());
		cartaoNovo = cartaoRepository.save(cartaoCreate);
		cartaoId = cartaoNovo.getIdcartao();
	}
	
	/*
	* Teste de criação de dados
	 * */
	@Test
	@Order(1)
	void criaCartaoTeste() {
		GastosCartao gastosCartaoCreate = new GastosCartao();
		Pessoa pessoaTeste = pessoaRepository.findByNome("PessoaTeste");
		gastosCartaoCreate.setArea(AreaGasto.CASA);
		gastosCartaoCreate.setCartao(cartaoNovo);
		gastosCartaoCreate.setDatacompra(LocalDate.now());
		gastosCartaoCreate.setDescricao("Gastos Cartão Teste");
		gastosCartaoCreate.setMes(LocalDate.now());
		gastosCartaoCreate.setPessoa(pessoaTeste);
		gastosCartaoCreate.setParcelas(1);
		gastosCartaoCreate.setTipogasto(TipoGasto.FAMILIAR);
		gastosCartaoCreate.setValor(BigDecimal.valueOf(540.23));
		gastoCartaoNovo = gastosCartaoRepository.save(gastosCartaoCreate);
		gastoCartaoId = gastoCartaoNovo.getIdgastoscartao();
	}
	
	/*
	* Teste de procura de dados
	 * */
	@Test
	@Order(2)
	void findByIdPessoaTeste() {
		List<GastosCartao> gastosCartaoTeste = gastosCartaoRepository.findPessoaId(pessoaTeste.getIdpessoa() , LocalDate.now().getMonthValue() , LocalDate.now().getYear());
		assertEquals(gastosCartaoTeste.get(0).getArea(),AreaGasto.CASA);
		assertEquals(gastosCartaoTeste.get(0).getCartao().getIdcartao(),cartaoNovo.getIdcartao());
		assertEquals(gastosCartaoTeste.get(0).getDatacompra(),LocalDate.now());
		assertEquals(gastosCartaoTeste.get(0).getDescricao(),"Gastos Cartão Teste");
		assertEquals(gastosCartaoTeste.get(0).getMes(), LocalDate.now());
		assertEquals(gastosCartaoTeste.get(0).getPessoa().getIdpessoa(), pessoaTeste.getIdpessoa());
		assertEquals(gastosCartaoTeste.get(0).getParcelas(), 1);
		assertEquals(gastosCartaoTeste.get(0).getTipogasto(), TipoGasto.FAMILIAR);
		assertEquals(gastosCartaoTeste.get(0).getValor(), BigDecimal.valueOf(540.23));

	}
	
	/*
	* Teste de procura de dados
	 * */
	@Test
	@Order(3)
	void findByIdCartaoTeste() {
		List<GastosCartao> gastosCartaoTeste = gastosCartaoRepository.findCartaoPeloCartao(cartaoNovo.getIdcartao() , LocalDate.now().getMonthValue() , LocalDate.now().getYear());
		assertEquals(gastosCartaoTeste.get(0).getArea(),AreaGasto.CASA);
		assertEquals(gastosCartaoTeste.get(0).getCartao().getIdcartao(),cartaoNovo.getIdcartao());
		assertEquals(gastosCartaoTeste.get(0).getDatacompra(),LocalDate.now());
		assertEquals(gastosCartaoTeste.get(0).getDescricao(),"Gastos Cartão Teste");
		assertEquals(gastosCartaoTeste.get(0).getMes(), LocalDate.now());
		assertEquals(gastosCartaoTeste.get(0).getPessoa().getIdpessoa(), pessoaTeste.getIdpessoa());
		assertEquals(gastosCartaoTeste.get(0).getParcelas(), 1);
		assertEquals(gastosCartaoTeste.get(0).getTipogasto(), TipoGasto.FAMILIAR);
		assertEquals(gastosCartaoTeste.get(0).getValor(), BigDecimal.valueOf(540.23));

	}
	
	/*
	* Teste de procura de dados
	 * */
	@Test
	@Order(4)
	void findByDataTeste() {
		List<GastosCartao> gastosCartaoTeste = gastosCartaoRepository.findGastosCartao(LocalDate.now().getMonthValue() , LocalDate.now().getYear());
		assertEquals(gastosCartaoTeste.get(0).getArea(),AreaGasto.CASA);
		assertEquals(gastosCartaoTeste.get(0).getCartao().getIdcartao(),cartaoNovo.getIdcartao());
		assertEquals(gastosCartaoTeste.get(0).getDatacompra(),LocalDate.now());
		assertEquals(gastosCartaoTeste.get(0).getDescricao(),"Gastos Cartão Teste");
		assertEquals(gastosCartaoTeste.get(0).getMes(), LocalDate.now());
		assertEquals(gastosCartaoTeste.get(0).getPessoa().getIdpessoa(), pessoaTeste.getIdpessoa());
		assertEquals(gastosCartaoTeste.get(0).getParcelas(), 1);
		assertEquals(gastosCartaoTeste.get(0).getTipogasto(), TipoGasto.FAMILIAR);
		assertEquals(gastosCartaoTeste.get(0).getValor(), BigDecimal.valueOf(540.23));

	}
	
	/*
	* Teste de atualização dos dados
	 * */
	@Test
	@Order(5)	
	void atualizaTeste() {
		Optional<GastosCartao> gastosCartaoProcura = gastosCartaoRepository.findById(gastoCartaoId);
		GastosCartao  gastosCartaoAtualizaCartao = gastosCartaoProcura.get();
		gastosCartaoAtualizaCartao.setArea(AreaGasto.DIVERSAO);
		gastosCartaoAtualizaCartao.setDescricao("Gastos Cartão Teste Novo");
		gastosCartaoAtualizaCartao.setParcelas(2);
		gastosCartaoAtualizaCartao.setTipogasto(TipoGasto.PESSOAL);
		gastosCartaoAtualizaCartao.setValor(BigDecimal.valueOf(731.89));
		gastosCartaoRepository.save(gastosCartaoAtualizaCartao);
		
	}
	
	/*
	* Teste de verificação da atualização dos dados
	 * */
	@Test
	@Order(6)	
	void atualizaTesteVerifica() {
		Optional<GastosCartao> gastosCartaoProcura = gastosCartaoRepository.findById(gastoCartaoId);
		GastosCartao  gastosCartaoAtualizaCartao = gastosCartaoProcura.get();
		assertEquals(gastosCartaoAtualizaCartao.getArea(),AreaGasto.DIVERSAO);
		assertEquals(gastosCartaoAtualizaCartao.getCartao().getIdcartao(),cartaoNovo.getIdcartao());
		assertEquals(gastosCartaoAtualizaCartao.getDatacompra(),LocalDate.now());
		assertEquals(gastosCartaoAtualizaCartao.getDescricao(),"Gastos Cartão Teste Novo");
		assertEquals(gastosCartaoAtualizaCartao.getPessoa().getIdpessoa(), pessoaTeste.getIdpessoa());
		assertEquals(gastosCartaoAtualizaCartao.getParcelas(), 2);
		assertEquals(gastosCartaoAtualizaCartao.getMes(), LocalDate.now());
		assertEquals(gastosCartaoAtualizaCartao.getTipogasto(), TipoGasto.PESSOAL);
		assertEquals(gastosCartaoAtualizaCartao.getValor(), BigDecimal.valueOf(731.89));

	}
	
	/*
	* Teste de deletar os dados
	 * */
	@Test
	@Order(7)	
	void deleteTeste() {
		
		gastosCartaoRepository.deleteById(gastoCartaoId);
	    Optional<GastosCartao> gastosCartaoDel = gastosCartaoRepository.findById(gastoCartaoId);
	    assertEquals(gastosCartaoDel,Optional.empty());
		
	}
	
	/*
	* Finalização após os testes
	 * */
	@AfterAll
	void finaliza() {
		Pessoa pessoa = pessoaRepository.findByNome("PessoaTeste");
		cartaoRepository.deleteById(cartaoId);
		pessoaRepository.deleteById(pessoa.getIdpessoa());
	}
	
}
