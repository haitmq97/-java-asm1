package me.haitmq.spring.mvc.crud.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import me.haitmq.spring.mvc.crud.entity.Donation;

public interface DonationDAO {

	public void saveOrUpdate(Donation donation);

	public Donation getDontaion(int theId);

	public List<Donation> getDonationList();

	public void delete(int theId);

	/*
	 * public Page<Donation> findByPhoneNumber(String phoneNumber, Pageable
	 * pageable);
	 * 
	 * public Page<Donation> findByOrganization(String organization, Pageable
	 * pageable);
	 * 
	 * public Page<Donation> findByCode(String code, Pageable pageable);
	 * 
	 * public Page<Donation> findByStatus(String status, Pageable pageable);
	 * 
	 * public Page<Donation> findByPhoneNumberOrOrganizationOrCodeOrStatus(String
	 * searchString, Pageable pageable);
	 * 
	 * public Page<Donation> findAll(Pageable pageable);
	 * 
	 * public long countDonations();
	 */

	public long countDonationsByQuery(String theQueryString);

	public Page<Donation> findByQuery(String theQueryString, Pageable pageable);
	
	public Page<Donation> findByQuery(String theQueryString, String searchingValue, Pageable pageable);
	
	public Page<Donation> findByPhoneNumber(String phoneNumber, Pageable pageable);

	public Page<Donation> findByOrganization(String organization, Pageable pageable);

	public Page<Donation> findByCode(String code, Pageable pageable);

	public Page<Donation> findByStatus(String status, Pageable pageable);

	public Page<Donation> findByPhoneNumberOrOrganizationOrCodeOrStatus(String searchString, Pageable pageable);

	public Page<Donation> findAll(Pageable pageable);

}
