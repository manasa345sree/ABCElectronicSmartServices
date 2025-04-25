package com.cg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.Client;
import com.cg.entity.Engineer;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

	Optional<Client> findByClientId(String username);



}
