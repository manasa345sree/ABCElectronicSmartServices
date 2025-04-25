package com.cg.serviceImpl;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cg.entity.Complaint;
import com.cg.entity.Engineer;
import com.cg.repository.ComplaintRepository;
import com.cg.repository.EngineerRepository;
import com.cg.service.IEngineerService;

@Service
public class EngineerServiceImpl implements IEngineerService {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private EngineerRepository engineerRepository;

    
    // Sign-in implementation
    @Override
    public Engineer signIn(Integer engineerId, String password) {
        Engineer engineer = engineerRepository.findById(engineerId).orElse(null);

        if (engineer != null && engineer.getPassword().equals(password)) {
            return engineer;
        }

        return null;
    }
    
    @Override
    public List<Complaint> getAllOpenComplaints(Engineer engineer) {
        // Retrieve open complaints assigned to the engineer
        return complaintRepository.findByEngineerIdAndStatus(engineer.getEngineerId(), "Open");
    }

    @Override
    public List<Complaint> getResolvedComplaints(Engineer engineer) {
        // Retrieve resolved complaints assigned to the engineer
        return complaintRepository.findByEngineerIdAndStatus(engineer.getEngineerId(), "Resolved");
    }

    @Override
    public List<Complaint> getResolvedComplaintsByDate(Engineer engineer, LocalDate date) {
        // Retrieve resolved complaints for a specific date
        return complaintRepository.findByEngineerIdAndStatusAndResolvedDate(engineer.getEngineerId(), "Resolved", date);
    }

    @Override
    public String changeComplaintStatus(Integer complaintId) {
        // Fetch the complaint by ID
        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        // Change the complaint status (example: from "Open" to "Resolved")
        complaint.setStatus("Resolved");

        // Save the updated complaint
        complaintRepository.save(complaint);

        return "Complaint status updated to Resolved";
    }
    
    
    
    public List<Complaint> getSortedComplaintsByPriority(Integer engineerId, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.ASC, sortBy); // default ascending
        if ("desc".equalsIgnoreCase(sortDirection)) {
            sort = Sort.by(Sort.Direction.DESC, sortBy);
        }
        return complaintRepository.findByEngineerId(engineerId, sort);
    }

    
    
    
    
}
