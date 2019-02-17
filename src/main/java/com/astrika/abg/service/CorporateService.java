package com.astrika.abg.service;

import java.util.List;

import com.astrika.abg.exception.DuplicateCorporateException;
import com.astrika.abg.exception.NoSuchCorporateException;
import com.astrika.abg.model.CorporateMaster;
import com.astrika.abg.model.Status;

public interface CorporateService {
	
	public CorporateMaster save(CorporateMaster corporate) throws DuplicateCorporateException;
	
	public void delete(Long id) throws NoSuchCorporateException;
	
	public void restore(Long id) throws NoSuchCorporateException;

	public CorporateMaster findById(long id) throws NoSuchCorporateException;

	public List<CorporateMaster> findAllByOrderByCorporateName(boolean active);

	CorporateMaster findByCorporateName(String corporateName);

	public void acceptAndReject(Long corporateId, Status status) throws NoSuchCorporateException;

	List<CorporateMaster> findAllStatus(Status status);

}
