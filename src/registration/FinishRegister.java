package registration;

import racer.RacerTime;
import res.Strings;

public class FinishRegister extends Register {

	@Override
	protected String getRacerTime() {
		return racer.getFinishTime();
	}

	@Override
	protected void addTime(RacerTime racerTime) {
		racer.addFinishTime(racerTime);
		
	}

	@Override
	protected String getFileName() {
		return Strings.FINISH;
	}

}
