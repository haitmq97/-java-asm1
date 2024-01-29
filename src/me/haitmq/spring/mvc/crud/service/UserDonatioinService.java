package me.haitmq.spring.mvc.crud.service;

import java.util.List;

import me.haitmq.spring.mvc.crud.entity.UserDonation;

public interface UserDonatioinService {
	
	public void saveOrUpdate(UserDonation userDonation);
	
	public UserDonation getUserDonation(int theId);
	
	public List<UserDonation> getUserDonations();
	
	public void delete(int theId);
	
	public List<UserDonation> getUserByDonationId(int theId);
	
	public List<UserDonation> getDonationByUserId(int theId);
	
}
