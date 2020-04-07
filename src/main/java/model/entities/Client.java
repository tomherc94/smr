package model.entities;

import model.enums.Status;

public class Client {

	private Integer id;
	private String hostname;
	private String ip;
	private Status status;
	
	public Client() {
		
	}

	public Client(Integer id, String hostname, String ip, Status status) {
		this.id = id;
		this.hostname = hostname;
		this.ip = ip;
		this.status = status;
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

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
}
