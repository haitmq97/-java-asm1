package me.haitmq.spring.mvc.crud.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import me.haitmq.spring.mvc.crud.common.LoginUser;
import me.haitmq.spring.mvc.crud.dao.UserDAO;
import me.haitmq.spring.mvc.crud.entity.User;
import me.haitmq.spring.mvc.crud.service.UserService;

import org.springframework.util.StringUtils;

public class IsPasswordCorrectedConstraintsValidator implements ConstraintValidator<IsPasswordCorrected, String>{
	
	@Autowired
    private UserService userService;

    @Override
    public void initialize(IsPasswordCorrected constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        // Lấy đối tượng LoginUser để lấy userNameOrEmail
        LoginUser loginUser = (LoginUser) ( context).getConstraintValidatorPayload();
        if (loginUser == null || password == null || password.isEmpty()) {
            return false;
        }
        User user = userService.getUserByUserNameOrEmail(loginUser.getUserNameOrEmail());
        return user != null && user.getPassword().equals(password); // Giả sử mật khẩu được lưu dưới dạng plain text
    }
    

}
