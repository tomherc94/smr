package model.entities;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public class Server {

	private Integer id;
	private String hostname;
	private String ip;
	private List<Client> clients;
	private List<Log> logs;

	public Server() {

	}

	public Server(Integer id, String hostname, String ip) {
		this.id = id;
		this.hostname = hostname;
		this.ip = ip;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) throws UnknownHostException {
		this.hostname = hostname;
		this.ip = InetAddress.getLocalHost().getHostName();
	}

	public String getIp() {
		return ip;
	}
	
	public void setIP() throws UnknownHostException {
		this.ip = InetAddress.getLocalHost().getHostAddress();
	}

	public List<Client> getClients() {
		return clients;
	}

	public void addClient (Client client) {
		this.clients.add(client);
	}

	public List<Log> getLogs() {
		return logs;
	}

	public void setLogs(Log log) {
		this.logs.add(log);
	}

	@Override
	public String toString() {
		return "Server [id=" + id + ", hostname=" + hostname + ", ip=" + ip + "]";
	}
	
}
