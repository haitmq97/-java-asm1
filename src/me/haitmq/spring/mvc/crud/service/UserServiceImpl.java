package me.haitmq.spring.mvc.crud.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import me.haitmq.spring.mvc.crud.common.LoginUser;
import me.haitmq.spring.mvc.crud.dao.UserDAO;
import me.haitmq.spring.mvc.crud.dao.UserDonationDAO;
import me.haitmq.spring.mvc.crud.dto.UserDTO;
import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.entity.Role;
import me.haitmq.spring.mvc.crud.entity.User;
import me.haitmq.spring.mvc.crud.entity.UserDonation;
import me.haitmq.spring.mvc.crud.entity.role.UserRole;
import me.haitmq.spring.mvc.crud.utils.Time;
import me.haitmq.spring.mvc.crud.entity.status.DonationStatus;
import me.haitmq.spring.mvc.crud.entity.status.UserStatus;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private UserDonationDAO userDonationDAO;

	// save user (update)

	@Override
	@Transactional
	public void add(User user, UserRole whoUpdate) {

		// them ngay tao va status ban dau
		if (user.getCreatedDate() == null) {
			user.setCreatedDate(Time.getCurrentDateTime());
			user.setStatus(UserStatus.ACTIVE);
			user.setShowing(true);
			if (whoUpdate == UserRole.USER) {
				user.setRole(new Role(UserRole.USER));
			}
		}
		userDAO.saveOrUpdate(user);
	}

	// update user
	@Override
	@Transactional
	public void update(User user, UserRole whoUpdate) {

		if (user.getId() != 0) {
			User existingUser = userDAO.getUser(user.getId());
			// giu nguyen cac truong không cập nhật
			if (whoUpdate == UserRole.ADMIN) {

			} else {
				user.setRole(existingUser.getRole());
			}
			user.setStatus(existingUser.getStatus());
			user.setCreatedDate(existingUser.getCreatedDate());
			user.setShowing(existingUser.getShowing());
			user.setPassword(existingUser.getPassword());

		}

		userDAO.saveOrUpdate(user);
	}

	@Override
	@Transactional
	public void changeUserStatus(int userId) {
		// get user by id
		User user = userDAO.getUser(userId);
		// set user status
		user.setStatus(user.getStatus() == UserStatus.ACTIVE ? UserStatus.LOCKED : UserStatus.ACTIVE);
		userDAO.saveOrUpdate(user);
	}

	@Override
	@Transactional
	public void changeUserShowingStatus(int userId) {
		User user = userDAO.getUser(userId);
		// set user showing status
		user.setShowing(user.getShowing() == true ? false : true);

		userDAO.saveOrUpdate(user);
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

	@Override
	@Transactional
	public User getUser(int theId) {
		return userDAO.getUser(theId);
	}

	// get user list
	@Override
	@Transactional
	public List<User> getUserList() {
		return userDAO.getUserList();
	}

	// get user list (pageable)
	
	/*
	@Override
	@Transactional
	public Page<User> getPaginatedData(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page - 1, size);
		return userDAO.findAll(pageRequest);
	}
	 */
	
	@Override
	@Transactional
	public Page<User> findByEmailOrUserNameOrPhoneNumber(int page, int size, String searchingValue) {
		PageRequest pageRequest = PageRequest.of(page - 1, size);
		return userDAO.findByEmailOrUserNameOrPhoneNumber(pageRequest, searchingValue);
	}

	@Override
	@Transactional
	public Page<User> findByEmailOrUserNameOrPhoneNumberOrStatus(int page, int size, String searchString) {
		PageRequest pageRequest = PageRequest.of(page - 1, size);
		return userDAO.findByEmailOrUserNameOrPhoneNumberOrStatus(searchString, pageRequest);
	}

	@Override
	@Transactional
	public Page<User> findAll(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page - 1, size);
		return userDAO.findAll(pageRequest);
	}

	@Override
	@Transactional
	public boolean isAdmin(Integer theId) {
		if(theId == null || theId <=0) {
			return false;
		}
		
		if (userDAO.getUser(theId).getRole().getRoleName() == UserRole.ADMIN) {
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public boolean isAdmin(User user) {
		if(user == null) {
			return false;
		}
		
		if (user.getRole().getRoleName() == UserRole.ADMIN) {
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public boolean isActive(Integer theId) {
		if(theId == null) {
			return false;
		}
		
		boolean result = false;
		User theUser = userDAO.getUser(theId);
		if (theUser.getStatus() == UserStatus.ACTIVE) {
			result = true;
		}
		return result;
	}

	@Override
	@Transactional
	public boolean isUserExisted(LoginUser loginUser) {
		User dbUser = getUserByUserNameOrEmail(loginUser.getUserNameOrEmail());
		if ((dbUser != null) && (dbUser.getPassword().equals(loginUser.getPassword()))) {
			return true;
		}
		return false;
	}

	// get singe user obj

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
	public User getUserByUserNameOrEmail(String userNameOrEmail) {
		User user = userDAO.getUserByUserNameOrEmail(userNameOrEmail);
		return user;
	}

	@Override
	@Transactional
	public int getIdIfUserExisted(LoginUser loginUser) {
		/*
		 * User dbUser = getUserByUserNameOrEmail(loginUser.getUserNameOrEmail());
		 * if((dbUser!=null)&&(dbUser.getPassword().equals(loginUser.getPassword()))) {
		 * return dbUser.getId(); } return -1;
		 */

		User dbUser = getUserByUserNameOrEmail(loginUser.getUserNameOrEmail());
		if ((dbUser == null) || !(dbUser.getPassword().equals(loginUser.getPassword()))) {
			return -1;
		}

		return dbUser.getId();
	}

	@Override
	@Transactional
	public User getUserIfUserExisted(LoginUser loginUser) {
		User dbUser = getUserByUserNameOrEmail(loginUser.getUserNameOrEmail());
		if ((dbUser == null) || !(dbUser.getPassword().equals(loginUser.getPassword()))) {
			return null;
		}

		return dbUser;
	}
	
	/// for login
	@Override
	@Transactional
	public boolean isPasswordMatched(String userName, String password) {
		User user = userDAO.getUserByUserName(userName);
		if (user.getPassword().equals(password)) {
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public void delete(int theId) {
		userDAO.delete(theId);

	}

	@Override
	public User getUserJoinFetch(int theId) {

		return userDAO.getUserJoinFetch(theId);
	}

	
}
