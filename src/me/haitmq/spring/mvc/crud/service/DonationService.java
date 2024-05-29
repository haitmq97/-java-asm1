package me.haitmq.spring.mvc.crud.service;

import java.util.List;

import org.springframework.data.domain.Page;

import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.entity.status.DonationStatus;

public interface DonationService {

	/*
	 * Cần định nghĩa các phương thức
	 * 
	 * - save donation obj (admin) + status ban đầu (mặc định là 0: mới tạo),
	 * showing mặc định là true + ngày tạo
	 * 
	 * - update (admin): + thay đổi thông tin + thay đổi status + showing
	 * (true/false) + update money từ các userDonation có status = 1
	 * 
	 * - delete (admin): + chỉ có thể xóa các donation có status mới tạo
	 * 
	 * - get single donation (details) (admin, user)
	 * 
	 * - get donation list (admin, user): + không phân trang + phân trang tìm kiếm,
	 * sắp xếp theo trạng thái, thời gian tạo
	 * 
	 */
	
	// kiểm tra xem có cập nhật donation không
	public boolean isAbleToUpdate(Donation donation);

	// save donation obj (create /upadate)
	public void saveOrUpdate(Donation donation);

	// update donation obj

	// kiểm tra xem donation có thể chuyển sang trạng thái mới hay không
	public boolean isAbleTochangeStatus(DonationStatus newStatus, int donationId);

	// thay đổi trạng thái cho donation
	public void changeDonationStatus(DonationStatus status, int donationId);
	
	// kiểm tra xem có thể xóa( ẩn) donation không
	public boolean isAbleToShowOff(Donation donation);
	
	// thay đổi trạng thái hiển thị (xóa/ẩn)
	public void changeDonationShowingStatus(int donationid);
	
	

	public void addMoneyToDonation(int theId, long amount);

	public void updateDonationMoneyByUserDonation(int theId);

	public void updateAllDonationMoney();

	

	

	public boolean isAbleToAutoDonatingStatus(Donation donation);

	public boolean isAbleToAutoEndStatus(Donation donation);

	public void autoChangeToDonatingStatus(Donation donation);

	public void autoChangeToEndStatus(Donation donation);

	public void autoUpdateStatus(Donation donation);

	public void autoUpdateStatusALL();

	public void addMoneyFromUserDonationToDonation(Long moneyAmount, int donationId);

	public void updateAllMoneyUserDonationtoDonation(int donationId);
	
	// get single donation obj
	public Donation getDonation(int theId);

	// get donation list
	public List<Donation> getDonationList();

	// get donation list (pageable)

	public Page<Donation> findByPhoneNumberOrOrganizationOrCodeOrStatus(String searchingValue, int page, int size);

	public Page<Donation> findAll(int page, int size);

	// delete donation obj
		public void delete(int theId);

}
