	package entidades;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Calendario")
public class Calendario {
	
	@Id
	@GeneratedValue
	private int id;
	private String nombre;
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToMany(mappedBy="calendarios")
	private List<Reunion> reuniones;
	
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
