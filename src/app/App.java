package app;

import javax.persistence.*;
import entidades.*;

public class App {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("calendarios");
		EntityManager em = emf.createEntityManager();

		Usuario s = em.find(Usuario.class, 1);
		//acciones

		em.close();
		emf.close();
	}
}

