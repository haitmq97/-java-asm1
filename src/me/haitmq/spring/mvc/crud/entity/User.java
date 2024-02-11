package me.haitmq.spring.mvc.crud.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import me.haitmq.spring.mvc.crud.validation.EmailFormat;
import me.haitmq.spring.mvc.crud.validation.PhoneNumberFormat;


@Entity
@Table(name = "user")
public class User {
	
	
	// fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	
	@Column(name = "full_name")
	//@NotBlank(message = "is required")
	private String fullName;
	
	@Column(name="user_name")
	//@NotBlank(message = "is required")
	private String userName;
	
	@Column(name = "password")
	//@NotBlank(message = "is required")
	private String password;
	
	@Column(name = "email")
	//@NotBlank
	//@EmailFormat
	private String email;
	
	@Column(name = "phone_number")
	//@PhoneNumberFormat
	private String phoneNumber;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "status")
	private int status;
	
	@Column(name = "created_date")
	private String createdDate;
	

	//@OneToOne(cascade = CascadeType.ALL, optional = false)
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private Role role;
	
	//constructor
	
	public User() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", fullName=" + fullName + ", userName=" + userName + ", password=" + password
				+ ", email=" + email + ", phoneNumber=" + phoneNumber + ", address=" + address + ", status=" + status
				+ ", createdDate=" + createdDate + ", role=" + role + "]";
	}
	
	

	

	

	
}
