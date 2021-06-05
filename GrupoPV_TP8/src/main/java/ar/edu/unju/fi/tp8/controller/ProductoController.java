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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.tp8.model.Producto;
import ar.edu.unju.fi.tp8.service.IProductoService;



@Controller
public class ProductoController {
	private static final Log LOGGER = LogFactory.getLog(ProductoController.class);
	
	@Autowired
	private Producto producto;
	
	@Autowired
	@Qualifier("productoServiceMysql")
	private IProductoService productoService;
	
	@GetMapping("/producto")
	public String getProductoPage(Model model) {
		LOGGER.info("CONTROLLER: ProductoController con /producto invoca al metodo get");
		LOGGER.info("METHOD: getProductoPage()");
		LOGGER.info("RESULT: Se visualiza la página nuevo.html");
		model.addAttribute("producto", producto);
		return ("nuevo");
	}
	
	@PostMapping("/producto/guardar")
	public String getProductoResultadoPage(@Valid @ModelAttribute("producto") Producto unProducto, BindingResult result, Model model) {
		LOGGER.info("CONTROLLER: ProductoController con /producto/guardar invoca al metodo post");
		LOGGER.info("METHOD: getProductoResultadoPage() -- PARAMS: producto'"+unProducto+"'");
		LOGGER.info("RESULT: Se visualiza la página resultado.html mostrando el formulario de productos");
		if(result.hasErrors()) {
			model.addAttribute("producto", unProducto);
			return "nuevo";
		}else {
			productoService.addProducto(unProducto);
			return "resultado";	
		}

	}
	
	@GetMapping("/producto/ultimo")
	public ModelAndView getUltimoProductoPage() {
		LOGGER.info("CONTROLLER: ProductoController con /producto/ultimo invoca al metodo get");
		LOGGER.info("METHOD: getUltimoProductoPage()");
		LOGGER.info("RESULT: Se visualiza la página ultimoproducto.html mostrando los detalles del ultimo producto");
		ModelAndView model = new ModelAndView("ultimoproducto");
		model.addObject("producto", productoService.getUltimoProducto());
		return model;
	}
	
	@GetMapping("/producto/listar")
	public String getProductosPage(Model model) {
		LOGGER.info("CONTROLLER: ProductoController con /producto/listar invoca al metodo get");
		LOGGER.info("METHOD: getProductosPage()");
		LOGGER.info("RESULT: Se visualiza la página listarproductos.html, mostrando los productos que se encuentran en la lista");
		model.addAttribute("productos",productoService.getAllProductos());
		return "listarproductos";
	}

}
