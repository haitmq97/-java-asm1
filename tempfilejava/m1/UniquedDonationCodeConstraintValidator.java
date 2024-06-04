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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.DependsOn;

import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.service.DonationService;
import me.haitmq.spring.mvc.crud.service.DonationServiceImpl;

@Component
public class UniquedDonationCodeConstraintValidator
		implements ConstraintValidator<UniquedDonationCode, String>, ApplicationContextAware {
	/*
	 * Tại sao các trường autowired (ví dụ service) trở thành null (hoặc yêu cầu
	 * default constructor) dù đã kiểm tra xác thực thành công (Lỗi xẩy ra khi cố
	 * gắng thao tác 1 lớp enity với service Các lớp constraint validator không do
	 * spring quản lý?
	 * 
	 */

	private static final Logger log = LoggerFactory.getLogger(UniquedDonationCodeConstraintValidator.class);

	@Autowired
	@Qualifier("donationServiceImpl")
	private DonationService donationService;

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		UniquedDonationCodeConstraintValidator.applicationContext = applicationContext;
	}

	@Override
	public void initialize(UniquedDonationCode constraintAnnotation) {
		this.donationService = applicationContext.getBean(DonationService.class);
	}

	@Override
	public boolean isValid(String theCode, ConstraintValidatorContext context) {
		if (theCode == null || theCode.isEmpty()) {
			return true;
		}

		List<Donation> donationList = donationService.getDonationList();
		log.info("................................................................the code: " + theCode);
		log.info(
				"................................................................the list: " + donationList.toString());

		return donationList.stream().noneMatch(donation -> donation.getCode().equals(theCode));
	}

}

/*
 * @Component
 * 
 * @DependsOn("donationService") public class
 * UniquedDonationCodeConstraintValidator implements
 * ConstraintValidator<UniquedDonationCode, String> {
 * 
 * @Autowired private final DonationService donationService;
 * 
 * 
 * public UniquedDonationCodeConstraintValidator(DonationService
 * donationService) { this.donationService = donationService; }
 * 
 * @Override public boolean isValid(String theCode, ConstraintValidatorContext
 * context) { // logic check // check if code is null or empty if (theCode ==
 * null || theCode.isEmpty()) { return true; } // get donation list
 * List<Donation> donationList = donationService.getDonationList(); // check if
 * code matches donation's code in list return
 * donationList.stream().noneMatch(donation ->
 * donation.getCode().equals(theCode)); }
 * 
 * }
 */