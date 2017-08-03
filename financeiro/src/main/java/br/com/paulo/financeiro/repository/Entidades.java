package br.com.paulo.financeiro.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.paulo.financeiro.dto.EntidadeDTO;
import br.com.paulo.financeiro.modelo.Entidade;

public interface Entidades extends JpaRepository<Entidade, Long>{
	
	List<Entidade> findByNomeContainingIgnoreCase(String nome);
	
	@Query("FROM Entidade e WHERE nome LIKE %?1% or ?1 is null ORDER BY e.nome")
	Page<Entidade> porNome(String nome, Pageable pageable);
	
	@Query("SELECT new br.com.paulo.financeiro.dto.EntidadeDTO(codigo, nome) FROM Entidade WHERE lower(nome) LIKE %?1%")
	List<EntidadeDTO> filtradas(String nome);
	

}