package com.cg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

	Optional<Admin> findByAdminId(Integer int1);


	
}
