package entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name="Usuario")

@NamedQuery(name= Usuario.BUSCAR_TODOS,query="SELECT u FROM Usuario u")
@NamedQuery(name= Usuario.BUSCAR_USUARIO_BY_DNI,query = "SELECT u FROM Usuario u WHERE u.dni = ?1")

public class Usuario {
	
	public static final String BUSCAR_TODOS = "Usuario.buscarTodos";
	public static final String BUSCAR_USUARIO_BY_DNI = "Usuario.buscarUsuarioByDni";
	
	@Id
	@GeneratedValue
	private int dni;
	
	private String nombre;
	private String apellido;
	
	//lista de calendarios propios
	@ManyToMany(cascade=CascadeType.PERSIST)
	private List<Calendario>calendarios;
	
	//notificaciones pendientes
	@OneToMany(mappedBy="invitado")
	private List<Notificacion> invitaciones;
	
	//reuniones a la que fui invitado
	@ManyToMany
	private List<Reunion>reunionesInvitado;
	
	public Usuario() {}
	
	public Usuario(String nombre,String apellido) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.calendarios = new ArrayList<Calendario>();
		this.invitaciones = new ArrayList<Notificacion>();
	}
	
	public List<Reunion> getReunionesByDay(Date day){
		List<Reunion> result = new ArrayList<Reunion>();
		
		for (int i = 0; i < this.calendarios.size(); i++) {
			result.addAll(this.calendarios.get(i).getReunionesByDay(day));
		}
		
		return result;
	}
	public boolean addReunionByCalendario(Reunion reunion, Calendario calendario) {
		if(this.calendarios.contains(calendario)) {
				return calendario.addReunion(reunion);
			}
		return false;
	}
	
	public boolean addCalendario(Calendario calendario) {
		return this.calendarios.add(calendario);
	}
	
	public boolean removeCalendario(Calendario calendario) {
		return this.calendarios.remove(calendario);
	}

	
	public boolean addNotificacion(Notificacion notificacion) {
		return this.invitaciones.add(notificacion);
	}
	
	public boolean removeNotificacion(Notificacion notificacion) {
		return this.invitaciones.remove(notificacion);
	}

	public boolean equals(Usuario usuario) {
		return this.dni == usuario.getDni();
	}
	
	public String toString() {
		return "DNI: "+this.dni+"   Nombre: "+this.nombre+"  Apellido: "+this.apellido;
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
