package me.haitmq.spring.mvc.crud.controller;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.mchange.v2.c3p0.test.ConnectionDispersionTest;

import me.haitmq.spring.mvc.crud.config.ApplicationContextProvider;
import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.service.DonationService;
import me.haitmq.spring.mvc.crud.utils.Time;

@Component
public class Test {
	


	public static void main(String[] args) {
		
		
		
	/*
		String regexString = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	
	
	String regexEmail = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	String emailString ="haitran@gmail.com";
	
	System.out.println(Pattern.compile(regexString).matcher(emailString).matches());
	System.out.println(Pattern.compile(regexEmail)
      .matcher(emailString)
      .matches());
	*/
	String currentDayString = Time.getCurrentDateTimeRaw();
	
	String checkDayString = "2024-01-10";
	
	System.out.println("currentDate: " + currentDayString);
	System.out.println("checkDate: " + checkDayString);
	
	System.out.println("is currentDate after checkDate: " + Time.isAfterDate(currentDayString, checkDayString));
	
	System.out.println("is currentDate before checkDate: " + Time.isBeforeDate(currentDayString, checkDayString));

	}
	


}
