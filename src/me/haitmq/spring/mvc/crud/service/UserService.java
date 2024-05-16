package me.haitmq.spring.mvc.crud.service;



import java.util.List;

import org.hibernate.boot.model.naming.ImplicitNameSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import me.haitmq.spring.mvc.crud.common.LoginUser;
import me.haitmq.spring.mvc.crud.entity.User;
import me.haitmq.spring.mvc.crud.entity.status.DonationStatus;
import me.haitmq.spring.mvc.crud.entity.status.UserStatus;

public interface UserService  {
	
	/*
	 * Định nghĩa các phương thức:
	 * 
	 * - Tạo user:
	 * 	+ cho user(new) (role mặc định là user)
	 * 	+ cho admin
	 * 	+ trạng thái: active
	 * 	+ ngày tạo
	 * 
	 * - update (admin, user)
	 * 	+ update thông tin trừ username và email (user, admin(role))
	 * 	+ thay đổi status (admin) (chỉ thay đổi status của user)
	 * 	+ thay đổi showing
	 * 
	 * - delete user
	 * 
	 * - get single user
	 * 	+ qua id
	 * 	+ qua username và email (dành cho login)
	 * 
	 * - get user list
	 * 	+ không phân trang
	 * 	+ phân trang, tìm kiếm, (sắp xếp theo status và thời gian tạo
	 * 
	 * - về login:
	 * 	+ đăng nhập bằng username
	 * 	+ đăng nhập bằng email
	 * 		(kiểm tra xem có user với username hay email tương ứng)
	 * 		(kiểm tra xem mật khẩu có khớp)
	 * 	+ kiểm tra xem có user với usernam hay email tương ứng không
	 */
	
	// save user (update)
	
	public void saveOrUpdate(User user);
	
	public void register(User user);
	
	// update user
	
	public void changeUserStatus(int userId);
	
	public void changeUserShowingStatus(int userId);
	
	
	// delete user
	
	public void deleteUser(int theId);
	
	
	// get single user
	
	public User getUser(int theId);
	
	public User getUserByUserName(String userName);
	
	public User getUserByEmail(String email);
	
	// get user list
	
	public List<User> getUserList();
	
	// get user list (pageable)
	
	public Page<User> findByEmailOrPhoneNumberOrStatus(String searchString, int page, int size);

	public Page<User> findAll(int page, int size);
	
	public Page<User> getPaginatedData(int page, int size);
	
	public Page<User> findAllByEmailOrPhoneNumber(int page, int size, String searchingValue);
	 

	
	
	/// for login
	
	
	public boolean isPasswordMatched(String userName, String password);
	
	public User getUserByUserNameOrEmail(String userName);
	
	public boolean isUserExisted(LoginUser user);
	
	public int getIdIfUserExisted(LoginUser user);
	
	public boolean isAdmin(User user);
	
	/////////////////////
	
	public boolean isAdmin(int theId );
	
	public boolean isActive(int theId);
	
	public void changeUserStatus(UserStatus status, int userId);


}
