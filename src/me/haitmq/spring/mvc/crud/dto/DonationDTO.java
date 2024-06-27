package me.haitmq.spring.mvc.crud.dto;

import java.util.List;

import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.entity.UserDonation;

public class DonationDTO {
	private Donation donation;
	
	private List<UserDonation> userDonations;

	public DonationDTO(Donation donation, List<UserDonation> userDonations) {
		this.donation = donation;
		this.userDonations = userDonations;
	}

	public Donation getDonation() {
		return donation;
	}

	public void setDonation(Donation donation) {
		this.donation = donation;
	}

	public List<UserDonation> getUserDonations() {
		return userDonations;
	}

	public void setUserDonations(List<UserDonation> userDonations) {
		this.userDonations = userDonations;
	}


}
