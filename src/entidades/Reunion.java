package entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name="Reunion")

@NamedQuery(name=Reunion.BUSCAR_REUNIONES_BY_USER,query="SELECT r FROM Reunion r JOIN r.calendario.duenio user WHERE user.dni =?1")
@NamedQuery(name=Reunion.BUSCAR_REUNIONES_BETWEEN_DATES,query="SELECT r FROM Reunion r WHERE r.fechaInicio BETWEEN ?1 AND ?2")


public class Reunion {
	
	public static final String BUSCAR_REUNIONES_BY_USER = "Reunion.BuscarReunionesByUser";
	public static final String BUSCAR_REUNIONES_BETWEEN_DATES = "Reunion.BuscarReunionesBetweenDates";
	
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
			this.fechaInicio = fechaInicio;
			this.fechaFin = fechaFin;
			this.sala = lugar;
			this.calendario = calendario;
			this.invitados = new ArrayList<Usuario>();
	}
	
	public boolean mismoDia(Date day) {
		return (this.fechaInicio.getYear() == day.getYear()) && (this.fechaInicio.getMonth() == day.getMonth()) && (this.fechaInicio.getDay() == day.getDay());
	}
	
	public boolean superposicionHorarios(Date nuevaI,Date nuevaF) {
		
		if(nuevaF.compareTo(this.fechaInicio)<=0){
			return false;//no se superpone
			
		}else if(nuevaF.compareTo(this.fechaFin)<=0) {
			return true;//se superpone!
			
		}else if(nuevaI.compareTo(this.fechaFin)<=0){
			return true;// se superpone
		}else {
			return false;//no se superpone!
		}
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
