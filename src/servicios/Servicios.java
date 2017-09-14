package servicios;
import java.util.Date;

import javax.persistence.*;
import entidades.*;

public class Servicios {
	
	public static void createUsuario(int dni,String nombre,String apellido,EntityManager em) {
		em.getTransaction( ).begin( );	
		Usuario user = new Usuario(dni,nombre,apellido);
		user.addCalendario(new Calendario("Mi Calendario"));//Calendario por default
		em.persist(user);
		em.getTransaction().commit();
	}
	
	public static void createCalendario(String nombreCalendario, int dniUser, EntityManager em) {
		em.getTransaction( ).begin( );	
		Usuario user = em.find(Usuario.class, dniUser);
		user.addCalendario(new Calendario(nombreCalendario));
		em.persist(user);
		em.getTransaction().commit();
	}
	
//	al crear una reunion hay que chequear si no se superponen fechas
	public static void createReunion(Date fechaInicio,Date fechaFin,int idSala,int idCalendario,EntityManager em) {
		em.getTransaction( ).begin();
		Sala lugar = em.find(Sala.class, idSala);
		Calendario calendario = em.find(Calendario.class, idCalendario);
		Reunion reunion = new Reunion(fechaInicio,fechaFin,lugar,calendario);
		em.persist(reunion);
		em.getTransaction().commit();
	}
}
