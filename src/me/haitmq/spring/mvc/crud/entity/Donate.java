package me.haitmq.spring.mvc.crud.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "donate")
public class Donate {

	//fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "created")
	private String created;
	
	@Column(name = "money")
	private int money;
	
	@Column(name = "status")
	private int status;
	
	@Column(name = "note")
	private String note;
	
	@OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "donation_id")
	private Donation donation;
	
	// constructor
	
	public Donate() {
		
	}

	public Donate(String name, String created, int money, int status, String note) {
		this.name = name;
		this.created = created;
		this.money = money;
		this.status = status;
		this.note = note;
	}
	
	public Donate(String name, int money, String note, User user, Donation donation) {
		this.name = name;
		this.money = money;
		this.note = note;
		this.user = user;
		this.donation = donation;
	}
	

	
	// getter and setter
	


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

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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
	
	
	// toString method
	
}

	@Override
	public String toString() {
		return "UserDonation [id=" + id + ", name=" + name + ", created=" + created + ", money=" + money + ", status="
				+ status + ", note=" + note + "]";
	}
}
