package me.haitmq.spring.mvc.crud.validation;



import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import me.haitmq.spring.mvc.crud.common.InitDonation;
import me.haitmq.spring.mvc.crud.common.InitUser;
import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.entity.User;
import me.haitmq.spring.mvc.crud.service.DonationService;
import me.haitmq.spring.mvc.crud.service.UserService;

@Component
public class UniquedEmailConstraintValidator implements ConstraintValidator<UniquedEmail, InitUser> {

	private static final Logger log = LoggerFactory.getLogger(UniquedEmailConstraintValidator.class);

	@Autowired
	private UserService userService;

	@Override
	public void initialize(UniquedEmail uniquedEmail) {

	}

	@Override
	public boolean isValid(InitUser theUser, ConstraintValidatorContext constraintValidatorContext) {

		// logic check
		// check if email is null or empty (handle by @NotBlank)
		if (theUser.getEmail() == null || theUser.getEmail().isEmpty()) {
			return true;
		}
		// get donation list

		List<User> userList = userService.getUserList();
		
		if(theUser.getId()==0) {
			return userList.stream().noneMatch(user-> user.getEmail().equals(theUser.getEmail()));
		} else {
			boolean condition1= userList
					.stream().filter(user->user.getId()==theUser.getId())
					.anyMatch(user->user.getEmail().equals(theUser.getEmail()));
			boolean condition2 =userList
					.stream().filter(user->user.getId()!=theUser.getId())
					.noneMatch(user-> user.getEmail().equals(theUser.getEmail()));
			if(condition1||condition2) {
				return true;
			}
		}
		
	
		
		return false;
	}
}