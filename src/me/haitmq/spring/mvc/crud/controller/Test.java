package me.haitmq.spring.mvc.crud.controller;

import java.util.regex.Pattern;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v2")
public class Test {

	public static void main(String[] args) {
	String regexString = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	
	
	String regexEmail = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	String emailString ="haitran@gmail.com";
	
	System.out.println(Pattern.compile(regexString).matcher(emailString).matches());
	System.out.println(Pattern.compile(regexEmail)
      .matcher(emailString)
      .matches());

	}

}
