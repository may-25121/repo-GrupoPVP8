package ar.edu.unju.fi.tp8.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.tp8.model.Cliente;
import ar.edu.unju.fi.tp8.service.IClienteService;

@Controller
public class ClienteController {
	
	@Autowired
	private Cliente cliente;
	
	@Autowired
	@Qualifier("clienteServiceMysql")
	private IClienteService clienteService;
	
	
	@GetMapping("/menu")
	public String getPrincipalPage() {
		return "index";
	}
	
	@GetMapping("/cliente/nuevo")
	public String getNuevoClientePage(Model model) {
		model.addAttribute("cliente",cliente);
		return "form-cliente";
	}
	
	@GetMapping("/cliente/editar/{id}")
	public String getEditarPage(@PathVariable Long id, Model model) {
		Optional<Cliente> cliente = clienteService.getClientePorId(id);
		model.addAttribute("cliente", cliente);
		return "form-cliente";
	}
	
	@GetMapping("/cliente/borrar/{id}")
	public String getBorrarPage(@PathVariable Long id, Model model) {
		clienteService.borrarCliente(id);
		return "redirect:/cliente/listado";
	}

	@PostMapping("/cliente/guardar")
	public ModelAndView guardarCliente(@Valid @ModelAttribute("cliente") Cliente cliente,BindingResult result) {
		ModelAndView modelAndView;
		if(result.hasErrors()) {
			modelAndView= new ModelAndView("form-cliente");
			List<Cliente> clientes= clienteService.getClientes();
			modelAndView.addObject("clientes", clientes);
			System.out.println(clientes.toString());
			return modelAndView;
		}else {
			modelAndView= new ModelAndView("clientes");
			cliente.setEdad(cliente.getEdad());
			cliente.getCuenta().setFechaCreacion(cliente.getCuenta().getFechaCreacion());
			clienteService.guardarCliente(cliente);
			modelAndView.addObject("clientes", clienteService.getClientes());
			System.out.println(cliente);
			return modelAndView;
		}
	}

	@GetMapping("/cliente/listado")
	public ModelAndView getClienteListadoPage() {
		ModelAndView model = new ModelAndView("clientes");
		model.addObject("clientes", clienteService.getClientes());
		return model;
	}
}
