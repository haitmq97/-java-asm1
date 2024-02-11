package me.haitmq.spring.mvc.crud.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



import me.haitmq.spring.mvc.crud.entity.Donate;
import me.haitmq.spring.mvc.crud.entity.User;
import me.haitmq.spring.mvc.crud.entity.Donation;

public interface DonateService {
	
	public void save(Donate donate);
	public void save(Donate donate, int userId, int donationId);
	
	public void update(Donate donate);
	
	public Donate getDonate(int theId);
	
	public List<Donate> getDonates();
	
	public void delete(int theId);
	
	public List<Donate> getDonateByDonationId(int theId);
	
	public List<Donate> getDonateByUserId(int theId);
	
	
	/////////////////////////////
	
	
	public Page<Donate> findAll(int page, int size);
	
	public Page<Donate> findAllSortByStatus(int page, int size);
	
	public Page<Donate> findAllSortByCreatedDate(int page, int size);
	
	public Page<Donate> findAllSortByStatusByCreatedDate(int page, int size);
	
	public Page<Donate> findByUserId(int userId, int page, int size);
	
	public Page<Donate> findByDonationId(int donationId, int page, int size);
	
	/////////////////////////
	
	public boolean isAbletoDonate(User user, Donation donation);
	
	public void donateComfirm(int donateId);
	
	public void updateAllMoney();
	
	
	public List<Donate> getDonteListByDonationId(int theId);
}
