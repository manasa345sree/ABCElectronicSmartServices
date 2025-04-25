package com.cg.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.cg.entity.Admin;
import com.cg.entity.Complaint;
import com.cg.entity.Engineer;
import com.cg.service.IAdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private PasswordEncoder passwordEncoder; 
    
    // Endpoint to add a new admin
    @PostMapping("/add")
    public ResponseEntity<String> addAdmin(@RequestBody Admin admin) {
        //adminService.addAdmin(admin);
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));

        return ResponseEntity.ok("Admin added successfully");
    }

    
    // 1. Add Engineer
    @PostMapping("/engineer")
    public ResponseEntity<String> addEngineer(@RequestBody Engineer engineer) {
        adminService.addEngineer(engineer);
        return ResponseEntity.ok("Engineer added successfully");
    }

    // 2. Change Domain
    @PutMapping("/engineer/{id}/domain")
    public ResponseEntity<String> changeEngineerDomain(@PathVariable int id, @RequestParam String domain) {
        adminService.changeDomain(id, domain);
        return ResponseEntity.ok("Domain updated successfully for engineer ID: " + id);
    }

    // 3. Remove Engineer
    @DeleteMapping("/engineer/{id}")
    public ResponseEntity<String> removeEngineer(@PathVariable int id) {
        adminService.removeEngineer(id);
        return ResponseEntity.ok("Engineer removed successfully");
    }

    // 4. Get Complaints by Product Name
    @GetMapping("/complaints/product/{productName}")
    public ResponseEntity<List<Complaint>> getComplaintsByProduct(@PathVariable String productName) {
        List<Complaint> complaints = adminService.getComplaintsByProducts(productName);
        return ResponseEntity.ok(complaints);
    }

    // 5. Get Complaints by Status and Domain
    @GetMapping("/complaints")
    public ResponseEntity<List<Complaint>> getComplaints(
            @RequestParam String status,
            @RequestParam String domain) {
        List<Complaint> complaints = adminService.getComplaints(status, domain);
        return ResponseEntity.ok(complaints);
    }

    // 6. Replace Engineer in a Complaint
    @PutMapping("/complaint/{complaintId}/replace-engineer")
    public ResponseEntity<Complaint> replaceEngineer(@PathVariable int complaintId) {
        Complaint updatedComplaint = adminService.replaceEngineerFromComplaint(complaintId);
        return ResponseEntity.ok(updatedComplaint);
    }
}
