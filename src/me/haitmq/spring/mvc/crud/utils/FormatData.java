package me.haitmq.spring.mvc.crud.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatData {
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	public static Date stringToDate(String data) {
		Date result;
		try {
			result = dateFormat.parse(data);
		} catch (ParseException e) {
			result = null;
		}
		return result;
	}
}
