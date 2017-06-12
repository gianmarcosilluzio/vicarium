package com.getfos.vicarium.builder;

import com.getfos.vicarium.model.Referendum;
import com.getfos.vicarium.model.external.ReferendumExternal;
import com.getfos.vicarium.util.DateUtil;

public class ReferendumBuilder {
	
	private final static String PATTERN_REFERENDUM_DATE = "yyyyMMdd";
	
	public static Referendum buildDeputy(ReferendumExternal external){
		
		Referendum referendum = new Referendum();
		referendum.setDate(DateUtil.convertStringToDate(external.getDate().getValue(), PATTERN_REFERENDUM_DATE));
		referendum.setDenomination(external.getDenomination().getValue());
		referendum.setDescription(external.getDescription().getValue());
		//TODO
		referendum.setPathDocument("");
		return referendum;
	}
}
