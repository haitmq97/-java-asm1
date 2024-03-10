package me.haitmq.spring.mvc.crud.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;


import me.haitmq.spring.mvc.crud.dao.DonateDAO;
import me.haitmq.spring.mvc.crud.entity.Donate;
import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.entity.User;
import me.haitmq.spring.mvc.crud.utils.Time;

@Service
public class DonateServiceImpl implements DonateService {
	
	private static final Logger log = LoggerFactory.getLogger(DonateService.class);
	
	@Autowired
	private DonateDAO donateDAO;
	
	@Autowired
	private DonationService donationService;
	
	@Autowired
	private UserService userService;

	@Override
	@Transactional
	public void save(Donate donate) {
		int userStatus = donate.getUser().getStatus();
		int donationStatus = donate.getDonation().getStatus();
		
		if(isAbletoDonate(donate.getUser(), donate.getDonation())) {
			if(donate.getCreatedDate()==null) {
				donate.setCreatedDate(Time.getCurrentDateTime());
			}
			
			donateDAO.save(donate);
		}
		
		
	}
	
	@Override
	@Transactional
	public void save(Donate donate, int userId, int donationId) {
		try {
			donate.setUser(userService.getUser(userId));
			donate.setDonation(donationService.getDonation(donationId));
			donateDAO.save(donate);
		} catch (Exception e) {
			//log.error("DonateService ERROR - save(Donate donate, int userId, int donationId): ", e);
			//e.printStackTrace();
		}
		
	}
	
	
	
	@Override
	@Transactional
	public void update(Donate donate) {
		if(isAbletoDonate(donate.getUser(), donate.getDonation())) {
			Donation donation = donationService.getDonation(donate.getDonation().getId());
			donation.setMoney(donation.getMoney()+donate.getMoney());
			donationService.saveOrUpdate(donation);
			donateDAO.update(donate);
			
		}
		
		
	}
	

	@Override
	@Transactional
	public Donate getDonate(int theId) {
		return donateDAO.getDonate(theId);
	}

	@Override
	@Transactional
	public List<Donate> getDonates() {
		return donateDAO.getAllDonates();
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

	

	@Override
	@Transactional
	public Page<Donate> findAll(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		return donateDAO.findAll( pageRequest);
	}

	@Override
	@Transactional
	public Page<Donate> findAllSortByStatus(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		return donateDAO.findAllSortByStatus(pageRequest);
	}

	@Override
	@Transactional
	public Page<Donate> findAllSortByCreatedDate(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		return donateDAO.findAllSortByCreatedDate(pageRequest);
	}

	@Override
	@Transactional
	public Page<Donate> findAllSortByStatusByCreatedDate(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		return donateDAO.findAllSortByStatusByCreatedDate(pageRequest);
	}

	@Override
	@Transactional
	public Page<Donate> findByUserId(int userId, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		return donateDAO.findByUserId(userId, pageRequest);
	}

	@Override
	@Transactional
	public Page<Donate> findByDonationId(int donationId, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		return donateDAO.findByDonationId(donationId, pageRequest);
	}

	@Override
	@Transactional
	public boolean isAbletoDonate(User user, Donation donation) {
		if(user.getStatus() == 1 && donation.getStatus() == 1) {
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public void donateComfirm(int donateId) {
		Donate donate = donateDAO.getDonate(donateId);
		donate.setStatus(1);
		donationService.addMoneyFromDonateToDonation(donate.getMoney(), donate.getDonation().getId());
		donateDAO.update(donate);
	}
	
	
	

	@Override
	public Long getTotalMoneyByDonationId(int donationId) {
		try {
			return donateDAO.getTotalMoneyByDonationId(donationId);
		} catch (Exception e) {
			//log.error("DonateService ERROR - getTotalMoneyByDonationId(): ", e);
			return null;
		}
	}

	@Override
	@Transactional
	public void updateAllMoney() {
		try {
			List<Donation> donations = donationService.getDonationList();
			for (Donation donation: donations) {
				donation.setMoney(getTotalMoneyByDonationId(donation.getId()));
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

	

	
	/////////////////////////////////////////
	
	
	
	@Override
	@Transactional
	public List<Donate> getDonteListByDonationId(int theId) {
		try {
			return donateDAO.getDonteListByDonationId(theId);
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
		
	}
	

}
