package model;


public class Busqueda {
	
	private int tipo;
	private String palabra;
	
	
	public Busqueda() {
		// TODO Auto-generated constructor stub
	}
	
	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	
	//GETS
	public String getPalabra() {
		return palabra;
	}
	public int getTipo() {
		return tipo;
	}
	

}
