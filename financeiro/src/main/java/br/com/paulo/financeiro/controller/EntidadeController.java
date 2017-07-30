package br.com.paulo.financeiro.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.paulo.financeiro.modelo.Entidade;
import br.com.paulo.financeiro.service.EntidadeService;

@Controller
@RequestMapping(value = "/entidades")
public class EntidadeController {

private static final String INDEX = "entidade/CadastrarEntidade";

@Autowired
private EntidadeService entidadeService;
	
	@RequestMapping(value = "/novo")
	public String novo(Entidade entidade) {
		return INDEX;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public String salvar(@Valid Entidade entidade, BindingResult result){
		
		if(result.hasErrors()) {
			this.novo(entidade);
		}
		
		this.entidadeService.salvar(entidade);
		return "redirect:/entidades/novo";
	}
}
