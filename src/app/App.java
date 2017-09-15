package app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import entidades.*;
import servicios.Servicios;

public class App {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("calendarios");
		EntityManager em = emf.createEntityManager();
		
		Servicios.createSala("Sala nro 1", "Pequeña", em);
		Servicios.createSala("Sala nro 2", "Mediana", em);
		Servicios.createSala("Sala nro 3", "Grande", em);
		
		Servicios.createUsuario("Juan", "Perez", em);//dni 4
		Servicios.createUsuario("Jose", "Flores", em);
		Servicios.createUsuario("Pepe", "Gomez", em);
		
		Servicios.createCalendario("CalendarioSuper", 4, em);//10
		
		Servicios.createReunion(new Date(100, 12, 25, 10, 00, 00), new Date(100, 12, 25, 12, 00, 00), 1, 5, em);//si
		Servicios.createReunion(new Date(100, 12, 25, 11, 00, 00), new Date(100, 12, 25, 15, 00, 00), 1, 10, em);//no xq se superpone
		
		List<Reunion> reuniones = new ArrayList<Reunion>();
		reuniones = Servicios.getReunionesByUserAndDay(4, new Date(100, 12, 25, 10, 00, 00), em);
		
		System.out.println(reuniones.toString());
		
		em.close();
		emf.close();
	}
}

