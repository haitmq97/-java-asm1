package me.haitmq.spring.mvc.crud.service;

import java.util.List;

import org.springframework.data.domain.Page;

import me.haitmq.spring.mvc.crud.entity.Donation;

public interface DonationService {

	public void saveOrUpdate(Donation donation);

	public Donation getDonation(int theId);

	public List<Donation> getDonationList();

	public void delete(int theId);

	/*
	public List<Donation> findByPhoneNumber(String phoneNumber);

	public List<Donation> findByOrganization(String organization);

	public List<Donation> findByCode(String code);

	public List<Donation> findByStatus(String status);

	public List<Donation> findByPhoneNumberOrOrganizationOrCodeOrStatus(String searchString);

	// public Page<Donation> getPaginatedData(int page, int size);

	 */
	///////////////////////////////////

	public Page<Donation> findByPhoneNumber(String phoneNumber, int page, int size);

	public Page<Donation> findByOrganization(String organization, int page, int size);

	public Page<Donation> findByCode(String code, int page, int size);

	public Page<Donation> findByStatus(String status, int page, int size);

	public Page<Donation> findByPhoneNumberOrOrganizationOrCodeOrStatus(String searchingValue, int page, int size);

	public Page<Donation> findAll(int page, int size);
	
	//////////////////////////////////
	public Page<Donation> findByPhoneNumberOrOrganizationOrCodeOrStatus2(String searchingValue, int page, int size);
	
	public void addMoneyFromDonateToDonation(Long moneyAmount, int donationId);
	
}
