package me.haitmq.spring.mvc.crud.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import me.haitmq.spring.mvc.crud.dao.UserDAO;
import me.haitmq.spring.mvc.crud.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	@Transactional
	public void saveOrUpdate(User user) {
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
	
	


	
	
}
