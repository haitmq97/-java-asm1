package me.haitmq.spring.mvc.crud.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import me.haitmq.spring.mvc.crud.entity.role.UserRole;

@Entity
@Table(name = "role")
public class Role {

	// fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role_name")
	private UserRole roleName;
	
	// constructor
	
	public Role() {
		
	}

	public Role(UserRole roleName) {
		this.roleName = roleName;
	}

	
	// getter and setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserRole getRoleName() {
		return roleName;
	}

	public void setRoleName(UserRole roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + "]";
	}
	
	// toString method
	
	
	
}
