package com.astrika.abg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.astrika.abg.exception.DuplicateAreaException;
import com.astrika.abg.exception.NoSuchAreaException;
import com.astrika.abg.model.AreaMaster;
import com.astrika.abg.model.CityMaster;
import com.astrika.abg.model.StateMaster;
import com.astrika.abg.repository.AreaRepository;
import com.astrika.abg.service.AreaService;
import com.astrika.abg.exception.CustomException;

/**
 * Service implementation for area.
 * 
 * @author Rohit
 */
@Service
public class AreaServiceImpl implements AreaService {

	@Autowired
	public AreaRepository areaRepository;

	@Override
	public AreaMaster save(String areaName, Long cityId, Long stateId,
			double latitude, double longitude) throws DuplicateAreaException {
		List<AreaMaster> areas = findByName(areaName);
		if (areas.size()>0) { // area already exist
			for (AreaMaster areamaster : areas) {
				if (areamaster.getCity().getCityId() == cityId
						&& areamaster.getState().getStateId() == stateId) {
					throw new DuplicateAreaException(
							CustomException.DUPLICATE_AREA.getCode());
				}
			}
		} 
		CityMaster city = new CityMaster();
		city.setCityId(cityId);
		StateMaster state = new StateMaster();
		state.setStateId(stateId);
		AreaMaster area = new AreaMaster(areaName, city, state, 1.0,
				1.0);
		return areaRepository.save(area);
	}

	@Override
	public AreaMaster delete(long areaId) throws NoSuchAreaException {
		AreaMaster area = findById(areaId);
		area.setActive(false);
		return areaRepository.save(area);
	}

	@Override
	public AreaMaster restore(long areaId) throws NoSuchAreaException {
		AreaMaster area = findById(areaId);
		area.setActive(true);
		return areaRepository.save(area);
	}

	@Override
	public AreaMaster update(long areaId, String areaName, Long cityId,
			Long stateId, double latitude, double longitude)
			throws NoSuchAreaException, DuplicateAreaException {
		List<AreaMaster> areas = findByName(areaName);
		if (areas != null) { // area already exist
			for (AreaMaster areamaster : areas) {
				if (areamaster.getAreaId() != areaId && areamaster.getCity().getCityId() == cityId
						&& areamaster.getState().getStateId() == stateId) {
					throw new DuplicateAreaException(
							CustomException.DUPLICATE_AREA.getCode());
				}
			}
		}
		AreaMaster area = findById(areaId);
		CityMaster city = new CityMaster();
		city.setCityId(cityId);
		StateMaster state = new StateMaster();
		state.setStateId(stateId);
		area.setAreaName(areaName);
		area.setCity(city);
		area.setState(state);
		area.setLatitude(latitude);
		area.setLongitude(longitude);
		return areaRepository.save(area);
	}

//	@Override
//	public List<AreaMaster> findByInActive(int start, int end) {
//		Page<AreaMaster> areaList = areaRepository
//				.findByActiveFalseOrderByAreaNameAsc(new PageRequest(start,
//						end, Direction.ASC, "areaName"));
//		return areaList.getContent();
//	}
//
//	@Override
//	public List<AreaMaster> findByActive(int start, int end) {
//		Page<AreaMaster> areaList = areaRepository
//				.findByActiveTrueOrderByAreaNameAsc(new PageRequest(start, end,
//						Direction.ASC, "areaName"));
//		return areaList.getContent();
//	}

	@Override
	public AreaMaster findById(long areaId) throws NoSuchAreaException {
		AreaMaster area = areaRepository.findById(areaId);
		if (area == null) {
			throw new NoSuchAreaException(
					CustomException.NO_SUCH_AREA.getCode());
		}
		return area;
	}

	@Override
	public List<AreaMaster> findByActive(boolean active) {
		return areaRepository.findByActiveOrderByAreaNameAsc(active);
	}

	@Override
	public List<AreaMaster> findByName(String areaName) {
		return areaRepository.findByAreaName(areaName);
	}

	@Override
	public List<AreaMaster> findAllByActiveByCityIdAndStateId(long cityId,
			long stateId) {
		return areaRepository
				.findByCityCityIdAndStateStateIdAndActiveTrueOrderByAreaNameAsc(
						cityId, stateId);
	}

	@Override
	public List<AreaMaster> findActiveByCityId(long cityId) {
		return areaRepository.findByActiveTrueAndCityCityId(cityId);
	}

}
