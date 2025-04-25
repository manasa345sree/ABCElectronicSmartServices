package com.cg.service;

import java.util.List;

import com.cg.entity.Client;
import com.cg.entity.Engineer;

public interface IClientService {

    Client saveClient(Client client);

    Client getClientByClientId(String clientId);

    Engineer getEngineerById(Integer engineerId);

    List<Engineer> getEngineerByDomains(String domain);

    Client changeStatusOfComplaint(Integer complaintId);

    Client signIn(Client client);

    Client signOut(Client client);
}
