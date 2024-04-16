package me.haitmq.spring.mvc.crud.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import me.haitmq.spring.mvc.crud.dao.UserDAO;
import me.haitmq.spring.mvc.crud.entity.Role;
import me.haitmq.spring.mvc.crud.entity.User;
import me.haitmq.spring.mvc.crud.utils.Time;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	

	// save user (update)
	
	@Override
	@Transactional
	public void saveOrUpdate(User user) {
		
		//  them ngay tao va status ban dau
		if(user.getCreatedDate()==null) {
			user.setCreatedDate(Time.getCurrentDateTime());
			user.setStatus(1);
		}
		
		userDAO.saveOrUpdate(user);
	}
	
	
	//register
	@Override
	@Transactional
	public void register(User user) {
		
		//  them ngay tao va status ban dau
		if(user.getCreatedDate()==null) {
			user.setCreatedDate(Time.getCurrentDateTime());
			user.setStatus(1);
			user.setRole(new Role("user"));
			user.setShowing(true);
		}
		
		userDAO.saveOrUpdate(user);
	}
	
	// update user
	

	@Override
	@Transactional
	public void changeUserStatus(int userId) {
		// get user by id
		User user = userDAO.getUser(userId);
		// set user status
		user.setStatus(user.getStatus()== 1 ? 0: 1);
		userDAO.saveOrUpdate(user);
	}

	@Override
	@Transactional
	public void changeUserShowingStatus(int userId) {
		User user = userDAO.getUser(userId);
		// set user showing status
		user.setShowing(user.getShowing()== true ? false:  true);
		userDAO.saveOrUpdate(user);
		
	}
	
	// delete user
	
	@Override
	@Transactional
	public void deleteUser(int theId) {
		userDAO.deleteUser(theId);
		
	}
	
	// get singe user obj
	
	@Override
	@Transactional
	public User getUser(int theId) {
		return userDAO.getUser(theId);
	}
	
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
	@Transactional
	public User getUserByUserNameOrEmail(String userName) {
		User user = userDAO.getUserByUserName(userName);
		if(user == null) {
			user = userDAO.getUserByEmail(userName);
		}
		
		return user;
	}
	
	// get user list
	@Override
	@Transactional
	public List<User> getUserList() {
		return userDAO.getUserList();
	}

	
	// get user list (pageable)


	@Override
	@Transactional
	public Page<User> getPaginatedData(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page -1, size);
        return userDAO.findAll(pageRequest);
    }

	@Override
	@Transactional
	public Page<User> findAllByEmailOrPhoneNumber(int page, int size, String searchingValue) {
		PageRequest pageRequest = PageRequest.of(page -1, size);
		return userDAO.findAllByEmailOrPhoneNumber(pageRequest, searchingValue);
	}

	@Override
	@Transactional
	public Page<User> findByEmailOrPhoneNumberOrStatus(String searchString, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page-1, size);
		return userDAO.findByEmailOrPhoneNumberOrStatus(searchString, pageRequest);
	}

	@Override
	@Transactional
	public Page<User> findAll(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page -1, size);
		return userDAO.findAll( pageRequest);
	}

	
		
	
	//// for login

	@Override
	@Transactional
	public boolean isPasswordMatched(String userName, String password) {
		User user = userDAO.getUserByUserName(userName);
		if(user.getPassword().equals(password)) {
			return true;
		}
		return false;
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

	@Override
	@Transactional
	public boolean isAdmin(User user) {
		if(user.getRole().getRoleName().toLowerCase().equals("admin")) {
			return true;
		}
		return false;
	}


	@Override
	@Transactional
	public boolean isAdmin(int theId) {
		if(userDAO.getUser(theId).getRole().getRoleName().equals("admin")) {
			return true;
		}
		return false;
	}
	
	
	
	
}
