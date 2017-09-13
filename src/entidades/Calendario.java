package entidades;

import java.util.ArrayList;
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
		this.usuarios = new ArrayList<Usuario>();
		this.reuniones = new ArrayList<Reunion>();
	}
	
	public boolean addUsuario(Usuario usuario) {
		return this.usuarios.add(usuario);
	}

	public boolean removeUsuario(Usuario usuario) {
		return this.usuarios.remove(usuario);
	}
	
	public boolean addReunion(Reunion reunion) {
//		return this.reuniones.add(reunion);
		//chequear que no se superponga
		return false;
	}
	public boolean removeReunion(Reunion reunion) {
		return this.reuniones.remove(reunion);
	}
	
	public boolean equals(Calendario cal) {
		return this.id == cal.getId();
	}

	//	Getters and Setters
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
