package entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Reunion")
//@NamedQuery(name="Reunion.BUSCAR_REUNIONES_BY_DATE",query="SELECT r FROM Reunion r WHERE ")

public class Reunion {

	@Id
	@GeneratedValue
	private int id;
	
	private Date fechaInicio;

	private Date fechaFin;

	@ManyToOne(cascade=CascadeType.PERSIST)
	private Sala sala;

	@ManyToOne
	private Calendario calendario;

	@ManyToMany
	private List<Usuario>invitados;

	public Reunion() {}

	public Reunion(Date fechaInicio,Date fechaFin,Sala lugar,Calendario calendario) {
//		if(!calendario.superposicionReunion(fechaInicio, fechaFin)) {
			this.fechaInicio = fechaInicio;
			this.fechaFin = fechaFin;
			this.sala = lugar;
			this.calendario = calendario;
			this.invitados = new ArrayList<Usuario>();
			calendario.addReunion(this);
//		}
		
	}
	
	public boolean mismoDia(Date day) {
		return (this.fechaInicio.getYear() == day.getYear()) && (this.fechaInicio.getMonth() == day.getMonth()) && (this.fechaInicio.getDay() == day.getDay());
	}
	
	public boolean superposicionHorarios(Date fechaI,Date fechaF) {
		return ((fechaI.after(this.fechaInicio) && this.fechaFin.after(fechaI)) || (fechaI.after(this.fechaFin) && fechaF.after(this.fechaFin)));
	}
	
	public boolean addInvitado(Usuario usuario) {
		return this.invitados.add(usuario);
	}

	public boolean removeInvitado(Usuario usuario) {
		return this.invitados.remove(usuario);
	}

	public boolean equals(Reunion reunion) {
		return this.id == reunion.getId();
	}

	//	Getters and Setter
	public int getId() {
		return id;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Sala getLugar() {
		return sala;
	}

	public void setLugar(Sala lugar) {
		this.sala = lugar;
	}

	public Calendario getCalendario() {
		return calendario;
	}

	public void setCalendario(Calendario calendario) {
		this.calendario = calendario;
	}

	public List<Usuario> getInvitados() {
		return invitados;
	}
}
