package br.com.tcc.financas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tcc.financas.model.Renda;

public interface RendaRepository extends JpaRepository<Renda, String> {

}
