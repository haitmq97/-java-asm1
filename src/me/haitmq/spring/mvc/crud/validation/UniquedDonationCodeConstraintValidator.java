package me.haitmq.spring.mvc.crud.validation;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import me.haitmq.spring.mvc.crud.common.InitDonation;
import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.service.DonationService;

@Component
public class UniquedDonationCodeConstraintValidator implements ConstraintValidator<UniquedDonationCode, InitDonation> {

	private static final Logger log = LoggerFactory.getLogger(UniquedDonationCodeConstraintValidator.class);

	@Autowired
	private DonationService donationService;

	@Override
	public void initialize(UniquedDonationCode uniquedDonationCode2) {

	}

	@Override
	public boolean isValid(InitDonation theDonation, ConstraintValidatorContext constraintValidatorContext) {

		// logic check
		// check if code is null or empty
		if (theDonation.getCode() == null || theDonation.getCode().isEmpty()) {
			return true;
		}
		// get donation list

		List<Donation> donationList = donationService.getDonationList();
		
		if(theDonation.getId()==0) {
			return donationList.stream().noneMatch(donation-> donation.getCode().equals(theDonation.getCode()));
		} else {
			boolean condition1= donationList
					.stream().filter(donation->donation.getId()==theDonation.getId())
					.anyMatch(donation->donation.getCode().equals(theDonation.getCode()));
			boolean condition2 =donationList
					.stream().filter(donation->donation.getId()!=theDonation.getId())
					.noneMatch(donation-> donation.getCode().equals(theDonation.getCode()));
			if(condition1||condition2) {
				return true;
			}
		}
		
	
		
		return false;
	}
}