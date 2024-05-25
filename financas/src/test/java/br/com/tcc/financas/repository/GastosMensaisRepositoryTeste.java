/*
 *   TCC Engenharia de Software
 * Projeto : Cadastro de Finanças
 * Autor : Flávio Fernando Borato
 * Versão : 0.1
 * Revisão : 24/05/2024
 * Classe - Teste Repository - Cartão
 * */
package br.com.tcc.financas.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import br.com.tcc.financas.model.GastosMensais;
import br.com.tcc.financas.model.Pessoa;
import br.com.tcc.financas.model.Senha;
import br.com.tcc.financas.model.dados.AreaGasto;
import br.com.tcc.financas.model.dados.TipoGasto;


@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GastosMensaisRepositoryTeste {

	
	
	@Autowired
	PessoaRepository pessoaRepository;

	@Autowired
	GastosMensaisRepository gastosMensaisRepository;
	
	private BigInteger cpf = BigInteger.valueOf(9999);
	private Pessoa pessoaTeste;
	private GastosMensais gastoMensaisNovo;
	private Long gastoMensaisId;
	private String dataTexto = "01/04/2020"; 
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private LocalDate dataTeste = LocalDate.parse(dataTexto, formatter);
	
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
		
	}
	
	/*
	* Teste de criação de dados
	 * */
	@Test
	@Order(1)
	void criaCartaoTeste() {
		GastosMensais gastosMensaisCreate = new GastosMensais();
		Pessoa pessoaTeste = pessoaRepository.findByNome("PessoaTeste");
		gastosMensaisCreate.setArea(AreaGasto.CASA);
		gastosMensaisCreate.setDatacompra(dataTeste);
		gastosMensaisCreate.setDescricao("Gastos Mensais Teste");
		gastosMensaisCreate.setMes(dataTeste);
		gastosMensaisCreate.setPessoa(pessoaTeste);
		gastosMensaisCreate.setTipogasto(TipoGasto.FAMILIAR);
		gastosMensaisCreate.setValor(BigDecimal.valueOf(540.23));
		gastoMensaisNovo = gastosMensaisRepository.save(gastosMensaisCreate);
		gastoMensaisId = gastoMensaisNovo.getIdgastosmensais();
	}
	
	/*
	* Teste de procura de dados
	 * */
	@Test
	@Order(2)
	void findByIdPessoaTeste() {
		List<GastosMensais> gastosMensaisTeste = gastosMensaisRepository.findPessoaId(pessoaTeste.getIdpessoa() ,  dataTeste.getMonth().getValue() , dataTeste.getYear());
		assertEquals(gastosMensaisTeste.get(0).getArea(),AreaGasto.CASA);
		assertEquals(gastosMensaisTeste.get(0).getDatacompra(),dataTeste);
		assertEquals(gastosMensaisTeste.get(0).getDescricao(),"Gastos Mensais Teste");
		assertEquals(gastosMensaisTeste.get(0).getMes(), dataTeste);
		assertEquals(gastosMensaisTeste.get(0).getPessoa().getIdpessoa(), pessoaTeste.getIdpessoa());
		assertEquals(gastosMensaisTeste.get(0).getTipogasto(), TipoGasto.FAMILIAR);
		assertEquals(gastosMensaisTeste.get(0).getValor(), BigDecimal.valueOf(540.23));

	}
	

	
	/*
	* Teste de procura de dados
	 * */
	@Test
	@Order(3)
	void findByDataTeste() {
		List<GastosMensais> gastosMensaisTeste = gastosMensaisRepository.findMensalData( dataTeste.getMonth().getValue() , dataTeste.getYear());
		assertEquals(gastosMensaisTeste.get(0).getArea(),AreaGasto.CASA);
		assertEquals(gastosMensaisTeste.get(0).getDatacompra(),dataTeste);
		assertEquals(gastosMensaisTeste.get(0).getDescricao(),"Gastos Mensais Teste");
		assertEquals(gastosMensaisTeste.get(0).getMes(),dataTeste);
		assertEquals(gastosMensaisTeste.get(0).getPessoa().getIdpessoa(), pessoaTeste.getIdpessoa());
		assertEquals(gastosMensaisTeste.get(0).getTipogasto(), TipoGasto.FAMILIAR);
		assertEquals(gastosMensaisTeste.get(0).getValor(), BigDecimal.valueOf(540.23));

	}
	
	/*
	* Teste de atualização dos dados
	 * */
	@Test
	@Order(4)	
	void atualizaTeste() {
		Optional<GastosMensais> gastosMensaisProcura = gastosMensaisRepository.findById(gastoMensaisId);
		GastosMensais  gastosMensaisAtualizaCartao = gastosMensaisProcura.get();
		gastosMensaisAtualizaCartao.setArea(AreaGasto.DIVERSAO);
		gastosMensaisAtualizaCartao.setDescricao("Gastos Mensais Teste Novo");
		gastosMensaisAtualizaCartao.setTipogasto(TipoGasto.PESSOAL);
		gastosMensaisAtualizaCartao.setValor(BigDecimal.valueOf(731.89));
		gastosMensaisRepository.save(gastosMensaisAtualizaCartao);
		
	}
	
	/*
	* Teste de verificação da atualização dos dados
	 * */
	@Test
	@Order(5)	
	void atualizaTesteVerifica() {
		Optional<GastosMensais> gastosMensaisProcura = gastosMensaisRepository.findById(gastoMensaisId);
		GastosMensais  gastosMensaisAtualizaCartao = gastosMensaisProcura.get();
		assertEquals(gastosMensaisAtualizaCartao.getArea(),AreaGasto.DIVERSAO);
		assertEquals(gastosMensaisAtualizaCartao.getDatacompra(),dataTeste);
		assertEquals(gastosMensaisAtualizaCartao.getDescricao(),"Gastos Mensais Teste Novo");
		assertEquals(gastosMensaisAtualizaCartao.getPessoa().getIdpessoa(), pessoaTeste.getIdpessoa());
		assertEquals(gastosMensaisAtualizaCartao.getMes(),dataTeste);
		assertEquals(gastosMensaisAtualizaCartao.getTipogasto(), TipoGasto.PESSOAL);
		assertEquals(gastosMensaisAtualizaCartao.getValor(), BigDecimal.valueOf(731.89));

	}
	
	/*
	* Teste de deletar os dados
	 * */
	@Test
	@Order(6)	
	void deleteTeste() {
		
		gastosMensaisRepository.deleteById(gastoMensaisId);
	    Optional<GastosMensais> gastosMensaisDel = gastosMensaisRepository.findById(gastoMensaisId);
	    assertEquals(gastosMensaisDel,Optional.empty());
		
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
