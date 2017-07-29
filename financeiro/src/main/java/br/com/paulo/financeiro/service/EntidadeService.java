package br.com.paulo.financeiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.paulo.financeiro.modelo.Entidade;
import br.com.paulo.financeiro.repository.Entidades;

@Service
public class EntidadeService {
	
	@Autowired
	private Entidades entidades;
	
	public void salvar(Entidade entidade) {
		this.entidades.save(entidade);
	}
}
