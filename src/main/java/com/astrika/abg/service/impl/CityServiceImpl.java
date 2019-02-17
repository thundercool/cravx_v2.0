package com.astrika.abg.service.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.astrika.abg.exception.DuplicateCityException;
import com.astrika.abg.exception.NoSuchCityException;
import com.astrika.abg.model.CityMaster;
import com.astrika.abg.model.StateMaster;
import com.astrika.abg.repository.CityRepository;
import com.astrika.abg.service.CityService;
import com.astrika.abg.service.ImageService;
import com.astrika.abg.service.StateService;
import com.astrika.abg.exception.BusinessException;
import com.astrika.abg.exception.CustomException;
import com.astrika.abg.exception.SystemException;

/**
 * Service implementation for City.
 * 
 * @author Rohit
 */
@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private ImageService imageService;
	
	@Autowired
	private StateService stateService;
	

	/*
	 * @Override public CityMaster save(String cityName, boolean showInPopup,
	 * long countryId, String timeZone, MultipartFile profile) throws
	 * DuplicateCityException, SystemException, BusinessException, IOException {
	 * 
	 * 
	 * CityMaster city = cityRepository.findByCountryCountryIdAndCityName(
	 * countryId, cityName); if (city != null) { // city already exist throw new
	 * DuplicateCityException( CustomException.DUPLICATE_CITY.getCode());
	 * 
	 * } ImageMaster cityImage = null; if (profile != null && profile.getSize()
	 * > 0) { String filename = profile.getOriginalFilename(); String ext =
	 * filename.substring(filename.lastIndexOf(".")); cityImage =
	 * imageService.addRestaurantImage(profile.getBytes(), ext, "City." + ext,
	 * false, false, "City", cityName);
	 * 
	 * }
	 * 
	 * 
	 * CountryMaster country = new CountryMaster();
	 * country.setCountryId(countryId); city = new CityMaster(cityName,
	 * showInPopup, country, timeZone); city.setProfileImage(cityImage); return
	 * cityRepository.save(city); }
	 */

	@Override
	public CityMaster delete(long cityId) throws NoSuchCityException {
		CityMaster city = findById(cityId);
		city.setActive(false);
		return cityRepository.save(city);
	}

	@Override
	public CityMaster restore(long cityId) throws NoSuchCityException {
		CityMaster city = findById(cityId);
		city.setActive(true);
		return cityRepository.save(city);
	}

	@Transactional
	@Override
	public CityMaster update(long cityId, String cityName, long stateId) throws NoSuchCityException, DuplicateCityException,
			 BusinessException, IOException {
		List<CityMaster> cities = findByCityName(cityName);
		if (cities.size() > 0) { // city already exist
			for (CityMaster citymaster : cities) {
				if (citymaster.getState().getStateId() == stateId
						&& citymaster.getState().getStateId() == stateId
						&& citymaster.getCityId() != cityId) {
					throw new DuplicateCityException(
							CustomException.DUPLICATE_CITY.getCode());
				}
			}
		}

		CityMaster city = findById(cityId);
		StateMaster state = stateService.findByStateId(stateId);	
		city.setState(state);
		return cityRepository.save(city);
	}

	@Override
	public List<CityMaster> findByActive(boolean active) {
		return cityRepository.findByActive(active);
	}


	@Override
	public List<CityMaster> findByIds(Long[] cities) {
		Iterable<Long> citiesId = Arrays.asList(cities);
		return cityRepository.findAll(citiesId);
	}

	@Override
	public List<CityMaster> findByCityName(String name) {
		return cityRepository.findByCityName(name);
	}

	@Override
	public CityMaster findById(long cityId) throws NoSuchCityException {
		CityMaster city = cityRepository.findByCityId(cityId);
		if (city == null) {
			throw new NoSuchCityException(
					CustomException.NO_SUCH_CITY.getCode());
		}
		return city;
	}

	@Override
	public List<CityMaster> findLatestAdded(int start, int end) {
		// Page<CityMaster> latestCities = cityRepository.findByActiveTrue(new
		// PageRequest(start, end, Direction.DESC,
		// "createdOn"));
		// return latestCities.getContent();
		return null;
	}

/*	@Override
	public CityMaster save(String cityName, Long stateId)
			throws BusinessException{

		List<CityMaster> cities = cityRepository.findByCityName(cityName);
		if (cities.size() > 0) {
			for (CityMaster citymaster : cities) {
				if ((citymaster.getState().getStateId() == stateId)
						&& (citymaster.getState().getStateId() == stateId)) {
					throw new DuplicateCityException(
							CustomException.DUPLICATE_CITY.getCode());
				}
			}
		}

		StateMaster state = stateService.findByStateId(stateId);	
		CityMaster city1 = new CityMaster(cityName, state);
		return cityRepository.save(city1);
	}*/
	
	@Override
	public CityMaster save(CityMaster city)
			throws BusinessException{

		List<CityMaster> cities = cityRepository.findByCityName(city.getCityName());
		if (cities.size() > 0) {
			for (CityMaster citymaster : cities) {
				if ((citymaster.getState().getStateId() == city.getState().getStateId()))
						{
					throw new DuplicateCityException(
							CustomException.DUPLICATE_CITY.getCode());
				}
			}
		}

		/*StateMaster state = stateService.findByStateId(stateId);	
		CityMaster city1 = new CityMaster(cityName, state);*/
		return cityRepository.save(city);
	}

	

	@Override
	public List<CityMaster> findByStateId(Long stateId) {
		
		return cityRepository.findByStateStateIdAndActiveTrueOrderByCityNameAsc(stateId);
	}

	/*@Override
	public CityMaster save(String cityName, long stateId)
			throws DuplicateCityException, BusinessException, IOException {
		List<CityMaster> cities = cityRepository.findByCityName(cityName);
		if (cities.size() > 0) {
			for (CityMaster citymaster : cities) {
				if ((citymaster.getState().getStateId() == stateId)
						&& (citymaster.getState().getStateId() == stateId)) {
					throw new DuplicateCityException(
							CustomException.DUPLICATE_CITY.getCode());
				}
			}
		}

		StateMaster state = stateService.findByStateId(stateId);	
		CityMaster city1 = new CityMaster(cityName, state);
		return cityRepository.save(city1);
	}*/

	@Override
	public CityMaster save(String cityName, Long stateId) throws BusinessException {
		List<CityMaster> cities = cityRepository.findByCityName(cityName);
		if (cities.size() > 0) {
			for (CityMaster citymaster : cities) {
				if ((citymaster.getState().getStateId() == stateId)
						&& (citymaster.getState().getStateId() == stateId)) {
					throw new DuplicateCityException(
							CustomException.DUPLICATE_CITY.getCode());
				}
			}
		}

		StateMaster state = stateService.findByStateId(stateId);	
		CityMaster city1 = new CityMaster(cityName, state);
		return cityRepository.save(city1);
	}

}
