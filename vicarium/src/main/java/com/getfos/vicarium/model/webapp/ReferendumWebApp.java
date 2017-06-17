package com.getfos.vicarium.model.webapp;

import com.getfos.vicarium.model.Referendum;

public class ReferendumWebApp {
	private Referendum referendum;
	private boolean userVoted;
	
	public ReferendumWebApp() {
	}

	public Referendum getReferendum() {
		return referendum;
	}

	public void setReferendum(Referendum referendum) {
		this.referendum = referendum;
	}

	public boolean isUserVoted() {
		return userVoted;
	}

	public void setUserVoted(boolean userVoted) {
		this.userVoted = userVoted;
	}
	
}
