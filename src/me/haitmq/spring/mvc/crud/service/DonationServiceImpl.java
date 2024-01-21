package me.haitmq.spring.mvc.crud.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.haitmq.spring.mvc.crud.dao.DonationDAO;
import me.haitmq.spring.mvc.crud.entity.Donation;


@Service
public class DonationServiceImpl implements DonationService {

	@Autowired
	private DonationDAO donationDAO;
	
	@Override
	@Transactional
	public void saveOrUpdate(Donation donation) {
		donationDAO.saveOrUpdate(donation);

	}

	@Override
	@Transactional
	public Donation getDonation(int theId) {
		return donationDAO.getDontaion(theId);
	}

	@Override
	@Transactional
	public List<Donation> getDonationList() {
		return donationDAO.getDonationList();
	}

	@Override
	public void delete(int theId) {
		donationDAO.delete(theId);
		
	}

}
