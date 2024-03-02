package br.com.tcc.financas.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.tcc.financas.model.GastosMensais;

@Repository
public interface GastosMensaisRepository extends JpaRepository<GastosMensais, Long> {
	
	@Query(value = "SELECT * FROM gastos_mensais g where g.pessoa_idpessoa= :id and MONTH(mes) = :mes and YEAR(mes) = :ano", nativeQuery = true)
	List<GastosMensais> findPessoaId(Long id, Integer mes, Integer ano);



}
