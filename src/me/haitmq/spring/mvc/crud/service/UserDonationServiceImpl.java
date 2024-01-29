package me.haitmq.spring.mvc.crud.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.haitmq.spring.mvc.crud.dao.DonationDAO;
import me.haitmq.spring.mvc.crud.dao.UserDonationDAO;
import me.haitmq.spring.mvc.crud.entity.UserDonation;
import me.haitmq.spring.mvc.crud.utils.Time;

@Service
public class UserDonationServiceImpl implements UserDonatioinService {
	
	
	@Autowired
	private UserDonationDAO userDonationDAO;

	@Override
	@Transactional
	public void saveOrUpdate(UserDonation userDonation) {
		if(userDonation.getCreated()==null) {
			userDonation.setCreated(Time.getCurrentDateTime());
		}
		userDonationDAO.saveOrUpdate(userDonation);

	}

	@Override
	@Transactional
	public UserDonation getUserDonation(int theId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<UserDonation> getUserDonations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void delete(int theId) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public List<UserDonation> getUserByDonationId(int theId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<UserDonation> getDonationByUserId(int theId) {
		// TODO Auto-generated method stub
		return null;
	}

}
