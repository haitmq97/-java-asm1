package me.haitmq.spring.mvc.crud.service;

import java.util.List;

import javax.transaction.Transactional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.TinyBitSet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

import me.haitmq.spring.mvc.crud.dao.DonationDAO;
import me.haitmq.spring.mvc.crud.dao.UserDonationDAO;
import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.entity.UserDonation;
import me.haitmq.spring.mvc.crud.utils.*;
import me.haitmq.spring.mvc.crud.entity.status.DonationStatus;

@Service
public class DonationServiceImpl implements DonationService {
	
	@Autowired
	Validator validator;

	private static final Logger log = LoggerFactory.getLogger(DonationService.class);

	@Autowired
	private DonationDAO donationDAO;

	@Autowired
	private UserDonationService userDonationService;

	@Autowired
	private UserDonationDAO userDonationDAO;

	// get single donation obj

	@Override
	@Transactional
	public Donation getDonation(int theId) {
		return donationDAO.getDontaion(theId);
	}

	// get donation list

	@Override
	@Transactional
	public List<Donation> getDonationList() {
		return donationDAO.getDonationList();
	}
	
	@Override
	@Transactional
	public void saveOrUpdate(Donation donation) {
		donationDAO.saveOrUpdate(donation);
	}
	

	// save donation obj
	@Override
	@Transactional
	public void add(Donation donation) {
		
		//đặt các giá trị ban đầu (created date, status, showing status)
		donation.setCreatedDate(Time.getCurrentDateTimeRaw());
		donation.setStatus(DonationStatus.NEW);
		donation.setShowing(true);
		donationDAO.saveOrUpdate(donation);
	}
	
	// update donaton obj
	
	@Override
	public boolean isAbleToUpdate(Donation donation) {
		if (donation.getStatus() != DonationStatus.CLOSED) {
			return true;
		}
		return false;
	}
	
	@Override
	@Transactional
	public void update(Donation donation) {
		if(isAbleToUpdate(donation)) {
			donationDAO.saveOrUpdate(donation);
		}
	}

	
	

	////////////////////////////
	@Override
	@Transactional
	public void addMoneyFromUserDonationToDonation(long moneyAmount, int donationId) {
		Donation donation = donationDAO.getDontaion(donationId);
		// lấy tiền hiện tại cộng với lượng tiền mới
		donation.setMoney(donation.getMoney() + moneyAmount);
		donationDAO.saveOrUpdate(donation);

	}
	
	/*
	
	@Override
	@Transactional
	public void addMoneyToDonation(int theId, long amount) {
		Donation donation = donationDAO.getDontaion(theId);
		donation.setMoney(donation.getMoney() + amount);
		donationDAO.saveOrUpdate(donation);
	}
	*/
	
	
	@Override
	public boolean isAbleTochangeStatus(DonationStatus newStatus, int donationId) {

		Donation donation = donationDAO.getDontaion(donationId);

		DonationStatus currentDonationStatus = donation.getStatus();

		return DonationStatus.getSTATUS_CHANGE_MAP().get(currentDonationStatus) == newStatus;
	}

	@Override
	@Transactional
	public void changeDonationStatus(DonationStatus newStatus, int donationId) {

		try {

			if (isAbleTochangeStatus(newStatus, donationId)) {

				Donation donation = donationDAO.getDontaion(donationId);
				donation.setStatus(newStatus);
				if(newStatus == DonationStatus.DONATING) {
					donation.setStartDate(Time.getCurrentDateTimeRaw());
				} else if(newStatus == DonationStatus.END) {
					donation.setEndDate(Time.getPreviousDayRaw(Time.getCurrentDateTimeRaw()));
				}
				donationDAO.saveOrUpdate(donation);
			}

		} catch (Exception e) {
			// log.error("DonationService ERROR - changeDonationStatus(): ", e);
		}

	}

	@Override
	public boolean isAbleToShowOff(Donation donation) {
		if (donation.getStatus() == DonationStatus.NEW) {
			return true;
		}
		return false;
	}

	

	@Override
	@Transactional
	public void changeDonationShowingStatus(int donationId) {
		try {

			Donation donation = donationDAO.getDontaion(donationId);
			if (isAbleToShowOff(donation)) {
				donation.setShowing(false);
				donationDAO.saveOrUpdate(donation);
			}
			

		} catch (Exception e) {
			// log.error("DonationService ERROR - changeDonationShowingStatus(): ", e);
		}

	}
	
	

	@Override
	public boolean isAbleToAutoDonatingStatus(Donation donation) {

		boolean condition1 = !Time.isBeforeDate( Time.getCurrentDateTimeRaw(), donation.getStartDate());
		boolean condition2 = donation.getStatus() == DonationStatus.NEW;

		if (condition1 && condition2) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isAbleToAutoEndStatus(Donation donation) {
		boolean condition1 = Time.isAfterDate(Time.getCurrentDateTimeRaw(), donation.getEndDate());
		boolean condition2 = donation.getStatus() == DonationStatus.DONATING;
		System.out.println(".............."+ donation.getId()+" is current after endate: " + condition1);
		
		System.out.println(".............."+ donation.getId()+" status is donating: " + condition2);
		if (condition1 && condition2) {
			return true;
		}
		return false;
	}


	@Override
	@Transactional
	public void autoUpdateStatus(Donation donation) {
		System.out.println("................autouopdateStatus: "+ donation.getStatus() );
		if (isAbleToAutoDonatingStatus(donation)) {
			donation.setStatus(DonationStatus.DONATING);

		}

		if (isAbleToAutoEndStatus(donation)) {
			donation.setStatus(DonationStatus.END);

		}

		donationDAO.saveOrUpdate(donation);

	}

	@Override
	@Scheduled(fixedRate = 300000)
	@Transactional
	public void autoUpdateStatusAll() {
		List<Donation> dList = donationDAO.getDonationList();
		System.out.println("//////////////////// scheduled");
		for (Donation donation : dList) {
			autoUpdateStatus(donation);
		}

	}
	
	@Override
	@Transactional
	public void updateAllMoneyUserDonationtoDonation(int theId) {
		Donation donation = donationDAO.getDontaion(theId);

		donation.setMoney(userDonationDAO.getTotalMoneyByDonationId(theId));
		donationDAO.saveOrUpdate(donation);
	}
	
	/*
	@Override
	@Transactional
	public void updateAllMoneyUserDonationtoDonation(int donationId) {
		try {
			Donation donation = donationDAO.getDontaion(donationId);
			donation.setMoney(userDonationService.getTotalMoneyByDonationId(donationId));
			donationDAO.saveOrUpdate(donation);
		} catch (Exception e) {
			// log.error("DonationService ERROR - updateAllMoneyUserDonationtoDonation(): ",
			// e);
		}

	}
	*/
	
	@Override
	@Transactional
	public void updateAllDonationMoney() {
		List<Donation> donations = donationDAO.getDonationList();
		for (Donation donation : donations) {
			updateAllMoneyUserDonationtoDonation(donation.getId());
			donationDAO.saveOrUpdate(donation);
		}
	}

	
	
	// get donation list (pageable)
	@Override
	@Transactional
	public Page<Donation> findByPhoneNumberOrOrganizationOrCodeOrStatus(String searchingValue, int page, int size) {
		// trừ 1 để page bắt đầu bằng 1
		PageRequest pageRequest = PageRequest.of(page - 1, size);
		return donationDAO.findByPhoneNumberOrOrganizationOrCodeOrStatus(searchingValue, pageRequest);
	}

	@Override
	@Transactional
	public Page<Donation> findAll(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page - 1, size);
		return donationDAO.findAll(pageRequest);
	}

	
	// delete donation obj

	// kiem tra status truoc khi xoa

	private boolean isAbleToDelete(int theId) {
		Donation donation = getDonation(theId);
		if (donation.getStatus() == DonationStatus.NEW) {
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public void delete(int theId) {
		if (isAbleToDelete(theId)) {
			donationDAO.delete(theId);
		}

	}
	
	@Override
	@Transactional
	public void setTotalConfirmedDonate(int donationId) {
		Donation donation = donationDAO.getDontaion(donationId);
		List<UserDonation> userDonations= userDonationDAO.getAllUserDonations()
				.stream()
				.filter(userDonation -> 
					(userDonation.getDonation().getCode().equals(donation.getCode())&&
							(userDonation.getShowing()==true))).toList();
		
		if(!userDonations.isEmpty()) {
			long total = userDonations.stream().count();
			
			donation.setDonationQuantity((int) total);
		}
		
	}
	
	


}
