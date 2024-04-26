/*
 *   TCC Engenharia de Software
 * Projeto : Cadastro de Finanças
 * Autor : Flávio Fernando Borato
 * Versão : 0.0
 * Revisão : 06/03/2024
 * Interface - Repositorio para acesso a DB da classe gastoscartao
 * */

package br.com.tcc.financas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.tcc.financas.model.GastosCartao;


@Repository
public interface GastosCartaoRepository extends JpaRepository<GastosCartao, Long> {
	
	@Query(value = "SELECT * FROM gastos_cartao g where g.pessoa_idpessoa= :id and MONTH(mes) = :mes and YEAR(mes) = :ano", nativeQuery = true)
	List<GastosCartao> findPessoaId(Long id, Integer mes, Integer ano);

	@Query(value = "SELECT * FROM gastos_cartao g where g.cartao_idcartao= :id and MONTH(mes) = :mes and YEAR(mes) = :ano", nativeQuery = true)
	List<GastosCartao> findCartaoPeloCartao(Long id, Integer mes, Integer ano);

	@Query(value = "SELECT * FROM gastos_cartao g where MONTH(mes) = :mes and YEAR(mes) = :ano", nativeQuery = true)
	List<GastosCartao> findGastosCartao(Integer mes, Integer ano);

}
