package me.haitmq.spring.mvc.crud.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import me.haitmq.spring.mvc.crud.entity.Donation;

public interface DonationDAO {
	/*
	 * Cần định nghĩa
	 CREATE / UPDATE
	  + Create or update a donation obj 
	 
	 READ/GET
	 	+ get one donation obj
	 	+ get donation list (list)
	 	+ Get donation list (pageable)
	 
	 DELETE
	 	+ delete a donation obj
	 */
	
	
	//CREATE / UPDATE
	// Create or update a donation obj 

	public void saveOrUpdate(Donation donation);
	
	
	//READ / GET
	// + get one donation obj
	public Donation getDontaion(int theId);
	
	// + get donation list (list)
	public List<Donation> getDonationList();


	// + get donation list (pageable)

	public Page<Donation> findByQuery(String theQueryString, Pageable pageable);
	
	public Page<Donation> findByQuery(String theQueryString, String searchingValue, Pageable pageable);
	
	public Page<Donation> findByPhoneNumberOrOrganizationOrCodeOrStatus(String searchString, Pageable pageable);

	public Page<Donation> findAll(Pageable pageable);

	/*
	public Page<Donation> findByPhoneNumberOrOrganizationOrCodeOrStatus2(String searchingValue, Pageable pageable);
	*/
	
	//DELETE
	// + delete a donation obj
	public void delete(int theId);
	
}
