package br.com.tcc.financas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tcc.financas.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, String> {

}
