package entidades;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="Usuario")
public class Usuario {
	
	@Id
	@GeneratedValue
	private int dni;
	
	private String nombre;
	private String apellido;
	
	//lista de calendarios propios
	@ManyToMany
	private List<Calendario>calendarios;
	
	//lista de reuniones en la cuales fue invitado
	@ManyToMany
	private List<Reunion> reuniones;
	
	//notificaciones pendientes
	@OneToMany(mappedBy="invitado")
	private List<Notificacion> invitaciones;
	
	public Usuario() {}
	
	public Usuario(int dni,String nombre,String apellido) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.calendarios = new ArrayList<Calendario>();
		this.reuniones = new ArrayList<Reunion>();
		this.invitaciones = new ArrayList<Notificacion>();
	}
	
	public boolean addCalendario(Calendario calendario) {
		return this.calendarios.add(calendario);
	}
	
	public boolean removeCalendario(Calendario calendario) {
		return this.calendarios.remove(calendario);
	}
	
	public boolean addReunionInvitacion(Reunion reunion) {//metodo para añadir reuniones invitadas
		return this.reuniones.add(reunion);
	}
	
	public boolean removeReunionInvitacion(Reunion reunion) {//metodo para remover reuniones invitadas
		return this.reuniones.remove(reunion);
	}
	
	public boolean addNotificacion(Notificacion notificacion) {
		return this.invitaciones.add(notificacion);
	}
	
	public boolean removeNotificacion(Notificacion notificacion) {
		return this.invitaciones.remove(notificacion);
	}
	
	public void aceptarInvitacion(int idNoti) {
		this.invitaciones.get(idNoti).aceptarNotificacion();
	}
	
	public void rechazarInvitacion(int idNoti) {
		this.invitaciones.get(idNoti).rechazarNotificacion();
	}
	
	public boolean equals(Usuario usuario) {
		return this.dni == usuario.getDni();
	}
	
//	Getters and Setters
	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
}
