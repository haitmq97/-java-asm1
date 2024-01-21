package me.haitmq.spring.mvc.crud.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.mysql.cj.x.protobuf.MysqlxCrud.Delete;

import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.entity.User;

public interface DonationDAO extends PagingAndSortingRepository<Donation, Long> {
	
	public void saveOrUpdate(Donation donation);
	
	public Donation getDontaion(int theId);
	
	public List<Donation> getDonationList();
	
	public void delete(int theId);
	

}
