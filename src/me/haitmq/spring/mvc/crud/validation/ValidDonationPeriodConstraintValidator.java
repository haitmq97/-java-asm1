package me.haitmq.spring.mvc.crud.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import me.haitmq.spring.mvc.crud.common.InitDonation;
import me.haitmq.spring.mvc.crud.utils.Time;

public class ValidDonationPeriodConstraintValidator implements ConstraintValidator<ValidDonationPeriod, InitDonation>{

	@Override
	public void initialize(ValidDonationPeriod constraintAnnotation) {

	}

	@Override
	public boolean isValid(InitDonation donation, ConstraintValidatorContext arg1) {
		if((donation.getStartDate().length() ==0) || (donation.getEndDate().length() ==0)) {
			// handled it on fields
			return true;
		}
		
		if(Time.isBeforeDate(donation.getEndDate(), donation.getStartDate())) {
			return false;
		}
		
		return true;
	}
	
	

}
