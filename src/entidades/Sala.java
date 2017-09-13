package entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Sala")
public class Sala {

	@Id
	@GeneratedValue
	private int id;
	
	private String nombre;
	
	private String descripcion;
	
	@OneToMany
	private List<Reunion>reuniones;
	
	public Sala() {}
	
	public Sala(String nombre,String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.reuniones = new ArrayList<Reunion>();
	}
	
	public boolean addReunion() {
		//chequear que no se superpongan
		return false;
	}
	
	public boolean equals(Sala sala) {
		return this.id == sala.getId();
	}
//	Getters and Setters
	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
