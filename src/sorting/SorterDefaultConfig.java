package sorting;

import java.util.Properties;

public class SorterDefaultConfig extends Properties{
	public SorterDefaultConfig(){
		this.setProperty("Namefile", "namnfil.txt");
		
		this.setProperty("TypeOfContest", "Marathon");
		
		this.setProperty("NumberOfLaps", "1");
		this.setProperty("MinimumLapTime", "00.15.00");
		this.setProperty("TimeStartIsOpen","01.00.00");
		
		this.setProperty("FinishFiles", "finish1.txt");
		this.setProperty("FinishFiles", "finish2.txt");
	}
}
