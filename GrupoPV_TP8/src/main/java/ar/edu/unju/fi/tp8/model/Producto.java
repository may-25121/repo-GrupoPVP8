package ar.edu.unju.fi.tp8.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;


@Entity
@Table(name="PRODUCTOS")
@Component
public class Producto {
	@Id
	@Column(name = "PRODUCT_CODIGO")
	private int codigo;
	
	@Column(name = "NOMBRE" , length = 150, nullable = true)
	@NotBlank(message = "Ingrese un nombre")
	@Size(min = 3,message = "Ingrese un nombre mayor a 3 caracteres")
	private String nombre;
	
	@Column(name = "PRECIO")
	@NotNull(message = "Ingrese un precio")
	@DecimalMin(value = "0.10" , message = "Ingrese un valor mayor a 0.10")
	@DecimalMax(value = "10000.00", message = "Ingrese un valor menor a 10000.00")
	private double precio;
	
	@Column(name = "MARCA")
	@NotBlank(message = "Ingrese una marca")
	@Size(min = 3, message = "Ingrese un nombre mayor a 3 caracteres")
	private String marca;
	
	@Column(name = "STOCK")
	@NotNull(message = "Ingrese un stock")
	@Min(value = 1, message = "Ingrese un valor mayor a 1")
	@Max(value = 10000 , message = "Ingrese un valor menor a 10000")
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
