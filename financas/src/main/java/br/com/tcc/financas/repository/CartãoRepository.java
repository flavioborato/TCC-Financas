package br.com.tcc.financas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tcc.financas.model.Cartao;

public interface CartãoRepository extends JpaRepository<Cartao, String> {

}
