package entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="Calendario")
public class Calendario {

	@Id
	@GeneratedValue
	private int id;
	private String nombre;
	
	@ManyToOne
	private Usuario duenio;
	
	@OneToMany(cascade=CascadeType.PERSIST)
	private List<Reunion> reuniones;

	public Calendario() {}

	public Calendario(String nombre,Usuario duenio) {
		this.nombre = nombre;
		this.duenio = duenio;
		this.reuniones = new ArrayList<Reunion>();
	}
	
	public boolean addReunion(Reunion reunion) {
		if(!this.checkSuperPosicion(reunion.getFechaInicio(), reunion.getFechaFin())) {
			return this.reuniones.add(reunion);
		}
		return false;
	}
	
	private boolean checkSuperPosicion(Date fechaI, Date fechaF) {
		for (int i = 0; i < this.reuniones.size(); i++) {
			if(this.reuniones.get(i).superposicionHorarios(fechaI, fechaF)) {
				return true;
			}
		}
		return false;
	}
	
	public List<Reunion>getReunionesByDay(Date day){
		List<Reunion>result = new ArrayList<Reunion>();
		
		for (int i = 0; i < this.reuniones.size(); i++) {
			if(this.reuniones.get(i).mismoDia(day)) {
				result.add(this.reuniones.get(i));
			}
		}
		
		return result;
	}
	
	public boolean removeReunion(Reunion reunion) {
		return this.reuniones.remove(reunion);
	}
	
	public boolean equals(Calendario cal) {
		return this.id == cal.getId();
	}

	//	Getters and Setters
	public int getId() {
		return this.id;
	}
	
	public int getCantReuniones() {
		return this.reuniones.size();
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
