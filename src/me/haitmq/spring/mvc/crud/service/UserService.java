package me.haitmq.spring.mvc.crud.service;



import java.util.List;

import org.hibernate.boot.model.naming.ImplicitNameSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import me.haitmq.spring.mvc.crud.entity.User;

public interface UserService  {
	
	public void saveOrUpdate(User user);
	
	public User getUser(int theId);
	
	public List<User> getUserList();
	
	public Page<User> getPaginatedData(int page, int size);
	
	public Page<User> findAllByEmailOrPhoneNumber(int page, int size, String searchingValue);
	 
	public void deleteUser(int theId);
	 

}
