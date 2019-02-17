package com.astrika.abg.service;

import java.util.List;

import com.astrika.abg.exception.DuplicateCompanyException;
import com.astrika.abg.exception.DuplicateLoginException;
import com.astrika.abg.exception.NoSuchBrandException;
import com.astrika.abg.exception.NoSuchCompanyException;
import com.astrika.abg.model.BrandMaster;
import com.astrika.abg.model.CompanyMaster;



public interface CompanyService {



	CompanyMaster save(CompanyMaster company)throws DuplicateCompanyException,
	DuplicateLoginException;

	List<BrandMaster> delete(long companyId) throws NoSuchCompanyException;

	List<BrandMaster> restore(long companyId) throws NoSuchCompanyException;

	CompanyMaster update(CompanyMaster company);

	CompanyMaster updateSingleRest(long companyId)
			throws NoSuchCompanyException;

	List<CompanyMaster> findByInActive(int start, int end);

	List<CompanyMaster> findByActive(int start, int end);

	CompanyMaster findById(long companyId) throws NoSuchCompanyException;

	List<CompanyMaster> findByActive(boolean active);

	CompanyMaster findByCompanyName(String name);
	
	CompanyMaster findByCompanyLoginId(String loginId);

	long findCount();
	
	public BrandMaster findBrandById(long brandId) throws NoSuchBrandException;

}
