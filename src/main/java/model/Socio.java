package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.web.bind.annotation.GetMapping;

@Entity
public class Socio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String dni;
	private String correo;
	private boolean profeosrAsignado = false;
	
	@ManyToOne
	private Profesor profesor;
	
	
		
	public Socio() {
	}
	
	public Socio(String nombre, String apellido1, String apellido2, String sexo) {
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		dni = "12345678T";
		correo = "test@test.com";
	}
	
	//SET
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setProfesor(Profesor profesor) {
		this.profeosrAsignado = true;
		this.profesor = profesor;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	
	//GET
	public String getApellido1() {
		return apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}

	public long getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public Profesor getProfesor() {
		return profesor;
	}
	
	public void deleteProfesor () {
		this.profeosrAsignado = false;
		this.profesor=null;
	}
	public String getCorreo() {
		return correo;
	}
	public String getDni() {
		return dni;
	}
	
	
	
	@Override
	public String toString() {
		return nombre + " "+ apellido1;
	}
	
	 @Override
	 public boolean equals(Object o) {
		 Socio socio = (Socio) o;
		 return (this.id == socio.getId());
	 }
	
	
	
}
