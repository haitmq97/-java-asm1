package me.haitmq.spring.mvc.crud.common;




import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import me.haitmq.spring.mvc.crud.entity.status.UserDonationStatus;
import me.haitmq.spring.mvc.crud.validation.ValidAmount;


public class InitUserDonation {

	
	private int id;
	
	@NotBlank(message = "is required")
	private String name;
	
	
	private String createdDate;
	
	//@Pattern(regexp = "^[1-9]\\d*000$", message = "Số tiền quyên góp phải từ hàng nghìn")
	
	@Max(value=500000000, message="Số tiền quyên góp tối đa 500.000.000vnđ")
	@Min(value=5000, message="Số tiền quyên góp tối thiểu 5.000vnđ")
	@ValidAmount
	private long money;
	
	
	private UserDonationStatus status;
	
	
	private String note;
		
	
	private boolean showing;
	
	
	private int userId;
	
	private int donationId;
	

	
	// constructor
	
	public InitUserDonation() {
		
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getCreatedDate() {
		return createdDate;
	}



	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}



	public long getMoney() {
		return money;
	}



	public void setMoney(long money) {
		this.money = money;
	}



	public UserDonationStatus getStatus() {
		return status;
	}



	public void setStatus(UserDonationStatus status) {
		this.status = status;
	}



	public String getNote() {
		return note;
	}



	public void setNote(String note) {
		this.note = note;
	}



	public boolean isShowing() {
		return showing;
	}



	public void setShowing(boolean showing) {
		this.showing = showing;
	}



	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}



	public int getDonationId() {
		return donationId;
	}



	public void setDonationId(int donationId) {
		this.donationId = donationId;
	}
	
	
}
