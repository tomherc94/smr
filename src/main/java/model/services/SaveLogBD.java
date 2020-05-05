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
import model.enums.Status;

public class SaveLogBD {

	private String file;

	public SaveLogBD() {

	}

	public SaveLogBD(String file) {
		this.file = file;
	}
	
	public SaveLogBD(String ip, Status status) {
		//find cliente pelo ip e atualiza o status
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
		// salvar this.log no BD
		//testa se o cliente existe no BD, se sim atualiza, senao cria novo cliente
		//System.out.println(log.toString());
		//System.out.println(cli.toString());
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin(); //inicia transa��o com o BD
		em.persist(log);

		//verifica se cliente ja existe no BD
		List<Client> clientes = em.createQuery("SELECT c FROM cliente c", Client.class).getResultList();
		boolean verificaClient = false;
		for(Client c : clientes) {
			if(cli.equals(c)) {
				verificaClient = true;
				break;
			}
		}
		
		if(verificaClient == false) {
			em.persist(cli);
		}
		
		
		
		
	
		em.getTransaction().commit();//confirma transa��o com o BD
		
		
		System.out.println("SALVO NO BD!");
		
		em.close();
		emf.close();
	}
	
	public void saveOff(String ip, Status status) {
		//atualiza cliente para OFFLINE
	}

}
