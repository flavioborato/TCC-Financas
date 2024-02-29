package br.com.tcc.financas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tcc.financas.model.Renda;

@Repository
public interface RendaRepository extends JpaRepository<Renda, Long> {

}
