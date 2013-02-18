package racer;

public class LapRacer extends AbstractLapRacer {

	public LapRacer(String startNumber) {
		super(startNumber);
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
	public String toString() {
	
		
		/**
		 * StartNr; Namn; Totaltid; #Etapper; Etapp1; Start1; Mål1
		 *	1; Ander Asson; 01.00.00; 1; 01.00.00; 11.00.00; 12.00.00
		 */
		
		StringBuilder sb = new StringBuilder();
		sb.append(startNumber + "; " + name + "; " + getTotalTime() + "; " + distanceList.size() + "; " + lapsToString());
		return sb.toString();
	}
	
	private String lapsToString(){
		StringBuilder sb = new StringBuilder();
		
		sb.append(distanceList.size() + "; ");
		
		for(Distance distance : distanceList){
			sb.append(distance.toString());
		}
		
		return sb.toString();
	}



	@Override
	public String getTotalTime() {
		
	}

}
