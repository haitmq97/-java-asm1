package me.haitmq.spring.mvc.crud.validation;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailFormatConstraintValidator implements ConstraintValidator<EmailFormat, String>{

	
	private String emailRegex;
	
	

	@Override
	public void initialize(EmailFormat theEmailFormat) {
		// TODO Auto-generated method stub
		emailRegex = theEmailFormat.value();
	}

	@Override
	public boolean isValid(String theCode, ConstraintValidatorContext constraintValidatorContext) {
		// TODO Auto-generated method stub
		
		//flag
		boolean result =false;
		
		
		// logic check 
		if (theCode != null) {
			result = Pattern.compile(emailRegex).matcher(theCode).matches();
			
		}
		
		return result;
	}
	
	

	
}
