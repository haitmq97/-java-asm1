package me.haitmq.spring.mvc.crud.common;

import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Pattern;




import me.haitmq.spring.mvc.crud.entity.Role;
import me.haitmq.spring.mvc.crud.entity.User;
import me.haitmq.spring.mvc.crud.entity.status.UserStatus;
import me.haitmq.spring.mvc.crud.validation.EmailFormat;
import me.haitmq.spring.mvc.crud.validation.PhoneNumberFormat;


public class InitUser {
	
	private int id;
	
	@NotBlank(message = "is required")
	private String fullName;
	
	@NotBlank(message = "is required")
	@Pattern(regexp = "^[^\\s]*$", message = "Không được chứa khoảng trắng")
	private String userName;
	
	@NotBlank(message = "is required")
	@Pattern(regexp = "^[^\\s]*$", message = "Không được chứa khoảng trắng")
	private String password;
	
	@NotBlank
	@EmailFormat
	private String email;
	
	@PhoneNumberFormat
	private String phoneNumber;
	
	private String address;
	
	private UserStatus status;
	
	private String createdDate;
	
	private boolean showing;
	
	private Role role;
	
	
	
	//constructor
	
	public InitUser() {
		
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

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	

	public boolean getShowing() {
		return showing;
	}

	public void setShowing(boolean showing) {
		this.showing = showing;
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
				+ ", createdDate=" + createdDate + ", showing=" + showing + ", role=" + role + "]";
	}


	
	public User copyPropertiesToUserObj(User user) {
		user.setId(this.getId());
		user.setFullName(this.getFullName());
		user.setUserName(this.getUserName());
		user.setPassword(this.getPassword());
		user.setEmail(this.getEmail());
		user.setAddress(this.getAddress());
		user.setPhoneNumber(this.getPhoneNumber());
		user.getRole().setRoleName(this.getRole().getRoleName());;
		
		
		return user;
	}
	
	public void getPropertiesFromDonationObj(User user) {
		this.setId(user.getId());
		this.setFullName(user.getFullName());
		this.setUserName(user.getUserName());
		this.setPassword(user.getPassword());
		this.setEmail(user.getEmail());
		this.setAddress(user.getAddress());
		this.setPhoneNumber(user.getPhoneNumber());
		this.setRole(user.getRole());
	}
	

	

	
}
