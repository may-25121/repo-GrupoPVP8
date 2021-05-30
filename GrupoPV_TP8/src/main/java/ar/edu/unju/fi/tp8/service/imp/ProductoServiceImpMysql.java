package ar.edu.unju.fi.tp8.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tp8.model.Producto;
import ar.edu.unju.fi.tp8.repository.IProductoDAO;
import ar.edu.unju.fi.tp8.service.IProductoService;

@Service("productoServiceMysql")
public class ProductoServiceImpMysql implements IProductoService{
	
	@Autowired
	IProductoDAO productoDAO;
	
	@Autowired
	private Producto producto;

	@Override
	public Producto getProducto() {
		return this.producto;
	}

	@Override
	public void addProducto(Producto producto) {
		productoDAO.save(producto);
	}

	@Override
	public Producto getUltimoProducto() {
		Producto producto;
		List<Producto> productos = (List<Producto>) productoDAO.findAll();
		int indice=productos.size()-1;
		if (indice>-1) {
			producto = productos.get(indice);
		}else {
			producto=this.producto;
		}
		return producto;
	}

	@Override
	public List<Producto> getAllProductos() {
		List<Producto> productos= (List<Producto>) productoDAO.findAll();
		return productos;
	}

	@Override
	public Producto searchProducto(int codigo) {
		Producto producto = productoDAO.findByCodigo(codigo);
		return producto;
	}

}
