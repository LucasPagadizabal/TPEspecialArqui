package entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Reunion")

public class Reunion {
	
	@Id
	@GeneratedValue
	private int id;
	
	private Date fecha;
	
	@ManyToOne
	private Sala lugar;
	
	@ManyToOne
	private Calendario calendario;
	
	@ManyToMany(mappedBy="reuniones")
	private List<Usuario>invitados;
	
//	@ManyToMany(mappedBy="invitacionesPendientes")
//	private List<Usuario>invitadosPendientes;
	
	public Reunion() {}
}
