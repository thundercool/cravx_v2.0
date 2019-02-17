package com.astrika.abg.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.astrika.abg.model.CityMaster;

public interface CityRepository extends JpaRepository<CityMaster, Long> {

	@Query("select city from CityMaster city  join fetch city.state where city.active = ?1 Order By city.cityName Asc")
	List<CityMaster> findByActive(boolean active);


	@Query("select city from CityMaster city  join fetch city.state where city.cityName=?1")
	List<CityMaster> findByCityName(String cityName);

	@Query("select city from CityMaster city  join fetch city.state where city.cityId=?1")
	CityMaster findByCityId(Long id);

	@Query("select city from CityMaster city  where city.state.stateId=?1 and city.cityName= ?2 Order By city.cityName Asc")
	CityMaster findByStateStateIdAndCityName(long stateId, String cityName);
	
	@Query("select city from CityMaster city  join fetch city.state where city.active = 1 and city.state.stateId=?1 Order By city.cityName Asc")
	List<CityMaster> findByStateStateIdAndActiveTrueOrderByCityNameAsc(long stateId);
	
}
