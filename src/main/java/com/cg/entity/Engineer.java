package com.cg.entity;

import com.cg.security.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Engineer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer engineerId;

    private String password;
    private String engineerName;
    private String domain;
     	
    
    @Enumerated(EnumType.STRING)
    private Role role;  
    
	public Integer getEngineerId() {
		return engineerId;
	}
	public void setEngineerId(Integer engineerId) {
		this.engineerId = engineerId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEngineerName() {
		return engineerName;
	}
	public void setEngineerName(String engineerName) {
		this.engineerName = engineerName;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}

    
}
