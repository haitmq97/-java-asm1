package me.haitmq.spring.mvc.crud.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.entity.User;
import me.haitmq.spring.mvc.crud.entity.UserDonation;
import me.haitmq.spring.mvc.crud.entity.status.DonationStatus;
import me.haitmq.spring.mvc.crud.entity.status.UserDonationStatus;

public interface UserDonationDAO {
	
	/*
	 cần định nghĩa các phương thức:
	 * CUD
	 - save 1 userDonation obj (dùng để khởi tạo) (user)
	 - update 1 userDonation obj 
	 	+ dùng để cập nhật trạng thái (admin - comfirm) (admin)
	 	+ 1 userDonation khỏi danh sách) (admin)
	 - xóa 1 userDonation obj (admin)
	 
	 
	 *R
	 - lấy 1 userDonation obj bằng id
	 
	 *R
	 - lấy ds userDonation obj:
	  	+ không phân trang
	  	+ có phân trang
	  	+ có phân trang sắp xếp theo trạng thái
	  	+ có phân trang sắp xếp theo thời gian tạo
	  	+ có phân trang sắp xếp theo trạng thái và thời gian tạo
	 - lấy ds userDonation obj bằng userId
	 	+ không phân trang
	  	+ có phân trang
	  	+ có phân trang sắp xếp theo trạng thái
	  	+ có phân trang sắp xếp theo thời gian tạo
	  	+ có phân trang sắp xếp theo trạng thái và thời gian tạo
	 - lấy ds userDonation obj bằng donationId
	 	+ không phân trang
	  	+ có phân trang
	  	+ có phân trang sắp xếp theo trạng thái
	  	+ có phân trang sắp xếp theo thời gian tạo
	  	+ có phân trang sắp xếp theo trạng thái và thời gian tạo
	 
	 *R
	 - lấy tổng tiền các userDonation obj theo donationId và status: comfirmed
	 
	 */
	
	public void save(UserDonation userDonation);
	
	public void update(UserDonation userDonation);
	
	public void saveOrUpdate(UserDonation userDonation);
	
	public UserDonation getUserDonation(int theId);
	
	public List<UserDonation> getAllUserDonations();
	
	public void delete(int theId);
	
	public List<UserDonation> getUserDonationByDonationId(int theId);
	
	public List<UserDonation> getUserDonationByUserId(int theId);
	
	
	public Page<UserDonation> findAll(Pageable pageable);
	
	public Page<UserDonation> findAllSortByStatus(Pageable pageable);
	
	public Page<UserDonation> findAllSortByCreatedDate(Pageable pageable);
	
	public Page<UserDonation> findAllSortByStatusByCreatedDate(Pageable pageable);
	
	public Page<UserDonation> findByUserId(int userId, Pageable pageable);
	
	public Page<UserDonation> findByDonationId(int donationId, Pageable pageable);
	
	public Long getTotalMoneyByDonationId(int theId);
	
	public List<UserDonation> getUserDonationListByDonationId(int theId);

	
	public Page<UserDonation> findByUserNameOrDonationCodeSortByStatusByCreatedDate(String searchingValue, Pageable pageable);
	
	public Page<UserDonation> findByDonationCodeSortByCreatedDate(String donationCode, String searchingValue, Pageable pageable);
	
	public Page<UserDonation> findByDonationCodeAndStatusSortByCreatedDate(String donationCode,UserDonationStatus status ,String searchingValue, Pageable pageable);
	
	public Page<UserDonation> findByDonationCodeGroupByUserSortByTotalMoney(String donationCode, String searchingValue, Pageable pageable);
	
	
	public Page<UserDonation> findByUserNameSortByCreatedDate(String username, String searchingValue, Pageable pageable);
	
	
	
}
