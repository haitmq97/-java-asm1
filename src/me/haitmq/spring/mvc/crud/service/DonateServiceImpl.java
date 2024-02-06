package me.haitmq.spring.mvc.crud.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.haitmq.spring.mvc.crud.dao.DonationDAO;
import me.haitmq.spring.mvc.crud.dao.DonateDAO;
import me.haitmq.spring.mvc.crud.entity.Donate;
import me.haitmq.spring.mvc.crud.utils.Time;

@Service
public class DonateServiceImpl implements DonateService {
	
	
	@Autowired
	private DonateDAO donateDAO;

	@Override
	@Transactional
	public void save(Donate donate) {
		if(donate.getCreated()==null) {
			donate.setCreated(Time.getCurrentDateTime());
		}
		
		if(donate.getStatus() != 4) {
			donateDAO.save(donate);
		}
		/*
		if(donate.getStatus()==null) {
			donate.setStatus(0);;
		}
		*/
		
	}
	
	@Override
	@Transactional
	public void update(Donate donate) {
		donate.setStatus(1);
		donateDAO.update(donate);
	}
	

	@Override
	@Transactional
	public Donate getDonate(int theId) {
		return donateDAO.getDonate(theId);
	}

	@Override
	@Transactional
	public List<Donate> getDonates() {
		return donateDAO.getDonates();
	}

	@Override
	@Transactional
	public void delete(int theId) {
		donateDAO.delete(theId);

	}

	@Override
	@Transactional
	public List<Donate> getDonateByDonationId(int theId) {
		return donateDAO.getDonateByDonationId(theId);
	}

	@Override
	@Transactional
	public List<Donate> getDonateByUserId(int theId) {
		return donateDAO.getDonateByUserId(theId);
	}

	
	
	
	

}
