package com.astrika.abg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.astrika.abg.exception.CustomException;
import com.astrika.abg.exception.DuplicateCorporateException;
import com.astrika.abg.exception.DuplicateLoginException;
import com.astrika.abg.exception.NoSuchCorporateException;
import com.astrika.abg.model.CorporateMaster;
import com.astrika.abg.model.Status;
import com.astrika.abg.repository.CorporateRepository;
import com.astrika.abg.service.CorporateService;
import com.astrika.abg.service.UserService;
import com.astrika.abg.util.HandlerValue;

@Service
public class CorporateServiceImpl implements CorporateService{
	
	@Autowired
	private CorporateRepository corporateRepository;
	
	@Autowired
	private UserService userService;

	/*@Autowired
	private AreaService areaService;

	@Autowired
	private CityService cityService;

	@Autowired
	private StateService stateService;*/


	@Autowired
	private HandlerValue props;

	@Override
	public CorporateMaster save(CorporateMaster corporate) throws DuplicateCorporateException {
		CorporateMaster corporate1 = corporateRepository.findByCorporateName(corporate.getCorporateName());
		if (corporate1 != null) { // company already exist
			if (corporate1.isActive())
				throw new DuplicateCorporateException(
						CustomException.DUPLICATE_BRAND.getCode());
		} else {
			corporate.getCorporateAdmin().setLoginId(props.CORPORATE_LOGIN_PREFIX + corporate.getCorporateAdmin().getLoginId());

			try {
			userService.findByLoginId(corporate.getCorporateAdmin().getLoginId());
				throw new DuplicateLoginException(
						CustomException.DUPLICATE_LOGIN.getCode());
			} catch (DuplicateLoginException e) {
				// LoginId does not exit, hence can be created
			}

			 userService.saveUser(corporate.getCorporateAdmin());

			 corporate.setCorporateAdmin(corporate.getCorporateAdmin());
			corporate = corporateRepository.save(corporate);
			
		}
		return corporate;
	}

	@Override
	public void delete(Long id) throws NoSuchCorporateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void restore(Long id) throws NoSuchCorporateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CorporateMaster findById(long id) throws NoSuchCorporateException {
		// TODO Auto-generated method stub
		return corporateRepository.findById(id);
	}

	@Override
	public List<CorporateMaster> findAllByOrderByCorporateName(boolean active) {
		List<CorporateMaster> corporateList=corporateRepository.findAllByOrderByCorporateName(active);
		return corporateList;
	}

	@Override
	public CorporateMaster findByCorporateName(String corporateName) {
		return corporateRepository.findByCorporateName(corporateName);
	}

	@Override
	public void acceptAndReject(Long corporateId, Status status) throws NoSuchCorporateException {
		CorporateMaster corporate=findById(corporateId);
		corporate.setStatus(status);
		corporateRepository.save(corporate);
	}
	
	@Override
	public List<CorporateMaster> findAllStatus(Status status) {
		
		return corporateRepository.findAllByStatus(status);
	}

}
