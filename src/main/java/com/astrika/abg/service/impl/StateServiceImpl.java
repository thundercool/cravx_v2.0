package com.astrika.abg.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.astrika.abg.exception.DuplicateCityException;
import com.astrika.abg.exception.DuplicateStateException;
import com.astrika.abg.exception.NoSuchCityException;
import com.astrika.abg.model.CityMaster;
import com.astrika.abg.model.StateMaster;
import com.astrika.abg.repository.StateRepository;
import com.astrika.abg.service.StateService;
import com.astrika.abg.exception.BusinessException;
import com.astrika.abg.exception.CustomException;
import com.astrika.abg.exception.SystemException;

@Service
public class StateServiceImpl implements StateService {
	
	@Autowired
	private StateRepository stateRepository;
	

	@Override
	public StateMaster save(StateMaster state) {
		
		return stateRepository.save(state);
	}

	@Override
	public List<StateMaster> findByActive(boolean active) {
		
		return stateRepository.findByActive(active);
	}

	@Override
	public StateMaster findByStateNameAndStateId(String stateName, Long StateId) {
		
		return null;
	}

	

	@Override
	public StateMaster findByStateId(long stateId) {			
		return stateRepository.findByStateId(stateId);
	}

	@Override
	public StateMaster update(Long stateId, String stateName)
			throws DuplicateStateException {
		StateMaster state = findByStateId(stateId);
		state.setStateName(stateName);
		return stateRepository.save(state);
		}
	

	@Override
	public List<StateMaster> findByStateName(String name) {

		return stateRepository.findByStateName(name);
	}

	@Override
	public StateMaster delete(Long stateId) {
		StateMaster state = findByStateId(stateId);
		state.setActive(false);
		return stateRepository.save(state);
		
	}

	@Override
	public StateMaster restore(Long stateId){
		StateMaster state = findByStateId(stateId);
		state.setActive(true);
		 return stateRepository.save(state);
	}
	
	
}