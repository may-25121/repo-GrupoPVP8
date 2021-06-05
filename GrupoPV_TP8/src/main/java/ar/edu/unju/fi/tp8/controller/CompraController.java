package ar.edu.unju.fi.tp8.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import ar.edu.unju.fi.tp8.model.Compra;
import ar.edu.unju.fi.tp8.service.ICompraService;
import ar.edu.unju.fi.tp8.service.IProductoService;

@Controller
public class CompraController {
	private static final Log LOGGER = LogFactory.getLog(CompraController.class);
	
	
	@Autowired
	@Qualifier("compraServiceMysql")
	private ICompraService compraService;
	
	@Autowired
	@Qualifier("productoServiceMysql")
	private IProductoService productoService;

	@GetMapping("/compra")
	public String getCompraPage(Model model) {
		LOGGER.info("CONTROLLER: CompraController con /compra invoca al metodo get");
		LOGGER.info("METHOD: getCompraPage()");
		LOGGER.info("RESULT: Se visualiza la página nuevacompra.html");
		model.addAttribute("compra", compraService.getCompra());
		model.addAttribute("productos", productoService.getAllProductos());
		return "nuevacompra";
	}
	
	@GetMapping("/compra/guardar")
	public String getCompraResultadoPage(@Valid @ModelAttribute("compra") Compra compra,BindingResult result, Model model, @RequestParam(name="codigo") String codigo, @RequestParam(name="cantidad") String cantidad) {
		compra.setCantidad(Integer.valueOf(cantidad));
		compra.setProducto(this.productoService.searchProducto(Integer.valueOf(codigo)));
		compra.setTotal(compra.getTotal());
		System.out.println(compra);
		if(result.hasErrors()) {
			model.addAttribute("compra", compra);
			model.addAttribute("productos", productoService.getAllProductos());
			return "nuevacompra";
		}else {
			compraService.agregarCompra(compra);
			System.out.println(compra);
			return "resultado02";
		}
	}
	
	@GetMapping("/compra/listar")
	public String getListarCompraPage(Model model) {
		LOGGER.info("CONTROLLER: CompraController con /compra/listar invoca al metodo get");
		LOGGER.info("METHOD: getListarComprasPage()");
		LOGGER.info("RESULT: Se visualiza la página listarcompras.html, mostrando las compras que se encuentran en la lista");
		model.addAttribute("compras", compraService.obtenerCompras());
		model.addAttribute("compra", compraService.getCompra());
	return "listarcompras";
	}
	
	/*
	
	@GetMapping("/compra/busqueda")
	public String buscarComprasPorFiltro(@RequestParam(name="nombre") String nombre, @RequestParam(name="total") double total, Model model, @ModelAttribute(name="compra") Compra compra) {
		model.addAttribute("compra",compraService.getCompra());
		System.out.println(compra);
		model.addAttribute("compras", compraService.buscarCompras(nombre, total));
		return "listarcompras";
	}*/
	
	@GetMapping("/compra/busqueda")
	public String buscarComprasPorFiltro(@RequestParam(name="nombre") String nombre, @RequestParam(name="total") double total, Model model) {
	/*	model.addAttribute("compra",compraService.getCompra());
		System.out.println(compra);*/
		model.addAttribute("nombre",nombre);
		model.addAttribute("total",total);
		System.out.println("Filtro Producto=> "+nombre);
		System.out.println("Filtro Total=> "+total);
		model.addAttribute("compras", compraService.buscarCompras(nombre, total));
		
		
		return "listarcompras";
	}

	
}
