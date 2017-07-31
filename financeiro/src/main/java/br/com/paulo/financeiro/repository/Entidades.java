package br.com.paulo.financeiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.paulo.financeiro.modelo.Entidade;

public interface Entidades extends JpaRepository<Entidade, Long>{
	
	List<Entidade> findByNomeContainingIgnoreCase(String nome);
	
	@Query("FROM Entidade WHERE nome LIKE %?1% or ?1 is null")
	List<Entidade> porNome(String nome);

}
