package com.astrika.abg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.astrika.abg.exception.DuplicateGenericMasterException;
import com.astrika.abg.model.GenericMaster;
import com.astrika.abg.model.GenericMasterType;
import com.astrika.abg.repository.GenericMasterRepository;
import com.astrika.abg.service.GenericMasterService;
import com.astrika.abg.exception.CustomException;


@Service
public class GenericMasterServiceImpl implements GenericMasterService {

	@Autowired
	public GenericMasterRepository repository;

	@Override
	public GenericMaster save(GenericMaster genericMaster)
			throws DuplicateGenericMasterException {
		GenericMaster genericMaster2 = repository
				.findByNameAndGenericMasterType(genericMaster.getName(),
						genericMaster.getGenericMasterType());
		if (genericMaster2 != null
				&& (genericMaster.getId() != null ?
				 genericMaster2.getId().longValue() != genericMaster.getId()
						.longValue():true)) 
		{
			throw new DuplicateGenericMasterException(
					CustomException.DUPLICATE_ADDRESS_CITY_EXCEPTION.getCode());
		}
		return repository.save(genericMaster);
	}

	@Override
	public GenericMaster update(GenericMaster genericMaster) {
		return repository.save(genericMaster);
	}

	@Override
	public GenericMaster delete(long id) {
		GenericMaster genericMaster = findById(id);
		genericMaster.setActive(false);
		return repository.save(genericMaster);
	}

	@Override
	public GenericMaster resort(long id) {
		GenericMaster genericMaster = findById(id);
		genericMaster.setActive(true);
		return repository.save(genericMaster);
	}

	@Override
	public GenericMaster findById(Long id) {
		return repository.findOne(id);
	}

	@Override
	public List<GenericMaster> findAllByActive(Boolean active) {
		// TODO Auto-generated method stub
		return repository.findAllByActive(active);
	}

	@Override
	public List<GenericMaster> findByGenericMasterType(GenericMasterType type) {
		// TODO Auto-generated method stub
		return repository.findByGenericMasterTypeAndActiveTrue(type);
	}

}
