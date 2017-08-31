package entidades;

import java.util.Date;
import java.util.List;

public class Reunion {
	
	private int id;
	
	private Date fecha;
	private String lugar;
	private List<Usuario> invitados;
	
	public Reunion() {}
	
	public Reunion(Date fecha,String lugar) {
		this.fecha = fecha;
		this.lugar = lugar;
	}
	
	public void addInvitados() {
		
	}
}
