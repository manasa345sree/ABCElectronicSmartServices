package com.cg.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.Exception.InValidComplaintIdException;
import com.cg.Exception.InValidEngineerIdException;
import com.cg.entity.Admin;
import com.cg.entity.Complaint;
import com.cg.entity.Engineer;
import com.cg.entity.Product;
import com.cg.repository.AdminRepository;
import com.cg.repository.ComplaintRepository;
import com.cg.repository.EngineerRepository;
import com.cg.repository.ProductRepository;
import com.cg.service.IAdminService;


@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private EngineerRepository engineerRepository;

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private ProductRepository productRepository;

    
    @Override
    public void addAdmin(Admin admin) {
        // Assuming that admin has a password and other properties
        // Save the new admin to the database
        adminRepository.save(admin);
    }
    
    @Override
    public void addEngineer(Engineer engineer) {

        engineerRepository.save(engineer);
    }

    @Override
    public void changeDomain(Integer engineerId, String newDomain) {
        Engineer engineer = engineerRepository.findById(engineerId)
                .orElseThrow(() -> new InValidEngineerIdException("Engineer not found with ID: " + engineerId  ));
        engineer.setDomain(newDomain);
        engineerRepository.save(engineer);
    }

    @Override
    public void removeEngineer(Integer engineerId) {
        if (!engineerRepository.existsById(engineerId)) {
            throw new InValidEngineerIdException("Engineer not found with ID: " + engineerId);
        }
        engineerRepository.deleteById(engineerId);
    }

    @Override
    public List<Complaint> getComplaintsByProducts(String productName) {
        List<Product> products = productRepository.findByProductName(productName);
        List<Complaint> result = new ArrayList<>();

        for (Product product : products) {
            result.addAll(complaintRepository.findByProductModelNumber(product.getModelNumber()));
        }

        return result;
    }

    @Override
    public List<Complaint> getComplaints(String status, String domain) {
        List<Complaint> complaints = complaintRepository.findByStatus(status);

        return complaints.stream()
                .filter(complaint -> {
                    Engineer engineer = engineerRepository.findById(complaint.getEngineerId()).orElse(null);
                   // Engineer engineer = complaint.getEngineer(); // Getting the Engineer object directly

                    return engineer != null && engineer.getDomain().equalsIgnoreCase(domain);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Complaint replaceEngineerFromComplaint(Integer complaintId) {
        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new InValidComplaintIdException("Complaint not found with ID: " + complaintId));

        Engineer currentEngineer = engineerRepository.findById(complaint.getEngineerId())
                .orElseThrow(() -> new InValidEngineerIdException("Current Engineer not found"));

//        Engineer currentEngineer = complaint.getEngineer(); // Fetching the Engineer object directly
//        if (currentEngineer == null) {
//            throw new InValidEngineerIdException("Current Engineer not found");
//        }  
        
        List<Engineer> engineers = engineerRepository.findByDomain(currentEngineer.getDomain());

        for (Engineer e : engineers) {
            if (e.getEngineerId()!= currentEngineer.getEngineerId()) {
                complaint.setEngineerId(e.getEngineerId());
              //  complaint.setEngineer(e);  // Assigning a new Engineer object to the complaint

                return complaintRepository.save(complaint);
            }
        }

        throw new RuntimeException("No alternate engineer available in the same domain");
    }
}

