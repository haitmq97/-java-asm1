package me.haitmq.spring.mvc.crud.entity;

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
	
	@Column(name = "created")
	private String created;
	
	@Column(name = "note")
	private String note;
	

	//@OneToOne(cascade = CascadeType.ALL, optional = false)
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private Role role;
	
	//constructor
	
	public User() {
		
	}

	
	public User(String fullName, String userName, String password, String email, String phoneNumber, String address,
			int status, String created, String note) {
		this.fullName = fullName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.status = status;
		this.created = created;
		this.note = note;
	}





	// setter and getter
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

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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
				+ ", created=" + created + ", note=" + note + ", role=" + role + "]";
	}

	


	// toString method
	

	
	
	
}
