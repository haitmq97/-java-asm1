package me.haitmq.spring.mvc.crud.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import java.time.LocalDate;


public class Time {
	
	private static DateTimeFormatter dateTimeFormatterRaw =DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	private static DateTimeFormatter dateTimeFormatter =DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	
	

	public static String getCurrentDateTime() {
		return LocalDateTime.now().format(dateTimeFormatter);
	}
	
	public static String getCurrentDateTimeRaw() {
		return LocalDateTime.now().format(dateTimeFormatterRaw);
	}
	
	public static String getNextDayOfCurrentDate() {
		LocalDateTime nextDay = LocalDateTime.now().plusDays(1);
		// 
		return nextDay.format(dateTimeFormatter);
	}
	
	public static String formatDateTime(String dateString) {
		LocalDate date = LocalDate.parse(dateString, dateTimeFormatterRaw);
		return date.format(dateTimeFormatter);
	}

    public static String getNextDay(String dateString) {

        LocalDate date = LocalDate.parse(dateString, dateTimeFormatter);
       
        LocalDate nextDay = date.plusDays(1);
    
        return nextDay.format(dateTimeFormatter);
    }
    
    
    public static String getNextDayRaw(String dateString) {

        LocalDate date = LocalDate.parse(dateString, dateTimeFormatterRaw);
       
        LocalDate nextDay = date.plusDays(1);
    
        return nextDay.format(dateTimeFormatterRaw);
    }
    
    
    public static boolean isAfterDate(String benMarkDate, String checkDateString) {
    	
    	if((benMarkDate.length() != 0) && (checkDateString.length() != 0)) {
    		LocalDate originalDate = LocalDate.parse(benMarkDate, dateTimeFormatterRaw);
        	LocalDate checkDate = LocalDate.parse(benMarkDate, dateTimeFormatterRaw);
        	if(checkDate.isAfter(originalDate)) {
        		return true;
        	}
    	}
    	
    	return false;
    	
    }
    
    public static boolean isBeforeDate(String benMarkDate, String checkDateString) {
    	if((benMarkDate.length() != 0) && (checkDateString.length() != 0)) {
    		LocalDate originalDate = LocalDate.parse(benMarkDate, dateTimeFormatterRaw);
        	LocalDate checkDate = LocalDate.parse(checkDateString, dateTimeFormatterRaw);
        	
        	if(checkDate.isBefore(originalDate)) {
        		return true;
        	}
    	}
    	
    	return false;
    	
    }

}
