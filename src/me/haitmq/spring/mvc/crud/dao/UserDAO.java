package me.haitmq.spring.mvc.crud.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.entity.User;

public interface UserDAO{
	
	/*
	 cần định nghĩa các phương thức:
	 * CUD
	 - save 1 user obj (dùng để khởi tạo)
	 - update 1 user obj
	 	+ update thông tin (user, admin)
	 	+ update trạng thái (admin)
	 	+ update ẩn khỏi danh sách (admin)
	 - xóa 1 user obj (admin)
	 
	 *R
	 - lấy 1 user obj bằng id
	 
	 *R
	 - lấy ds user obj:
	  	+ không phân trang
	  	+ có phân trang, tìm kiếm (phoneNumber, email, username, trạng thái(admin)) (user, admin)
	  	+ có phân trang, tìm kiếm sắp xếp theo trạng thái (admin)
	  	+ có phân trang, tìm kiếm sắp xếp theo thời gian tạo (admin)
	  	+ có phân trang, tìm kiếm sắp xếp theo trạng thái và thời gian tạo (admin)


	- for login:
		+ lấy user bằng userName
		+ lấy user bằng email
	 
	 */
	

	// add user
	
	public void saveOrUpdate(User user);
	
	public User getUser(int theId);
	
	public List<User> getUserList();
	
	public Page<User> findAllByEmailOrPhoneNumber(Pageable pageable , String searchingValue);
	
	
	public void deleteUser(int theId);
	
	
	/// for login
	
	
	public User getUserByUserName(String userName);
	
	public User getUserByEmail(String email);
	
	public User getUserByUserNameOrEmail(String userName);
	////
	
	
	
	
	//// find all, findByEmailorPhoneNumberOrStatus
	
	public Page<User> findByQuery(String theQueryString, String searchingValue, Pageable pageable);
	
	public Page<User> findByEmailOrPhoneNumberOrStatus(String searchingValue, Pageable pageable);

	public Page<User> findAll(Pageable pageable);
}
