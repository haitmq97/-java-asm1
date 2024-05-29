package me.haitmq.spring.mvc.crud.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import me.haitmq.spring.mvc.crud.dao.DonationDAO;
import me.haitmq.spring.mvc.crud.dao.UserDonationDAO;
import me.haitmq.spring.mvc.crud.entity.Donation;

import me.haitmq.spring.mvc.crud.utils.*;
import me.haitmq.spring.mvc.crud.entity.status.DonationStatus;

@Service
public class DonationServiceImpl implements DonationService {

	private static final Logger log = LoggerFactory.getLogger(DonationService.class);

	@Autowired
	private DonationDAO donationDAO;

	@Autowired
	private UserDonationService userDonationService;

	@Autowired
	private UserDonationDAO userDonationDAO;

	// save/update donation obj
	
	@Override
	public boolean isAbleToUpdate(Donation donation) {
		if (donation.getStatus() != DonationStatus.END) {
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public void saveOrUpdate(Donation donation) {
		
		// nếu donation là đối tượng mới thì đặt các giá trị ban đầu (created date, status, showing status)
		if (donation.getCreatedDate() == null) {
			donation.setCreatedDate(Time.getCurrentDateTimeRaw());
			donation.setStatus(DonationStatus.NEW);
			donation.setShowing(true);
		}
		
		donationDAO.saveOrUpdate(donation);

	}

	// update donaton obj

	@Override
	@Transactional
	public void addMoneyFromUserDonationToDonation(Long moneyAmount, int donationId) {
		Donation donation = donationDAO.getDontaion(donationId);
		donation.setMoney(donation.getMoney() + moneyAmount);
		donationDAO.saveOrUpdate(donation);

	}

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
				donation.setShowing(donation.getShowing() == true ? false : true);
				donationDAO.saveOrUpdate(donation);
			}

		} catch (Exception e) {
			// log.error("DonationService ERROR - changeDonationShowingStatus(): ", e);
		}

	}

	@Override
	@Transactional
	public void updateAllMoneyUserDonationtoDonation(int donationId) {
		try {
			Donation donation = donationDAO.getDontaion(donationId);
			donation.setMoney(userDonationService.getTotalMoneyByDonationId(donationId));
		} catch (Exception e) {
			// log.error("DonationService ERROR - updateAllMoneyUserDonationtoDonation(): ",
			// e);
		}

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
	// get donation list (pageable)


	@Override
	@Transactional
	public Page<Donation> findByPhoneNumberOrOrganizationOrCodeOrStatus(String searchingValue, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page - 1, size);
		return donationDAO.findByPhoneNumberOrOrganizationOrCodeOrStatus(searchingValue, pageRequest);
	}

	@Override
	@Transactional
	public Page<Donation> findAll(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page - 1, size);
		return donationDAO.findAll(pageRequest);
	}

	

	////////////////////////////

	@Override
	@Transactional
	public void addMoneyToDonation(int theId, long amount) {
		Donation donation = donationDAO.getDontaion(theId);
		donation.setMoney(donation.getMoney() + amount);
		donationDAO.saveOrUpdate(donation);
	}

	public void updateDonationMoneyByUserDonation(int theId) {
		Donation donation = donationDAO.getDontaion(theId);

		donation.setMoney(userDonationDAO.getTotalMoneyByDonationId(theId));
		donationDAO.saveOrUpdate(donation);
	}

	public void updateAllDonationMoney() {
		List<Donation> donations = donationDAO.getDonationList();
		for (Donation donation : donations) {
			updateDonationMoneyByUserDonation(donation.getId());
			donationDAO.saveOrUpdate(donation);
		}
	}

	@Override
	public boolean isAbleToAutoDonatingStatus(Donation donation) {

		boolean condition1 = !Time.isBeforeDate(donation.getStartDate(), Time.getCurrentDateTimeRaw());
		boolean condition2 = donation.getStatus() == DonationStatus.NEW;

		if (condition1 && condition2) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isAbleToAutoEndStatus(Donation donation) {
		boolean condition1 = Time.isAfterDate(donation.getEndDate(), Time.getCurrentDateTimeRaw());
		boolean condition2 = donation.getStatus() == DonationStatus.DONATING;
		
		if (condition1 && condition2) {
			return true;
		}
		return false;
	}

	@Override
	public void autoChangeToDonatingStatus(Donation donation) {

	}

	@Override
	public void autoChangeToEndStatus(Donation donation) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public void autoUpdateStatus(Donation donation) {
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
	public void autoUpdateStatusALL() {
		List<Donation> dList = donationDAO.getDonationList();

		for (Donation donation : dList) {
			autoUpdateStatus(donation);
		}

	}

}
