package com.cg.serviceImpl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Client;
import com.cg.entity.Complaint;
import com.cg.entity.Engineer;
import com.cg.entity.Product;
import com.cg.repository.ClientRepository;
import com.cg.repository.ComplaintRepository;
import com.cg.repository.EngineerRepository;
import com.cg.repository.ProductRepository;
import com.cg.service.IComplaintService;

@Service
public class ComplaintServiceImpl implements IComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EngineerRepository engineerRepository;

    
    @Override
    public List<Complaint> getComplaintsByEngineerStatusAndResolvedDate(Integer engineerId, String status, LocalDate resolvedDate) {
        return complaintRepository.findByEngineerIdAndStatusAndResolvedDate(engineerId, status, resolvedDate);
    }
    @Override
    public boolean bookComplaint(Client client, Complaint complaint, Product product) {
        // Validate client
        Client dbClient = clientRepository.findById(client.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        // Validate product
        Product dbProduct = productRepository.findById(product.getModelNumber())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Engineer engineer = engineerRepository
                .findFirstByDomain(dbProduct.getProductCategoryName())
                .orElseThrow(() -> new RuntimeException("Engineer not found"));

        // Set complaint data
        complaint.setClientId(dbClient.getClientId());
        complaint.setProductModelNumber(dbProduct.getModelNumber());
        complaint.setEngineerId(engineer.getEngineerId());
      //  complaint.setEngineer(engineer);  // Now setting the Engineer entity directly

        complaint.setStatus("Open");

        complaintRepository.save(complaint);
        return true;
    }

    @Override
    public String changeComplaintStatus(Complaint complaint) {
        complaintRepository.save(complaint);
        return "Complaint status updated.";
    }

    @Override
    public List<Complaint> getClientAllComplaints(Client client) {
        return complaintRepository.findByClientId(client.getClientId());
    }

    @Override
    public List<Complaint> getClientAllOpenComplaints(Client client) {
        return complaintRepository.findByClientIdAndStatus(client.getClientId(), "Open");
    }

    @Override
    public Engineer getEngineer(Integer engineerId) {
        return engineerRepository.findById(engineerId)
                .orElseThrow(() -> new RuntimeException("Engineer not found"));
    }

    @Override
    public Product getProductByCompliant(Integer complaintId) {
        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
        return productRepository.findById(complaint.getProductModelNumber())
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Complaint addComplaint(Complaint complaint) {
    
    	  // Step 1: Set the status to "Open"
        complaint.setStatus("Open");

        // Step 2: Set the complaint registered date
        complaint.setDateOfComplaint(LocalDate.now());

        // Step 3: Set maximum date to resolve (for example, 3 days later)
        LocalDate resolvedDate = LocalDate.now().plusDays(3);
        complaint.setResolvedDate(resolvedDate);

        // Step 4: Calculate and set priority (the lower the days, the higher the priority)
        int priority = (int) ChronoUnit.DAYS.between(LocalDate.now(), resolvedDate);
        complaint.setPriority(priority);

        // Step 5: Save complaint
    	
    	
    	return complaintRepository.save(complaint);
    }

    public List<Complaint> getComplaintsSortedByPriority(Long engineerId) {
        return complaintRepository.findByEngineerIdOrderByPriorityAsc(engineerId);
    }
}
