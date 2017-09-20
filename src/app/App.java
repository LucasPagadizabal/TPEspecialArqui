package app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
		Servicios.createUsuario("Idella", "Morrow", em);
		Servicios.createUsuario("Abdul", "Blanchard", em);
		Servicios.createUsuario("Helena", "Mclaughlin", em);
		Servicios.createUsuario("Ammie", "Fraser", em);
		Servicios.createUsuario("Billye", "Bird", em);
		Servicios.createUsuario("Lavonia", "Mcculloch", em);
		Servicios.createUsuario("Arcelia", "Atkinson", em);
		
		Servicios.createCalendario("Trabajo", 4, em);
		Servicios.createCalendario("Escuela", 4, em);
		Servicios.createCalendario("Familia", 6, em);
		
		Date r1I = new GregorianCalendar(2017, Calendar.SEPTEMBER, 19, 12, 00).getTime();
		Date r1F = new GregorianCalendar(2017, Calendar.SEPTEMBER, 19, 14, 00).getTime();	
		
		Servicios.createReunion(r1I, r1F, 1, 5, em);//si
		
		Date r2I = new GregorianCalendar(2017, Calendar.SEPTEMBER, 19, 13, 00).getTime();
		Date r2F = new GregorianCalendar(2017, Calendar.SEPTEMBER, 19, 18, 00).getTime();	
		
		Servicios.createReunion(r2I,r2F, 1, 5, em);//no xq se superpone
		
		System.out.println(Servicios.getUserByDni(4, em).toString());
		
//		List<Reunion> reuniones = new ArrayList<Reunion>();
//		reuniones = Servicios.getReunionesByUserAndDay(4, new Date(100, 12, 25, 10, 00, 00), em);
//		
//		System.out.println(reuniones.toString());
		
		em.close();
		emf.close();
	}
}

