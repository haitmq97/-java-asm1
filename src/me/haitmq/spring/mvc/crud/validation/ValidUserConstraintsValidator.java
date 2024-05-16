package me.haitmq.spring.mvc.crud.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import me.haitmq.spring.mvc.crud.common.LoginUser;
import me.haitmq.spring.mvc.crud.entity.User;
import me.haitmq.spring.mvc.crud.service.UserService;

public class ValidUserConstraintsValidator implements ConstraintValidator<ValidUser, LoginUser> {

	@Autowired
    private UserService userService;
	
	@Override
	public void initialize(ValidUser constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(LoginUser loginUser, ConstraintValidatorContext context) {
		 if (loginUser == null) {
	            return true;
	        }

	        User user = userService.getUserByUserNameOrEmail(loginUser.getUserNameOrEmail());
	        if (user == null || !user.getPassword().equals(loginUser.getPassword())) {
	            return false;
	        }
	        return true;
	}
	
	
}
