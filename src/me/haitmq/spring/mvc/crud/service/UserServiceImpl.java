package me.haitmq.spring.mvc.crud.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;

import me.haitmq.spring.mvc.crud.dao.UserDAO;
import me.haitmq.spring.mvc.crud.entity.User;
import me.haitmq.spring.mvc.crud.utils.Time;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	@Transactional
	public void saveOrUpdate(User user) {
		if(user.getCreated()==null) {
			user.setCreated(Time.getCurrentDateTime());
		}
		
		userDAO.saveOrUpdate(user);

	}

	@Override
	@Transactional
	public User getUser(int theId) {
		return userDAO.getUser(theId);
	}

	@Override
	@Transactional
	public List<User> getUserList() {
		return userDAO.getUserList();
	}

	@Override
	@Transactional
	public Page<User> getPaginatedData(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return userDAO.findAll(pageRequest);
    }

	@Override
	@Transactional
	public Page<User> findAllByEmailOrPhoneNumber(int page, int size, String searchingValue) {
		PageRequest pageRequest = PageRequest.of(page, size);
		return userDAO.findAllByEmailOrPhoneNumber(pageRequest, searchingValue);
	}

	@Override
	@Transactional
	public void deleteUser(int theId) {
		userDAO.deleteUser(theId);
		
	}

	
	
	
	// for login
	
	@Override
	@Transactional
	public User getUserByUserName(String userName) {
		return userDAO.getUserByUserName(userName);
	}
	
	

	@Override
	@Transactional
	public User getUserByEmail(String email) {
		return userDAO.getUserByEmail(email);
	}

	@Override
	public boolean isUserNameMatched(String userName) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isEmailMatched(String email) {
		return false;
	}

	@Override
	@Transactional
	public boolean isPasswordMatched(String userName, String password) {
		User user = userDAO.getUserByUserName(userName);
		if(user.getPassword().equals(password)) {
			return true;
		}
		return false;
	}
	
}
