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
	
	
	/// for login
	
	public User getUserByUserName(String userName);
	
	public User getUserByEmail(String email);
	
	public boolean isUserNameMatched(String userName);
	
	public boolean isEmailMatched(String email);
	
	public boolean isPasswordMatched(String userName, String password);
	
	public User getUserByUserNameOrEmail(String userName);
	
	public boolean isUserExisted(User user);
	public int getIdIfUserExisted(User user);
	
	/////////////////////
	
	
	public Page<User> findByEmailOrPhoneNumberOrStatus(String searchString, int page, int size);

	public Page<User> findAll(int page, int size);

}
