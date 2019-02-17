package com.astrika.abg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.astrika.abg.exception.CustomException;
import com.astrika.abg.exception.DuplicateCompanyException;
import com.astrika.abg.exception.DuplicateLoginException;
import com.astrika.abg.exception.NoSuchBrandException;
import com.astrika.abg.exception.NoSuchCompanyException;
import com.astrika.abg.model.BrandMaster;
import com.astrika.abg.model.CompanyMaster;
import com.astrika.abg.repository.BrandRepository;
import com.astrika.abg.repository.CompanyRepository;
import com.astrika.abg.service.AreaService;
import com.astrika.abg.service.CityService;
import com.astrika.abg.service.CompanyService;
import com.astrika.abg.service.StateService;
import com.astrika.abg.service.UserService;
import com.astrika.abg.util.HandlerValue;


@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	
	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private AreaService areaService;

	@Autowired
	private CityService cityService;

	@Autowired
	private StateService stateService;


	@Autowired
	private HandlerValue props;

	

	

	@Override
	@Transactional
	public CompanyMaster save(CompanyMaster company) throws DuplicateCompanyException,
			DuplicateLoginException
			 {

		CompanyMaster companyMaster = companyRepository
				.findByCompanyName(company.getCompanyName());
		if (companyMaster != null) { // company already exist
			if (companyMaster.isActive())
				throw new DuplicateCompanyException(
						CustomException.DUPLICATE_BRAND.getCode());
			else
				throw new DuplicateCompanyException(
						CustomException.DUPLICATE_DELETED_BRAND.getCode());
		} else {
			company.getCompanyAdmin().setLoginId(props.COMPANY_LOGIN_PREFIX + company.getCompanyAdmin().getLoginId());

			try {
			userService.findByLoginId(company.getCompanyAdmin().getLoginId());
				throw new DuplicateLoginException(
						CustomException.DUPLICATE_LOGIN.getCode());
			} catch (DuplicateLoginException e) {
				// LoginId does not exit, hence can be created
			}

			 userService.saveUser(company.getCompanyAdmin());

			company.setCompanyAdmin(company.getCompanyAdmin());
			company = companyRepository.save(company);
			/*admin.setModuleId(company.getCompanyId());
			admin.setModuleName(company.getCompanyName());
			admin.setCountry(company.getCompanyCountry());
			admin.setCity(company.getCompanyCity());
			user = userService.update(admin);
			userService.sendRegstrationMail(user, company.getCompanyName(), url);
			if (control != null) {
				control.setCompany(company);
				control.setLevel(OutletFeatureControlLevel.COMPANY);
				controlService.add(control);
			}*/
		}
		return company;
	}

	/*@Override
	public List<BrandMaster> delete(long companyId)
			throws NoSuchCompanyException {

		List<User> userList = new ArrayList<User>();
		List<Outlet> outletList = new ArrayList<Outlet>();
		CompanyMaster company = findById(companyId);
		company.setActive(false);
		User companyAdmin = company.getCompanyAdmin();
		companyAdmin.setStatus(Status.Inactive);
		userList.add(companyAdmin);
		List<BrandMaster> brandList = brandRepository
				.findAllByActiveTrueAndCompanyCompanyIdOrderByBrandNameAsc(companyId);

		for (BrandMaster brandMaster : brandList) {
			brandMaster.setActive(false);
			User brandAdmin = brandMaster.getBrandAdmin();
			brandAdmin.setStatus(Status.Inactive);
			userList.add(brandAdmin);

			outletList = outletService
					.findAllByBrandAndActive(brandMaster.getBrandId());
			for (Outlet outlet : outletList) {
				outlet.setActive(false);
				User outletAdmin = outlet.getAdmin();
				outletAdmin.setStatus(Status.Inactive);
				userList.add(outletAdmin);
			}
			outletService.updateAll(outletList);
		}
		// brandService.updateAll(brandList);
		userService.updateAll(userList);
		companyRepository.save(company);
		return brandList;
	}

	@Override
	public List<BrandMaster> restore(long companyId) {
		List<User> userList = new ArrayList<User>();
		List<Outlet> outletList = new ArrayList<Outlet>();
		CompanyMaster company = companyRepository.findById(companyId);
		company.setActive(true);
		User companyAdmin = company.getCompanyAdmin();
		companyAdmin.setStatus(Status.Active);
		userList.add(companyAdmin);
		List<BrandMaster> brandList = brandRepository
				.findAllByActiveFalseAndCompanyCompanyId(companyId);
		for (BrandMaster brandMaster : brandList) {
			brandMaster.setActive(true);
			User brandAdmin = brandMaster.getBrandAdmin();
			brandAdmin.setStatus(Status.Active);
			userList.add(brandAdmin);

			outletList = outletService
					.findAllInactiveByBrand(brandMaster.getBrandId());
			for (Outlet outlet : outletList) {
				outlet.setActive(true);
				User outletAdmin = outlet.getAdmin();
				outletAdmin.setStatus(Status.Active);
				userList.add(outletAdmin);
			}
			outletService.updateAll(outletList);
		}

		userService.updateAll(userList);
		companyRepository.save(company);
		return brandList;
	}

	@Override
	@Transactional
	public CompanyMaster update(CompanyMaster company, String otp)
			throws NoSuchUserException, DuplicateLoginException,
			DuplicateCompanyException, NoSuchAreaException,
			NoSuchCityException, NoSuchCountryException,
			DuplicateMobileException, DuplicateEmailException,
			NoSuchBrandException, NoSuchOutletException, OTPTextException, OTPExpireException {
		System.out.println(new Date());
		CompanyMaster companyMaster = companyRepository
				.findByCompanyName(company.getCompanyName());
		if (companyMaster != null
				&& companyMaster.getCompanyId().longValue() != company.getCompanyId().longValue()) 
		{ 
			// company already exist
			if (companyMaster.isActive())
				throw new DuplicateCompanyException(
						CustomException.DUPLICATE_COMPANY.getCode());
			else
				throw new DuplicateCompanyException(
						CustomException.DUPLICATE_DELETED_COMPANY.getCode());
		} else {
			User user = null;
			List<User> users = null;
			try {

				users = userService.findByEmailId(company.getCompanyAdmin()
						.getEmailId());
				if (users != null && users.size() > 0) {
					for (User u : users) {
						boolean validation = true;
						if (u.getRole() == Role.COMPANY_ADMIN
								&& company.getCompanyId().longValue() == u.getModuleId().longValue()) {
							validation = false;
						}
						if (u.getRole() == Role.BRAND_ADMIN) {
							BrandMaster b = findBrandById(u
									.getModuleId());
							if (company.getCompanyId().longValue() == b.getCompany()
									.getCompanyId().longValue()) {
								validation = false;
							}
						}
						if (u.getRole() == Role.OUTLET_ADMIN) {
							Outlet r = outletService.findById(u
									.getModuleId());
							if (company.getCompanyId().longValue() == r.getBrand()
									.getCompany().getCompanyId().longValue()) {
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

				users = userService.findByAdminMobile(company.getCompanyAdmin()
						.getMobile());
				if (users != null && users.size() > 0) {
					for (User u : users) {
						boolean validation = true;
						if (u.getRole() == Role.COMPANY_ADMIN
								&& company.getCompanyId().longValue() == u.getModuleId().longValue()) {
							validation = false;
						}
						if (u.getRole() == Role.BRAND_ADMIN) {
							BrandMaster b = findBrandById(u
									.getModuleId());
							if (company.getCompanyId().longValue() == b.getCompany()
									.getCompanyId().longValue()) {
								validation = false;
							}
						}
						if (u.getRole() == Role.OUTLET_ADMIN) {
							Outlet r = outletService.findById(u
									.getModuleId());
							if (company.getCompanyId().longValue() == r.getBrand()
									.getCompany().getCompanyId().longValue()) {
								validation = false;
							}
						}
						if (validation) {
							
							if(company.getCompanyAdmin().getOtp() != null && !company.getCompanyAdmin().getOtp().isEmpty()){
								u.setMobile(null);
								userService.saveUser(u);
							}
							else
							throw new DuplicateMobileException(
									CustomException.DUPLICATE_ADMIN_MOBILE
											.getCode());
						}
					}
				}
			} catch (NoSuchUserException e) {
				// Do nothing
			}

			AreaMaster area = areaService.findById(company.getCompanyArea()
					.getAreaId());
			CityMaster city = cityService.findById(company.getCompanyCity()
					.getCityId());
			CountryMaster country = countryService.findById(company
					.getCompanyCountry().getCountryId()); //After removing the contact info fields

			user = userService.findById(companyMaster.getCompanyAdmin().getUserId());
			user.setFirstName(company.getCompanyAdmin().getFirstName());
			user.setLastName(company.getCompanyAdmin().getLastName());
			user.setEmailId(company.getCompanyAdmin().getEmailId());
			if(otp!=null && !otp.isEmpty()){
				oTPService.check(company.getCompanyAdmin().getMobile(), otp);
			}
			user.setMobile(company.getCompanyAdmin().getMobile());
			userService.update(user);
			companyMaster = companyRepository.findById(company.getCompanyId());
			companyMaster.setCompanyAddressLine1(company
					.getCompanyAddressLine1());
			companyMaster.setCompanyAddressLine2(company
					.getCompanyAddressLine2());
			companyMaster.setCompanyMobile1(company.getCompanyMobile1());
			companyMaster.setCompanyMobile2(company.getCompanyMobile2());
			companyMaster.setCompanyTelephone1(company.getCompanyTelephone1());
			companyMaster.setCompanyTelephone2(company.getCompanyTelephone2());
//			companyMaster.setCompanyArea(area);
//			companyMaster.setCompanyCity(city);
//			companyMaster.setCompanyCountry(country); //After removing the contact info fields
			companyMaster.setCompanyPincode(company.getCompanyPincode());
			companyMaster.setCompanyEmail(company.getCompanyEmail());
			companyMaster.setCompanyName(company.getCompanyName());
			if (company.getAgreementFileUlr() != null
					&& company.getAgreementFileUlr().length() > 2) {
				companyMaster
						.setAgreementFileUlr(company.getAgreementFileUlr());
				companyMaster.setAgreementFileName(company
						.getAgreementFileName());
			}
			companyMaster
					.setCompAgreementStart(company.getCompAgreementStart());
			companyMaster.setCompAgreementEnd(company.getCompAgreementEnd());
			System.out.println(new Date());
			return companyRepository.save(companyMaster);
		}
	}*/

	@Override
	public List<CompanyMaster> findByInActive(int start, int end) {
		Page<CompanyMaster> companyList = companyRepository
				.findByActiveFalseOrderByCompanyNameAsc(new PageRequest(start,
						end, Direction.ASC, "companyName"));
		return companyList.getContent();
	}

	@Override
	public List<CompanyMaster> findByActive(int start, int end) {
		Page<CompanyMaster> companyList = companyRepository
				.findByActiveTrueOrderByCompanyNameAsc(new PageRequest(start,
						end, Direction.ASC, "companyName"));
		return companyList.getContent();
	}

	@Override
	public CompanyMaster findById(long companyId) throws NoSuchCompanyException {
		return companyRepository.findById(companyId);
	}

	@Override
	public List<CompanyMaster> findByActive(boolean active) {
		return companyRepository.findAllByActive(active);
	}

	@Override
	public CompanyMaster findByCompanyName(String name) {
		return companyRepository.findByCompanyName(name);
	}

	@Override
	public long findCount() {
		return companyRepository.count();
	}

	@Override
	public CompanyMaster updateSingleRest(long companyId)
			throws NoSuchCompanyException {
		CompanyMaster company = findById(companyId);
		//company.setSingleOutlet(false);
		companyRepository.save(company);
		return company;
	}
	
	
	@Override
	public BrandMaster findBrandById(long brandId) throws NoSuchBrandException {
		BrandMaster brand = brandRepository.findById(brandId);
		if (brand == null) {
			throw new NoSuchBrandException(
					CustomException.NO_SUCH_BRAND.getCode());
		}
		return brand;
	}

	@Override
	public List<BrandMaster> delete(long companyId) throws NoSuchCompanyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BrandMaster> restore(long companyId) throws NoSuchCompanyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompanyMaster update(CompanyMaster company) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompanyMaster findByCompanyLoginId(String loginId) {
		// TODO Auto-generated method stub
		return null;
	}

	/*@Override
	public CompanyMaster findByCompanyLoginId(String loginId) {
		CompanyMaster company=companyRepository.findByLoginId(loginId);
		return company;
	}
*/
}
