package com.cg.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.entity.Complaint;
import com.cg.entity.Engineer;

public interface IEngineerService {

    List<Complaint> getAllOpenComplaints(Engineer engineer);

    List<Complaint> getResolvedComplaints(Engineer engineer);

    List<Complaint> getResolvedComplaintsByDate(Engineer engineer, LocalDate date);

    String changeComplaintStatus(Integer complaintId);
    Engineer signIn(Integer engineerId, String password);

	//List<Complaint> getSortedComplaintsByPriority(Integer engineerId, String sortBy);
	public List<Complaint> getSortedComplaintsByPriority(Integer engineerId, String sortBy, String sortDirection);

}
