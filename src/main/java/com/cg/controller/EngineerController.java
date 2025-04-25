package com.cg.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cg.entity.Complaint;
import com.cg.entity.Engineer;
import com.cg.service.IComplaintService;
import com.cg.service.IEngineerService;

@RestController
@RequestMapping("/engineer")
public class EngineerController {

    @Autowired
    private IEngineerService engineerService;

    @Autowired
    private IComplaintService complaintService;
    
 // Endpoint for engineer sign-in
    @PostMapping("/signin")
    public ResponseEntity<?> engineerSignIn(@RequestBody Engineer engineer) {
        Engineer signedInEngineer = engineerService.signIn(engineer.getEngineerId(), engineer.getPassword());
        
        if (signedInEngineer != null) {
            return ResponseEntity.ok(signedInEngineer);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid engineer credentials");
        }
    }
    
    // Endpoint to get all open complaints for a specific engineer
    @GetMapping("/{engineerId}/open-complaints")
    public List<Complaint> getAllOpenComplaints(@PathVariable Integer engineerId) {
        Engineer engineer = new Engineer();
        engineer.setEngineerId(engineerId);
        return engineerService.getAllOpenComplaints(engineer);
    }

    // Endpoint to get all resolved complaints for a specific engineer
    @GetMapping("/{engineerId}/resolved-complaints")
    public List<Complaint> getResolvedComplaints(@PathVariable Integer engineerId) {
        Engineer engineer = new Engineer();
        engineer.setEngineerId(engineerId);
        return engineerService.getResolvedComplaints(engineer);
    }

    // Endpoint to get resolved complaints for a specific engineer before a specific date
    @GetMapping("/{engineerId}/resolved-complaints/{date}")
    public List<Complaint> getResolvedComplaintsByDate(
            @PathVariable int engineerId, @PathVariable String date) {
        Engineer engineer = new Engineer();
        engineer.setEngineerId(engineerId);
        LocalDate localDate = LocalDate.parse(date); // Parse the date string to LocalDate
        return engineerService.getResolvedComplaintsByDate(engineer, localDate);
    }

    // Endpoint to change the status of a complaint
    @PutMapping("/complaint/{complaintId}/status")
    public String changeComplaintStatus(@PathVariable int complaintId) {
        return engineerService.changeComplaintStatus(complaintId);
    }
    
    
    
    @GetMapping("/{engineerId}/complaints/sort")
    public ResponseEntity<List<Complaint>> getSortedComplaints(
            @PathVariable Integer engineerId,
            @RequestParam String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        List<Complaint> sortedComplaints = engineerService.getSortedComplaintsByPriority(engineerId, sortBy, sortDirection);
        return ResponseEntity.ok(sortedComplaints);
    }
    
 // Endpoint to get complaints sorted by priority
    @GetMapping("/{engineerId}/complaints/sorted-by-priority")
    public ResponseEntity<List<Complaint>> getComplaintsSortedByPriority(@PathVariable Long engineerId) {
        List<Complaint> complaints = complaintService.getComplaintsSortedByPriority(engineerId);
        return new ResponseEntity<>(complaints, HttpStatus.OK);
    }

}
