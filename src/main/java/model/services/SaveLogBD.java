package model.services;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.opencsv.CSVReader;

import model.entities.Client;
import model.entities.Log;

public class SaveLogBD {

	private String file;

	public SaveLogBD() {

	}

	public SaveLogBD(String file) {
		this.file = file;
	}

	public void convertFileToLog() throws IOException {

		// converter file em log

		Reader reader = Files.newBufferedReader(Paths.get(file));

		CSVReader csvReader = new CSVReader(reader);

		String[] nextRecord;
		Log log = new Log();
		Client cli = new Client();

		while ((nextRecord = csvReader.readNext()) != null) {
			log.setId(null);
			log.setIpClient(nextRecord[0]);
			
			if(nextRecord[1] == null) { //caso tenha falha no recebimento do arquivo o programa nao ira travar
				System.out.println("Falhou mas nao travou!");
				break;
			}
			
			log.setDateHour(nextRecord[1]);
			log.setCpuMhz(Double.parseDouble(nextRecord[2]));
			log.setFreeRam(Long.parseLong(nextRecord[3]));
			log.setFreeSwap(Long.parseLong(nextRecord[4]));
			log.setDiskUsagePerc(Double.parseDouble(nextRecord[5]));
			cli.setId(null);
			cli.setIp(nextRecord[6]);
			cli.setSystem(nextRecord[7]);
			cli.setStatus("ONLINE");
			break;
		}

		csvReader.close();

		this.saveOn(cli, log);

	}

	public void saveOn(Client cli, Log log) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin(); // inicia transa��o com o BD
		em.persist(log); //salva log no BD

		// verifica se cliente ja existe no BD
		List<Client> clientes = em.createQuery("SELECT c FROM cliente c", Client.class).getResultList();
		boolean verificaClient = false;
		for (Client c : clientes) {
			if (cli.equals(c)) {
				verificaClient = true;
				cli = em.find(Client.class, c.getId());
				cli.setStatus("ONLINE");
				System.out.println(cli);
				em.merge(cli); //atualiza cliente para ONLINE
				break;
			}
		}

		if (verificaClient == false) {
			em.persist(cli); //salva novo cliente no BD
		}

		em.getTransaction().commit();// confirma transa��o com o BD

		System.out.println("SALVO NO BD!");

		em.close();
		emf.close();
	}

	public void saveOff(String ip) {
		// atualiza cliente para OFFLINE
		Client cli = new Client();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin(); // inicia transa��o com o BD

		List<Client> clientes = em.createQuery("SELECT c FROM cliente c", Client.class).getResultList();
		for (Client c : clientes) {
			if (c.getIp().equals(ip)) {
				cli = em.find(Client.class, c.getId());
				cli.setStatus("OFFLINE");
				System.out.println(cli);
				em.merge(cli);

				break;
			}
		}

		em.getTransaction().commit();// confirma transa��o com o BD

		System.out.println("ATUALIZADO PARA OFFLINE!");

		em.close();
		emf.close();
	}

}
