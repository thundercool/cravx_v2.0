package com.astrika.abg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.astrika.abg.model.GenericMaster;
import com.astrika.abg.model.GenericMasterType;


public interface GenericMasterRepository extends JpaRepository<GenericMaster, Long> {
	
      List<GenericMaster> findAllByActive(Boolean active);
      
      GenericMaster findByNameAndGenericMasterType(String name,GenericMasterType type);
      
      List<GenericMaster> findByGenericMasterTypeAndActiveTrue(GenericMasterType type);
}
