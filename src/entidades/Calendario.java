package entidades;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="Calendario")
public class Calendario {
	
	@Id
	@GeneratedValue
	private int id;
	//usuario creador preguntar
	private String nombre;
	
	@ManyToMany(mappedBy ="calendarios")
	private List<Usuario>usuarios;
	
	@OneToMany
	private List<Reunion> reuniones;
	
	public Calendario() {}
	
	public Calendario(String nombre) {
		this.nombre = nombre;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
