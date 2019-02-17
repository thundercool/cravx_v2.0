package com.astrika.abg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.astrika.abg.exception.BusinessException;
import com.astrika.abg.exception.CustomException;
import com.astrika.abg.exception.DuplicateLoginException;
import com.astrika.abg.exception.NoSuchAreaException;
import com.astrika.abg.exception.NoSuchCityException;
import com.astrika.abg.model.AreaMaster;
import com.astrika.abg.model.BrandMaster;
import com.astrika.abg.model.CityMaster;
import com.astrika.abg.model.CompanyMaster;
import com.astrika.abg.model.OutletMaster;
import com.astrika.abg.model.StateMaster;
import com.astrika.abg.model.User;
import com.astrika.abg.repository.OutletRepository;
import com.astrika.abg.service.AreaService;
import com.astrika.abg.service.BrandService;
import com.astrika.abg.service.CityService;
import com.astrika.abg.service.CompanyService;
import com.astrika.abg.service.OutletService;
import com.astrika.abg.service.StateService;
import com.astrika.abg.service.UserService;
import com.astrika.abg.util.HandlerValue;

@Service
public class OutletServiceImpl implements OutletService {
	
	@Autowired
	private OutletRepository outletRepository;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private BrandService brandService;
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private HandlerValue props;
	
	@Autowired
	private AreaService areaService;

	@Autowired
	private CityService cityService;
	
	@Autowired
	private StateService stateService;
	

	@Override
	public List<OutletMaster> findAllByActiveTrueOrderByNameAsc() {
    List<OutletMaster> outletList=outletRepository.findAllByActiveTrueOrderByNameAsc();
    return outletList;
	}

	@Override
	public List<OutletMaster> findAllByActiveFalseOrderByNameAsc() {
		List<OutletMaster> outletList=outletRepository.findAllByActiveFalseOrderByNameAsc();
	    return outletList;
	}

	@Override
	public OutletMaster saveOutlet(OutletMaster restaurant) {
		String loginId = props.RESTAURANT_LOGIN_PREFIX
				+ restaurant.getOutletAdmin().getLoginId();
		restaurant.getOutletAdmin().setLoginId(loginId);
	   User user = userService.findByLoginId(restaurant.getOutletAdmin().getLoginId());
			if(user!=null){
				try {
					throw new DuplicateLoginException(CustomException.DUPLICATE_LOGIN.getCode());
				} catch (DuplicateLoginException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		

		AreaMaster area = null;
		try {
			area = areaService
					.findById(restaurant.getOutletArea().getAreaId());
		} catch (NoSuchAreaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CityMaster city= null;
		try {
			city = cityService
					.findById(restaurant.getOutletCity().getCityId());
		} catch (NoSuchCityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StateMaster state= null;
		try {
			state = stateService.findByStateId(restaurant.getOutletState()
					.getStateId());
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		restaurant.setOutletArea(area);
		restaurant.setOutletCity(city);
		restaurant.setOutletState(state);
		//restaurant.getAdmin().setStatus(Status.Active);
		//fillAddress(restaurant);
		user = userService.saveUser(restaurant.getOutletAdmin());

		restaurant.setOutletAdmin(user);
		restaurant = outletRepository.save(restaurant);
		return restaurant;
	}

	@Override
	public OutletMaster saveSingleOutlet(OutletMaster outlet) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public OutletMaster saveSingleRestaurant(CompanyMaster company,
			User companyAdmin, BrandMaster brand,
			OutletMaster restaurant) throws Exception {

		company = companyService.save(company);
		brand.setCompany(company);
		brand = brandService.save(brand);
		restaurant.setCompany(company);
		restaurant.setBrand(brand);
		String loginId = props.RESTAURANT_LOGIN_PREFIX
				+ restaurant.getOutletAdmin().getLoginId();
		restaurant.getOutletAdmin().setLoginId(loginId);
	   User user = userService.findByLoginId(restaurant.getOutletAdmin().getLoginId());
			if(user!=null){
				throw new DuplicateLoginException(CustomException.DUPLICATE_LOGIN.getCode());
			}
		

		AreaMaster area = areaService
				.findById(restaurant.getOutletArea().getAreaId());
		CityMaster city = cityService
				.findById(restaurant.getOutletCity().getCityId());
		StateMaster state = stateService.findByStateId(restaurant.getOutletState()
				.getStateId());
		restaurant.setOutletArea(area);
		restaurant.setOutletCity(city);
		restaurant.setOutletState(state);
		//restaurant.getAdmin().setStatus(Status.Active);
		//fillAddress(restaurant);
		user = userService.saveUser(restaurant.getOutletAdmin());

		restaurant.setOutletAdmin(user);
		restaurant = outletRepository.save(restaurant);
		return restaurant;

	}

	@Override
	public OutletMaster findByOutletId(Long outletId) {
		return outletRepository.findById(outletId);
	}
	
	

}
