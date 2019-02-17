package com.astrika.abg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.astrika.abg.model.AreaMaster;

public interface AreaRepository extends JpaRepository<AreaMaster, Long> {
	
	@Query("select a from AreaMaster a join fetch a.city c join fetch c.state where a.active =?1 Order By a.areaName ASC")
	List<AreaMaster> findByActiveOrderByAreaNameAsc(boolean active);
	
	List<AreaMaster> findByAreaName(String name);
	
	@Query("select a from AreaMaster a join fetch a.city  where a.areaId=?1")
	AreaMaster findById(long areaId);

	@Query("select a from AreaMaster a join fetch a.city  where a.active =1 and a.city.cityId=?1 and a.state.stateId=?2 Order By a.areaName ASC")
	List<AreaMaster> findByCityCityIdAndStateStateIdAndActiveTrueOrderByAreaNameAsc(long cityId,long stateId);
	
	@Query("select a from AreaMaster a join fetch a.city  where a.active =1 and a.city.cityId=?1 Order By a.areaName ASC")
	List<AreaMaster> findByActiveTrueAndCityCityId(long cityId);
}
