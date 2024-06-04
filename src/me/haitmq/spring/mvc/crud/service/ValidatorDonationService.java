package me.haitmq.spring.mvc.crud.service;

import java.util.List;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import me.haitmq.spring.mvc.crud.dao.DonationDAO;
import me.haitmq.spring.mvc.crud.entity.Donation;

@Service
public class ValidatorDonationService {
	
	@Autowired
	private DonationDAO donationDAO;
	
	@Transactional
	public boolean isUniqueCode(String theCode) {
		List<Donation> listDonations = donationDAO.getDonationList();
		
		
		return listDonations.stream().noneMatch(donation -> donation.getCode().equals(theCode));
	}

}
