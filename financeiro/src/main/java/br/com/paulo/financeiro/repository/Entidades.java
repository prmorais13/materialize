package br.com.paulo.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.paulo.financeiro.modelo.Entidade;

public interface Entidades extends JpaRepository<Entidade, Long>{

}
