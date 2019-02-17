package com.astrika.abg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.astrika.abg.model.OutletMaster;

public interface OutletRepository  extends JpaRepository<OutletMaster, Long>{
	
	
	@Query("select rest from OutletMaster rest join fetch rest.outletAdmin join fetch rest.outletState join fetch rest.outletCity join fetch rest.outletArea join fetch rest.brand b join fetch b.company   where  rest.active=1 ")
	List<OutletMaster> findAllByActiveTrueOrderByNameAsc();

	@Query("select rest from OutletMaster rest  join fetch rest.outletAdmin  join fetch rest.outletState join fetch rest.outletCity join fetch rest.outletArea join fetch rest.brand b join fetch b.company  where  rest.active=0")
	List<OutletMaster> findAllByActiveFalseOrderByNameAsc();
	
	@Query("select rest from OutletMaster rest " + "join fetch rest.outletAdmin user "
			+ "join fetch rest.brand b " + "join fetch b.company "
			+ "join fetch rest.outletArea area " + "join fetch area.city "
			+ "join fetch rest.outletCity city " + "join fetch city.state "
			+ "join fetch rest.outletState " 
			+ "where rest.outletId = ?1")
	OutletMaster findById(long id);

}  
