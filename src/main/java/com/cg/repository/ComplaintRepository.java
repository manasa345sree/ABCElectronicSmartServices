package com.cg.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.Complaint;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {

    List<Complaint> findByClientId(String clientId);

    List<Complaint> findByClientIdAndStatus(String clientId, String status);

    List<Complaint> findByEngineerId(int engineerId);

	//Collection<? extends Complaint> findByProductModelNumber(String modelNumber);

	List<Complaint> findByStatus(String status);
	
    List<Complaint> findByProductModelNumber(String productModelNumber);

    // Find complaints by engineer ID and status
    List<Complaint> findByEngineerIdAndStatus(Integer engineerId, String status);

	List<Complaint> findByEngineerIdAndStatusAndResolvedDate(Integer engineerId, String string, LocalDate resolvedDate);
    List<Complaint> findByEngineerId(Integer engineerId, Sort sort);

	List<Complaint> findByEngineerIdOrderByPriorityAsc(Long engineerId);



}
