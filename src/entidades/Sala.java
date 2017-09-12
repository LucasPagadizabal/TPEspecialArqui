package entidades;

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
	
	
}
