package com.cg.serviceImpl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.Exception.InValidClienIdException;
import com.cg.entity.Client;
import com.cg.entity.Complaint;
import com.cg.entity.Engineer;
import com.cg.repository.ComplaintRepository;
import com.cg.repository.EngineerRepository;
import com.cg.service.IClientRepository;
import com.cg.service.IClientService;

@Service
public class ClientImplService implements IClientService {

    @Autowired
    private IClientRepository clientRepository;

    @Autowired
    private EngineerRepository engineerRepository;

    @Autowired
    private ComplaintRepository complaintRepository;

    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client getClientByClientId(String clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new InValidClienIdException("Client not found with ID: " + clientId));
    }

    @Override
    public Engineer getEngineerById(Integer engineerId) {
        return engineerRepository.findById(engineerId)
                .orElseThrow(() -> new RuntimeException("Engineer not found with ID: " + engineerId));
    }

    @Override
    public List<Engineer> getEngineerByDomains(String domain) {
        return engineerRepository.findByDomain(domain);
    }

    @Override
    public Client changeStatusOfComplaint(Integer complaintId) {
        // Fetch the complaint by ID
        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found with ID: " + complaintId));

        // Set the complaint status to "Resolved"
        complaint.setStatus("Resolved");
        
        // Save the updated complaint
        complaintRepository.save(complaint);

        // Fetch the client using the clientId from the complaint
        Client client = clientRepository.findById(complaint.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found with ID: " + complaint.getClientId()));

        // Return the client object
        return client;
    }



    @Override
    public Client signIn(Client client) {
        Client existing = getClientByClientId(client.getClientId());
        if (existing.getPassword().equals(client.getPassword())) {
            existing.setLoggedIn(true);
            return clientRepository.save(existing);
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    @Override
    public Client signOut(Client client) {
        Client existing = getClientByClientId(client.getClientId());
        existing.setLoggedIn(false);
        return clientRepository.save(existing);
    }
}
