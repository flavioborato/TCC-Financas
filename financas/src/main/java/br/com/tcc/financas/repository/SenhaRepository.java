/*
 *   TCC Engenharia de Software
 * Projeto : Cadastro de Finanças
 * Autor : Flávio Fernando Borato
 * Versão : 0.0
 * Revisão : 03/06/2024
 * Interface - Repositorio para acesso a DB da classe senha
 * */

package br.com.tcc.financas.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tcc.financas.model.Senha;

@Repository
public interface SenhaRepository extends JpaRepository<Senha, Long> {

	

}
