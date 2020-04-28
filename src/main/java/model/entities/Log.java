package model.entities;

import java.util.Date;

public class Log {

	//private Integer id;
	private String ipClient;
	private Date dateHour;
	private Double cpuMhz;
	private Double freeRam;
	private Double diskUsagePerc;

	
	public Log() {
		
	}


	public Log(String ipClient, Date dateHour, Double cpuMhz, Double freeRam, Double diskUsagePerc) {
		//this.id = null;
		this.ipClient = ipClient;
		this.dateHour = dateHour;
		this.cpuMhz = cpuMhz;
		this.freeRam = freeRam;
		this.diskUsagePerc = diskUsagePerc;
	}


	/*public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}*/


	public String getIpClient() {
		return ipClient;
	}


	public void setIpClient(String ipClient) {
		this.ipClient = ipClient;
	}


	public Date getDateHour() {
		return dateHour;
	}


	public void setDateHour(Date dateHour) {
		this.dateHour = dateHour;
	}


	public Double getCpuMhz() {
		return cpuMhz;
	}


	public void setCpuMhz(Double cpuMhz) {
		this.cpuMhz = cpuMhz;
	}


	public Double getFreeRam() {
		return freeRam;
	}


	public void setFreeRam(Double freeRam) {
		this.freeRam = freeRam;
	}


	public Double getDiskUsagePerc() {
		return diskUsagePerc;
	}


	public void setDiskUsagePerc(Double diskUsagePerc) {
		this.diskUsagePerc = diskUsagePerc;
	}


	@Override
	public String toString() {
		return "Log [ipClient=" + ipClient + ", dateHour=" + dateHour + ", cpuMhz=" + cpuMhz
				+ ", freeRam=" + freeRam + ", diskUsagePerc=" + diskUsagePerc + "]";
	}

	
	
}
