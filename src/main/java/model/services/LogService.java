package model.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.entities.Log;

public class LogService {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
	private EntityManager em = emf.createEntityManager();

	public List<Log> findAll() {
		em.getTransaction().begin(); // inicia transação com o BD

		List<Log> logs = em.createQuery("SELECT l FROM log l", Log.class).getResultList();

		em.getTransaction().commit();// confirma transação com o BD

		return logs;
	}
}
