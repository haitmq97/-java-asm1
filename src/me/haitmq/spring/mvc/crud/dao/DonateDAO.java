package me.haitmq.spring.mvc.crud.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.entity.User;
import me.haitmq.spring.mvc.crud.entity.Donate;

public interface DonateDAO {
	
	public void save(Donate donate);
	
	public void update(Donate donate);
	
	public void saveOrUpdate(Donate donate);
	
	public Donate getDonate(int theId);
	
	public List<Donate> getDonates();
	
	public void delete(int theId);
	
	public List<Donate> getDonateByDonationId(int theId);
	
	public List<Donate> getDonateByUserId(int theId);
	
	
	/////////
	
	public Page<Donate> findAll(Pageable pageable);
	
	public Page<Donate> findAllSortByStatus(Pageable pageable);
	
	public Page<Donate> findAllSortByCreatedDate(Pageable pageable);
	
	public Page<Donate> findAllSortByStatusByCreatedDate(Pageable pageable);
	
	public Page<Donate> findByUserId(int userId, Pageable pageable);
	
	public Page<Donate> findByDonationId(int donationId, Pageable pageable);
	
	
	
	//////////////////////////
	
	public Long getTotalMoneyByDonationId(int theId);
	
	public List<Donate> getDonteListByDonationId(int theId);

}
