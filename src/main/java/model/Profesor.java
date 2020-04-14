package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Profesor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String dni;
	private String correo;

	@OneToOne(cascade = CascadeType.ALL)
	private User usuario;
	
	@OneToMany
	private List<Socio> socios = new ArrayList<Socio>();
	
	@OneToMany 
	private List<Clase> clases = new ArrayList<Clase>();
	
	public Profesor() {
		// TODO Auto-generated constructor stub
	}
	public Profesor(String nombre, String apellido1, String apellido2, String dni, String correo) {
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.dni = dni;
		this.correo = correo;
	}
		
	//SETS
	public void setId(long id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public void setSocios(List<Socio> socios) {
		this.socios = socios;
	}
	public void setClases(List<Clase> clases) {
		this.clases = clases;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}
	
	
	//GETS
	public String getApellido1() {
		return apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public String getNombre() {
		return nombre;
	}
	public List<Socio> getSocios() {
		return socios;
	}
	public List<Clase> getClases() {
		return clases;
	}
	public String getDni() {
		return dni;
	}
	public String getCorreo() {
		return correo;
	}
	public User getUsuario() {
		return usuario;
	}
	public long getId() {
		return id;
	}
	
	
	//OPERACIONES
	public void deleteClase (Clase clase) {
		clases.remove(clase);
	}
	public void deleteSocio (Socio socio) {
		this.socios.remove(socio);
	}
	public void addSocio (Socio socio) {
		this.socios.add(socio);
	}
	
	
	@Override
	public String toString() {
		return nombre + " "+ apellido1 ;
	}
	
	 @Override
	 public boolean equals(Object o) {
		 Profesor profesor = (Profesor) o;
		 return (this.id == profesor.getId());
	 }

}
