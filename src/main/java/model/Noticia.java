package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class Noticia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;

	private String titulo;
	private String texto;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = ISO.DATE)
	private Date fecha;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="articulo")
	private List<Comentario> comentarios = new ArrayList<>();

	protected Noticia() {}

	public Noticia(String titulo, String texto) {
		this.titulo = titulo;
		this.texto = texto;
		Calendar calendar = Calendar.getInstance();
		this.fecha = calendar.getTime();
	}

	public void setId(long id) {
		this.id = id;
	}
	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	
	public long getId() {
		return id;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getTexto() {
		return texto;
	}
	public List<Comentario> getComentarios() {
		return comentarios;
	}
	
	
	public void addComentario (Comentario comentario) {
		comentario.setArticulo(this);
		comentarios.add(comentario);
	}
	
	@Override
	public String toString() {
		return titulo + " " + fecha;
	}
}
