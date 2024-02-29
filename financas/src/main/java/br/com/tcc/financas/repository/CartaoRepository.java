package br.com.tcc.financas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tcc.financas.model.Cartao;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {

}