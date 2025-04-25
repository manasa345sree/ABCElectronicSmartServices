package com.cg.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.entity.Client;
import com.cg.entity.Complaint;
import com.cg.entity.Engineer;
import com.cg.entity.Product;

public interface IComplaintService {

    boolean bookComplaint(Client client, Complaint complaint, Product product);

    String changeComplaintStatus(Complaint complaint);

    List<Complaint> getClientAllComplaints(Client client);

    List<Complaint> getClientAllOpenComplaints(Client client);

    Engineer getEngineer(Integer engineerId);

    Product getProductByCompliant(Integer complaintId);

	Complaint addComplaint(Complaint complaint);

	List<Complaint> getComplaintsByEngineerStatusAndResolvedDate(Integer engineerId, String status,
			LocalDate resolvedDate);

	List<Complaint> getComplaintsSortedByPriority(Long engineerId);

}
