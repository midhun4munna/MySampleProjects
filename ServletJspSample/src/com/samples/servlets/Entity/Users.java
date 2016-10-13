package com.samples.servlets.Entity;

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class Users implements Serializable {

	@Id
	@Column(name = "firstname")
	private String firstName;
	@Column(name = "lastname")
	private String lastName;
	@Column(name = "gender")
	private String gender;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users", cascade = CascadeType.ALL)
	private Set<UserVehicle> userVehicle = new HashSet<UserVehicle>();

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Set<UserVehicle> getUserVehicle() {
		return userVehicle;
	}

	public void setUserVehicle(Set<UserVehicle> userVehicle) {
		this.userVehicle = userVehicle;
	}

	public void removeUserVehicle(UserVehicle userVehicle) {
		this.userVehicle.remove(userVehicle);
		if (userVehicle != null)
			userVehicle.setUsers(null);

	}
	public void addNewVehicle(UserVehicle userVehicle){
		this.userVehicle.add(userVehicle);
	}

}
