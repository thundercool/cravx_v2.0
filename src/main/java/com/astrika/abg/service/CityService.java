package com.astrika.abg.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.astrika.abg.exception.BusinessException;
import com.astrika.abg.exception.DuplicateCityException;
import com.astrika.abg.exception.NoSuchCityException;
import com.astrika.abg.model.CityMaster;



public interface CityService {

	/**
	 * Save city
	 * 
	 * @param cityName
	 * @param showInPopup
	 * @param countryId
	 * @param timeZoneName
	 * @return
	 * @throws DuplicateCityException
	 */
	
	/*CityMaster save(String cityName, long stateId)
			throws DuplicateCityException, BusinessException,
			IOException;*/
	
	CityMaster save(CityMaster city)
			throws DuplicateCityException, BusinessException,
			IOException;

	/**
	 * Delete City
	 * 
	 * @param cityId
	 * @return
	 * @throws NoSuchCityException
	 */
	CityMaster delete(long cityId) throws NoSuchCityException;

	/**
	 * Restore City
	 * 
	 * @param cityId
	 * @return
	 * @throws NoSuchCityException
	 */
	CityMaster restore(long cityId) throws NoSuchCityException;

	/**
	 * Update
	 * 
	 * @param cityId
	 * @param cityName
	 * @param showInPopup
	 * @param countryId
	 * @param timeZoneName
	 * @return
	 * @throws NoSuchCityException
	 * @throws DuplicateCityException
	 */
	CityMaster update(long cityId, String cityName, long stateId) throws NoSuchCityException,
			DuplicateCityException, BusinessException,
			IOException;

	//
	// /**
	// *
	// * @param start
	// * @param end
	// * @return
	// */
	// List<CityMaster> findByInActive(int start, int end);

	// List<CityMaster> findByActive(int start, int end);

	/**
	 * 
	 * @return
	 */
	List<CityMaster> findByActive(boolean active);

	/**
	 * Fetch with country
	 * 
	 * @param cityId
	 * @return
	 * @throws NoSuchCityException
	 */
	CityMaster findById(long cityId) throws NoSuchCityException;

	/**
	 * without country
	 * 
	 * @return
	 */

	/**
	 * 
	 * @param countryId
	 * @return
	 */

	List<CityMaster> findByIds(Long[] cities);



	/**
	 * without country
	 * 
	 * @return
	 */
	List<CityMaster> findByCityName(String name);

	List<CityMaster> findLatestAdded(int start, int end);
	
	CityMaster save(String cityName, Long stateId) throws BusinessException ;
	
	List<CityMaster> findByStateId(Long stateId);


	
	
	
}
