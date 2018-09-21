package br.com.lojapet.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.lojapet.model.Grupo;
import br.com.lojapet.model.User;
import br.com.lojapet.persistence.service.GrupoService;

@Controller
@RequestMapping(value = "/grupo")
public class GrupoController {

	@Autowired
	protected GrupoService grupoService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView lista(ModelAndView modelAndView) {
		modelAndView = new ModelAndView("/grupo/lista_grupo");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView listaComSearch(@RequestParam(value = "search", required = false) String q,
			RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("/grupo/lista_grupo");

		List<Grupo> grupos;
		String error = null;

		if (q == null) {
			grupos = grupoService.getAllGrupos();
		} else {
			grupos = grupoService.search(q);

		}

		if (grupos.isEmpty() && q != null) {
			error = "error.empty";
		}
		modelAndView.addObject("error", error);
		modelAndView.addObject("grupos", grupos);
		return modelAndView;

	}

	@RequestMapping(value = "cadastro")
	public ModelAndView form(Grupo grupo) {
		ModelAndView modelAndView = new ModelAndView("/grupo/cadastro_grupo");
		modelAndView.addObject("grupo", grupo);
		return modelAndView;
	}

	@RequestMapping(value = "gravar", method = RequestMethod.POST)
	public ModelAndView gravar(@Valid Grupo grupo, BindingResult result) {
		if (grupoService.existeComNome(grupo.getNome())) {
			result.rejectValue("nome", "field.mustBe.Unique");
		}
		if (result.hasErrors()) {
			return form(grupo);
		}

		grupoService.saveGrupo(grupo);

		return new ModelAndView("redirect:/grupo/cadastro");

	}
	
	
	@RequestMapping(value = "/editarGrupo/{uuid}")
	public ModelAndView editar(@PathVariable(value = "uuid") UUID uuid) {
		Grupo grupo= grupoService.getGrupoById(uuid);

		return form(grupo);

	}

	@RequestMapping(value = "/excluirGrupo/{uuid}")
	public ModelAndView excluirCliente(@PathVariable(value = "uuid") UUID uuid) {
		grupoService.deleteGrupo(uuid);

		return new ModelAndView("redirect:/grupo");

	}

}
