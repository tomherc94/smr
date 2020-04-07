package model.entities;

import java.util.Date;

public class Log {

	private Integer id;
	private String ipClient;
	private Date dateHour;
	private Double temperature;
	private Double cpu;
	private Double gpu;
	private Double diskUsage;
	private Double freeRam;
	
	public Log() {
		
	}

	public Log(Integer id, String ipClient, Date dateHour, Double temperature, Double cpu, Double gpu, Double diskUsage,
			Double freeRam) {
		this.id = id;
		this.ipClient = ipClient;
		this.dateHour = dateHour;
		this.temperature = temperature;
		this.cpu = cpu;
		this.gpu = gpu;
		this.diskUsage = diskUsage;
		this.freeRam = freeRam;
	}

	public Integer getId() {
		return id;
	}

	public String getIpClient() {
		return ipClient;
	}

	public Date getDateHour() {
		return dateHour;
	}

	public Double getTemperature() {
		return temperature;
	}

	public Double getCpu() {
		return cpu;
	}

	public Double getGpu() {
		return gpu;
	}

	public Double getDiskUsage() {
		return diskUsage;
	}

	public Double getFreeRam() {
		return freeRam;
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", ipClient=" + ipClient + ", dateHour=" + dateHour + ", temperature=" + temperature
				+ ", cpu=" + cpu + ", gpu=" + gpu + ", diskUsage=" + diskUsage + ", freeRam=" + freeRam + "]";
	}
	
}
