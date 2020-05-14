package model.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.entities.Client;

public class ClienteService {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
	private EntityManager em = emf.createEntityManager();

	public List<Client> findAll() {
		List<Client> clientes = null;
		try {
			em.getTransaction().begin(); // inicia transação com o BD

			clientes = em.createQuery("select c from cliente c", Client.class).getResultList();
			

			em.getTransaction().commit();// confirma transação com o BD
		}catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			em.close();
			emf.close();
		}		
		
		return clientes;
	}

	public void remove(Client obj) {
		em.getTransaction().begin(); // inicia transação com o BD

		List<Client> clientes = em.createQuery("SELECT c FROM cliente c", Client.class).getResultList();

		for (Client c : clientes) {
			if (obj.equals(c)) {
				em.remove(c);
				break;
			}
		}

		em.getTransaction().commit();// confirma transação com o BD
	}

}
