package com.astrika.abg.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.astrika.abg.model.CompanyMaster;


public interface CompanyRepository extends JpaRepository<CompanyMaster, Long> {

	@Query(value="select c from CompanyMaster c left join fetch c.companyAdmin where c.active=1 Order By c.companyName ASC ",countQuery="select count(c) from CompanyMaster c where c.active = 1")
	Page<CompanyMaster> findByActiveTrueOrderByCompanyNameAsc(Pageable page);

	@Query(value="select c from CompanyMaster c left join fetch c.companyAdmin where c.active=0 Order By c.companyName ASC ",countQuery="select count(c) from CompanyMaster c where c.active = 0")
	Page<CompanyMaster> findByActiveFalseOrderByCompanyNameAsc(Pageable page);
	
	@Query("select c from CompanyMaster c left join fetch c.companyAdmin where c.active=?1 Order By c.companyName ASC ")
	List<CompanyMaster> findAllByActive(boolean active);

	CompanyMaster findByCompanyName(String name);
	
	@Query("select c from CompanyMaster c left join fetch c.companyArea left join fetch c.companyCity left join fetch c.companyState left join fetch c.companyAdmin where c.companyId = ?1")
	CompanyMaster findById(long id);
	
}
