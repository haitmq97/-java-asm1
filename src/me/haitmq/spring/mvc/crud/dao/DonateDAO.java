package me.haitmq.spring.mvc.crud.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.entity.User;
import me.haitmq.spring.mvc.crud.entity.Donate;

public interface DonateDAO {
	
	/*
	 cần định nghĩa các phương thức:
	 * CUD
	 - save 1 donate obj (dùng để khởi tạo) (user)
	 - update 1 donate obj 
	 	+ dùng để cập nhật trạng thái (admin - comfirm) (admin)
	 	+ 1 donate khỏi danh sách) (admin)
	 - xóa 1 donate obj (admin)
	 
	 
	 *R
	 - lấy 1 donate obj bằng id
	 
	 *R
	 - lấy ds donate obj:
	  	+ không phân trang
	  	+ có phân trang
	  	+ có phân trang sắp xếp theo trạng thái
	  	+ có phân trang sắp xếp theo thời gian tạo
	  	+ có phân trang sắp xếp theo trạng thái và thời gian tạo
	 - lấy ds donate obj bằng userId
	 	+ không phân trang
	  	+ có phân trang
	  	+ có phân trang sắp xếp theo trạng thái
	  	+ có phân trang sắp xếp theo thời gian tạo
	  	+ có phân trang sắp xếp theo trạng thái và thời gian tạo
	 - lấy ds donate obj bằng donationId
	 	+ không phân trang
	  	+ có phân trang
	  	+ có phân trang sắp xếp theo trạng thái
	  	+ có phân trang sắp xếp theo thời gian tạo
	  	+ có phân trang sắp xếp theo trạng thái và thời gian tạo
	 
	 *R
	 - lấy tổng tiền các donate obj theo donationId và status: comfirmed
	 
	 */
	
	public void save(Donate donate);
	
	public void update(Donate donate);
	
	public void saveOrUpdate(Donate donate);
	
	public Donate getDonate(int theId);
	
	public List<Donate> getAllDonates();
	
	public void delete(int theId);
	
	

	
	public List<Donate> getDonateByDonationId(int theId);
	
	public List<Donate> getDonateByUserId(int theId);
	
	
	/////////
	
	public Page<Donate> findAll(Pageable pageable);
	
	public Page<Donate> findAllSortByStatus(Pageable pageable);
	
	public Page<Donate> findAllSortByCreatedDate(Pageable pageable);
	
	public Page<Donate> findAllSortByStatusByCreatedDate(Pageable pageable);
	
	public Page<Donate> findByUserId(int userId, Pageable pageable);
	
	public Page<Donate> findByDonationId(int donationId, Pageable pageable);
	
	
	
	//////////////////////////
	
	public Long getTotalMoneyByDonationId(int theId);
	
	public List<Donate> getDonteListByDonationId(int theId);

}
