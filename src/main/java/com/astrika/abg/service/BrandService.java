package com.astrika.abg.service;

import java.util.List;

import com.astrika.abg.exception.DuplicateBrandException;
import com.astrika.abg.exception.DuplicateLoginException;
import com.astrika.abg.exception.NoSuchBrandException;
import com.astrika.abg.model.BrandMaster;


public interface BrandService {

	

	BrandMaster save(BrandMaster brand)throws DuplicateBrandException,
	 DuplicateLoginException;

	BrandMaster delete(long brandId);

	BrandMaster restore(long brandId);

	BrandMaster update(BrandMaster brand) throws DuplicateBrandException;

	List<BrandMaster> findByInActive(int start, int end);

	List<BrandMaster> findByActive(int start, int end);

	List<BrandMaster> findAllByInActive();

	List<BrandMaster> findAllByActive();

	BrandMaster findById(long brandId) throws NoSuchBrandException;

	List<BrandMaster> findAllByCompanyId(long companyId);

	List<BrandMaster> findByIds(Long[] brands);

	void updateAll(List<BrandMaster> brandList);

	BrandMaster findByBrandNameAndCompanyCompanyId(String brandName,
			Long companyId);

	List<BrandMaster> findAllInactiveByCompanyId(long companyId);

	long findCountByCompany(long comapnyId, boolean active);
}
