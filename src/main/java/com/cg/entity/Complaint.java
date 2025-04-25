package com.cg.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer complaintId;

    private String productModelNumber;
    private String complaintName;
    private String status;

    private String clientId;
    
    private Integer engineerId;
    private LocalDate dateOfComplaint;

    private LocalDate resolvedDate;
    
    private Integer priority; // lower value = higher priority

//    @ManyToOne
//    @JoinColumn(name = "engineer_id")  // Map the foreign key column in the database
//    private Engineer engineer;
    
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Integer getComplaintId() {
		return complaintId;
	}
	public LocalDate getResolvedDate() {
		return resolvedDate;
	}
	public void setResolvedDate(LocalDate resolvedDate) {
		this.resolvedDate = resolvedDate;
	}
	public void setComplaintId(Integer complaintId) {
		this.complaintId = complaintId;
	}
	public String getProductModelNumber() {
		return productModelNumber;
	}
	public void setProductModelNumber(String productModelNumber) {
		this.productModelNumber = productModelNumber;
	}
	public String getComplaintName() {
		return complaintName;
	}
	public void setComplaintName(String complaintName) {
		this.complaintName = complaintName;
	}
	public String getStatus() {
		return status;
	}
//	public Engineer getEngineer() {
//		return engineer;
//	}
//	public void setEngineer(Engineer engineer) {
//		this.engineer = engineer;
//	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public Integer getEngineerId() {
		return engineerId;
	}
	public void setEngineerId(Integer engineerId) {
		this.engineerId = engineerId;
	}
	public LocalDate getDateOfComplaint() {
		return dateOfComplaint;
	}
	public void setDateOfComplaint(LocalDate dateOfComplaint) {
		this.dateOfComplaint = dateOfComplaint;
	}
    
	
    
}
