package entidades;

import java.util.List;

public class Calendario {
	
	private int id;
	private String nombre;
	
	List<Reunion> reuniones;
	
	public Calendario() {}
	
	public Calendario(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void addReunion () {
		
	}
	
}
