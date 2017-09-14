package entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Reunion")

public class Reunion {
	
	@Id
	@GeneratedValue
	private int id;
	
	private Date fechaInicio;
	
	private Date fechaFin;
	
	@ManyToOne
	private Sala lugar;
	
	@ManyToOne
	private Calendario calendario;
	
	@ManyToMany(mappedBy="reuniones")
	private List<Usuario>invitados;
	
	@OneToMany(mappedBy="reunion")
	private List<Notificacion>invitaciones;
	
	public Reunion() {}
	
	public Reunion(Date fechaInicio,Date fechaFin,Sala lugar,Calendario calendario) {
		if(calendario.addReunion(this)) {//chequear que no se superpongan
			this.fechaInicio = fechaInicio;
			this.fechaFin = fechaFin;
			this.lugar = lugar;
			this.calendario = calendario;
			this.invitados = new ArrayList<Usuario>();
			this.invitaciones = new ArrayList<Notificacion>();
		}
		
	}
	
	public boolean addInvitado(Usuario usuario) {
		return this.invitados.add(usuario);
	}
	
	public boolean removeInvitado(Usuario usuario) {
		return this.invitados.remove(usuario);
	}
	
	public boolean addNotificacion(Notificacion notificacion) {
		return this.invitaciones.add(notificacion);
	}
	
	public boolean removeNotificacion(Notificacion notificacion) {
		return this.invitaciones.remove(notificacion);
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
		return lugar;
	}

	public void setLugar(Sala lugar) {
		this.lugar = lugar;
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

	public List<Notificacion> getInvitaciones() {
		return invitaciones;
	}
	
}
