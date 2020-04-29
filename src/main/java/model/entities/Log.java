package model.entities;

public class Log {

	//private Integer id;
	
	private String ipClient;
	private String dateHour;
	private Double cpuMhz;
	private Long freeRam;
	private Long freeSwap;
	private Double diskUsagePerc;

	
	public Log() {
		
	}


	public Log(String ipClient, String dateHour, Double cpuMhz, Long freeRam, Long freeSwap, Double diskUsagePerc) {
		this.ipClient = ipClient;
		this.dateHour = dateHour;
		this.cpuMhz = cpuMhz;
		this.freeRam = freeRam;
		this.freeSwap = freeSwap;
		this.diskUsagePerc = diskUsagePerc;
	}


	public String getIpClient() {
		return ipClient;
	}


	public void setIpClient(String ipClient) {
		this.ipClient = ipClient;
	}


	public String getDateHour() {
		return dateHour;
	}


	public void setDateHour(String dateHour) {
		this.dateHour = dateHour;
	}


	public Double getCpuMhz() {
		return cpuMhz;
	}


	public void setCpuMhz(Double cpuMhz) {
		this.cpuMhz = cpuMhz;
	}


	public Long getFreeRam() {
		return freeRam;
	}


	public void setFreeRam(Long freeRam) {
		this.freeRam = freeRam;
	}


	public Long getFreeSwap() {
		return freeSwap;
	}


	public void setFreeSwap(Long freeSwap) {
		this.freeSwap = freeSwap;
	}


	public Double getDiskUsagePerc() {
		return diskUsagePerc;
	}


	public void setDiskUsagePerc(Double diskUsagePerc) {
		this.diskUsagePerc = diskUsagePerc;
	}


	@Override
	public String toString() {
		return "Log [ipClient=" + ipClient + ", dateHour=" + dateHour + ", cpuMhz=" + cpuMhz + ", freeRam=" + freeRam
				+ ", freeSwap=" + freeSwap + ", diskUsagePerc=" + diskUsagePerc + "]";
	}


	
}
