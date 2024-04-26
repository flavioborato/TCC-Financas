/*
 *   TCC Engenharia de Software
 * Projeto : Cadastro de Finanças
 * Autor : Flávio Fernando Borato
 * Versão : 0.0
 * Revisão : 06/03/2024
 * Interface - Repositorio para acesso a DB da classe cartao
 * */

package br.com.tcc.financas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tcc.financas.model.Cartao;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {
	

}
