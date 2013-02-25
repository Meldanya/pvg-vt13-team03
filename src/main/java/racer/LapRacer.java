package racer;

public class LapRacer extends AbstractRacer {

	public LapRacer(String startNumber) {
		super(startNumber);
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
	public String toString() {
		
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



	@Override
	public int getNumberOfLaps() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public void addStartTime(RacerTime racerTime) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void addFinishTime(RacerTime racerTime) {
		// TODO Auto-generated method stub
		
	}

}
