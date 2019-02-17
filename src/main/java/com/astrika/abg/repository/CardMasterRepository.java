package com.astrika.abg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.astrika.abg.model.CardMaster;
import com.astrika.abg.model.CardType;

public interface CardMasterRepository extends JpaRepository<CardMaster, Long> {
	
	List<CardMaster> findByActive(boolean active);
	
	@Query("select count(c) from CardMaster c where c.cardType=?1")
	long countAssignedCardType(CardType type);

}
