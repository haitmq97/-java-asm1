package me.haitmq.spring.mvc.crud.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;


import me.haitmq.spring.mvc.crud.dao.UserDonationDAO;
import me.haitmq.spring.mvc.crud.entity.UserDonation;
import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.entity.User;
import me.haitmq.spring.mvc.crud.utils.Time;
import me.haitmq.spring.mvc.crud.entity.status.DonationStatus;
import me.haitmq.spring.mvc.crud.entity.status.UserDonationStatus;
import me.haitmq.spring.mvc.crud.entity.status.UserStatus;

@Service
public class UserDonationServiceImpl implements UserDonationService {
	
	private static final Logger log = LoggerFactory.getLogger(UserDonationService.class);
	
	@Autowired
	private UserDonationDAO userDonationDAO;
	
	@Autowired
	private DonationService donationService;
	
	@Autowired
	private UserService userService;

	@Override
	@Transactional
	public void save(UserDonation userDonation) {
		UserStatus userStatus = userDonation.getUser().getStatus();
		DonationStatus donationStatus = userDonation.getDonation().getStatus();
		
		if(isAbletoUserDonation(userDonation.getUser(), userDonation.getDonation())) {
			if(userDonation.getCreatedDate()==null) {
				userDonation.setCreatedDate(Time.getCurrentDateTime());
				userDonation.setStatus(UserDonationStatus.WAITING);
				userDonation.setShowing(true);
			}
			userDonationDAO.save(userDonation);
		}
		
		
	}
	
	@Override
	@Transactional
	public void save(UserDonation userDonation, int userId, int donationId) {
		try {
			userDonation.setUser(userService.getUser(userId));
			userDonation.setDonation(donationService.getDonation(donationId));
			userDonation.setShowing(true);
			userDonationDAO.save(userDonation);
		} catch (Exception e) {
			//log.error("UserDonationService ERROR - save(UserDonation userDonation, int userId, int donationId): ", e);
			//e.printStackTrace();
		}
		
	}
	
	
	
	@Override
	@Transactional
	public void update(UserDonation userDonation) {
		if(isAbletoUserDonation(userDonation.getUser(), userDonation.getDonation())) {
			Donation donation = donationService.getDonation(userDonation.getDonation().getId());
			donation.setMoney(donation.getMoney()+userDonation.getMoney());
			donationService.saveOrUpdate(donation);
			userDonationDAO.update(userDonation);
			
		}
		
		
	}
	
	
	
	

	@Override
	@Transactional
	public void changeUserDonationShowingStatus(int userDonationid) {
		UserDonation userDonation = userDonationDAO.getUserDonation(userDonationid);
		userDonation.setShowing(false);
		userDonationDAO.update(userDonation);
		
	}

	@Override
	@Transactional
	public UserDonation getUserDonation(int theId) {
		return userDonationDAO.getUserDonation(theId);
	}

	@Override
	@Transactional
	public List<UserDonation> getUserDonations() {
		return userDonationDAO.getAllUserDonations();
	}

	@Override
	@Transactional
	public void delete(int theId) {
		userDonationDAO.delete(theId);

	}

	@Override
	@Transactional
	public List<UserDonation> getUserDonationByDonationId(int theId) {
		return userDonationDAO.getUserDonationByDonationId(theId);
	}

	@Override
	@Transactional
	public List<UserDonation> getUserDonationByUserId(int theId) {
		return userDonationDAO.getUserDonationByUserId(theId);
	}

	

	@Override
	@Transactional
	public Page<UserDonation> findAll(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page-1, size);
		return userDonationDAO.findAll( pageRequest);
	}

	@Override
	@Transactional
	public Page<UserDonation> findAllSortByStatus(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		return userDonationDAO.findAllSortByStatus(pageRequest);
	}

	@Override
	@Transactional
	public Page<UserDonation> findAllSortByCreatedDate(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		return userDonationDAO.findAllSortByCreatedDate(pageRequest);
	}

	@Override
	@Transactional
	public Page<UserDonation> findAllSortByStatusByCreatedDate(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page-1, size);
		return userDonationDAO.findAllSortByStatusByCreatedDate(pageRequest);
	}

	@Override
	@Transactional
	public Page<UserDonation> findByUserId(int userId, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		return userDonationDAO.findByUserId(userId, pageRequest);
	}

	@Override
	@Transactional
	public Page<UserDonation> findByDonationId(int donationId, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		return userDonationDAO.findByDonationId(donationId, pageRequest);
	}

	@Override
	@Transactional
	public boolean isAbletoUserDonation(User user, Donation donation) {
		System.out.println("===================> in isable: user status: " + user.getStatus());
		System.out.println("===================> in isable: donation status: " + donation.getStatus());
		if(user.getStatus() == UserStatus.ACTIVE && donation.getStatus() == DonationStatus.DONATING) {
			System.out.println("===================> in isable: (true)");
			return true;
		}
		System.out.println("===================> in isable: (false)");
		return false;
	}

	@Override
	@Transactional
	public void userDonationComfirm(int userDonationId) {
		UserDonation userDonation = userDonationDAO.getUserDonation(userDonationId);
		userDonation.setStatus(UserDonationStatus.CONFIRMED);
		donationService.addMoneyFromUserDonationToDonation(userDonation.getMoney(), userDonation.getDonation().getId());
		userDonationDAO.update(userDonation);
	}
	
	
	

	

	
	/////////////////////////////////////////
	
	
	
	@Override
	@Transactional
	public List<UserDonation> getDonteListByDonationId(int theId) {
		try {
			return userDonationDAO.getUserDonationListByDonationId(theId);
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
		
	}
	
	@Override
	@Transactional
	public Page<UserDonation> findByUserNameOrDonationCodeSortByStatusByCreatedDate(String searchingValue,int page,int size) {
		
		PageRequest pageRequest = PageRequest.of(page-1, size);
		return userDonationDAO.findByUserNameOrDonationCodeSortByStatusByCreatedDate(searchingValue, pageRequest);
	}
	
	
	

	@Override
	public Long getTotalMoneyByDonationId(int donationId) {
		try {
			return userDonationDAO.getTotalMoneyByDonationId(donationId);
		} catch (Exception e) {
			//log.error("UserDonationService ERROR - getTotalMoneyByDonationId(): ", e);
			return null;
		}
	}

	@Override
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

	
	
	
	@Override
	@Transactional
	public void changeUserDonationStatus(int theId, UserDonationStatus status) {
		try {
			UserDonation userDonation= userDonationDAO.getUserDonation(theId);
			
			if(status == UserDonationStatus.CANCELED) {
				userDonation.setShowing(false);
			} else {
				donationService.addMoneyToDonation(userDonation.getDonation().getId(),userDonation.getMoney());
			}
			userDonation.setStatus(status);
			userDonationDAO.saveOrUpdate(userDonation);
		} catch (Exception e) {
			//log.error("DonationService ERROR - changeDonationStatus(): ", e);
		}
	}
	


}
