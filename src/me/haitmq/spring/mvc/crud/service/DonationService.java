package me.haitmq.spring.mvc.crud.service;

import java.util.List;

import org.springframework.data.domain.Page;

import me.haitmq.spring.mvc.crud.entity.Donation;


public interface DonationService {
	
	public void saveOrUpdate(Donation donation);
	
	public Donation getDonation(int theId);
	
	public List<Donation> getDonationList();
	
	public void delete(int theId);
	
	public List<Donation> findByPhoneNumber(String phoneNumber);
	
	public List<Donation> findByOrganization(String organization);
	
	public List<Donation> findByCode(String code);
	
	public List<Donation> findByStatus(String status);
	
	
	public List<Donation> findByPhoneNumberOrOrganizationOrCode(String searchString);
	
	
	// phan trang
	public Page<Donation> getPaginatedData(int page, int size);

}
