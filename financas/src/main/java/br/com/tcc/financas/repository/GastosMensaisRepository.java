package br.com.tcc.financas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tcc.financas.model.GastosMensais;

public interface GastosMensaisRepository extends JpaRepository<GastosMensais, String> {

}
