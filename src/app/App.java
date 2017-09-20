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
		Servicios.createReunion(r1I, r1F, 1, 5, em);
		
		Date r2I = new GregorianCalendar(2017, Calendar.SEPTEMBER, 19, 15, 00).getTime();
		Date r2F = new GregorianCalendar(2017, Calendar.SEPTEMBER, 19, 18, 00).getTime();	
		Servicios.createReunion(r2I,r2F, 1, 24, em);
		
//		C.I
//		System.out.println(Servicios.getUserByDni(4, em).toString());
		
//		C.II
//		Date day = new GregorianCalendar(2017, Calendar.SEPTEMBER, 19).getTime();
//		List<Reunion> reuniones = new ArrayList<Reunion>();
//		reuniones = Servicios.getReunionesByUserAndDay(4,day, em);
//		System.out.println(reuniones.toString());
		
//		C.III
//		Date day1 = new GregorianCalendar(2017, Calendar.SEPTEMBER, 19).getTime();
//		Date day2 = new GregorianCalendar(2017, Calendar.SEPTEMBER, 21).getTime();
//
//		List<Reunion> reuniones = new ArrayList<Reunion>();
//		reuniones = Servicios.getReunionesEntreFechas(day1, day2, em);
//		System.out.println(reuniones.toString());
		
//		C.IV
//		Date newReunionInicio = new GregorianCalendar(2017, Calendar.SEPTEMBER, 19,19,00).getTime();
//		Date newReunionFin =  new GregorianCalendar(2017, Calendar.SEPTEMBER, 19,20,00).getTime();
//		List<Reunion> reuniones = new ArrayList<Reunion>();
//		reuniones = Servicios.getReunionesSuperpuestas(4, newReunionInicio, newReunionFin, em);
//		System.out.println(reuniones.toString());
		
//		G
//		Servicios.restaurarBD(em);
		
		em.close();
		emf.close();
	}
}

