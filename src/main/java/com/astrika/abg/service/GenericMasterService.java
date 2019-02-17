package com.astrika.abg.service;

import java.util.List;

import com.astrika.abg.exception.DuplicateGenericMasterException;
import com.astrika.abg.model.GenericMaster;
import com.astrika.abg.model.GenericMasterType;

public interface GenericMasterService {
	
	/**
	 * find one GenericMaster by id 
	 * @param id
	 * @return
	 */
	public GenericMaster findById(Long id);

	/**
	 * save gerneric master
	 * @param genericMaster
	 * @return
	 */
	public GenericMaster save(GenericMaster genericMaster)throws DuplicateGenericMasterException;
	
	/**
	 * update gerneric master
	 * @param genericMaster
	 * @return
	 */
	public GenericMaster update(GenericMaster genericMaster);
	
	/**
	 * inactive GenericMaster
	 * @param id
	 * @return
	 */
	public GenericMaster delete(long id);
	
	/**
	 * active GenericMaster 
	 * @param id
	 * @return
	 */
	public GenericMaster resort(long id);
	
	/**
	 * find all by active
	 * @param Active
	 * @return
	 */
	public List<GenericMaster> findAllByActive(Boolean Active);
	
	/**
	 * This method will give the list of generic master by passing GenericMasterType
	 * @param type
	 * @return
	 */
	public List<GenericMaster> findByGenericMasterType(GenericMasterType type);
	
	
}
