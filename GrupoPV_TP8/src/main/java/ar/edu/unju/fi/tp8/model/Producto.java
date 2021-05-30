package ar.edu.unju.fi.tp8.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Entity
@Table(name="PRODUCTOS")
@Component
public class Producto {
	@Id
	@Column(name = "PRODUCT_CODIGO")
	private int codigo;
	
	@Column(name = "NOMBRE" , length = 150, nullable = true)
	private String nombre;
	
	@Column(name = "PRECIO")
	private double precio;
	
	@Column(name = "MARCA")
	private String marca;
	
	@Column(name = "STOCK")
	private int stock;
	

	public Producto() {
	}

	public Producto(int codigo, String nombre, double precio, String marca, int stock) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.marca = marca;
		this.stock = stock;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Producto [codigo=" + codigo + ", nombre=" + nombre + ", precio=" + precio + ", marca=" + marca
				+ ", stock=" + stock + "]";
	}

}
