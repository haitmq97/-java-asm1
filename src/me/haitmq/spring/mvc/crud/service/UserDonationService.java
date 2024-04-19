package me.haitmq.spring.mvc.crud.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



import me.haitmq.spring.mvc.crud.entity.UserDonation;
import me.haitmq.spring.mvc.crud.entity.User;
import me.haitmq.spring.mvc.crud.entity.Donation;

public interface UserDonationService {
	
	/*
	 * cần xác định các phương thức:
	 * - save 1 userDonation obj:
	 * 	+ kiểm tra điều kiện có thể tạo: user status, donation status
	 * 	+ trạng thái mặc định mới tạo: (not comfirm)
	 * 	+ set User và Donation tương ứng
	 * 	+ ngày tạo
	 * 
	 * - update:
	 * 	+ update trạng thái (comfirm) (admin) => thêm money vào donation tương ứng
	 * 	+ update hủy trạng thái xác nhận => không hiển thị trong danh sách
	 * 
	 * - xóa 1 userDonation obj
	 * 
	 * - lấy 1 userDonation obj
	 * 
	 * - lấy ds userDonation obj:
	 * 	+ ds donte obj, sắp xếp theo trạng thái và thời gian tạo, phân trang (admin)
	 * 	+ ds userDonation obj thông qua donation Id (sắp xếp theo thứ tự thời gian tạo) (phân trang)
	 * 	+ ds userDonation obj thông qua user Id (sắp xếp theo thứ tự thời gian tạo) (phân trang)
	 * 
	 */
	
	
	
	// save
	
	public void save(UserDonation userDonation);
	
	public void save(UserDonation userDonation, int userId, int donationId);
	
	public boolean isAbletoUserDonation(User user, Donation donation);
	
	// update
	
	public void update(UserDonation userDonation);
	
	public void userDonationComfirm(int userDonationId);
	
	public void changeUserDonationShowingStatus(int userDonationid);
	
	// delete
	
	public void delete(int theId);
	
	// get single
	
	public UserDonation getUserDonation(int theId);
	
	
	// get list
	
	public List<UserDonation> getUserDonations();
	
	public List<UserDonation> getUserDonationByDonationId(int theId);
	
	public List<UserDonation> getUserDonationByUserId(int theId);
	
	public List<UserDonation> getDonteListByDonationId(int theId);
	
	// get list pageable
	
	public Page<UserDonation> findAll(int page, int size);
	
	public Page<UserDonation> findAllSortByStatus(int page, int size);
	
	public Page<UserDonation> findAllSortByCreatedDate(int page, int size);
	
	public Page<UserDonation> findAllSortByStatusByCreatedDate(int page, int size);
	
	public Page<UserDonation> findByUserId(int userId, int page, int size);
	
	public Page<UserDonation> findByDonationId(int donationId, int page, int size);
	
	
	
	
	
	/////////////////////////
	
	
	
	
	
	public void updateAllMoney();
	
	public Long getTotalMoneyByDonationId(int donationId);
	
	

}
