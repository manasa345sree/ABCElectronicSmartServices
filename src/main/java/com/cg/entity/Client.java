package com.cg.entity;

import com.cg.security.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Client {

    @Id
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String clientId;

    private String password;
    private String address;
    private long phoneNumber;
    private boolean loggedIn; // Add loggedIn field to track the client's status

    @Enumerated(EnumType.STRING)
    private Role role;
    
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddresss(String address) {
		this.address = address;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
	  public boolean isLoggedIn() {
	        return loggedIn;
	    }

	    public void setLoggedIn(boolean loggedIn) {
	        this.loggedIn = loggedIn;
	    }
    
}
