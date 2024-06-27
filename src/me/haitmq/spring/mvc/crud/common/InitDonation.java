package me.haitmq.spring.mvc.crud.common;

import java.util.ArrayList;

import java.util.List;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.haitmq.spring.mvc.crud.controller.AdminController;
import me.haitmq.spring.mvc.crud.entity.Donation;
import me.haitmq.spring.mvc.crud.entity.UserDonation;
import me.haitmq.spring.mvc.crud.entity.status.DonationStatus;
import me.haitmq.spring.mvc.crud.validation.PhoneNumberFormat;
import me.haitmq.spring.mvc.crud.validation.UniquedDonationCode;
import me.haitmq.spring.mvc.crud.validation.ValidStartDate;
import me.haitmq.spring.mvc.crud.validation.ValidDonationPeriod;

@ValidDonationPeriod
@UniquedDonationCode(message = "Mã đã tồn tại")
public class InitDonation {
	
	private static final Logger log = LoggerFactory.getLogger(InitDonation.class);

	private int id;
	
	@Pattern(regexp = "^[a-zA-Z]{2}[0-9]{3}$", message = "Mã phải gồm 2 kí tự chữ và 3 kí tự số")
	@NotBlank(message = "is required")
	private String code;
	

	@NotBlank(message = "is required")
	private String name;
	

	@NotBlank(message = "is required")
	@PhoneNumberFormat
	private String phoneNumber;
	
	@NotBlank(message = "is required")
	private String organization;


	private String createdDate;
	

	private long money;
	

	private String description;
	
	private DonationStatus status;
	
	@NotBlank(message = "is required")
	@ValidStartDate
	private String startDate;
	
	@NotBlank(message = "is required")
	@ValidStartDate(message = "Ngày kết thúc không được trước hôm này")
	private String endDate;
	
	private int donationQuantity;
	
	private boolean showing;
	
	
	
	//constructor
	public InitDonation() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DonationStatus getStatus() {
		return status;
	}

	public void setStatus(DonationStatus status) {
		this.status = status;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getDonationQuantity() {
		return donationQuantity;
	}

	public void setDonationQuantity(int donationQuantity) {
		this.donationQuantity = donationQuantity;
	}

	public boolean getShowing() {
		return showing;
	}

	public void setShowing(boolean showing) {
		this.showing = showing;
	}
	
	

	@Override
	public String toString() {
		return "Donation [id=" + id + ", code=" + code + ", name=" + name + ", phoneNumber=" + phoneNumber
				+ ", organization=" + organization + ", createdDate=" + createdDate + ", money=" + money
				+ ", description=" + description + ", status=" + status + ", startDate=" + startDate + ", endDate="
				+ endDate + ", donationQuantity=" + donationQuantity + ", showing=" + showing + "]";
	}
	
	
	public Donation copyPropertiesToDonationObj(Donation donation) {
		try {
			donation.setId(this.getId());
			donation.setName(this.getName());
			donation.setCode(this.getCode());
			donation.setStartDate(this.getStartDate());
			donation.setEndDate(this.getEndDate());
			donation.setOrganization(this.getOrganization());
			donation.setPhoneNumber(this.getPhoneNumber());
			donation.setStatus(this.getStatus());
			
			return donation;
		} catch (Exception e) {
			log.error("InitDonation - copyPropertiesToDonationObj: {}", e);
			return new Donation();
		}
	}
	
	public void getPropertiesFromDonationObj(Donation donation) {
		try {
			this.setId(donation.getId());
			this.setName(donation.getName());
			this.setCode(donation.getCode());
			this.setStartDate(donation.getStartDate());
			this.setEndDate(donation.getEndDate());
			this.setOrganization(donation.getOrganization());
			this.setPhoneNumber(donation.getPhoneNumber());
			this.setStatus(donation.getStatus());
		} catch (Exception e) {
			log.error("InitDonation - getPropertiesFromDonationObj: {}", e);
		}
	}
	

	

}
