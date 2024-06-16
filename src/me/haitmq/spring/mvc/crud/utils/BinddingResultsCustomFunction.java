package me.haitmq.spring.mvc.crud.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import me.haitmq.spring.mvc.crud.common.InitDonation;
import me.haitmq.spring.mvc.crud.common.InitUser;
import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.entity.User;
import me.haitmq.spring.mvc.crud.service.DonationService;
import me.haitmq.spring.mvc.crud.service.UserService;
import me.haitmq.spring.mvc.crud.validation.UniquedEmail;
import me.haitmq.spring.mvc.crud.validation.UniquedUserName;

@Component
public class BinddingResultsCustomFunction {
	

	private static final Logger log = LoggerFactory.getLogger(BinddingResultsCustomFunction.class);

	public static boolean isErrorForAddDonation(BindingResult theBindingResult) {
		boolean result = false;
		if (theBindingResult.hasErrors()) {

			theBindingResult.getGlobalErrors().forEach(globalError -> {

				log.error("BinddingResultsCustomFunction - isErrorForAddDonation - error global (validator) {}",
						globalError);

				log.error("BinddingResultsCustomFunction - isErrorForAddDonation - error global (message) {}",
						globalError.getDefaultMessage());

				// insert to field validator instead of class
				// for add donation

				System.out.println("is global error from @UniquedDonationCode:"
						+ globalError.getCode().equals("UniquedDonationCode"));
				if (globalError.getCode().equals("UniquedDonationCode")) {
					theBindingResult.rejectValue("code", "UniquedDonationCode", globalError.getDefaultMessage());
				}

				if (globalError.getCode().equals("ValidDonationPeriod")) {
					theBindingResult.rejectValue("startDate", "ValidDonationPeriod", globalError.getDefaultMessage());
					theBindingResult.rejectValue("endDate", "ValidDonationPeriod", globalError.getDefaultMessage());
				}

			});

			theBindingResult.getFieldErrors().forEach(fieldError -> {
				log.error("BinddingResultsCustomFunction - isErrorForAddDonation - error field (validator) {}",
						fieldError);
				log.error("BinddingResultsCustomFunction - isErrorForAddDonation - error field (message) {}",
						fieldError.getDefaultMessage());
			});

			result = true;
		}

		return result;
	}
	
	
	public static boolean isErrorForUpdateDonation(BindingResult theBindingResult, DonationService donationService ,InitDonation theDonation) {
		boolean result = true;
		
		
		if (theBindingResult.hasErrors()) {
			
			Donation theExistingDonation = donationService.getDonation(theDonation.getId());
		
			
			boolean isCodeNotChanged = theDonation.getCode().equals(theExistingDonation.getCode()); 
			boolean isStartDateNotChanged = theDonation.getStartDate().equals(theExistingDonation.getStartDate());
			boolean isEndDateNotChanged = theDonation.getEndDate().equals(theExistingDonation.getEndDate()) || !Time.isBeforeDate(theDonation.getEndDate(), Time.getCurrentDateTimeRaw());
			/*
			System.out.println("..................................isErrorForUpdateDonation isCodeNotChanged: " + isCodeNotChanged);
			System.out.println("..................................isErrorForUpdateDonation isStartDateNotChanged: " + isStartDateNotChanged);
			System.out.println("..................................isErrorForUpdateDonation isENDDATE not change: " + theDonation.getEndDate().equals(theExistingDonation.getEndDate()));
			System.out.println("..................................isErrorForUpdateDonation isENDDATE in form after  endate in database: " +  Time.isAfterDate(theDonation.getEndDate(), theExistingDonation.getEndDate()));
			System.out.println("..................................isErrorForUpdateDonation isEndDateNotChanged: " + isEndDateNotChanged);
			*/
			result = isCodeNotChanged && isStartDateNotChanged && isEndDateNotChanged;
			
			for(ObjectError globalError: theBindingResult.getGlobalErrors()) {
				
				if (globalError.getCode().equals("UniquedDonationCode") && !isEndDateNotChanged) {
					theBindingResult.rejectValue("code", "UniquedDonationCode", globalError.getDefaultMessage());
				}

				if (globalError.getCode().equals("ValidDonationPeriod")) {
					theBindingResult.rejectValue("startDate", "ValidDonationPeriod", globalError.getDefaultMessage());
					theBindingResult.rejectValue("endDate", "ValidDonationPeriod", globalError.getDefaultMessage());
				}
				
				log.error("BinddingResultsCustomFunction - isErrorForUpdateDonation - error global (validator) {}", globalError);		
				log.error("BinddingResultsCustomFunction - isErrorForUpdateDonation - error global (message) {}", globalError.getDefaultMessage());
				
			}

			for(FieldError fieldError: theBindingResult.getFieldErrors()) {
				// handle logic
				if((fieldError.getCode().equals("UniquedDonationCode")&& isCodeNotChanged) 
						|| (fieldError.getCode().equals("ValidStartDate")&&(fieldError.getField().equals("startDate"))&&(isStartDateNotChanged))
						|| (fieldError.getCode().equals("ValidStartDate")&&(fieldError.getField().equals("endDate"))&&(isEndDateNotChanged))) {
					
				} else {
					result = result && false;
				}
				
				log.error("BinddingResultsCustomFunction - isErrorForUpdateDonation - error field (validator) {}", fieldError);
				log.error("BinddingResultsCustomFunction - isErrorForUpdateDonation - error field (message) {}", fieldError.getDefaultMessage());
			}

		}

		return !result;
	}
	
	
	
	
	public static boolean isErrorForUpdateUser(BindingResult theBindingResult, UserService userService ,InitUser theUser) {
		boolean result = true;
		
		
		if (theBindingResult.hasErrors()) {
			
			User theExistingUser= userService.getUser(theUser.getId());
		
			
			boolean isUserNameNotChanged = theUser.getUserName().equals(theExistingUser.getUserName()); 
			boolean isEmailNotChanged = theUser.getEmail().equals(theExistingUser.getEmail());
			
			result = isUserNameNotChanged && isEmailNotChanged;
			
			for(ObjectError globalError: theBindingResult.getGlobalErrors()) {
				
			
				if (globalError.getCode().equals("UniquedEmail") && !isEmailNotChanged) {
					theBindingResult.rejectValue("email", "UniquedEmail", globalError.getDefaultMessage());
				}
				
				if (globalError.getCode().equals("UniquedUserName") && !isUserNameNotChanged) {
					theBindingResult.rejectValue("userName", "UniquedUserName", globalError.getDefaultMessage());
				}

				log.error("BinddingResultsCustomFunction - isErrorForUpdateUser - error global (validator) {}", globalError);		
				log.error("BinddingResultsCustomFunction - isErrorForUpdateUser - error global (message) {}", globalError.getDefaultMessage());
			}

			for(FieldError fieldError: theBindingResult.getFieldErrors()) {
				// handle logic
				if(fieldError.getCode().equals("UniquedUserName") 
						|| fieldError.getCode().equals("UniquedEmail")) {
					
				} else {
					result = result && false;
				}
				
				log.error("BinddingResultsCustomFunction - isErrorForUpdateUser - error field (validator) {}", fieldError);
				log.error("BinddingResultsCustomFunction - isErrorForUpdateUser - error field (message) {}", fieldError.getDefaultMessage());
			}

		}

		return !result;
	}
	
	
	public static boolean isErrorForAddUser(BindingResult theBindingResult) {
		boolean result = false;
		if (theBindingResult.hasErrors()) {

			for(ObjectError globalError: theBindingResult.getGlobalErrors()) {
				
				
				if (globalError.getCode().equals("UniquedEmail")) {
					theBindingResult.rejectValue("email", "UniquedEmail", globalError.getDefaultMessage());
				}
				
				if (globalError.getCode().equals("UniquedUserName")) {
					theBindingResult.rejectValue("userName", "UniquedUserName", globalError.getDefaultMessage());
				}

				log.error("BinddingResultsCustomFunction - isErrorForAddUser - error global (validator) {}", globalError);		
				log.error("BinddingResultsCustomFunction - isErrorForAddUser - error global (message) {}", globalError.getDefaultMessage());
			}

			theBindingResult.getFieldErrors().forEach(fieldError -> {
				log.error("BinddingResultsCustomFunction - isErrorForAddUser - error field (validator) {}",
						fieldError);
				log.error("BinddingResultsCustomFunction - isErrorForAddUser - error field (message) {}",
						fieldError.getDefaultMessage());
			});

			result = true;
		}

		return result;
	}

}
