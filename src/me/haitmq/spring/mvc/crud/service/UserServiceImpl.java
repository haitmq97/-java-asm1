package me.haitmq.spring.mvc.crud.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import me.haitmq.spring.mvc.crud.common.LoginUser;
import me.haitmq.spring.mvc.crud.dao.UserDAO;
import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.entity.Role;
import me.haitmq.spring.mvc.crud.entity.User;
import me.haitmq.spring.mvc.crud.entity.role.UserRole;
import me.haitmq.spring.mvc.crud.utils.Time;
import me.haitmq.spring.mvc.crud.entity.status.DonationStatus;
import me.haitmq.spring.mvc.crud.entity.status.UserStatus;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	

	// save user (update)
	
	@Override
	@Transactional
	public void saveOrUpdate(User user) {

		// giu nguyen cac truong không cập nhật

		if(user.getId() != 0) {
			User existingUser = userDAO.getUser(user.getId());
			/*
			user.setRole(existingUser.getRole());
			 */
			user.setPassword(existingUser.getPassword());
			user.setStatus(existingUser.getStatus());
			user.setCreatedDate(existingUser.getCreatedDate());
			user.setShowing(existingUser.getShowing());
			System.out.println("==================================>>>>>>>>service existing user: " + existingUser);
		}
		
		
		
		System.out.println("==================================>>>>>>>> test here in service");

		System.out.println("==================================>>>>>>>>service user: " + user);

		
	//  them ngay tao va status ban dau
			if(user.getCreatedDate()==null) {
				user.setCreatedDate(Time.getCurrentDateTime());
				user.setStatus(UserStatus.ACTIVE);
				user.setShowing(true);
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
			user.setStatus(UserStatus.ACTIVE);
			user.setRole(new Role(UserRole.USER));
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
		user.setStatus(user.getStatus()== UserStatus.ACTIVE ? UserStatus.LOCKED: UserStatus.ACTIVE);
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
	public boolean isUserExisted(LoginUser loginUser) {
		User dbUser = getUserByUserNameOrEmail(loginUser.getUserNameOrEmail());
		if((dbUser!=null)&&(dbUser.getPassword().equals(loginUser.getPassword()))) {
			return true;
		}
		return false;
	}
	
	@Override
	@Transactional
	public int getIdIfUserExisted(LoginUser loginUser) {
		User dbUser = getUserByUserNameOrEmail(loginUser.getUserNameOrEmail());
		if((dbUser!=null)&&(dbUser.getPassword().equals(loginUser.getPassword()))) {
			return dbUser.getId();
		}
		return -1;
	}

	@Override
	@Transactional
	public boolean isAdmin(User user) {
		if(user.getRole().getRoleName()== UserRole.USER.ADMIN) {
			return true;
		}
		return false;
	}


	@Override
	@Transactional
	public boolean isAdmin(int theId) {
		if(userDAO.getUser(theId).getRole().getRoleName()== UserRole.USER.ADMIN) {
			return true;
		}
		return false;
	}
	
	
	@Override
	@Transactional
	public boolean isActive(int theId) {
		boolean result =false;
		User theUser = userDAO.getUser(theId);
		if(theUser.getStatus() == UserStatus.ACTIVE) {
			result = true;
		}
		
		return result;
		
	}
	
	@Override
	@Transactional
	public void changeUserStatus(UserStatus status, int userId) {

		try {
			User user = userDAO.getUser(userId);
			user.setStatus(status);
			userDAO.saveOrUpdate(user);
		} catch (Exception e) {
			
		}
		
	}
	
	
}
