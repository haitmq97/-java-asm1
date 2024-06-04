package me.haitmq.spring.mvc.crud.validation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Validator;

import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.DependsOn;

import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.service.DonationService;
import me.haitmq.spring.mvc.crud.service.DonationServiceImpl;


public class UniquedDonationCodeConstraintValidator2 implements ConstraintValidator<UniquedDonationCode2, String>{

	
	private static final Logger log = LoggerFactory.getLogger(UniquedDonationCodeConstraintValidator2.class);

	@Autowired
	private DonationService donationService;

    
    public UniquedDonationCodeConstraintValidator2(DonationService donationService) {
        this.donationService = donationService;
    }

    public UniquedDonationCodeConstraintValidator2() {
    }


	@Override
	public void initialize(UniquedDonationCode2 uniquedDonationCode2) {

	}

	@Override
	public boolean isValid(String theCode, ConstraintValidatorContext constraintValidatorContext) {
		
		
		// logic check 
		// check if code is null or empty
		 if (theCode == null || theCode.isEmpty()) {
	            return true;
	        }
		// get donation list
		 
		 
		 List<Donation> donationList = donationService.getDonationList();
		 log.info("................................................................the code: " + theCode);
		 log.info("................................................................the list: " + donationList.toString());
		 System.out.println("................................................................the code: " + theCode);
		 System.out.println("................................................................the list: " + donationList.toString());
		 
		 // check if code matches donation's code in list
		 return donationList.stream().noneMatch(donation -> donation.getCode().equals(theCode));
	}

	

} 
