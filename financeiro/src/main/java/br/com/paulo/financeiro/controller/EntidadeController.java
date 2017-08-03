package br.com.paulo.financeiro.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.paulo.financeiro.controller.page.PageWrapper;
import br.com.paulo.financeiro.modelo.Entidade;
import br.com.paulo.financeiro.service.EntidadeService;

@Controller
@RequestMapping(value = "/entidades")
public class EntidadeController {

private static final String INDEX = "entidade/CadastrarEntidade";

@Autowired
private EntidadeService entidadeService;

private ModelAndView mv;
	
	@GetMapping("/novo")
	@RequestMapping(value = "/novo")
	public String novo(Entidade entidade) {
		return INDEX;
	}
	
	@PostMapping("/novo")
	//@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public String salvar(@Valid Entidade entidade, BindingResult result, RedirectAttributes attributes){
		
		if(result.hasErrors()) {
			return this.novo(entidade);
		}
		
		this.entidadeService.salvar(entidade);
		attributes.addFlashAttribute("mensagem", "Entidade <strong> " + entidade.getNome() + "</strong> salva com sucesso.");
		return "redirect:/entidades/novo";
	}
	
	@GetMapping
	//@RequestMapping
	/*public String pesquisar(Entidade entidade, Model model) {
		String nome = entidade.getNome() == null ? "%" : entidade.getNome();
		model.addAttribute("entidades", this.entidadeService.porNome(nome));
		return "entidade/PesquisarEntidade";
	}*/
	
	public ModelAndView pesquisar(Entidade entidade, @PageableDefault(size = 3) Pageable pageable,
			HttpServletRequest httpServletRequest) {
		
		
		this.mv = new ModelAndView("entidade/PesquisarEntidade");
		
		PageWrapper<Entidade> paginaWrapper = 
				new PageWrapper<>(this.entidadeService.porNome(entidade.getNome(), pageable), httpServletRequest);
		
		this.mv.addObject("pagina", paginaWrapper);
		return this.mv;
	}
	
	@GetMapping("/{codigo}")
	//@RequestMapping(value = "/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Entidade entidade) {
		this.mv = new ModelAndView(INDEX);
		this.mv.addObject(entidade);
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	//@RequestMapping(value = "/{codigo}", method = RequestMethod.DELETE)
	public String remover(@PathVariable Long codigo) {
		this.entidadeService.excluir(codigo);
		return "redirect:/entidades";
	}
}
