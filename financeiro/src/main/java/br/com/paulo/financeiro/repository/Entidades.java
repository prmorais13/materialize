package br.com.paulo.financeiro.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.paulo.financeiro.dto.EntidadeDTO;
import br.com.paulo.financeiro.modelo.Entidade;

public interface Entidades extends JpaRepository<Entidade, Long>{
	
	List<Entidade> findByNomeContainingIgnoreCase(String nome);
	
	@Query("FROM Entidade WHERE nome LIKE %?1% or ?1 is null")
	List<Entidade> porNome(String nome, Pageable pageable);
	
	 @Query("SELECT new com.crud.financeiro.dto.EntidadeDTO(codigo, nome) FROM Entidade WHERE lower(nome) LIKE %?1%")
	List<EntidadeDTO> filtradas(String nome);

}