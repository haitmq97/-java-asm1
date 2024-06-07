package me.haitmq.spring.mvc.crud.service;

import java.util.List;

import org.springframework.data.domain.Page;

import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.entity.status.DonationStatus;

public interface DonationService {

	// save donation obj (create /upadate)
	public void add(Donation donation);
	
	// update donation obj
	// kiểm tra xem có cập nhật donation không
	public boolean isAbleToUpdate(Donation donation);
	
	public void update(Donation donation);
	
	// kiểm tra xem donation có thể chuyển sang trạng thái mới hay không
	public boolean isAbleTochangeStatus(DonationStatus newStatus, int donationId);

	// thay đổi trạng thái cho donation
	public void changeDonationStatus(DonationStatus status, int donationId);
	
	// kiểm tra xem có thể xóa( ẩn) donation không
	public boolean isAbleToShowOff(Donation donation);
	
	// thay đổi trạng thái hiển thị (xóa/ẩn)
	public void changeDonationShowingStatus(int donationid);
	
	// kiểm tra xem donation có thể tự chuyển trạng thái dựa trên startDate và enđate
	public boolean isAbleToAutoDonatingStatus(Donation donation);
	
	public boolean isAbleToAutoEndStatus(Donation donation);
	
	// tự động chuyển trạng thái dựa trên startDate và enđate
	public void autoUpdateStatus(Donation donation);
	
	// tự động cập nhật trạng thái cho tất cả
	public void autoUpdateStatusAll();
	
	// cập nhật tiền cho donation theo lượng tiền donate(userDonation)
	/*
	public void addMoneyToDonation(int theId, long amount);
	*/
	public void addMoneyFromUserDonationToDonation(long moneyAmount, int donationId);
	
	// cập nhật tiền cho donation thông qua toàn bộ donate (userDonation)
	public void updateAllMoneyUserDonationtoDonation(int theId);
	
	/*
	public void updateAllMoneyUserDonationtoDonation(int donationId);
	*/
	
	// cập nhật tiền cho toàn bộ donation
	public void updateAllDonationMoney();

	
	// get single donation obj
	public Donation getDonation(int theId);

	// get donation list
	public List<Donation> getDonationList();

	// get donation list (pageable)

	public Page<Donation> findByPhoneNumberOrOrganizationOrCodeOrStatus(String searchingValue, int page, int size);

	public Page<Donation> findAll(int page, int size);

	// delete donation obj
	public void delete(int theId);
	
	public void saveOrUpdate(Donation donation);
	
	public void setTotalConfirmedDonate(int donationId);
}
