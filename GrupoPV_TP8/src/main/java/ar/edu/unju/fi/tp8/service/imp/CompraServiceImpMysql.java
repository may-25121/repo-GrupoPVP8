package ar.edu.unju.fi.tp8.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tp8.model.Compra;
import ar.edu.unju.fi.tp8.repository.ICompraDAO;
import ar.edu.unju.fi.tp8.service.ICompraService;

@Service("compraServiceMysql")
public class CompraServiceImpMysql implements ICompraService{
	
	@Autowired
	ICompraDAO compraDAO;
	
	@Autowired
	private Compra compra;

	@Override
	public Compra getCompra() {
		return this.compra;
	}

	@Override
	public void agregarCompra(Compra compra) {
		compraDAO.save(compra);
	}

	@Override
	public List<Compra> obtenerCompras() {
		List<Compra> compras = (List<Compra>) compraDAO.findAll();
		return compras;
	}

	@Override
	public List<Compra> buscarCompras(String nombre, double total) {
		List<Compra> compras = new ArrayList<>();
		if(!nombre.isEmpty()&& total>=0) {
			compras = compraDAO.findByProductoNombreAndTotalGreaterThanEqual(nombre, total);
		}else if (nombre.isEmpty()&& total>=0) {
			compras = compraDAO.findByTotalGreaterThanEqual(total);
		}
		return compras;
	}

}
