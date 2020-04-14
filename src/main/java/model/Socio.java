package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
	
	@OneToOne(cascade = CascadeType.ALL)
	private User usuario;
	
	@ManyToOne
	private Profesor profesor;
	
	@ManyToMany
	private List<Clase> clases = new ArrayList<Clase>();

	
	
		
	public Socio() {
	}
	
	public Socio(String nombre, String apellido1, String apellido2, String dni, String correo) {
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.dni = dni;
		this.correo = correo;
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
	public void setUsuario(User usuario) {
		this.usuario = usuario;
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
	public String getCorreo() {
		return correo;
	}
	public String getDni() {
		return dni;
	}
	public User getUsuario() {
		return usuario;
	}
	public List<Clase> getClases() {
		return clases;
	}

	// OPERACIONES
	public void addClase(Clase clase) {
		this.clases.add(clase);
	}
	public void deleteClase(Clase clase) {
		this.clases.remove(clase);
	}
	public void deleteProfesor () {
		this.profeosrAsignado = false;
		this.profesor=null;
	}
	public boolean isEmpyProfesor () {
		return this.profeosrAsignado;
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
