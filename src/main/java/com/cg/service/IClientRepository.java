package com.cg.service;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entity.Client;

public interface IClientRepository extends JpaRepository<Client, String> {
    // No additional methods needed now since findById from JpaRepository can be used for getClientByClientId
}
