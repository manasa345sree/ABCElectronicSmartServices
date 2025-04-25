package com.cg.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Client;
import com.cg.entity.Engineer;
import com.cg.service.IClientService;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private IClientService clientService;

    // 1. Save client
    @PostMapping("/register")
    public Client saveClient(@RequestBody Client client) {
        return clientService.saveClient(client);
    }

    // 2. Get client by ID
    @GetMapping("/{clientId}")
    public Client getClientByClientId(@PathVariable String clientId) {
        return clientService.getClientByClientId(clientId);
    }

    // 3. Get engineer by ID
    @GetMapping("/engineer/{engineerId}")
    public Engineer getEngineerById(@PathVariable int engineerId) {
        return clientService.getEngineerById(engineerId);
    }

    // 4. Get engineers by domain
    @GetMapping("/engineers/domain/{domain}")
    public List<Engineer> getEngineerByDomains(@PathVariable String domain) {
        return clientService.getEngineerByDomains(domain);
    }

    // 5. Change complaint status
    @PutMapping("/complaint/status/{complaintId}")
    public Client changeStatusOfComplaint(@PathVariable int complaintId) {
        return clientService.changeStatusOfComplaint(complaintId);
    }

    // 6. Sign in
    @PostMapping("/signin")
    public Client signIn(@RequestBody Client client) {
        return clientService.signIn(client);
    }

    // 7. Sign out
    @PostMapping("/signout")
    public Client signOut(@RequestBody Client client) {
        return clientService.signOut(client);
    }
}
