package entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Sala")
public class Sala {

	@Id
	@GeneratedValue
	private int id;
	
	private String nombre;
	
	private String descripcion;
	
	@OneToMany(mappedBy="sala")
	private List<Reunion>reuniones;
	
	public Sala() {}
	
	public Sala(String nombre,String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.reuniones = new ArrayList<Reunion>();
	}
	
	private boolean checkSuperPosicion(Date fechaI,Date fechaF) {
		for (int i = 0; i < this.reuniones.size(); i++) {
			if(this.reuniones.get(i).superposicionHorarios(fechaI, fechaF)) {
				return true;
			}
		}
		return false;
	}
	public boolean addReunion(Reunion reunion) {
		if(!this.checkSuperPosicion(reunion.getFechaInicio(), reunion.getFechaFin())) {
			return this.reuniones.add(reunion);
		}
		//chequear que no se superpongan
		return false;
	}
	
	public boolean equals(Sala sala) {
		return this.id == sala.getId();
	}
//	Getters and Setters
	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
