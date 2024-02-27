package br.com.tcc.financas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tcc.financas.model.GastosCartao;

public interface GastosCartaoRepository extends JpaRepository<GastosCartao, String> {

}
