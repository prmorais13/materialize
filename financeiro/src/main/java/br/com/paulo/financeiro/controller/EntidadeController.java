package br.com.paulo.financeiro.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.paulo.financeiro.modelo.Entidade;
import br.com.paulo.financeiro.service.EntidadeService;

@Controller
@RequestMapping(value = "/entidades")
public class EntidadeController {

private static final String INDEX = "entidade/CadastrarEntidade";

@Autowired
private EntidadeService entidadeService;

private ModelAndView mv;
	
	@RequestMapping(value = "/novo")
	public String novo(Entidade entidade) {
		return INDEX;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public String salvar(@Valid Entidade entidade, BindingResult result){
		
		if(result.hasErrors()) {
			return this.novo(entidade);
		}
		
		this.entidadeService.salvar(entidade);
		return "redirect:/entidades/novo";
	}
	
	@RequestMapping
	/*public String pesquisar(Entidade entidade, Model model) {
		String nome = entidade.getNome() == null ? "%" : entidade.getNome();
		model.addAttribute("entidades", this.entidadeService.buscar(nome));
		return "entidade/PesquisarEntidade";
	}*/
	
	public ModelAndView pesquisar(Entidade entidade) {
		this.mv = new ModelAndView("entidade/PesquisarEntidade");
		this.mv.addObject("entidades", this.entidadeService.buscar(entidade.getNome()));
		return this.mv;
	}
	
	@RequestMapping(value = "/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Entidade entidade) {
		this.mv = new ModelAndView(INDEX);
		this.mv.addObject(entidade);
		return mv;
	}
	
	@RequestMapping(value = "/{codigo}", method = RequestMethod.DELETE)
	public String remover(@PathVariable Long codigo) {
		this.entidadeService.excluir(codigo);
		return "redirect:/entidades";
	}
}
