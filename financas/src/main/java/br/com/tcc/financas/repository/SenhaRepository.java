package br.com.tcc.financas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tcc.financas.model.Senha;

public interface SenhaRepository extends JpaRepository<Senha, String> {

}
