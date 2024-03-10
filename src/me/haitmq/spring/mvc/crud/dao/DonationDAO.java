package me.haitmq.spring.mvc.crud.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import me.haitmq.spring.mvc.crud.entity.Donation;

public interface DonationDAO {
	/*
	 * Cần định nghĩa
	 *CUD
	 - tạo 1 donation obj (admin, user)
	 - update 1 donation obj
	 	+ update thông tin (admin, user)
	 	+ update status (admin)
	 	+ update money từ các donate có status: comfirmed
	 	+ update trạng thái hiện thị (có show trong list hay ko) (admin)
	 
	 - xóa 1 donation obj (admin)
	 
	 *R
	 - lấy 1 donation obj (admin, user)
	 
	 - lấy ds donations (admin, user)
	 	+ không phân trang 
	 	+ phân trang, tìm kiếm (phoneNumber, organization, code, status) (admin, user)
	 	+ phân trang, tìm kiềm sắp xếp theo thời gian tạo (admin, user)
	 	+ phân trang, tìm kiếm sắp xếp theo trạng thái (admin, user)
	 	+ phân trang, tìm kiếm sắp xếp theo trạng thái, thời gian tạo (admin, user)
	 
	 */

	public void saveOrUpdate(Donation donation);

	public Donation getDontaion(int theId);

	public List<Donation> getDonationList();

	public void delete(int theId);
	
	/// loại?

	public Page<Donation> findByQuery(String theQueryString, Pageable pageable);
	
	public Page<Donation> findByQuery(String theQueryString, String searchingValue, Pageable pageable);
	
	public Page<Donation> findByPhoneNumber(String phoneNumber, Pageable pageable);

	public Page<Donation> findByOrganization(String organization, Pageable pageable);

	public Page<Donation> findByCode(String code, Pageable pageable);

	public Page<Donation> findByStatus(String status, Pageable pageable);

	public Page<Donation> findByPhoneNumberOrOrganizationOrCodeOrStatus(String searchString, Pageable pageable);

	public Page<Donation> findAll(Pageable pageable);

	public Page<Donation> findByPhoneNumberOrOrganizationOrCodeOrStatus2(String searchingValue, Pageable pageable);


}
