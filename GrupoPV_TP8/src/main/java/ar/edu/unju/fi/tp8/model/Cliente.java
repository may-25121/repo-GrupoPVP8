package ar.edu.unju.fi.tp8.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "CLIENTES")
@Component
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CLIENTE_ID")
	private long id;
	
	@Column(name = "TIPO_DOCUMENTO")
	@NotBlank(message = "Ingrese un tipo de documento")
	private String tipoDocumento;
	
	@Column(name = "DNI")
	@Min(value = 1, message = "La cantidad minima de digitos debe ser de 1")
	@Max(value = 99999999, message = "La cantidad maxima de digitos debe ser de 8")
	private int nroDocumento;
	
	@Column(name = "NOMBRE_APELLIDO")
	@Size(min = 3, max =100, message = "Ingrese al menos 3 caracteres para el campo de nombre y apellido")
	private String nombreApellido;
	
	@Column(name = "EMAIL")
	@NotBlank(message = "Debe ingresar un correo.")
	@Email(message = "Ingrese un correo electronico valido")
	private String email;
	
	@Column(name = "PASS")
	@NotBlank(message = "Debe ingresar una contraseña.")
	private String password;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name = "FECHA_NAC")
	@NotNull(message = "Debe ingresar la fecha de nacimiento.")
	private LocalDate fechaNacimiento;
	
	@Column(name = "EDAD")
	private int edad;
	
	@Column(name = "COD_AREA")
	@Min(value = 100, message = "La cantidad minima de digitos debe ser de 3")
	@Max(value = 9999, message = "La cantidad maxima de digitos debe ser de 4")
	private int codigoAreaTelefono;
	
	@Column(name = "TELEFONO")
	@Min(value = 100,message = "La cantidad minima de digitos debe ser de 3")
	@Max(value= 9999999, message = "La cantidad maxima de digitos debe ser de 7")
	private int nroTelefono;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name = "FECHA_ULTIMA_COMPRA")
	@NotNull(message = "Debe ingresar la fecha de la ultima compra realizada.")
	private LocalDate fechaUltimaCompra;
	
	 @Valid
	 @Autowired
	 @OneToOne(cascade = CascadeType.ALL)
	 @JoinColumn(name = "CUENTA_ID")
	private Cuenta cuenta;

	public Cliente() {
	}

	public Cliente(String tipoDocumento, int nroDocumento, String nombreApellido, String email, String password,
			LocalDate fechaNacimiento, int edad, int codigoAreaTelefono, int nroTelefono, LocalDate fechaUltimaCompra) {
		this.tipoDocumento = tipoDocumento;
		this.nroDocumento = nroDocumento;
		this.nombreApellido = nombreApellido;
		this.email = email;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		this.edad = getEdad();
		this.codigoAreaTelefono = codigoAreaTelefono;
		this.nroTelefono = nroTelefono;
		this.fechaUltimaCompra = fechaUltimaCompra;
	}
	

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public int getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(int nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getNombreApellido() {
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getEdad() {
		int edad= 0;
		LocalDate hoy= LocalDate.now();
		Period periodo=Period.between(this.fechaNacimiento, hoy);
		edad=periodo.getYears();
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getCodigoAreaTelefono() {
		return codigoAreaTelefono;
	}

	public void setCodigoAreaTelefono(int codigoAreaTelefono) {
		this.codigoAreaTelefono = codigoAreaTelefono;
	}

	public int getNroTelefono() {
		return nroTelefono;
	}

	public void setNroTelefono(int nroTelefono) {
		this.nroTelefono = nroTelefono;
	}

	public LocalDate getFechaUltimaCompra() {
		return fechaUltimaCompra;
	}

	public void setFechaUltimaCompra(LocalDate fechaUltimaCompra) {
		this.fechaUltimaCompra = fechaUltimaCompra;
	}
	
	public String getTiempoUltimaCompra() {
		String texto="";
		LocalDate hoy = LocalDate.now();
		Period periodo = Period.between(fechaUltimaCompra,hoy);
		texto=periodo.getYears()+" años - "+periodo.getMonths()+" meses - "+periodo.getDays()+" dias";
		return texto;
	}
	
	public long getTiempoTranscurridoFechaNacimiento() {
		LocalDate hoy = LocalDate.now();
		long dias=0;
		if(fechaNacimiento!=null) {
			dias =  ChronoUnit.DAYS.between(fechaNacimiento, hoy);
		}
		return dias;
	}
	
	public String getTiempoHastaCumple() {
		String texto="";
		LocalDate hoy = LocalDate.now();
		int varanio;
		if (hoy.getMonthValue() < this.fechaNacimiento.getMonthValue()) {
			varanio=hoy.getYear();
		} else if(hoy.getMonthValue() == this.fechaNacimiento.getMonthValue()){
			if(hoy.getDayOfMonth()<this.fechaNacimiento.getDayOfMonth()) {
				varanio=hoy.getYear();
			}else {
				varanio=hoy.getYear() +1;
			}
		}else{
			varanio=hoy.getYear() +1;
		}
		LocalDate fechaProxCuumple= LocalDate.of(varanio, this.fechaNacimiento.getMonth(), this.fechaNacimiento.getDayOfMonth());
		Period periodo = Period.between(hoy, fechaProxCuumple);
		texto=periodo.getYears()+" años - "+periodo.getMonths()+" meses - "+periodo.getDays()+" dias - ";
		
		LocalDateTime ahora=LocalDateTime.now();
		LocalDateTime fechaHoraProxCumple= LocalDateTime.of(varanio, this.fechaNacimiento.getMonth(), this.fechaNacimiento.getDayOfMonth(),0,0,0);
		Duration duracion=Duration.between(ahora, fechaHoraProxCumple);
		
		texto=texto+" "+duracion.toHoursPart()+" hs - "+duracion.toMinutesPart()+" min - "+duracion.toSecondsPart()+" seg";
		return texto;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", tipoDocumento=" + tipoDocumento + ", nroDocumento=" + nroDocumento
				+ ", nombreApellido=" + nombreApellido + ", email=" + email + ", password=" + password
				+ ", fechaNacimiento=" + fechaNacimiento + ", edad=" + edad + ", codigoAreaTelefono="
				+ codigoAreaTelefono + ", nroTelefono=" + nroTelefono + ", fechaUltimaCompra=" + fechaUltimaCompra
				+ ", cuenta=" + cuenta + "]";
	}

	

}
