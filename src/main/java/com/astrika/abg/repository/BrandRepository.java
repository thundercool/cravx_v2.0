
package com.astrika.abg.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.astrika.abg.model.BrandMaster;


public interface BrandRepository extends JpaRepository<BrandMaster, Long> {

	Page<BrandMaster> findByActiveTrueOrderByBrandNameAsc(Pageable page);

	Page<BrandMaster> findByActiveFalseOrderByBrandNameAsc(Pageable page);

	@Query("select b from BrandMaster b left join fetch b.company left join fetch b.brandAdmin where b.active=1 ")
	List<BrandMaster> findAllByActiveTrueOrderByBrandNameAsc();
	
	@Query("select b from BrandMaster b left join fetch b.company left join fetch b.brandAdmin where b.active=1  and b.company.companyId = ?1  ")
	List<BrandMaster> findAllByActiveTrueAndCompanyCompanyIdOrderByBrandNameAsc(long companyId);

	@Query("select b from BrandMaster b left join fetch b.company left join fetch b.brandAdmin where b.active=0  ")
	List<BrandMaster> findAllByActiveFalseOrderByBrandNameAsc();

	@Query("select b from BrandMaster b left join fetch b.company left join fetch b.brandAdmin where b.active=1 and b.brandName=?1 and b.company.companyId = ?2  ")
	BrandMaster findByBrandNameAndCompanyCompanyId(String brandName,Long companyId);
	
	BrandMaster findByBrandName(String brandName);
	
	@Query("select b from BrandMaster b left join fetch b.company left join fetch b.brandAdmin where b.active=0  and b.company.companyId = ?1  ")
	List<BrandMaster> findAllByActiveFalseAndCompanyCompanyId(long companyId);
	
	@Query("select brand from BrandMaster brand left join fetch brand.brandAdmin left join fetch brand.brandArea left join fetch brand.brandCity left join fetch brand.brandState left join fetch brand.company where brand.brandId = ?1")
	BrandMaster findById(long id);
	
	@Query("select count(*) from BrandMaster fo where fo.company.companyId=?1 and fo.active=?2")
	long  findCountByCompany(long comapnyId, boolean active);
}
