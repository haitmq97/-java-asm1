package me.haitmq.spring.mvc.crud.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import me.haitmq.spring.mvc.crud.entity.User;

public interface UserDAO {

	/*
	 * cần định nghĩa các phương thức: CUD - save 1 user obj (dùng để khởi tạo) -
	 * update 1 user obj + update thông tin (user, admin) + update trạng thái
	 * (admin) + update ẩn khỏi danh sách (admin) - xóa 1 user obj (admin)
	 * 
	 * R - lấy 1 user obj bằng id
	 * 
	 * R - lấy ds user obj: + không phân trang + có phân trang, tìm kiếm
	 * (phoneNumber, email, username, trạng thái(admin)) (user, admin) + có phân
	 * trang, tìm kiếm sắp xếp theo trạng thái (admin) + có phân trang, tìm kiếm sắp
	 * xếp theo thời gian tạo (admin) + có phân trang, tìm kiếm sắp xếp theo trạng
	 * thái và thời gian tạo (admin)
	 * 
	 * 
	 * - for login: + lấy user bằng userName + lấy user bằng email
	 * 
	 */

	// add or update

	public void saveOrUpdate(User user);

	// get user obj
	public User getUser(int theId);

	// get user list

	public List<User> getUserList();

	// get user list(pageable)

	public Page<User> findByEmailOrUserNameOrPhoneNumber(Pageable pageable, String searchingValue);

	public Page<User> findByEmailOrUserNameOrPhoneNumberOrStatus(String searchingValue, Pageable pageable);

	public Page<User> findAll(Pageable pageable);

	// delete

	public void delete(int theId);

	/// for login
	// tìm user qua username hoặc email
	public User getUserByUserName(String userName);

	public User getUserByEmail(String email);

	public User getUserByUserNameOrEmail(String userNameOrEmail);
	
	// join fetch
	
	public User getUserJoinFetch(int theId);
	
}
