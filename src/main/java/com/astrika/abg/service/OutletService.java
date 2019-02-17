package com.astrika.abg.service;

import java.util.List;

import com.astrika.abg.model.BrandMaster;
import com.astrika.abg.model.CompanyMaster;
import com.astrika.abg.model.OutletMaster;
import com.astrika.abg.model.User;

public interface OutletService {
	
	List<OutletMaster> findAllByActiveTrueOrderByNameAsc();
	
	List<OutletMaster> findAllByActiveFalseOrderByNameAsc();
	
	OutletMaster saveOutlet(OutletMaster outlet);
	
	OutletMaster saveSingleOutlet(OutletMaster outlet);
	
	OutletMaster findByOutletId(Long outletId);

	OutletMaster saveSingleRestaurant(CompanyMaster company, User companyAdmin, BrandMaster brand,
			OutletMaster restaurant) throws Exception;

}
