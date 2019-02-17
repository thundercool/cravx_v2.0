package com.astrika.abg.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.astrika.abg.exception.BusinessException;
import com.astrika.abg.exception.CustomException;
import com.astrika.abg.exception.DuplicateBrandException;
import com.astrika.abg.exception.DuplicateCompanyException;
import com.astrika.abg.exception.DuplicateLoginException;
import com.astrika.abg.exception.NoSuchAreaException;
import com.astrika.abg.exception.NoSuchBrandException;
import com.astrika.abg.exception.NoSuchCityException;
import com.astrika.abg.exception.NoSuchCompanyException;
import com.astrika.abg.model.AreaMaster;
import com.astrika.abg.model.BrandMaster;
import com.astrika.abg.model.CityMaster;
import com.astrika.abg.model.CompanyMaster;
import com.astrika.abg.model.OutletMaster;
import com.astrika.abg.model.StateMaster;
import com.astrika.abg.model.User;
import com.astrika.abg.service.AreaService;
import com.astrika.abg.service.BrandService;
import com.astrika.abg.service.CityService;
import com.astrika.abg.service.CompanyService;
import com.astrika.abg.service.DocumentService;
import com.astrika.abg.service.OutletService;
import com.astrika.abg.service.StateService;
import com.astrika.abg.service.UserService;
import com.astrika.abg.util.HandlerValue;
import com.astrika.abg.util.RandomCodeGenerator;

@Controller
public class OutletController {
	
	@Autowired
	private AreaService areaService;

	@Autowired
	private CityService cityService;
	
	@Autowired
	private StateService stateService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DocumentService documentService;
	
	@Autowired 
	private HandlerValue propsValue;
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private OutletService outletService;
	
	@RequestMapping("/*/RestaurantList")
	public String restaurantList(
			@RequestParam(value = "success", required = false, defaultValue = "") String success,
			@RequestParam(value = "error", required = false, defaultValue = "") String error,
			Map<String, Object> model) {
		if (success != null && !success.isEmpty()) {
			model.put("success", success);
		} else if (error != null && !error.isEmpty()) {
			model.put("error", error);
		}
		model.put("inactiveRestaurantList", outletService.findAllByActiveFalseOrderByNameAsc());
		model.put("restaurantList", outletService.findAllByActiveTrueOrderByNameAsc());
		User user = userService.getLoggedInUser();
			model.put("user", user);
		    return "admin/restaurant/restaurantgrid";
	}
	
	@RequestMapping("/ManageRestaurant/RestaurantList/AddRestaurant")
	public String addRestaurant(
			@RequestParam(value = "error", required = false, defaultValue = "") String error,
			Map<String, Object> model) {
		if (error != null && !error.isEmpty()) {
			model.put("error", error);
		}
		List<CompanyMaster> companyList = companyService.findByActive(true);
		model.put("stateList", stateService.findByActive(true));
		model.put("companyList", companyList);
		User user = userService.getLoggedInUser();
		model.put("user", user);
		return "admin/restaurant/addrestaurant";
	}
	
	@RequestMapping("/ManageRestaurant/RestaurantList/AddSingleRestaurant")
	public String addSingleRestaurant(
			@RequestParam(value = "error", required = false, defaultValue = "") String error,
			Map<String, Object> model) {
		if (error != null && !error.isEmpty()) {
			model.put("error", error);
		}
		User user = userService.getLoggedInUser();
		model.put("user", user);
		model.put("stateList", stateService.findByActive(true));
		return "admin/restaurant/addsinglerestaurant";
	}
	
	@RequestMapping("/*/*/EditRestaurant")
	public String editRestaurant(
			@RequestParam(value = "id") Long id,
			@RequestParam(value = "success", required = false, defaultValue = "") String success,
			Map<String, Object> model) throws NoSuchCompanyException, NoSuchBrandException {
			OutletMaster outlet = outletService.findByOutletId(id);
			if (success != null && !success.isEmpty()) {
				model.put("success", success);
			}
			model.put("outlet", outlet);
			List<CompanyMaster> companyList = companyService.findByActive(true);
			model.put("stateList", stateService.findByActive(true));
			model.put("companyList", companyList);		
			return "admin/restaurant/addrestaurant";
	}
	
	@RequestMapping("/*/*/ViewRestaurant")
	public String viewRestaurant(
			@RequestParam(value = "id") Long id,
			@RequestParam(value = "success", required = false, defaultValue = "") String success,
			Map<String, Object> model) throws NoSuchCompanyException, NoSuchBrandException {
			model.put("viewMode", true);
			OutletMaster outlet = outletService.findByOutletId(id);
			if (success != null && !success.isEmpty()) {
				model.put("success", success);
			}
			model.put("outlet", outlet);
			List<CompanyMaster> companyList = companyService.findByActive(true);
			model.put("stateList", stateService.findByActive(true));
			model.put("companyList", companyList);		
			return "admin/restaurant/addrestaurant";
	}
	
	@RequestMapping(value = "/{url1}/{url2}/SaveRestaurant")
	public String saveRestaurant(
			@PathVariable(value = "url1") String url1,
			@PathVariable(value = "url2") String url2,
			@RequestParam(value = "file", required = false, defaultValue = "") MultipartFile file,
			@RequestParam("userCount")Integer userCount,
			@RequestParam(value = "multiscreen", required = false) boolean multiscreen,
			@RequestParam(value = "restaurantLoyalityCards", required = false) boolean restaurantLoyalityCards,
			@RequestParam(value = "restaurantPersonalDeals", required = false) boolean restaurantPersonalDeals,
			@RequestParam(value = "askForDiscountOnline", required = false) boolean askForDiscountOnline,
			@RequestParam(value = "personalRecordManagement", required = false) boolean personalRecordManagement,
			@RequestParam(value = "userEngagement", required = false) boolean userEngagement,
			HttpServletRequest request, HttpServletResponse response,
			HashMap<String, Object> model) throws IOException {
		boolean exceptionFlag = false;
		/*Integer userCount=(Integer) request.getParameter("userCount");
		Boolean multiscreen=(Boolean) request.getParameter("multiscreen");
		Boolean restaurantLoyalityCards=(Boolean) request.getParameter("restaurantLoyalityCards");
		Boolean restaurantPersonalDeals=(Boolean) request.getParameter("restaurantPersonalDeals");
		Boolean askForDiscountOnline=(Boolean) request.getParameter("askForDiscountOnline");
		Boolean personalRecordManagement=(Boolean) request.getParameter("personalRecordManagement");
		Boolean userEngagement=(Boolean) request.getParameter("userEngagement");*/

		Long chosenCompanyId = Long.parseLong(request.getParameter("chosenCompanyId"));
		Long chosenBrandId = Long.parseLong(request.getParameter("chosenBrandId"));
		String outletName = request.getParameter("outletName");
		String outletAgreementStart = request.getParameter("outletAgreementStart");
		String outletAgreementEnd = request.getParameter("outletAgreementEnd");
		
		String outletTelephone = request.getParameter("outletTelephone"); //After removing the contact info fields
		String outletEmail = request.getParameter("outletEmail");
		//String outletMobile1 = request.getParameter("outletMobile");
		String outletAddressLine1 = request.getParameter("outletAddressLine1");
		String outletAddressLine2 = request.getParameter("outletAddressLine2");
		Long outletAreaId = Long.parseLong(request.getParameter("chosenAreaId"));
		Long outletCityId = Long.parseLong(request.getParameter("chosenCityId"));
		Long outletStateId = Long.parseLong(request.getParameter("chosenStateId"));
		String outletPincode = request.getParameter("outletPincode");

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String emailId = request.getParameter("emailId");
		String mobile = request.getParameter("mobile");
		String loginId = request.getParameter("loginId");
		
		byte[] byteArray = new byte[] {};
		String ext = "";
		String filename = "";
		String agreementFileUlr = "";
		if (file.getOriginalFilename() != null
				&& file.getOriginalFilename().length() > 0) {
			filename = file.getOriginalFilename();
			ext = filename.substring(filename.lastIndexOf("."));
			byteArray = file.getBytes();
			String absolutePath = documentService.getAbsoluteSaveLocationDir(1);
			
			File uploadedFile = documentService.saveFile(byteArray, ext, "AggrementFile",
					absolutePath);
			agreementFileUlr = uploadedFile.getAbsolutePath();
			filename="AggrementFile";
			agreementFileUlr = agreementFileUlr
					.split(propsValue.DOCUMENTS_ROOT)[1];
		}
		
		AreaMaster outletArea = null;							//After removing the contact info fields
		try {
			outletArea = areaService.findById(outletAreaId);
		} catch (NoSuchAreaException e) {
			exceptionFlag = true;
			model.put("error", CustomException.NO_SUCH_AREA.getCode());
		}
		CityMaster outletCity = null;
		if (exceptionFlag == false) {
			try {
				outletCity = cityService.findById(outletCityId);
			} catch (NoSuchCityException e) {
				exceptionFlag = true;
				model.put("error", CustomException.NO_SUCH_CITY.getCode());
			}
		}
		StateMaster outletState = null;
			try {
				outletState = stateService.findByStateId(outletStateId);
			} catch (BusinessException e) {
				exceptionFlag = true;
				model.put("error", CustomException.NO_SUCH_COUNTRY.getCode());
		}
			
			CompanyMaster company=null;
			try {
				company = companyService.findById(chosenCompanyId);
			} catch (BusinessException e) {
				exceptionFlag = true;
				model.put("error", CustomException.NO_SUCH_COUNTRY.getCode());
		}
			
			BrandMaster brand=null;
			try {
				brand = brandService.findById(chosenBrandId);
			} catch (BusinessException e) {
				exceptionFlag = true;
				model.put("error", CustomException.NO_SUCH_COUNTRY.getCode());
		}

	
		OutletMaster outlet=new OutletMaster
				(outletName, company, brand, outletAddressLine1, outletAddressLine2, 
						outletPincode, outletArea, outletCity, outletState, 
						true, userCount, multiscreen, restaurantLoyalityCards, restaurantPersonalDeals, 
						askForDiscountOnline, personalRecordManagement, userEngagement);
		
		User admin = new User(loginId, firstName, lastName, RandomCodeGenerator.randomString(8),
				emailId, mobile,true);
		outlet.setOutletAdmin(admin);
		outlet.setOutletTelephone(outletTelephone);
		outlet.setAgreementFileName(file.getName());
		outlet.setAgreementFileUlr(agreementFileUlr);
		outlet.setOutletEmail(outletEmail);
		outlet.setOutletAgreementStart(outletAgreementStart);
		outlet.setOutletAgreementEnd(outletAgreementEnd);
		outlet.setUserCount(userCount);

			try {
				outletService.saveOutlet(outlet);
			} catch (Exception e) {
				exceptionFlag=false;
				e.printStackTrace();
			} 
		if (exceptionFlag == false) {
			model.put("success", true);
			return "redirect:/" + url1 + "/" + url2;
		} else {
			List<CityMaster> cityList = cityService.findByActive(true);
			List<StateMaster> stateList = stateService.findByActive(true);
			List<AreaMaster> areaList = areaService.findByActive(true);
			model.put("cityList", cityList);
			model.put("stateList", stateList);
			model.put("areaList", areaList);//Removing the contact info fields
			model.put("user", admin);
			model.put("brand", brand);
			model.put("companyList", companyService.findByActive(true));
			return "admin/restaurant/addrestaurant";
		}
	
		
	}
	
	
	@RequestMapping(value = "/{url1}/{url2}/SaveSingleRestaurant")
	public String saveSingleRestaurant(
			@PathVariable(value = "url1") String url1,
			@PathVariable(value = "url2") String url2,
			@RequestParam(value = "file", required = false, defaultValue = "") MultipartFile file,
			@RequestParam("userCount")Integer userCount,
			@RequestParam(value = "multiscreen", required = false) boolean multiscreen,
			@RequestParam(value = "restaurantLoyalityCards", required = false) boolean restaurantLoyalityCards,
			@RequestParam(value = "restaurantPersonalDeals", required = false) boolean restaurantPersonalDeals,
			@RequestParam(value = "askForDiscountOnline", required = false) boolean askForDiscountOnline,
			@RequestParam(value = "personalRecordManagement", required = false) boolean personalRecordManagement,
			@RequestParam(value = "userEngagement", required = false) boolean userEngagement,
			HttpServletRequest request, HttpServletResponse response,
			HashMap<String, Object> model) throws IOException {
		boolean exceptionFlag = false;
		String outletName = request.getParameter("outletName");
		String outletAgreementStart = request.getParameter("outletAgreementStart");
		String outletAgreementEnd = request.getParameter("outletAgreementEnd");
		
		String outletTelephone = request.getParameter("outletTelephone"); //After removing the contact info fields
		String outletEmail = request.getParameter("outletEmail");
		//String outletMobile1 = request.getParameter("outletMobile");
		String outletAddressLine1 = request.getParameter("outletAddressLine1");
		String outletAddressLine2 = request.getParameter("outletAddressLine2");
		Long outletAreaId = Long.parseLong(request.getParameter("chosenAreaId"));
		Long outletCityId = Long.parseLong(request.getParameter("chosenCityId"));
		Long outletStateId = Long.parseLong(request.getParameter("chosenStateId"));
		String outletPincode = request.getParameter("outletPincode");

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String emailId = request.getParameter("emailId");
		String mobile = request.getParameter("mobile");
		String loginId = request.getParameter("loginId");
		/*Integer userCount=(Integer) request.getAttribute("userCount");
		Boolean multiscreen=(Boolean) request.getAttribute("multiscreen");
		Boolean restaurantLoyalityCards=(Boolean) request.getAttribute("restaurantLoyalityCards");
		Boolean restaurantPersonalDeals=(Boolean) request.getAttribute("restaurantPersonalDeals");
		Boolean askForDiscountOnline=(Boolean) request.getAttribute("askForDiscountOnline");
		Boolean personalRecordManagement=(Boolean) request.getAttribute("personalRecordManagement");
		Boolean userEngagement=(Boolean) request.getAttribute("userEngagement");*/

		byte[] byteArray = new byte[] {};
		String ext = "";
		String filename = "";
		String agreementFileUlr = "";
		if (file.getOriginalFilename() != null
				&& file.getOriginalFilename().length() > 0) {
			filename = file.getOriginalFilename();
			ext = filename.substring(filename.lastIndexOf("."));
			byteArray = file.getBytes();
			String absolutePath = documentService.getAbsoluteSaveLocationDir(1);
			
			File uploadedFile = documentService.saveFile(byteArray, ext, "AggrementFile",
					absolutePath);
			agreementFileUlr = uploadedFile.getAbsolutePath();
			filename="AggrementFile";
			agreementFileUlr = agreementFileUlr
					.split(propsValue.DOCUMENTS_ROOT)[1];
		}
		
		AreaMaster outletArea = null;							//After removing the contact info fields
		try {
			outletArea = areaService.findById(outletAreaId);
		} catch (NoSuchAreaException e) {
			exceptionFlag = true;
			model.put("error", CustomException.NO_SUCH_AREA.getCode());
		}
		CityMaster outletCity = null;
		if (exceptionFlag == false) {
			try {
				outletCity = cityService.findById(outletCityId);
			} catch (NoSuchCityException e) {
				exceptionFlag = true;
				model.put("error", CustomException.NO_SUCH_CITY.getCode());
			}
		}
		StateMaster outletState = null;
			try {
				outletState = stateService.findByStateId(outletStateId);
			} catch (BusinessException e) {
				exceptionFlag = true;
				model.put("error", CustomException.NO_SUCH_COUNTRY.getCode());
		}
			
			
			CompanyMaster company=new CompanyMaster(outletName, outletAgreementStart, outletAgreementEnd,
					agreementFileUlr, file.getName(), outletTelephone, outletEmail,
					outletAddressLine1, outletAddressLine2, outletPincode, outletArea, outletCity, outletState);
			company.setActive(true);
			User companyAdmin = new User(loginId, firstName, lastName, RandomCodeGenerator.randomString(8),
					emailId, mobile,true);
			company.setCompanyAdmin(companyAdmin);
			try {
				company=companyService.save(company);
			} catch (DuplicateCompanyException e1) {
				exceptionFlag=false;
				e1.printStackTrace();
			} catch (DuplicateLoginException e1) {
				exceptionFlag=false;
				e1.printStackTrace();
			}
			BrandMaster brand=new BrandMaster(outletName, outletAgreementStart, outletAgreementEnd,
					agreementFileUlr, file.getName(), company, outletTelephone, outletEmail, outletAddressLine1, 
					outletAddressLine2, outletPincode, outletArea, outletCity, outletState, true);
			User brandAdmin = new User(loginId, firstName, lastName, RandomCodeGenerator.randomString(8),
					emailId, mobile,true);
	        brand.setBrandAdmin(brandAdmin);
	        try {
				brand=brandService.save(brand);
			} catch (DuplicateBrandException e1) {
				exceptionFlag=false;
				e1.printStackTrace();
			} catch (DuplicateLoginException e1) {
				exceptionFlag=false;
				e1.printStackTrace();
			}
		OutletMaster outlet=new OutletMaster
				(outletName, company, brand, outletAddressLine1, outletAddressLine2, 
						outletPincode, outletArea, outletCity, outletState, 
						true, userCount, multiscreen, restaurantLoyalityCards, restaurantPersonalDeals, 
						askForDiscountOnline, personalRecordManagement, userEngagement);
		User outletAdmin = new User(loginId, firstName, lastName, RandomCodeGenerator.randomString(8),
				emailId, mobile,true);
		
		outlet.setOutletAdmin(outletAdmin);
		outlet.setOutletTelephone(outletTelephone);
		outlet.setAgreementFileName(file.getName());
		outlet.setAgreementFileUlr(agreementFileUlr);
		outlet.setOutletEmail(outletEmail);
		outlet.setOutletAgreementStart(outletAgreementStart);
		outlet.setOutletAgreementEnd(outletAgreementEnd);
		outlet.setUserCount(userCount);
			try {
				outletService.saveOutlet(outlet);
			} catch (Exception e) {
				exceptionFlag=false;
				e.printStackTrace();
			} 
		if (exceptionFlag == false) {
			model.put("success", true);
			return "redirect:/" + url1 + "/" + url2;
		} else {
			List<CityMaster> cityList = cityService.findByActive(true);
			List<StateMaster> stateList = stateService.findByActive(true);
			List<AreaMaster> areaList = areaService.findByActive(true);
			model.put("cityList", cityList);
			model.put("stateList", stateList);
			model.put("areaList", areaList);//Removing the contact info fields
			model.put("user", userService.getLoggedInUser());
			model.put("brand", brand);
			model.put("companyList", companyService.findByActive(true));
			return "admin/restaurant/addrestaurant";
		}
	
		
	}
	
	@RequestMapping(value = "/{url1}/{url2}/RestoreRestaurant")
	public String restoreRestaurant(@PathVariable(value = "url1") String url1,
			@PathVariable(value = "url2") String url2,
			@RequestParam(value = "id") Long id,
			HashMap<String, Object> model)
	{
	//	brandService.restore(id);
		return "redirect:/" + url1 + "/" + url2;
	}
	
	@RequestMapping("/{url1}/{url2}/DeleteRestaurant")
	public String deleteRestaurant(@PathVariable(value = "url1") String url1,
			@PathVariable(value = "url2") String url2,
			@RequestParam(value = "id") Long id,
			Map<String, Object> model)
	{
		//brandService.delete(id);
		return "redirect:/" + url1 + "/" + url2;
	}
	
	

}
