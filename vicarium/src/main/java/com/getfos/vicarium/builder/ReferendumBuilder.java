package com.getfos.vicarium.builder;

import com.getfos.vicarium.model.Referendum;
import com.getfos.vicarium.model.external.ReferendumExternal;
import com.getfos.vicarium.util.DateUtil;

public class ReferendumBuilder {
	
	private final static String PATTERN_REFERENDUM_DATE = "yyyyMMdd";
	private final static String SPLIT_IDENTIFIER = ".rdf/vs";
	
	public static Referendum buildReferendum(ReferendumExternal external){
		
		//identifier converter
		String identifier = external.getIdentifier().getValue().split(SPLIT_IDENTIFIER)[1];
		identifier = identifier.substring(2, identifier.length());
		identifier = identifier.replace("_", "");
		
		Referendum referendum = new Referendum();
		referendum.setIdentifier(identifier);
		referendum.setDate(DateUtil.convertStringToDate(external.getDate().getValue(), PATTERN_REFERENDUM_DATE));
		referendum.setDenomination(external.getDenomination().getValue());
		referendum.setDescription(external.getDescription().getValue());
		//TODO
		referendum.setPathDocument("");
		return referendum;
	}
}
