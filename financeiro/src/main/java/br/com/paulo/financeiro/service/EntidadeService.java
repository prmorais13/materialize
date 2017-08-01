package br.com.paulo.financeiro.service;

import java.util.List;

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
	
	public List<Entidade> buscar(String nome){
		//return this.entidades.findByNomeContainingIgnoreCase(nome);
		return this.entidades.porNome(nome);
	}
	
	public void excluir(Long codigo) {
		this.entidades.delete(codigo);
	}
}
