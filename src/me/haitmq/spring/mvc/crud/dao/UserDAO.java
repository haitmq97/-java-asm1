package me.haitmq.spring.mvc.crud.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.entity.User;

public interface UserDAO{

	// add user
	
	public void saveOrUpdate(User user);
	
	public User getUser(int theId);
	
	public List<User> getUserList();
	
	public Page<User> findAllByEmailOrPhoneNumber(Pageable pageable , String searchingValue);
	
	public void deleteUser(int theId);
	
	
	/// for login
	
	
	public User getUserByUserName(String userName);
	
	public User getUserByEmail(String email);
	
	public User getUserByUserNameOrEmail(String userName);
	////
	
	
	
	
	//// find all, findByEmailorPhoneNumberOrStatus
	public Page<User> findByEmailOrPhoneNumberOrStatus(String searchingValue, Pageable pageable);

	public Page<User> findAll(Pageable pageable);
}
