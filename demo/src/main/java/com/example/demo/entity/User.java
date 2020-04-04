package com.example.demo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int uid;
	@NotBlank(message = "firstname should not be empty")
	private String firstName;
	private String lastName; 
	private String city;
	//@Min(value = 5000,message = "Opening  bal should be atleat 5000")
	//private long openingBalance; 
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinColumn(name = "account_fk")
	private Account acc = new Account();
	//private List<Account> accountsList = new ArrayList<Account>();
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	



	public User(int uid, @NotBlank(message = "firstname should not be empty") String firstName, String lastName,
			String city,
			Account acc) {
		super();
		this.uid = uid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.acc = acc;
	}





	/**
	 * @return the uid
	 */
	public int getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(int uid) {
		this.uid = uid;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}


	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}








	/**
	 * @return the acc
	 */
	public Account getAcc() {
		return acc;
	}





	/**
	 * @param acc the acc to set
	 */
	public void setAcc(Account acc) {
		this.acc = acc;
	}





	@Override
	public String toString() {
		return "User [uid=" + uid + ", firstName=" + firstName + ", lastName=" + lastName + ", city=" + city
				+ ",s acc=" + acc + "]";
	}


}
