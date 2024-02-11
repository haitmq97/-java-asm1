package me.haitmq.spring.mvc.crud.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Time {
	private static DateTimeFormatter dateTimeFormatter =DateTimeFormatter.ofPattern("dd/MM/yyyy");
	

	public static String getCurrentDateTime() {
		return LocalDateTime.now().format(dateTimeFormatter);
	}

	

}
