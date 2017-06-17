package com.getfos.vicarium.builder;

import java.util.Date;

import com.getfos.vicarium.model.Deputy;
import com.getfos.vicarium.model.external.PoliticExternal;
import com.getfos.vicarium.util.DateUtil;

public class DeputyBuilder {
	
	private final static String PATTERN_BIRTHDAY_DATE = "yyyyMMdd";
	private final static String PATH_PHOTO_DEPUTY = "http://documenti.camera.it/_dati/leg17/schededeputatiprototipo/foto/scheda_big/d";
	private final static String PHOTO_EXTENSION = ".jpg";
	private final static String SPLIT_IDENTIFIER = ".rdf/p";
	
	public static Deputy buildDeputy(PoliticExternal external){
		Deputy deputy = new Deputy();
		deputy.setBirthday(DateUtil.convertStringToDate(external.getBirthday().getValue(), PATTERN_BIRTHDAY_DATE));
		deputy.setBirthPlace(external.getBirthplace().getValue());
		deputy.setIdentifier(external.getIdentifier().getValue().split(SPLIT_IDENTIFIER)[1]);
		//TODO
		//deputy.setLastUpdateDate(external.getLastUpdateDate().getValue());
		deputy.setName(external.getName().getValue());
		//deputy.setOccupation(external.getQualification().getValue());
		deputy.setPathPhoto(PATH_PHOTO_DEPUTY + deputy.getIdentifier() + PHOTO_EXTENSION);
		deputy.setPoliticalGroup(external.getPoliticalGroup().getValue().split("\\s\\(",0)[0].split("\\s-")[0]);
		//deputy.setPoliticalList(external.getPoliticalList().getValue());
		//deputy.setQualification(external.getQualification().getValue());
		deputy.setRegistrationDate(new Date());
		deputy.setSex(external.getSex().getValue());
		deputy.setSurname(external.getSurname().getValue());
		return deputy;
	}
}
