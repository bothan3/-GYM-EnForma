package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
	private boolean profeosorAsignado = false;

	@ManyToOne
	private Profesor profesor;
	
	@ManyToMany
	private List<Socio> socios = new ArrayList<Socio>();

	public Clase() {
		// TODO Auto-generated constructor stub
	}

	public Clase(String tipo, int duracion, String intensidad) {
		this.tipo = tipo;
		this.duracion = duracion;
		this.intensidad = intensidad;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2020);
		calendar.set(Calendar.MONTH, 3);
		calendar.set(Calendar.DATE, 14);
		this.fecha = calendar.getTime();
		this.profeosorAsignado = false;
	}	
	public Clase(String tipo, int duracion, String intensidad, int dia) {
		this.tipo = tipo;
		this.duracion = duracion;
		this.intensidad = intensidad;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2020);
		calendar.set(Calendar.MONTH, 3);
		calendar.set(Calendar.DATE, dia);
		this.fecha = calendar.getTime();
		this.profeosorAsignado = false;
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
		this.profeosorAsignado = true;
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

	// OPERACIONES
	public void deleteProfesor() {
		this.profesor = null;
		this.profeosorAsignado = false;

	}
	public boolean isEmptyProfesor() {
		return this.profeosorAsignado;
	}
	
	public void addSocio(Socio socio) {
		this.socios.add(socio);
	}
	
	public void deleteSocio(Socio socio) {
		this.socios.remove(socio);
	}
	
	

	@Override
	public String toString() {
		return tipo + " " +fecha;
	}

	@Override
	public boolean equals(Object o) {
		Clase clase = (Clase) o;
		return (this.id == clase.getId());

	}

}
