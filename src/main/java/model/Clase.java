package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class Clase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String tipo;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = ISO.DATE)
	private Date fecha;
	private int duracion;
	private String intensidad;
	private int participantes;
	
	@ManyToOne
	private Profesor profesor;
	@OneToMany
	private List<Socio> socios = new ArrayList<Socio>();
	
	public Clase() {
		// TODO Auto-generated constructor stub
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public void setTipo(String name) {
		this.tipo = name;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public void setIntensidad(String intensidad) {
		this.intensidad = intensidad;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	public void setSocios(List<Socio> socios) {
		this.socios = socios;
	}

	
	
	public long getId() {
		return id;
	}
	public String getTipo() {
		return tipo;
	}
	public int getDuracion() {
		return duracion;
	}
	public String getIntensidad() {
		return intensidad;
	}
	public Date getFecha() {
		return fecha;
	}
	public Profesor getProfesor() {
		return profesor;
	}
	public List<Socio> getSocios() {
		return socios;
	}

	
	@Override
	public String toString() {
		return "Clase: [id=" + id + ", tipo: " + tipo + ", Duraci'on: " + duracion + ", Intendidad: " + intensidad + "]";
	}
	
	 @Override
	 public boolean equals(Object o) {
		 Clase clase = (Clase) o;
		 return (this.id == clase.getId());
	 
	 }

}
