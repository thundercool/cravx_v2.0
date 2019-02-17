package com.astrika.abg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.astrika.abg.model.CorporateMaster;
import com.astrika.abg.model.Status;

public interface CorporateRepository extends JpaRepository<CorporateMaster, Long> {
	
	@Query("select c from CorporateMaster c left join fetch c.corporateArea left join fetch c.corporateCity left join fetch c.corporateState left join fetch c.corporateAdmin where c.active = ?1")
	List<CorporateMaster> findAllByOrderByCorporateName(boolean active);
	
	@Query("select c from CorporateMaster c left join fetch c.corporateArea left join fetch c.corporateCity left join fetch c.corporateState  left join fetch c.corporateAdmin  left join fetch c.industryType  where c.corporateId = ?1")
	CorporateMaster findById(long id);
	
	@Query("select c from CorporateMaster c left join fetch c.corporateArea left join fetch c.corporateCity left join fetch c.corporateState left join fetch c.corporateAdmin where c.status = ?1")
	List<CorporateMaster> findAllByStatus(Status status);
	
	CorporateMaster findByCorporateName(String corporateName);

}
