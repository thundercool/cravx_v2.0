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
import org.springframework.web.bind.annotation.ResponseBody;
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
import com.astrika.abg.model.StateMaster;
import com.astrika.abg.model.User;
import com.astrika.abg.repository.RoleRepository;
import com.astrika.abg.service.AreaService;
import com.astrika.abg.service.BrandService;
import com.astrika.abg.service.CityService;
import com.astrika.abg.service.CompanyService;
import com.astrika.abg.service.DocumentService;
import com.astrika.abg.service.StateService;
import com.astrika.abg.service.UserService;
import com.astrika.abg.util.HandlerValue;
import com.astrika.abg.util.RandomCodeGenerator;

@Controller
public class BrandController {
	
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
	private RoleRepository roleRepository;
	
	@Autowired
	private BrandService brandService;
	
	@RequestMapping("/*/BrandList")
	public String showCompnies(
			@RequestParam(value = "success", required = false, defaultValue = "") String success,
			@RequestParam(value = "error", required = false, defaultValue = "") String error,
			Map<String, Object> model) {
		if (success != null && !success.isEmpty()) {
			model.put("success", success);
		} else if (error != null && !error.isEmpty()) {
			model.put("error", error);
		}
	/*	List<CompanyMaster> companyList = companyService.findByActive(true);
		List<CompanyMaster> inactiveCompanyList = companyService.findByActive(false);*/
		model.put("inactiveBrandList", brandService.findAllByInActive());
		model.put("barndList", brandService.findAllByActive());
		User user = userService.getLoggedInUser();
			model.put("user", user);
		    return "admin/brand/brandgrid";
	}
	
	@RequestMapping("/brandbycompanyid/{companyId}")
	public @ResponseBody
	List<BrandMaster> getBrandByCompanyId(@PathVariable("companyId") Long companyId)
			throws NoSuchCityException {
		List<BrandMaster> list =	brandService.findAllByCompanyId(companyId);
		return list;
	}
	
	@RequestMapping("/ManageBrand/BrandList/AddBrand")
	public String addBrand(
			@RequestParam(value = "error", required = false, defaultValue = "") String error,
			Map<String, Object> model) {
		if (error != null && !error.isEmpty()) {
			model.put("error", error);
		}
		List<CompanyMaster> companyList = companyService.findByActive(true);
		model.put("stateList", stateService.findByActive(true));
		model.put("companyList", companyList);
		return "admin/brand/addbrand";
	}
	
	@RequestMapping("/*/*/EditBrand")
	public String editBrand(
			@RequestParam(value = "id") Long id,
			@RequestParam(value = "success", required = false, defaultValue = "") String success,
			Map<String, Object> model) throws NoSuchCompanyException, NoSuchBrandException {
			BrandMaster brand = brandService.findById(id);
			if (success != null && !success.isEmpty()) {
				model.put("success", success);
			}
			model.put("brand", brand);
			List<CompanyMaster> companyList = companyService.findByActive(true);
			model.put("stateList", stateService.findByActive(true));
			model.put("companyList", companyList);		
			return "admin/brand/addbrand";
	}
	
	@RequestMapping("/*/*/ViewBrand")
	public String viewBrand(
			@RequestParam(value = "id") Long id,
			@RequestParam(value = "success", required = false, defaultValue = "") String success,
			Map<String, Object> model) throws NoSuchCompanyException, NoSuchBrandException {
		BrandMaster brand = brandService.findById(id);
			if (success != null && !success.isEmpty()) {
				model.put("success", success);
			}
			model.put("viewMode", true);
			model.put("brand", brand);
			List<CompanyMaster> companyList = companyService.findByActive(true);
			model.put("stateList", stateService.findByActive(true));
			model.put("companyList", companyList);		
			return "admin/brand/addbrand";
	}
	
	@RequestMapping(value = "/{url1}/{url2}/SaveBrand")
	public String saveBrand(
			@PathVariable(value = "url1") String url1,
			@PathVariable(value = "url2") String url2,
			@RequestParam(value = "file", required = false, defaultValue = "") MultipartFile file,
			@RequestParam(value = "offer", required = false) boolean offer,
			HttpServletRequest request, HttpServletResponse response,
			HashMap<String, Object> model) throws IOException {
		boolean exceptionFlag = false;
		Long chosenCompanyId = Long.parseLong(request.getParameter("chosenCompanyId"));
		String brandName = request.getParameter("brandName");
		String brandAgreementStart = request.getParameter("brandAgreementStart");
		String brandAgreementEnd = request.getParameter("brandAgreementEnd");
		
		String brandTelephone = request.getParameter("brandTelephone"); //After removing the contact info fields
		String brandEmail = request.getParameter("brandEmail");
		//String brandMobile1 = request.getParameter("brandMobile");
		String brandAddressLine1 = request.getParameter("brandAddressLine1");
		String brandAddressLine2 = request.getParameter("brandAddressLine2");
		Long brandAreaId = Long.parseLong(request.getParameter("chosenAreaId"));
		Long brandCityId = Long.parseLong(request.getParameter("chosenCityId"));
		Long brandStateId = Long.parseLong(request.getParameter("chosenStateId"));
		String brandPincode = request.getParameter("brandPincode");

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
		
		AreaMaster brandArea = null;							//After removing the contact info fields
		try {
			brandArea = areaService.findById(brandAreaId);
		} catch (NoSuchAreaException e) {
			exceptionFlag = true;
			model.put("error", CustomException.NO_SUCH_AREA.getCode());
		}
		CityMaster brandCity = null;
		if (exceptionFlag == false) {
			try {
				brandCity = cityService.findById(brandCityId);
			} catch (NoSuchCityException e) {
				exceptionFlag = true;
				model.put("error", CustomException.NO_SUCH_CITY.getCode());
			}
		}
		StateMaster brandState = null;
			try {
				brandState = stateService.findByStateId(brandStateId);
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

		/*OutletFeatureControl control = new OutletFeatureControl(
				giftameal, preorder, evite, waittimetracker, splitbill, offer,happyHour,
				promocode, mpay, tableregistration, callForService,polls,buffet,checkIns);*/
		
	    BrandMaster brand =	new BrandMaster(brandName, brandAgreementStart, brandAgreementEnd, 
	    		agreementFileUlr, filename, company, brandTelephone, brandEmail, brandAddressLine1, 
	    		brandAddressLine2, brandPincode, brandArea, brandCity, 
	    		brandState, true);	
		
		
		User admin = new User(loginId, firstName, lastName, RandomCodeGenerator.randomString(8),
				emailId, mobile,true);
		brand.setBrandAdmin(admin);
		
			try {
				brandService.save(brand);
			} catch (DuplicateBrandException e) {
				exceptionFlag=false;
				e.printStackTrace();
			} catch (DuplicateLoginException e) {
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
			return "admin/company/addcompany";
		}
	
		
	}
	
	@RequestMapping(value = "/{url1}/{url2}/RestoreBrand")
	public String restoreBrand(@PathVariable(value = "url1") String url1,
			@PathVariable(value = "url2") String url2,
			@RequestParam(value = "id") Long id,
			HashMap<String, Object> model)
	{
		brandService.restore(id);
		return "redirect:/" + url1 + "/" + url2;
	}
	
	@RequestMapping("/{url1}/{url2}/DeleteBrand")
	public String deleteBrand(@PathVariable(value = "url1") String url1,
			@PathVariable(value = "url2") String url2,
			@RequestParam(value = "id") Long id,
			Map<String, Object> model)
	{
		brandService.delete(id);
		return "redirect:/" + url1 + "/" + url2;
	}
	

}
