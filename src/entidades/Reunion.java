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
	private Sala sala;
	
	@OneToOne
	private Calendario calendario;
	
	private List<Usuario> invitados;
	
	public Reunion() {}
	
	public Reunion(Date fecha,Sala lugar,Calendario c) {
		this.fecha = fecha;
		this.sala = lugar;
		this.calendario = c;
	}
	
	
	public void addInvitados() {
		
	}
}
