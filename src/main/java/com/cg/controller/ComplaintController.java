package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cg.entity.Client;
import com.cg.entity.Complaint;
import com.cg.entity.Product;
import com.cg.service.IComplaintService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

    @Autowired
    private IComplaintService complaintService;

    @PostMapping("/add")
    public ResponseEntity<Complaint> addComplaint(@RequestBody Complaint complaint) {
        Complaint savedComplaint = complaintService.addComplaint(complaint);
        return new ResponseEntity<>(savedComplaint, HttpStatus.CREATED);
    }
    
    
    // Get complaints by engineerId, status, and resolvedDate
    @GetMapping("/engineer/status/date")
    public ResponseEntity<List<Complaint>> getComplaintsByEngineerStatusAndResolvedDate(
            @RequestParam Integer engineerId,
            @RequestParam String status,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate resolvedDate) {

        List<Complaint> complaints = complaintService
                .getComplaintsByEngineerStatusAndResolvedDate(engineerId, status, resolvedDate);
        return new ResponseEntity<>(complaints, HttpStatus.OK);
    }
    
    // Book a new complaint
    @PostMapping("/book")
    public String bookComplaint(@RequestBody Complaint complaint,
                                @RequestParam String clientId,
                                @RequestParam String productModelNumber) {
        Client client = new Client();
        client.setClientId(clientId);

        Product product = new Product();
        product.setModelNumber(productModelNumber);

        boolean success = complaintService.bookComplaint(client, complaint, product);
        return success ? "Complaint booked successfully!" : "Failed to book complaint.";
    }

    // Get all complaints by client
    @GetMapping("/all/{clientId}")
    public List<Complaint> getAllClientComplaints(@PathVariable String clientId) {
        Client client = new Client();
        client.setClientId(clientId);
        return complaintService.getClientAllComplaints(client);
    }

    // Get open complaints by client
    @GetMapping("/open/{clientId}")
    public List<Complaint> getOpenComplaints(@PathVariable String clientId) {
        Client client = new Client();
        client.setClientId(clientId);
        return complaintService.getClientAllOpenComplaints(client);
    }

    // Change complaint status
    @PutMapping("/update-status")
    public String updateStatus(@RequestBody Complaint complaint) {
        return complaintService.changeComplaintStatus(complaint);
    }
}
