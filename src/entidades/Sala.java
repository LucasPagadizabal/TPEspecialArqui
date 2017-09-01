package entidades;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="Sala")
public class Sala {
	
	@Id
	@GeneratedValue
	private int id;
	
	private List<Reunion> reuniones;
	
	private boolean ocupada;
	
	public Sala() {}
}
