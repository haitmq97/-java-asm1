package me.haitmq.spring.mvc.crud.validation;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberFormatConstraintValidator implements ConstraintValidator<PhoneNumberFormat, String>{

	
	private String phoneNumberRegex;
	
	

	@Override
	public void initialize(PhoneNumberFormat thePhoneNumberFormat) {
		// TODO Auto-generated method stub
		phoneNumberRegex = thePhoneNumberFormat.value();
	}

	@Override
	public boolean isValid(String theCode, ConstraintValidatorContext constraintValidatorContext) {
		// TODO Auto-generated method stub
		
		// logic check 
		
		if(theCode.length()==0) {
			//because this field is not require
			
			return true;
		}
		
		if (theCode != null) {
			return Pattern.compile(phoneNumberRegex).matcher(theCode).matches();
		}
		
		return false;
	}
	
	

	
}
