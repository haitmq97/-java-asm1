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
    
    public static String getPreviousDayRaw(String dateString) {

        LocalDate date = LocalDate.parse(dateString, dateTimeFormatterRaw);
       
        LocalDate previousDay = date.minusDays(1);
    
        return previousDay.format(dateTimeFormatterRaw);
    }
    
    
    public static boolean isAfterDate(String benMarkDateString, String checkDateString) {
    	
    	if((benMarkDateString.length() != 0) && (checkDateString.length() != 0)) {
    		//System.out.println("check");
    		LocalDate benMarkDate = LocalDate.parse(benMarkDateString, dateTimeFormatterRaw);
    		
    		
        	LocalDate checkDate = LocalDate.parse(checkDateString, dateTimeFormatterRaw);
        	/*
        	System.out.println("In isAfterDate method ......original Date:" + benMarkDate);
        	System.out.println("In isAfterDate method ......check Date:" + checkDate);
        	*/
        	if(benMarkDate.isAfter(checkDate)) {
        		return true;
        	}
    	}
    	
    	return false;
    	
    }
    
    public static boolean isBeforeDate(String benMarkDateString, String checkDateString) {
    	if((benMarkDateString.length() != 0) && (checkDateString.length() != 0)) {
    		LocalDate benMarkDate = LocalDate.parse(benMarkDateString, dateTimeFormatterRaw);
        	LocalDate checkDate = LocalDate.parse(checkDateString, dateTimeFormatterRaw);
        	/*
        	System.out.println("In isBefore method ......original Date:" + benMarkDate);
        	System.out.println("In isBefore method ......check Date:" + checkDate);
        	*/
        	if(benMarkDate.isBefore(checkDate)) {
        		return true;
        	}
    	}
    	
    	return false;
    	
    }

}
