package com.getfos.vicarium.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static Date convertStringToDate(String date, String pattern){
		Date dateResult = null;
		try {
			dateResult = new SimpleDateFormat(pattern).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateResult;
	}

	public static String convertDateToString(Date date, String pattern){
		String dateString = null;
		SimpleDateFormat sdfr = new SimpleDateFormat(pattern);
		try{
			dateString = sdfr.format(date);
		}catch (Exception ex ){
			System.out.println(ex);
		}
		return dateString;
	}
}
