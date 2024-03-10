package me.haitmq.spring.mvc.crud.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



import me.haitmq.spring.mvc.crud.entity.Donate;
import me.haitmq.spring.mvc.crud.entity.User;
import me.haitmq.spring.mvc.crud.entity.Donation;

public interface DonateService {
	
	/*
	 * cần xác định các phương thức:
	 * - save 1 donate obj:
	 * 	+ kiểm tra điều kiện có thể tạo: user status, donation status
	 * 	+ trạng thái mặc định mới tạo: (not comfirm)
	 * 	+ set User và Donation tương ứng
	 * 	+ ngày tạo
	 * 
	 * - update:
	 * 	+ update trạng thái (comfirm) (admin) => thêm money vào donation tương ứng
	 * 	+ update hủy trạng thái xác nhận => không hiển thị trong danh sách
	 * 
	 * - xóa 1 donate obj
	 * 
	 * - lấy 1 donate obj
	 * 
	 * - lấy ds donate obj:
	 * 	+ ds donte obj, sắp xếp theo trạng thái và thời gian tạo, phân trang (admin)
	 * 	+ ds donate obj thông qua donation Id (sắp xếp theo thứ tự thời gian tạo) (phân trang)
	 * 	+ ds donate obj thông qua user Id (sắp xếp theo thứ tự thời gian tạo) (phân trang)
	 * 
	 */
	
	
	
	// save
	
	public void save(Donate donate);
	
	public void save(Donate donate, int userId, int donationId);
	
	public boolean isAbletoDonate(User user, Donation donation);
	
	// update
	
	public void update(Donate donate);
	
	public void donateComfirm(int donateId);
	
	// delete
	
	public void delete(int theId);
	
	// get single
	
	public Donate getDonate(int theId);
	
	
	// get list
	
	public List<Donate> getDonates();
	
	public List<Donate> getDonateByDonationId(int theId);
	
	public List<Donate> getDonateByUserId(int theId);
	
	public List<Donate> getDonteListByDonationId(int theId);
	
	// get list pageable
	
	public Page<Donate> findAll(int page, int size);
	
	public Page<Donate> findAllSortByStatus(int page, int size);
	
	public Page<Donate> findAllSortByCreatedDate(int page, int size);
	
	public Page<Donate> findAllSortByStatusByCreatedDate(int page, int size);
	
	public Page<Donate> findByUserId(int userId, int page, int size);
	
	public Page<Donate> findByDonationId(int donationId, int page, int size);
	
	
	
	
	
	/////////////////////////
	
	
	
	
	
	public void updateAllMoney();
	
	public Long getTotalMoneyByDonationId(int donationId);
	
	

}
