package registration;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import racer.Racer;

public class Register {
	private Racer racer;
	private boolean start;
	
	public Register(boolean start) {
		this.start = start;
	}

	public void writeToFile(String fileName) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			writer.write(racer.getStartNumber() + "; ");
			if (start) {
				System.out.println(racer.getStartTime());
				writer.write(Integer.toString(racer.getStartTime()));
			} else {
				writer.write(Integer.toString(racer.getFinishTime()));
			}
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void register(int startNumber, int time) {
		racer = new Racer(startNumber);
		if (start) {
			racer.setStartTime(time);
			writeToFile("start.txt");
		} else {
			racer.setFinishTime(time);
			writeToFile("finish.txt");
		}
	}
	
	public static void main(String[] args) {
		new Register(false).register(1, (int) (System.currentTimeMillis() / 1000));
	}
}
