package model.services;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.opencsv.CSVReader;

import model.entities.Log;

public class SaveLogBD {

	private String file;
	private Log log;

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
		Log obj = new Log();
		while ((nextRecord = csvReader.readNext()) != null) {
			obj.setIpClient(nextRecord[0]);
			obj.setDateHour(nextRecord[1]);
			obj.setCpuMhz(Double.parseDouble(nextRecord[2]));
			obj.setFreeRam(Long.parseLong(nextRecord[3]));
			obj.setFreeSwap(Long.parseLong(nextRecord[4]));
			obj.setDiskUsagePerc(Double.parseDouble(nextRecord[5]));
			break;
		}
		
		csvReader.close();

		this.log = obj;
		this.save();

	}

	public void save() {
		// salvar this.log no BD
		System.out.println(this.log.toString());
	}

}
