package me.haitmq.spring.mvc.crud.service;

import java.util.List;

import org.hibernate.boot.model.naming.ImplicitNameSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import me.haitmq.spring.mvc.crud.common.LoginUser;
import me.haitmq.spring.mvc.crud.entity.User;
import me.haitmq.spring.mvc.crud.entity.role.UserRole;
import me.haitmq.spring.mvc.crud.entity.status.DonationStatus;
import me.haitmq.spring.mvc.crud.entity.status.UserStatus;

public interface UserService {

	/*
	 * Định nghĩa các phương thức:
	 * 
	 * - Tạo user: + cho user(new) (role mặc định là user) + cho admin + trạng thái:
	 * active + ngày tạo
	 * 
	 * - update (admin, user) + update thông tin trừ username và email (user,
	 * admin(role)) + thay đổi status (admin) (chỉ thay đổi status của user) + thay
	 * đổi showing
	 * 
	 * - delete user
	 * 
	 * - get single user + qua id + qua username và email (dành cho login)
	 * 
	 * - get user list + không phân trang + phân trang, tìm kiếm, (sắp xếp theo
	 * status và thời gian tạo
	 * 
	 * - về login: + đăng nhập bằng username + đăng nhập bằng email (kiểm tra xem có
	 * user với username hay email tương ứng) (kiểm tra xem mật khẩu có khớp) + kiểm
	 * tra xem có user với usernam hay email tương ứng không
	 */

	// save user (update)
	// add new usser
	public void add(User user, UserRole whoUpdate);

	// update
	public void update(User user, UserRole whoUpdate);

	public void changeUserStatus(int userId);

	// delete user (hidden from data table)
	public void changeUserShowingStatus(int userId);

	public void changeUserStatus(UserStatus status, int userId);

	// get single user
	public User getUser(int theId);

	// get user list
	public List<User> getUserList();

	// get user list (pageable)
	public Page<User> findByEmailOrUserNameOrPhoneNumber(int page, int size, String searchingValue);

	public Page<User> findByEmailOrUserNameOrPhoneNumberOrStatus(int page, int size, String searchString);

	public Page<User> findAll(int page, int size);

	/*
	 * public Page<User> getPaginatedData(int page, int size);
	 * 
	 */

	// get/ read user
	public boolean isAdmin(int theId);

	public boolean isAdmin(User user);

	public boolean isActive(int theId);

	// check if login user is existed and get it
	public boolean isUserExisted(LoginUser user);
	
	public User getUserByUserName(String userName);

	public User getUserByEmail(String email);

	public User getUserByUserNameOrEmail(String userNameOrEmail);

	public int getIdIfUserExisted(LoginUser user);

	public User getUserIfUserExisted(LoginUser user);

	public boolean isPasswordMatched(String userName, String password);

	// delete user
	public void delete(int theId);

}
