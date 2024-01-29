package me.haitmq.spring.mvc.crud.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import me.haitmq.spring.mvc.crud.entity.Donation;



public interface DonationDAO {
	
	public void saveOrUpdate(Donation donation);
	
	public Donation getDontaion(int theId);
	
	public List<Donation> getDonationList();
	
	public void delete(int theId);
	
	public List<Donation> findByPhoneNumber(String phoneNumber);
	
	public List<Donation> findByOrganization(String organization);
	
	public List<Donation> findByCode(String code);
	
	public List<Donation> findByStatus(String status);
	
	public List<Donation> findByPhoneNumberOrOrganizationOrCode(String searchString);

	public Page<Donation> findAll(Pageable pageable);
	
	public long countDonations();
	

}
