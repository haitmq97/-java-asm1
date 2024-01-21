package me.haitmq.spring.mvc.crud.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "donation")
public class Donation {
	
	// fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "organization")
	private String organization;

	@Column(name = "created")
	private String creadted;
	
	@Column(name = "money")
	private int money;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "status")
	private int status;
	
	@Column(name = "start_date")
	private String startDate;
	
	@Column(name = "end_date")
	private String endDate;
	
	
	//constructor
	public Donation() {
		
	}


	public Donation(String code, String name, String phoneNumber, String organization, String creadted, int money,
			String description, int status, String startDate, String endDate) {
		super();
		this.code = code;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.organization = organization;
		this.creadted = creadted;
		this.money = money;
		this.description = description;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
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


	public String getCreadted() {
		return creadted;
	}


	public void setCreadted(String creadted) {
		this.creadted = creadted;
	}


	public int getMoney() {
		return money;
	}


	public void setMoney(int money) {
		this.money = money;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
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


	@Override
	public String toString() {
		return "Donation [id=" + id + ", code=" + code + ", name=" + name + ", phoneNumber=" + phoneNumber
				+ ", organization=" + organization + ", creadted=" + creadted + ", money=" + money + ", description="
				+ description + ", status=" + status + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
	


}
