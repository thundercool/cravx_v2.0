package com.astrika.abg.service;

import java.util.List;

import com.astrika.abg.exception.BusinessException;
import com.astrika.abg.exception.DuplicateStateException;
import com.astrika.abg.exception.NoSuchCityException;
import com.astrika.abg.model.StateMaster;

public interface StateService {
	
	List<StateMaster> findByActive(boolean active);
	
	StateMaster findByStateNameAndStateId(String stateName, Long StateId);

	StateMaster findByStateId(long stateId) throws NoSuchCityException,BusinessException;

	StateMaster update(Long stateId,String stateName) throws DuplicateStateException;
	
	List<StateMaster> findByStateName(String name);

	StateMaster delete(Long stateId) ;

	StateMaster  restore(Long stateId) ;
	
	StateMaster save(StateMaster state);
	
}

