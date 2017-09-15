package servicios;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import entidades.*;

public class Servicios {
	
	public static void createUsuario(String nombre,String apellido,EntityManager em) {
		em.getTransaction( ).begin( );	
		Usuario user = new Usuario(nombre,apellido);
		user.addCalendario(new Calendario("Mi Calendario",user));//Calendario por default
		em.persist(user);
		em.getTransaction().commit();
	}
	
	public static Usuario getUserByDni(int dni,EntityManager em) {
		return em.find(Usuario.class, dni);
	}
	
	public static void createCalendario(String nombreCalendario, int dniUser, EntityManager em) {
		em.getTransaction( ).begin( );	
		Usuario user = em.find(Usuario.class, dniUser);
		user.addCalendario(new Calendario(nombreCalendario,user));
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
	
	public static void createSala(String nombre,String descripcion,EntityManager em) {
		em.getTransaction( ).begin( );	
		Sala sala = new Sala(nombre,descripcion);
		em.persist(sala);
		em.getTransaction().commit();
	}
	
	public static List<Reunion> getReunionesByUserAndDay(int dni,Date day,EntityManager em) {
		Usuario user = Servicios.getUserByDni(dni, em);
		List<Reunion> reuniones = new ArrayList<Reunion>();
		reuniones = user.getReunionesByDay(day);
		return reuniones;
	}
}
