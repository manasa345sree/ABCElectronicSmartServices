package com.cg.service;

import java.util.List;

import com.cg.entity.Admin;
import com.cg.entity.Complaint;
import com.cg.entity.Engineer;

public interface IAdminService {

    void addEngineer(Engineer engineer);

    void changeDomain(Integer engineerId, String newDomain);

    void removeEngineer(Integer engineerId);

    List<Complaint> getComplaintsByProducts(String productName);

    List<Complaint> getComplaints(String status, String domain);

    Complaint replaceEngineerFromComplaint(Integer complaintId);

	void addAdmin(Admin admin);

}
