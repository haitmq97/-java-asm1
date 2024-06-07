package me.haitmq.spring.mvc.crud.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.haitmq.spring.mvc.crud.utils.Time;


public class ValidStartDateContraintValidator implements ConstraintValidator<ValidStartDate, String>{
	private static final Logger log = LoggerFactory.getLogger(ValidStartDateContraintValidator.class);

	
	@Override
	public void initialize(ValidStartDate validStartDate) {

	}

	@Override
	public boolean isValid(String startDate, ConstraintValidatorContext constraintValidatorContext) {
		if((startDate == null)||(startDate.length()== 0)) {
			// handled on not blank annotation
			return true;
		}
		
		if(Time.isBeforeDate(startDate, Time.getCurrentDateTimeRaw())) {
			return false;
		}
		return true;
	}
		
		
}
