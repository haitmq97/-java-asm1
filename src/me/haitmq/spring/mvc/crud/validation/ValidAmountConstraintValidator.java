package me.haitmq.spring.mvc.crud.validation;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.haitmq.spring.mvc.crud.utils.Time;

public class ValidAmountConstraintValidator implements ConstraintValidator<ValidAmount, Long>{

	private static final Logger log = LoggerFactory.getLogger(ValidAmountConstraintValidator.class);
	
	@Override
	public void initialize(ValidAmount validAmount) {

	}

	@Override
	public boolean isValid(Long amount, ConstraintValidatorContext constraintValidatorContext) {
		
		if(amount%1000>0) {
			return false;
		}
		
		return true;
	}
	
	

	
}
