package com.astrika.abg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.astrika.abg.model.StateMaster;


public interface StateRepository extends JpaRepository<StateMaster, Long> {

	
	@Query("select state from StateMaster state  where state.stateId = ?1") 
	StateMaster findByStateId(Long stateId);

	@Query("select state from StateMaster state  where state.stateName=?1")
	List<StateMaster> findByStateName(String stateName);


	@Query("select state from StateMaster state  where state.active = ?1 Order By state.stateName Asc")
	List<StateMaster> findByActive(boolean Active);
	
	
	@Query("select state from StateMaster state  where state.active = true Order By state.stateName Asc")
	List<StateMaster> findAllByActiveTrue();
	
	
}
