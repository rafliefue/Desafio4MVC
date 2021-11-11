package com.spring.nttdata.desafio3.web;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.nttdata.desafio3.entities.Cliente;
import com.spring.nttdata.desafio3.services.ClienteService;

@Controller
public class ClienteController {
	
	private ClienteService clienteService;
	
	private static final String		VISTA_CLIENTE	= "clientes/clienteView";
	private static final String		VISTA_LIST_CLIENTES	= "clientes/clientesList";
	private static final String		VISTA_ADD_CLIENTES	= "clientes/clientesAdd";
	private static final String		VISTA_FINDBYNAME_CLIENTES	= "clientes/clientesFindByName";
	
	@Autowired
	public ClienteController(final ClienteService clienteService) throws ClassNotFoundException {
		this.clienteService = clienteService;

	}

	//Index
	
	@GetMapping({"/"})
	public String welcome(final Map<String, Object> model) {

		return "index";
	}
	
	//Show Clientes
	
	@GetMapping(value = "/clientes")
	public String showClientes(final ModelMap model) {

		List<Cliente> lista = this.clienteService.findAll();
		
		model.addAttribute("lista", lista);
	
		return ClienteController.VISTA_LIST_CLIENTES	;
	}
	
	//Añadir Clientes
	
	@GetMapping(value = "/new")
	public String createClientes(final ModelMap model) {

		
			final Cliente cliente = new Cliente();
			model.addAttribute("cliente", cliente);
			return VISTA_ADD_CLIENTES;
		
	}
	
	//Borrar Clientes
	
	@RequestMapping(value = "/delete/{name}", method = RequestMethod.POST)
	public String deleteCliente(@PathVariable("name") final String name, final ModelMap model) {
		
		
		this.clienteService.delete(this.clienteService.findByName(name));

		List<Cliente> lista = this.clienteService.findAll();
		
		model.addAttribute("lista", lista);
		
		return VISTA_LIST_CLIENTES;	
	}
	
	@PostMapping(value = "/new")
	public String createClientes(@Valid final Cliente cliente, final BindingResult result, final ModelMap model) {

		cliente.setId(5);

		
		if (result.hasErrors()) {
			model.addAttribute("cliente", cliente);
			return VISTA_ADD_CLIENTES;

		} else {
			
			
			this.clienteService.save(cliente);
			
			
			//Voy a devolver la vista de lista de clientes para que se vea que se ha creado un nuevo cliente
			List<Cliente> lista = this.clienteService.findAll();		
			model.addAttribute("lista", lista);

			return VISTA_LIST_CLIENTES;
		}

	}
	
	//Buscar Clientes por Nombre
	

		@GetMapping(value = "/find")
		public String findClientes(final ModelMap model) {
			
			Cliente a = new Cliente();
			model.addAttribute(a);

				return VISTA_FINDBYNAME_CLIENTES;
			
		}
		
		@RequestMapping(value = "/find/cliente", method = RequestMethod.GET)
		public String findClientes(@RequestParam("name") String nombre, final ModelMap model) {

		
			if(this.clienteService.findByName(nombre) != null) {
				model.addAttribute("cliente", this.clienteService.findByName(nombre));
	
			} else {
				Cliente a = new Cliente();
				model.addAttribute(a);
				model.addAttribute("errorNoEncontrado", "No se ha encontrado el usuario.");
				return VISTA_FINDBYNAME_CLIENTES;
			}
		
				return VISTA_CLIENTE;
		}

}
