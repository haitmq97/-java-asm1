package me.haitmq.spring.mvc.crud.common;

import javax.validation.constraints.NotBlank;

import me.haitmq.spring.mvc.crud.validation.IsExistedUserNameOrEmail;
import me.haitmq.spring.mvc.crud.validation.ValidUser;

@ValidUser
public class LoginUser {
	
	/* @IsExistedUserNameOrEmail */
	@NotBlank(message = "not blank")
	private String userNameOrEmail;
	
	@NotBlank(message = "not blank")
	private String password;

	public LoginUser() {
		
	}
	
	public LoginUser(String userNameOrEmail, String password) {
		super();
		this.userNameOrEmail = userNameOrEmail;
		this.password = password;
	}

	public String getUserNameOrEmail() {
		return userNameOrEmail;
	}

	public void setUserNameOrEmail(String userNameOrEmail) {
		this.userNameOrEmail = userNameOrEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return super.toString();
	}

	
	
	
	
}
