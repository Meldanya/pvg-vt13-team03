package registration;

import racer.RacerTime;
import res.Strings;

public class StartRegister extends Register {

	@Override
	protected String getRacerTime() {
		return racer.getStartTime();
	}

	@Override
	protected void addTime(RacerTime racerTime) {
		racer.addStartTime(racerTime);
		
	}

	@Override
	protected String getFileName() {
		return Strings.START;
	}

}
