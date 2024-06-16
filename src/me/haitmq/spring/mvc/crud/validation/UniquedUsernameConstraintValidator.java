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
public class UniquedUsernameConstraintValidator implements ConstraintValidator<UniquedUserName, InitUser> {

	private static final Logger log = LoggerFactory.getLogger(UniquedUsernameConstraintValidator.class);

	@Autowired
	private UserService userService;

	@Override
	public void initialize(UniquedUserName uniquedUserName) {

	}

	@Override
	public boolean isValid(InitUser theUser, ConstraintValidatorContext constraintValidatorContext) {

		// logic check
		// check if username is null or empty (handle by @NotBlank)
		if (theUser.getUserName() == null || theUser.getUserName().isEmpty()) {
			return true;
		}
		// get donation list

		List<User> userList = userService.getUserList();
		
		if(theUser.getId()==0) {
			// for add
			return userList.stream().noneMatch(user-> user.getUserName().equals(theUser.getUserName()));
		} else {
			// for update
			// check is username is not changed
			boolean condition1= userList
					.stream().filter(user->user.getId()==theUser.getId())
					.anyMatch(user->user.getUserName().equals(theUser.getUserName()));
			// if username changed then check is unique
			boolean condition2 =userList
					.stream().filter(user->user.getId()!=theUser.getId())
					.noneMatch(user-> user.getUserName().equals(theUser.getUserName()));
			if(condition1||condition2) {
				return true;
			}
		}
		
	
		
		return false;
	}
}