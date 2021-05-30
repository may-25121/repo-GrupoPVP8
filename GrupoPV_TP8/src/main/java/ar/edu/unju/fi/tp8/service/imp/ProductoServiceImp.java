package ar.edu.unju.fi.tp8.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tp8.model.Producto;
import ar.edu.unju.fi.tp8.service.IProductoService;



@Service("productoImp")
public class ProductoServiceImp implements IProductoService{
	
	private static final Log LOGGER = LogFactory.getLog(ProductoServiceImp.class);
	
	@Autowired
	private Producto producto;
	
	List<Producto> productos = new ArrayList<Producto>();
	
	@Override
	public Producto getProducto() {
		LOGGER.info("SERVICE: IProductoService -> ProductoServiceImp");
		LOGGER.info("METHOD: getProducto()");
		LOGGER.info("RESULT: devuelve un objeto producto");
		return this.producto;
	}
	
	@Override
	public void addProducto(Producto producto) {
		LOGGER.info("SERVICE: IProductoService -> ProductoServiceImp");
		LOGGER.info("METHOD: addProducto()");
		LOGGER.info("RESULT: agrega un objeto producto a la lista");
		this.productos.add(producto);
	}

	@Override
	public Producto getUltimoProducto() {
		int indiceUltimoProducto=productos.size() - 1;
		if(indiceUltimoProducto<0) {
			Producto producto = new Producto (796,"Cafe",229,"La Virginia",123);
			addProducto(producto);			
		}else {
			producto=productos.get(indiceUltimoProducto);	
		}
		LOGGER.info("SERVICE: IProductoService -> ProductoServiceImp");
		LOGGER.info("METHOD: getUltimoProducto()");
		LOGGER.info("RESULT: devuelve el ultimo producto agregado");
		return producto;
	}

	@Override
	public List<Producto> getAllProductos() {
		LOGGER.info("SERVICE: IProductoService -> ProductoServiceImp");
		LOGGER.info("METHOD: getAllProductos()");
		LOGGER.info("RESULT: devuelve una lista de los productos agregados");
		if(this.productos.size()==0) {
			this.addProducto(producto=new Producto(796,"Cafe",229,"La Virginia",123)); 
		}
		return this.productos;
	}

	@Override
	public Producto searchProducto(int codigo) {
		LOGGER.info("SERVICE: IProductoService -> ProductoServiceImp");
		LOGGER.info("METHOD: searchProducto(codigo)");
		LOGGER.info("RESULT: devuelve el producto buscado mediante su codigo enviado por parametro");
		Producto prod = new Producto();
		for(Producto producto : this.productos) {
			if(producto.getCodigo() == codigo) {
				prod = producto;
			}
		}
		return prod;
	}

}
