package entidades;

import javax.persistence.*;

@Entity
@Table(name="Notificacion")
public class Notificacion {
	
	@Id
	@GeneratedValue
	private int id;
		
	@ManyToOne
	private Usuario invitado;
	
	@ManyToOne
	private Reunion reunion;
	
	public Notificacion() {}
	
	public Notificacion(Usuario invitado,Reunion reunion) {
		this.invitado = invitado;
		this.reunion = reunion;
	}

	public int getId() {
		return id;
	}

	public Usuario getInvitado() {
		return invitado;
	}

	public void setInvitado(Usuario invitado) {
		this.invitado = invitado;
	}

	public Reunion getReunion() {
		return reunion;
	}

	public void setReunion(Reunion reunion) {
		this.reunion = reunion;
	}
	
	public boolean equals(Notificacion noti) {
		return this.id == noti.getId();
	}
}
