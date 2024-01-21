package me.haitmq.spring.mvc.crud.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import me.haitmq.spring.mvc.crud.entity.User;

public interface UserDAO  extends PagingAndSortingRepository<User, Long>{

	// add user
	
	public void saveOrUpdate(User user);
	
	public User getUser(int theId);
	
	public List<User> getUserList();
	
	public Page<User> findAllByEmailOrPhoneNumber(Pageable pageable , String searchingValue);
	
	public void deleteUser(int theId);
	
	
}
