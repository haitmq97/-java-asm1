package me.haitmq.spring.mvc.crud.service;



import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import me.haitmq.spring.mvc.crud.dao.UserDonationDAO;
import me.haitmq.spring.mvc.crud.dao.DonationDAO;
import me.haitmq.spring.mvc.crud.entity.Donation;

import me.haitmq.spring.mvc.crud.utils.*;
import me.haitmq.spring.mvc.crud.utils.status.DonationStatus;


@Service
public class DonationServiceImpl implements DonationService {

	private static final Logger log = LoggerFactory.getLogger(DonationService.class);
	
	@Autowired
	private DonationDAO donationDAO;
	
	@Autowired
	private UserDonationService userDonationService;
	
	
	// save donation obj
	
	@Override
	@Transactional
	public void saveOrUpdate(Donation donation) {
		System.out.println("================>> donation service iml");
		System.out.println(donation);
		/*
		 * try { if(donation.getCreatedDate().isEmpty()) {
		 * donation.setCreatedDate(Time.getCurrentDateTime()); donation.setStatus(0); }
		 * System.out.println("================>> donation service iml: test 1");
		 * 
		 * donationDAO.saveOrUpdate(donation); } catch (Exception e) {
		 * //log.error("DonationService ERROR - saveOrUpdate(): ", e);
		 * 
		 * System.out.println("================>> donation service iml: have error"); }
		 */
		if(donation.getCreatedDate() == null) {
			donation.setCreatedDate(Time.getCurrentDateTime());
			donation.setStatus(DonationStatus.NEW);
		}
		System.out.println("================>> donation service iml: test 1");

		donationDAO.saveOrUpdate(donation);
		
		System.out.println("================>> donation service iml finish");

	}
	
	// update donaton obj
	
	@Override
	@Transactional
	public void addMoneyFromUserDonationToDonation(Long moneyAmount, int donationId) {
		Donation donation = donationDAO.getDontaion(donationId);
		donation.setMoney(donation.getMoney()+moneyAmount);
		donationDAO.saveOrUpdate(donation);
		
	}
	
	@Override
	public void changeDonationStatus(DonationStatus status, int donationId) {
		 
		/*
		try {
			
			switch (status) {
			case 0: 
				donation.setStatus(status);
				break;
			case 1:
				donation.setStatus(status);
				break;
			case 2:
				donation.setStatus(status);
				break;
			case 3:
				donation.setStatus(status);
				break;	
			}	
		
		} catch (IllegalArgumentException e) {
			log.error("DonationService ERROR - changeDonationStatus(): ", e);
		}
		
		*/
		try {
			Donation donation = donationDAO.getDontaion(donationId);
			donation.setStatus(status);
			donationDAO.saveOrUpdate(donation);
		} catch (Exception e) {
			//log.error("DonationService ERROR - changeDonationStatus(): ", e);
		}
		
	}

	@Override
	public void changeDonationShowingStatus(int donationId) {
		try {
			Donation donation = donationDAO.getDontaion(donationId);
			donation.setShowing(donation.getShowing() ==  true? false: true);
		} catch (Exception e) {
			//log.error("DonationService ERROR - changeDonationShowingStatus(): ", e);
		}
		
	}

	@Override
	public void updateAllMoneyUserDonationtoDonation(int donationId) {
		try {
			Donation donation = donationDAO.getDontaion(donationId);
			donation.setMoney(userDonationService.getTotalMoneyByDonationId(donationId));
		} catch (Exception e) {
			//log.error("DonationService ERROR - updateAllMoneyUserDonationtoDonation(): ", e);
		}
		
	}
	
	
	// delete donation obj
	
	// kiem tra status truoc khi xoa
	


	private boolean isAbleToDelete(int theId) {
		Donation donation = getDonation(theId);
		if(donation.getStatus() == 0) {
			return true;
		}
		return false;
	}
	
	@Override
	@Transactional
	public void delete(int theId) {
		if(isAbleToDelete(theId)) {
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
	
	/*
	@Override
	@Transactional
	public List<Donation> findByPhoneNumber(String phoneNumber) {
		return donationDAO.findByPhoneNumber(phoneNumber);
	}

	@Override
	@Transactional
	public List<Donation> findByOrganization(String organization) {
		return donationDAO.findByOrganization(organization);
	}

	@Override
	@Transactional
	public List<Donation> findByCode(String code) {
		return donationDAO.findByCode(code);
	}
	
	@Override
	@Transactional
	public List<Donation> findByStatus(String status) {
		return donationDAO.findByStatus(status);
	}

	@Override
	@Transactional
	public List<Donation> findByPhoneNumberOrOrganizationOrCodeOrStatus(String searchString) {
		return donationDAO.findByPhoneNumberOrOrganizationOrCodeOrStatus(searchString);
	}
	
	*/
	
	// get donation list (pageable)
	
	
	@Override
	@Transactional
	public Page<Donation> findByPhoneNumber(String phoneNumber, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		return donationDAO.findByPhoneNumber(phoneNumber, pageRequest);
	}

	@Override
	@Transactional
	public Page<Donation> findByOrganization(String organization, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		return donationDAO.findByOrganization(organization, pageRequest);
	}

	@Override
	@Transactional
	public Page<Donation> findByCode(String code, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		return donationDAO.findByCode(code, pageRequest);
	}

	@Override
	@Transactional
	public Page<Donation> findByStatus(String status, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		return donationDAO.findByStatus(status, pageRequest);
	}

	@Override
	@Transactional
	public Page<Donation> findByPhoneNumberOrOrganizationOrCodeOrStatus(String searchingValue, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page-1, size);
		return donationDAO.findByPhoneNumberOrOrganizationOrCodeOrStatus(searchingValue, pageRequest);
	}

	@Override
	@Transactional
	public Page<Donation> findAll(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page-1, size);
		return donationDAO.findAll( pageRequest);
	}
	

	@Override
	@Transactional
	public Page<Donation> findByPhoneNumberOrOrganizationOrCodeOrStatus2(String searchingValue, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		return donationDAO.findByPhoneNumberOrOrganizationOrCodeOrStatus2(searchingValue, pageRequest);
	}

	




	


	
	
	/*



	@Override
	@Transactional
	public Page<Donation> getPaginatedData(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
        return donationDAO.findAll(pageRequest);
	}
	
	
	private boolean isAbleToUserDonation(int theId) {
		Donation donation = getDonation(theId);
		if(donation.getStatus() == 1) {
			return true;
		}
		return false;
	}
	*/
	
	////////////////////////////
	
	


	
	
	
}
