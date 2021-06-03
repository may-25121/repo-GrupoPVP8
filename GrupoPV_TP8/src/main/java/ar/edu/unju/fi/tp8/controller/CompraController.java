package ar.edu.unju.fi.tp8.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	private Compra compra;
	
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
	public String getCompraResultadoPage(Model model, @RequestParam(name="codigo") String codigo, @RequestParam(name="cantidad") String cantidad) {
		LOGGER.info("CONTROLLER: CompraController con /compra/guardar invoca al metodo Get");
		LOGGER.info("METHOD: getCompraResultadoPage() -- PARAMS: compra '"+codigo+"' codigo '"+cantidad);
		LOGGER.info("RESULT: Se visualiza la página resultado02.html mostrando un mensaje que certifica que los datos de la compra se guado correctamente");
		this.compra.setCantidad(Integer.valueOf(cantidad));
		this.compra.setProducto(this.productoService.searchProducto(Integer.valueOf(codigo)));
		this.compra.setTotal(this.compra.getTotal());
		compraService.agregarCompra(compra);
		return "resultado02";
	}
	
	@GetMapping("/compra/listar")
	public String getListarCompraPage(Model model) {
		LOGGER.info("CONTROLLER: CompraController con /compra/listar invoca al metodo get");
		LOGGER.info("METHOD: getListarComprasPage()");
		LOGGER.info("RESULT: Se visualiza la página listarcompras.html, mostrando las compras que se encuentran en la lista");
		model.addAttribute("compras", compraService.obtenerCompras());
	return "listarcompras";
	}
	/*
	@GetMapping("/compra/busqueda")
	public String buscarComprasPorFiltro(@RequestParam(name="nombreProducto") String nombreProducto, @RequestParam(name="montoSuperior") double montoSuperior, Model model, @ModelAttribute(name="compra") Compra compra) {
		model.addAttribute("compra",compraService.getCompra());
		model.addAttribute("compras", compraService.buscarCompras(nombreProducto, montoSuperior));
		return "listarcompras";
	}*/
	
	
}
