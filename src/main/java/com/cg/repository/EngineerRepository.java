package com.cg.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.Engineer;

@Repository
public interface EngineerRepository extends JpaRepository<Engineer, Integer> {
	List<Engineer> findByDomain(String domain);

    Optional<Engineer> findFirstByDomain(String domain); // for one engineer

    Optional<Engineer> findByEngineerName(String username);



}

