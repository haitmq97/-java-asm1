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
	
		if(user.getCreatedDate().isEmpty()) {
			user.setCreatedDate(Time.getCurrentDateTime());
			//user.setStatus("NEW");
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
	
	

	


	
	///////////////////////////////////
	
	@Override
	@Transactional
	public Page<User> findByEmailOrPhoneNumberOrStatus(String searchString, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		return userDAO.findByEmailOrPhoneNumberOrStatus(searchString, pageRequest);
	}

	@Override
	@Transactional
	public Page<User> findAll(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		return userDAO.findAll( pageRequest);
	}
		
	
	//// for login
	
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

	@Override
	@Transactional
	public User getUserByUserNameOrEmail(String userName) {
		User user = userDAO.getUserByUserName(userName);
		if(user == null) {
			user = userDAO.getUserByEmail(userName);
		}
		
		return user;
	}

	
	// user trong method nay chi co 2 thuoc tinh co gia tri la userName va password
	@Override
	@Transactional
	public boolean isUserExisted(User loginUser) {
		User dbUser = getUserByUserNameOrEmail(loginUser.getUserName());
		if((dbUser!=null)&&(dbUser.getPassword().equals(loginUser.getPassword()))) {
			return true;
		}
		return false;
	}
	
	@Override
	@Transactional
	public int getIdIfUserExisted(User loginUser) {
		User dbUser = getUserByUserNameOrEmail(loginUser.getUserName());
		if((dbUser!=null)&&(dbUser.getPassword().equals(loginUser.getPassword()))) {
			return dbUser.getId();
		}
		return -1;
	}
	
	
	
	
}
