package model.services;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

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

		CsvToBean<Log> csvToBean = new CsvToBeanBuilder<Log>(reader).withType(Log.class).build();

		List<Log> logs = csvToBean.parse();

		for (Log l : logs) {
			this.log = l;
			break;
		}
		this.save();
			
	}

	public void save() {
		// salvar this.log no BD
		System.out.println(this.log);
	}

}
