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

import br.com.lojapet.model.Cliente;
import br.com.lojapet.persistence.service.ClienteService;

@Controller
@RequestMapping(value = "/cliente")
public class ClienteController {

	@Autowired
	protected ClienteService clienteService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView lista(ModelAndView modelAndView) {
		modelAndView = new ModelAndView("/cliente/lista_cliente");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView listaComSearch(@RequestParam(value = "search", required = false) String q,
			RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("/cliente/lista_cliente");
		List<Cliente> clientes;
		String error = null;

		if (q == null) {
			clientes = clienteService.getAllClientes();
		} else {
			clientes = clienteService.search(q);

		}

		if (clientes.isEmpty() && q != null) {
			error = "error.empty";
		}
		modelAndView.addObject("error", error);
		modelAndView.addObject("clientes", clientes);
		return modelAndView;

	}

	@RequestMapping(value = "/cadastro")
	public ModelAndView form(Cliente cliente) {
		ModelAndView modelAndView = new ModelAndView("/cliente/cadastro_cliente");
		modelAndView.addObject("cliente", cliente);

		return modelAndView;
	}

	@RequestMapping(value = "gravarCliente", method = RequestMethod.POST)
	public ModelAndView gravarCliente(@Valid Cliente cliente, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return form(cliente);
		}

		clienteService.saveCliente(cliente);

		return new ModelAndView("redirect:/cliente/cadastro");

	}

	@RequestMapping(value = "/editarCliente/{uuid}")
	public ModelAndView editar(@PathVariable(value = "uuid") UUID uuid) {
		Cliente cliente = clienteService.getClienteById(uuid);

		return form(cliente);

	}

	@RequestMapping(value = "/excluirCliente/{uuid}")
	public ModelAndView excluirCliente(@PathVariable(value = "uuid") UUID uuid) {
		clienteService.deleteCliente(uuid);

		return new ModelAndView("redirect:/cliente");

	}

}
