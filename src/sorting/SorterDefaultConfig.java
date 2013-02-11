package sorting;

import java.util.Properties;

public class SorterDefaultConfig extends Properties{
	public SorterDefaultConfig(){
		this.setProperty("Namefile", "namnfil.txt");
		
		this.setProperty("TypeOfContest", "Marathon");
		
		this.setProperty("NumberOfLaps", "1");
		this.setProperty("MinumumLapTime", "00.15.00");
		this.setProperty("TimeLimit","01.00.00");
	}
}
