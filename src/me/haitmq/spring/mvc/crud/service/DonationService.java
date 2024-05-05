package me.haitmq.spring.mvc.crud.service;

import java.util.List;

import org.springframework.data.domain.Page;

import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.entity.status.DonationStatus;

public interface DonationService {
	
	/*
	 * Cần định nghĩa các phương thức
	 * 
	 * - save donation obj (admin)
	 * 	+ status ban đầu (mặc định là 0: mới tạo), showing mặc định là true
	 * 	+ ngày tạo
	 * 
	 * - update (admin):
	 * 	+ thay đổi thông tin
	 * 	+ thay đổi status
	 * 	+ showing (true/false)
	 * 	+ update money từ các userDonation có status = 1
	 * 
	 * - delete (admin):
	 * 	+ chỉ có thể xóa các donation có status mới tạo
	 * 
	 * - get single donation (details) (admin, user)
	 * 
	 * - get donation list (admin, user):
	 * 	+ không phân trang
	 * 	+ phân trang tìm kiếm, sắp xếp theo trạng thái, thời gian tạo
	 * 
	 */

	
	// save donation obj (upadate)
	public void saveOrUpdate(Donation donation);
	
	// update donation obj
	public void changeDonationStatus(DonationStatus status, int donationId);
	
	public void changeDonationShowingStatus(int donationid);
	
	public void addMoneyFromUserDonationToDonation(Long moneyAmount, int donationId);
	
	public void updateAllMoneyUserDonationtoDonation(int donationId);
	
	
	// delete donation obj
	public void delete(int theId);
	
	
	// get single donation obj
	public Donation getDonation(int theId);

	
	// get donation list
	public List<Donation> getDonationList();
	
	/*
	public List<Donation> findByPhoneNumber(String phoneNumber);

	public List<Donation> findByOrganization(String organization);

	public List<Donation> findByCode(String code);

	public List<Donation> findByStatus(String status);

	public List<Donation> findByPhoneNumberOrOrganizationOrCodeOrStatus(String searchString);

	// public Page<Donation> getPaginatedData(int page, int size);

	 */

	
	
	// get donation list (pageable)


	public Page<Donation> findByPhoneNumber(String phoneNumber, int page, int size);

	public Page<Donation> findByOrganization(String organization, int page, int size);

	public Page<Donation> findByCode(String code, int page, int size);

	public Page<Donation> findByStatus(String status, int page, int size);

	public Page<Donation> findByPhoneNumberOrOrganizationOrCodeOrStatus(String searchingValue, int page, int size);

	public Page<Donation> findAll(int page, int size);
	
	public Page<Donation> findByPhoneNumberOrOrganizationOrCodeOrStatus2(String searchingValue, int page, int size);



	
	
}
