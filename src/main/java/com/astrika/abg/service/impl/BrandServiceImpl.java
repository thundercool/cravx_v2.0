package com.astrika.abg.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.astrika.abg.exception.CustomException;
import com.astrika.abg.exception.DuplicateBrandException;
import com.astrika.abg.exception.DuplicateLoginException;
import com.astrika.abg.exception.NoSuchBrandException;
import com.astrika.abg.exception.NoSuchUserException;
import com.astrika.abg.model.BrandMaster;
import com.astrika.abg.model.User;
import com.astrika.abg.repository.BrandRepository;
import com.astrika.abg.service.AreaService;
import com.astrika.abg.service.BrandService;
import com.astrika.abg.service.CityService;
import com.astrika.abg.service.CompanyService;
import com.astrika.abg.service.StateService;
import com.astrika.abg.service.UserService;
import com.astrika.abg.util.HandlerValue;



@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private UserService userService;

	@Autowired
	private AreaService areaService;

	@Autowired
	private CityService cityService;

	/*@Autowired
	private SendMailService mailService;*/

	@Autowired
	private StateService stateService;



	@Autowired
	private HandlerValue props;


	@Override
	@Transactional
	public BrandMaster save(BrandMaster brand) throws DuplicateBrandException,
			 DuplicateLoginException{

		BrandMaster brandMaster = findByBrandNameAndCompanyCompanyId(
				brand.getBrandName(), brand.getCompany().getCompanyId());
		if (brandMaster != null) { // brand already exist
			if (brandMaster.isActive())
				throw new DuplicateBrandException(
						CustomException.DUPLICATE_BRAND.getCode());
			else
				throw new DuplicateBrandException(
						CustomException.DUPLICATE_DELETED_BRAND.getCode());
		} else {
			User user;
			List<User> users = null;
			String loginId = props.BRAND_LOGIN_PREFIX
					+ brand.getBrandAdmin().getLoginId();
			brand.getBrandAdmin().setLoginId(loginId);
			try {
				user = userService.findByLoginId(brand.getBrandAdmin()
						.getLoginId());
				throw new DuplicateLoginException(
						CustomException.DUPLICATE_LOGIN.getCode());
			} catch (DuplicateLoginException e) {
				// LoginId does not exit, hence can be created
			}

		  user = userService.saveUser(brand.getBrandAdmin());

			brand.setBrandAdmin(user);
			brand = brandRepository.save(brand);
		}
			/*userService.sendRegstrationMail(user, brand.getBrandName(), url);
			compareFeatures(brand.getBrandId(), brand.getCompany()
					.getCompanyId());
			if (control != null) {
				control.setCompany(brand.getCompany());
				control.setBrand(brand);
				control.setLevel(OutletFeatureControlLevel.BRAND);
				controlService.add(control);
			}*/

		return brand;
	}

	/*private void compareFeatures(long brandId, long companyId) {
		boolean validate = false;
		OutletFeatureControl brandFeature = controlService
				.findByBrand(brandId);
		OutletFeatureControl companyFeature = controlService
				.findByCompany(companyId);
		if (brandFeature != null && companyFeature != null) {
			if (brandFeature.isOffer() != companyFeature.isOffer()) {
				validate = true;
			}
			if (brandFeature.isOffer() != companyFeature.isOffer()) {
				validate = true;
			}
			if (brandFeature.isPromocode() != companyFeature.isPromocode()) {
				validate = true;
			}
			if (brandFeature.isGiftAMeal() != companyFeature.isGiftAMeal()) {
				validate = true;
			}
			if (brandFeature.isEvite() != companyFeature.isEvite()) {
				validate = true;
			}
			if (brandFeature.isPreorder() != companyFeature.isPreorder()) {
				validate = true;
			}
			if (brandFeature.isMpay() != companyFeature.isMpay()) {
				validate = true;
			}
			if (brandFeature.isWaitTimeTracker() != companyFeature
					.isWaitTimeTracker()) {
				validate = true;
			}
			if (brandFeature.isSplitBill() != companyFeature.isSplitBill()) {
				validate = true;
			}
			if (brandFeature.isTableRegistration() != companyFeature
					.isTableRegistration()) {
				validate = true;
			}
			if (brandFeature.isCallForService() != companyFeature
					.isCallForService()) {
				validate = true;
			}
		}

		if (validate) {
			HashMap<String, Object> model = new HashMap<String, Object>();
			model.put("brandName", brandFeature.getBrand().getBrandName());
			EmailData data = new EmailData();
			data.setTo(brandFeature.getBrand().getCompany().getCompanyAdmin()
					.getEmailId());
			data.setSubject("Brand features have been changed.");
			data.setFrom(props.EMAIL_FROM);
			data.setModel(model);
			data.setMailTemplate(props.BRAND_FEATURE_CHANGE_TO_COMPANY);
			mailService.sendMail(data);
		}
	}*/

	/*@Override
	public BrandMaster delete(long brandId) {
		List<User> userList = new ArrayList<User>();
		List<Outlet> outletList = new ArrayList<Outlet>();
		BrandMaster brand = brandRepository.findById(brandId);
		brand.setActive(false);
		User brandAdmin = brand.getBrandAdmin();
		brandAdmin.setStatus(Status.Inactive);
		userList.add(brandAdmin);
		outletList = outletService.findAllByBrandAndActive(brand
				.getBrandId());
		for (Outlet outlet : outletList) {
			outlet.setActive(false);
			User outletAdmin = outlet.getAdmin();
			outletAdmin.setStatus(Status.Inactive);
			userList.add(outletAdmin);
		}

		outletService.updateAll(outletList);
		userService.updateAll(userList);
		return brandRepository.save(brand);
	}

	@Override
	public BrandMaster restore(long brandId) {
		List<User> userList = new ArrayList<User>();
		List<Outlet> outletList = new ArrayList<Outlet>();
		BrandMaster brand = brandRepository.findById(brandId);
		brand.setActive(true);
		User brandAdmin = brand.getBrandAdmin();
		brandAdmin.setStatus(Status.Active);
		userList.add(brandAdmin);
		outletList = outletService.findAllInactiveByBrand(brand
				.getBrandId());
		for (Outlet outlet : outletList) {
			outlet.setActive(true);
			User outletAdmin = outlet.getAdmin();
			outletAdmin.setStatus(Status.Active);
			userList.add(outletAdmin);
		}

		outletService.updateAll(outletList);
		userService.updateAll(userList);
		return brandRepository.save(brand);
	}

	@Override
	@Transactional
	public BrandMaster update(BrandMaster brand)
			throws DuplicateBrandException, DuplicateEmailException,
			NoSuchAreaException, NoSuchCityException, NoSuchCountryException,
			NoSuchUserException, NoSuchBrandException,
			DuplicateMobileException, NoSuchOutletException {
		CompanyMaster comp = companyService.findByCompanyName(brand
				.getCompany().getCompanyName());
		BrandMaster brandMaster = findByBrandNameAndCompanyCompanyId(
				brand.getBrandName(), comp.getCompanyId());
		if (brandMaster != null
				&& brand.getBrandId().longValue() != brandMaster.getBrandId().longValue()) { // brand
																		// already
																		// exist
			if (brandMaster.isActive())
				throw new DuplicateBrandException(
						CustomException.DUPLICATE_BRAND.getCode());
			else
				throw new DuplicateBrandException(
						CustomException.DUPLICATE_DELETED_BRAND.getCode());
		} else {
			User user;
			List<User> users = null;
			try {
				users = userService.findByEmailId(brand.getBrandAdmin()
						.getEmailId());
				if (users != null && users.size() > 0) {
					for (User u : users) {
						boolean validation = true;
						if (u.getRole() == Role.COMPANY_ADMIN
								&& comp.getCompanyId().longValue() == u.getModuleId().longValue()) {
							validation = false;
						}
						if (u.getUserId().longValue() == brand.getBrandAdmin()
								.getUserId().longValue()) {
							validation = false;
						}
						if (u.getRole() == Role.BRAND_ADMIN) {
							BrandMaster b = findById(u.getModuleId());
							if (comp.getCompanyId().longValue() == b.getCompany()
									.getCompanyId().longValue()) {
								validation = false;
							}
						}
						if (u.getRole() == Role.OUTLET_ADMIN) {
							Outlet rest = outletService.findById(u
									.getModuleId());
							if (comp.getCompanyId().longValue() == rest
									.getBrand().getCompany().getCompanyId().longValue()) {
								validation = false;
							}
						}
						if (validation) {
							throw new DuplicateEmailException(
									CustomException.DUPLICATE_ADMIN_EMAIL
											.getCode());
						}
					}
				}
				users = userService.findByAdminMobile(brand.getBrandAdmin()
						.getMobile());
				if (users != null && users.size() > 0) {
					for (User u : users) {
						boolean validation = true;
						if (u.getRole() == Role.COMPANY_ADMIN
								&& comp.getCompanyId().longValue() == u.getModuleId().longValue()) {
							validation = false;
						}
						if (u.getUserId().longValue() == brand.getBrandAdmin()
								.getUserId().longValue()) {
							validation = false;
						}
						if (u.getRole() == Role.BRAND_ADMIN) {
							BrandMaster b = findById(u.getModuleId());
							if (comp.getCompanyId().longValue() == b.getCompany()
									.getCompanyId().longValue()) {
								validation = false;
							}
						}
						if (u.getRole() == Role.OUTLET_ADMIN) {
							Outlet rest = outletService.findById(u
									.getModuleId());
							if (comp.getCompanyId().longValue() == rest
									.getBrand().getCompany().getCompanyId().longValue()) {
								validation = false;
							}
						}
						if (validation) {
							if(brand.getBrandAdmin().getOtp()!=null && !brand.getBrandAdmin().getOtp().isEmpty()){
								u.setMobile(null);
								userService.save(u);
							}
							else {
							throw new DuplicateMobileException(
									CustomException.DUPLICATE_ADMIN_MOBILE
											.getCode());
							}
						}
					}
				}
			} catch (NoSuchUserException e) {
				// EmailId does not exit, hence can be created
			}

			AreaMaster area = areaService.findById(brand.getBrandArea()
					.getAreaId());
			CityMaster city = cityService.findById(brand.getBrandCity()
					.getCityId());
			CountryMaster country = countryService.findById(brand
					.getBrandCountry().getCountryId()); 					//Country,City,Area removed from Contact Info

			user = userService.findById(brand.getBrandAdmin().getUserId());
			user.setFirstName(brand.getBrandAdmin().getFirstName());
			user.setLastName(brand.getBrandAdmin().getLastName());
			user.setEmailId(brand.getBrandAdmin().getEmailId());
			user.setMobile(brand.getBrandAdmin().getMobile());
			userService.update(user);

			brandMaster = findById(brand.getBrandId());
			brandMaster.setBrandAddressLine1(brand.getBrandAddressLine1());
			brandMaster.setBrandAddressLine2(brand.getBrandAddressLine2());
			brandMaster.setBrandMobile1(brand.getBrandMobile1());
			brandMaster.setBrandMobile2(brand.getBrandMobile2());
			brandMaster.setBrandTelephone1(brand.getBrandTelephone1());
			brandMaster.setBrandTelephone2(brand.getBrandTelephone2());
//			brandMaster.setBrandArea(area);
//			brandMaster.setBrandCity(city);
//			brandMaster.setBrandCountry(country);							//Country,City,Area removed from Contact Info
			brandMaster.setBrandPincode(brand.getBrandPincode());
			brandMaster.setBrandEmail(brand.getBrandEmail());
			brandMaster.setBrandName(brand.getBrandName());
			brandMaster.setIncludeInMembership(brand.isIncludeInMembership());
			brandRepository.save(brandMaster);
			compareFeatures(brandMaster.getBrandId(), brandMaster.getCompany()
					.getCompanyId());
			return brandMaster;
		}
	}*/

	@Override
	public void updateAll(List<BrandMaster> brandList) {
		brandRepository.save(brandList);
	}

	@Override
	public List<BrandMaster> findByInActive(int start, int end) {
		Page<BrandMaster> brandList = brandRepository
				.findByActiveFalseOrderByBrandNameAsc(new PageRequest(start,
						end, Direction.ASC, "brandName"));
		return brandList.getContent();
	}

	@Override
	public List<BrandMaster> findByActive(int start, int end) {
		Page<BrandMaster> brandList = brandRepository
				.findByActiveTrueOrderByBrandNameAsc(new PageRequest(start,
						end, Direction.ASC, "brandName"));
		return brandList.getContent();
	}

	@Override
	public BrandMaster findById(long brandId) throws NoSuchBrandException {
		BrandMaster brand = brandRepository.findById(brandId);
		if (brand == null) {
			throw new NoSuchBrandException(
					CustomException.NO_SUCH_BRAND.getCode());
		}
		return brand;
	}

	@Override
	public List<BrandMaster> findAllByInActive() {
		return brandRepository.findAllByActiveFalseOrderByBrandNameAsc();
	}

	@Override
	public List<BrandMaster> findAllByActive() {
		return brandRepository.findAllByActiveTrueOrderByBrandNameAsc();
	}

	@Override
	public List<BrandMaster> findAllByCompanyId(long companyId) {
		return brandRepository
				.findAllByActiveTrueAndCompanyCompanyIdOrderByBrandNameAsc(companyId);
	}

	@Override
	public List<BrandMaster> findAllInactiveByCompanyId(long companyId) {
		return brandRepository
				.findAllByActiveFalseAndCompanyCompanyId(companyId);
	}

	@Override
	public List<BrandMaster> findByIds(Long[] brands) {
		Iterable<Long> brandIds = Arrays.asList(brands);
		return brandRepository.findAll(brandIds);
	}

	@Override
	public BrandMaster findByBrandNameAndCompanyCompanyId(String brandName,
			Long companyId) {
		return brandRepository.findByBrandNameAndCompanyCompanyId(brandName,
				companyId);
	}

	@Override
	public long findCountByCompany(long comapnyId, boolean active) {
		return brandRepository.findCountByCompany(comapnyId, active);
	}

	@Override
	public BrandMaster delete(long brandId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BrandMaster restore(long brandId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BrandMaster update(BrandMaster brand) throws DuplicateBrandException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
