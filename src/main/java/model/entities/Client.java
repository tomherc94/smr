package model.entities;

import model.enums.Status;

public class Client {

	private Integer id;
	private String ip;
	private String system;
	private Status status;
	
	public Client() {
		
	}

	public Client(Integer id, String ip, String system, Status status) {
		this.id = id;
		this.ip = ip;
		this.system = system;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", ip=" + ip + ", system=" + system + ", status=" + status + "]";
	}

	
	
}
