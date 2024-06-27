package me.haitmq.spring.mvc.crud.dto;

import java.util.List;

import me.haitmq.spring.mvc.crud.entity.User;
import me.haitmq.spring.mvc.crud.entity.UserDonation;

public class UserDTO {
	private User user;
	
	private List<UserDonation> userDonations;

	public UserDTO(User user, List<UserDonation> userDonations) {
		this.user = user;
		this.userDonations = userDonations;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<UserDonation> getUserDonations() {
		return userDonations;
	}

	public void setUserDonations(List<UserDonation> userDonations) {
		this.userDonations = userDonations;
	}
	
	
	
}
