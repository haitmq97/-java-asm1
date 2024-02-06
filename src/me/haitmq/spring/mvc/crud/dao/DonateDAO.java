package me.haitmq.spring.mvc.crud.dao;


import java.util.List;

import me.haitmq.spring.mvc.crud.entity.Donation;
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
	

}
