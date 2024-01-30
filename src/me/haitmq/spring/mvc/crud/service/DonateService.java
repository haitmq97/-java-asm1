package me.haitmq.spring.mvc.crud.service;

import java.util.List;

import com.mysql.cj.xdevapi.Schema.CreateCollectionOptions;

import me.haitmq.spring.mvc.crud.entity.Donate;

public interface DonateService {
	
	public void save(Donate donate);
	
	public void update(Donate donate);
	
	public Donate getDonate(int theId);
	
	public List<Donate> getDonates();
	
	public void delete(int theId);
	
	public List<Donate> getDonateByDonationId(int theId);
	
	public List<Donate> getDonateByUserId(int theId);
	
	

}
