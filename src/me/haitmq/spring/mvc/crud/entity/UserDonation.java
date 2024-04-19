package me.haitmq.spring.mvc.crud.entity;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import me.haitmq.spring.mvc.crud.utils.status.UserDonationStatus;

@Entity
@Table(name = "user_donation")
public class UserDonation {

	//fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "created_date")
	private String createdDate;
	
	@Column(name = "money")
	private long money;
	
	@Column(name = "status")
	private UserDonationStatus status;
	
	@Column(name = "note")
	private String note;
		
	@Column(name = "showing")
	private boolean showing;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "donation_id")
	private Donation donation;
	

	
	// constructor
	
	public UserDonation() {
		
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
	
	

	public boolean getShowing() {
		return showing;
	}

	public void setShowing(boolean showing) {
		this.showing = showing;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Donation getDonation() {
		return donation;
	}

	public void setDonation(Donation donation) {
		this.donation = donation;
	}

	@Override
	public String toString() {
		return "UserDonation [id=" + id + ", name=" + name + ", createdDate=" + createdDate + ", money=" + money + ", status="
				+ status + ", note=" + note + ", showing=" + showing + ", user=" + user + ", donation=" + donation
				+ "]";
	}

	
	
	

	
	
	

	
	
	
}
