package ar.edu.unju.fi.tp8.service;



import java.util.List;

import ar.edu.unju.fi.tp8.model.Compra;

public interface ICompraService {
	
	public Compra getCompra();
	
	public void  agregarCompra(Compra compra);
  	
  	public List<Compra> obtenerCompras();
  	
	public List<Compra> buscarCompras(String nombre, double total);



}
