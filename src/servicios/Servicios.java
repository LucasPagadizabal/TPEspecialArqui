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
		user.addCalendario(new Calendario("Mi Calendario "+user.getApellido(),user));//Calendario por default
		em.persist(user);
		em.getTransaction().commit();
	}
	
	public static Usuario getUserByDni(int dni,EntityManager em) {
		Query query = em.createNamedQuery(Usuario.BUSCAR_USUARIO_BY_DNI);
		query.setParameter(1, dni);
		return (Usuario) query.getSingleResult();
	}
	
	public static void createCalendario(String nombreCalendario, int dniUser, EntityManager em) {
		em.getTransaction( ).begin( );	
		Usuario user = em.find(Usuario.class, dniUser);
		user.addCalendario(new Calendario(nombreCalendario,user));
		em.persist(user);
		em.getTransaction().commit();
	}
	
	public static void createReunion(Date fechaInicio,Date fechaFin,int idSala,int idCalendario,EntityManager em) {
		//las reuniones solo pueden pertenecer a un solo calendario??
		
		em.getTransaction( ).begin();
		Sala lugar = em.find(Sala.class, idSala);//chequear q no se superponga
		Calendario calendario = em.find(Calendario.class, idCalendario);//chequear q no se superoponga con otra reuniones
		
		if(!lugar.checkSuperPosicion(fechaInicio, fechaFin)){
			if(!calendario.checkSuperPosicion(fechaInicio, fechaFin)){
				Reunion reunion = new Reunion(fechaInicio,fechaFin,lugar,calendario);
				calendario.addReunion(reunion);
				lugar.addReunion(reunion);
				em.persist(reunion);
				em.persist(calendario);
				em.persist(lugar);
			}else {
				System.out.println("Superposicion de horarios en el calendario");//mensajes de error
			}
		}else {
			System.out.println("Superposicion de horarios en el lugar");//mensajes de error
		}
		
		em.getTransaction().commit();
	}
	
	public static void createSala(String nombre,String descripcion,EntityManager em) {
		em.getTransaction( ).begin( );	
		Sala sala = new Sala(nombre,descripcion);
		em.persist(sala);
		em.getTransaction().commit();
	}
	
	public static List<Reunion> getReunionesByUserAndDay(int dni,Date day,EntityManager em) {
		Query query = em.createNamedQuery(Reunion.BUSCAR_REUNIONES_BY_USER_DAY);
		query.setParameter(1, dni);
		query.setParameter(2, day);

		List<Reunion> result = query.getResultList();
		
		return result;
	}
	
	public static List<Reunion> getReunionesEntreFechas(Date day1, Date day2, EntityManager em){
		List<Reunion> result = new ArrayList<Reunion>();
		
		Query query = em.createNamedQuery(Reunion.BUSCAR_REUNIONES_BETWEEN_DATES);
		query.setParameter(1, day1);
		query.setParameter(2, day2);
		result = query.getResultList();
		return result;
	}
	
	public static List<Reunion> getReunionesSuperpuestas(int dni,Date newIni,Date newFin,EntityManager em){
		List<Reunion> result = new ArrayList<Reunion>();

		Query query = em.createNamedQuery(Reunion.BUSCAR_REUNIONES_BY_USER);
		query.setParameter(1, dni);
		List<Reunion> reunionesUser = query.getResultList();
		
		for (int i = 0; i < reunionesUser.size(); i++) {
			if(reunionesUser.get(i).superposicionHorarios(newIni, newFin)) {
				result.add(reunionesUser.get(i));
			}
		}
		return result;
	}
	
	public static void restaurarBD(EntityManager em) {
		em.getTransaction( ).begin( );	
		em.createNamedQuery(Usuario.BORRAR_DATOS).executeUpdate();
		em.createNamedQuery(Calendario.BORRAR_DATOS).executeUpdate();
		em.createNamedQuery(Reunion.BORRAR_DATOS).executeUpdate();
		em.createNamedQuery(Sala.BORRAR_DATOS).executeUpdate();
		em.createNamedQuery(Notificacion.BORRAR_DATOS).executeUpdate();
		em.getTransaction().commit();
	}
	
	public static void addInvitado(int dni,int id_reunion,EntityManager em) {
		em.getTransaction( ).begin( );
		Usuario user = em.find(Usuario.class, dni);
		Reunion reunion = em.find(Reunion.class, id_reunion);
		Notificacion noti = new Notificacion(user,reunion);
		user.addNotificacion(noti);
		reunion.addInvitado(user);
		em.persist(noti);
		em.persist(user);
		em.persist(reunion);
		em.getTransaction().commit();
	}
	
	public static void aceptarInvitacion(int dni, int id_noti,EntityManager em) {
		em.getTransaction( ).begin( );
		Usuario user = em.find(Usuario.class, dni);
		Notificacion noti = em.find(Notificacion.class, id_noti);
		
		if(user.existeInvitacion(noti)) {
			user.addInvitacion(noti.getReunion());
			em.remove(noti);
			em.persist(user);
			em.flush();
		}
		em.getTransaction().commit();
	}
	
}
