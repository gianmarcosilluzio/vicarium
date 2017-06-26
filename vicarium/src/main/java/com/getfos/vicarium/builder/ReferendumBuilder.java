package com.getfos.vicarium.builder;

import com.getfos.vicarium.model.Referendum;
import com.getfos.vicarium.model.external.ReferendumExternal;
import com.getfos.vicarium.util.DateUtil;

public class ReferendumBuilder {
	
	private final static String PATTERN_REFERENDUM_DATE = "yyyyMMdd";
	
	public static Referendum buildReferendum(ReferendumExternal external){
				
		Referendum referendum = new Referendum();
		referendum.setIdentifier(external.getIdentifier().getValue());
		referendum.setDate(DateUtil.convertStringToDate(external.getDate().getValue(), PATTERN_REFERENDUM_DATE));
		referendum.setDenomination(external.getDenomination().getValue());
		referendum.setDescription(external.getDescription().getValue());
		//TODO
		referendum.setPathDocument("");
		referendum.setTotalVoteDeputyAssente(0);
		referendum.setTotalVoteDeputyAstenuto(0);
		referendum.setTotalVoteDeputyContrario(0);
		referendum.setTotalVoteDeputyFavorevole(0);
		referendum.setTotalVoteUserAstenuto(0);
		referendum.setTotalVoteUserContrario(0);
		referendum.setTotalVoteUserFavorevole(0);
		return referendum;
	}
}
