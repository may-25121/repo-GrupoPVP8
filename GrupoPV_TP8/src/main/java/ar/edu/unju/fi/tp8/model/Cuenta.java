package ar.edu.unju.fi.tp8.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "CUENTAS")
@Component
public class Cuenta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CUENTA_ID")
	private Long id;
	
	@Column(name = "SALDO")
	@DecimalMin(value = "0.01", message = "Debe ingresar una cantidad mayor a 0.01")
	@DecimalMax(value = "100000.00", message = "Debe ingresar una cantidad menor a 100000.00")
	private double saldo;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name= "FECHA_CREACION")
	@NotNull(message = "Debe ingresar una fecha")
	private LocalDate fechaCreacion;
	
	@Column(name = "ESTADO")
	@NotBlank(message = "Debe ingresar el estado de la cuenta.")
	private String estado;
	
	@OneToOne(mappedBy = "cuenta", fetch = FetchType.LAZY)
	private Cliente cliente;
	

	public Cuenta() {
	}

	public Cuenta(Long id, double saldo, LocalDate fechaCreacion, String estado) {
		this.id = id;
		this.saldo = saldo;
		this.fechaCreacion = fechaCreacion;
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public LocalDate getFechaCreacion() {
		LocalDate hoy = LocalDate.now();
		return hoy;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	 
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Cuenta [id=" + id + ", saldo=" + saldo + ", fechaCreacion=" + fechaCreacion + ", estado=" + estado
				+ ", cliente=" + cliente + "]";
	}

	
	
}
