package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comentario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String autor;
	private String mensaje;
	
	@ManyToOne
	private Noticia articulo;
	
	protected Comentario() {
		// TODO Auto-generated constructor stub
	}
	
	public Comentario(String autor, String mensaje) {
		this.autor = autor;
		this.mensaje = mensaje;
	}
	
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public void setArticulo(Noticia articulo) {
		this.articulo = articulo;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	public Noticia getArticulo() {
		return articulo;
	}
	public String getAutor() {
		return autor;
	}
	public long getId() {
		return id;
	}
	public String getMensaje() {
		return mensaje;
	}
	
	@Override
	public String toString() {
		return "Comentario [id=" + id + ", autor=" + autor + ", message=" + mensaje + "]";
	}

	
}
