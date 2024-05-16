package me.haitmq.spring.mvc.crud.validation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import me.haitmq.spring.mvc.crud.dao.UserDAO;
import me.haitmq.spring.mvc.crud.entity.User;
import me.haitmq.spring.mvc.crud.service.UserService;

public class IsExistedConstraintValidator implements ConstraintValidator<IsExistedUserNameOrEmail, String>{

	@Autowired
	private UserService userService;

	@Override
	public boolean isValid(String userNameOrEmail, ConstraintValidatorContext context) {
		if (userNameOrEmail == null || userNameOrEmail.isEmpty()) {
			return false;
		}
		List<User> users = userService.getUserList();
		return users.stream().anyMatch(
				user -> userNameOrEmail.equals(user.getUserName()) || userNameOrEmail.equals(user.getEmail()));
	}

}
