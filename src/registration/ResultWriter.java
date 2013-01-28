package registration;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import racer.Racer;

public class ResultWriter {
	private Map<Integer, Racer> data;
	private String filename;
	
	public ResultWriter(Map data, String filename) {
		this.data = data;
		this.filename = filename;
	}
	
	public void writeToFile() {
		
	}
}
