package ar.edu.unju.fi.tp8.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.fi.tp8.model.Compra;

public interface ICompraDAO extends CrudRepository<Compra, Long>{
	
//	public List<Compra> findBynombreProductoAndMontoSuperior(String nombreProducto, double montoSuperior);
	
//	public List<Compra> findByMontoSuperior(double montoSupeprior);
	
}
