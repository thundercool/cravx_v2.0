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
import com.astrika.abg.exception.DuplicateCompanyException;
import com.astrika.abg.exception.DuplicateLoginException;
import com.astrika.abg.exception.NoSuchAreaException;
import com.astrika.abg.exception.NoSuchCityException;
import com.astrika.abg.exception.NoSuchCompanyException;
import com.astrika.abg.model.AreaMaster;
import com.astrika.abg.model.CityMaster;
import com.astrika.abg.model.CompanyMaster;
import com.astrika.abg.model.StateMaster;
import com.astrika.abg.model.User;
import com.astrika.abg.repository.RoleRepository;
import com.astrika.abg.service.AreaService;
import com.astrika.abg.service.CityService;
import com.astrika.abg.service.CompanyService;
import com.astrika.abg.service.DocumentService;
import com.astrika.abg.service.StateService;
import com.astrika.abg.service.UserService;
import com.astrika.abg.util.HandlerValue;
import com.astrika.abg.util.RandomCodeGenerator;

@Controller
public class CompanyController {
	
	
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
	
	@RequestMapping("/*/CompanyList")
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
		model.put("inactiveCompanyList", companyService.findByActive(false));
		model.put("companyList", companyService.findByActive(true));
		User user = userService.getLoggedInUser();
			model.put("user", user);
		    return "admin/company/companygrid";
	}
	
	@RequestMapping("/ManageCompany/CompanyList/AddCompany")
	public String addCompany(
			@RequestParam(value = "error", required = false, defaultValue = "") String error,
			Map<String, Object> model) {
		if (error != null && !error.isEmpty()) {
			model.put("error", error);
		}
		//List<CountryMaster> countryList = countryService.findByActive(true);
		model.put("stateList", stateService.findByActive(true));
		return "admin/company/addcompany";
	}
	
	@RequestMapping("/*/*/EditCompany")
	public String editCompany(
			@RequestParam(value = "id") Long id,
			@RequestParam(value = "success", required = false, defaultValue = "") String success,
			Map<String, Object> model) throws NoSuchCompanyException {
			CompanyMaster company = companyService.findById(id);
			if (success != null && !success.isEmpty()) {
				model.put("success", success);
			}
			model.put("company", company);
			model.put("stateList", stateService.findByActive(true));
			model.put("cityList", cityService.findByActive(true));
			model.put("areaList", areaService.findByActive(true));
		return "admin/company/editcompany";
	}
	
	@RequestMapping("/*/*/ViewCompany")
	public String viewCompany(
			@RequestParam(value = "id") Long id,
			@RequestParam(value = "success", required = false, defaultValue = "") String success,
			Map<String, Object> model) throws NoSuchCompanyException {
			CompanyMaster company = companyService.findById(id);
			if (success != null && !success.isEmpty()) {
				model.put("success", success);
			}
			model.put("viewMode", true);
			model.put("company", company);
			model.put("stateList", stateService.findByActive(true));
			model.put("cityList", cityService.findByActive(true));
			model.put("areaList", areaService.findByActive(true));
			return "admin/company/editcompany";
	}
	
	@RequestMapping(value = "/{url1}/{url2}/SaveCompany")
	public String addCompany(
			@PathVariable(value = "url1") String url1,
			@PathVariable(value = "url2") String url2,
			@RequestParam(value = "file", required = false, defaultValue = "") MultipartFile file,
			@RequestParam(value = "offer", required = false) boolean offer,
			@RequestParam(value = "companyId", required = false) Long companyId,
			HttpServletRequest request, HttpServletResponse response,
			HashMap<String, Object> model) throws IOException {
		boolean exceptionFlag = false;
		String companyName = request.getParameter("companyName");
		String compAgreementStart = request.getParameter("compAgreementStart");
		String compAgreementEnd = request.getParameter("compAgreementEnd");
		
		String companyTelephone = request.getParameter("companyTelephone"); //After removing the contact info fields
		String companyEmail = request.getParameter("companyEmail");
		//String companyMobile1 = request.getParameter("companyMobile");
		String companyAddressLine1 = request.getParameter("companyAddressLine1");
		String companyAddressLine2 = request.getParameter("companyAddressLine2");
		Long companyAreaId = Long.parseLong(request.getParameter("chosenAreaId"));
		Long companyCityId = Long.parseLong(request.getParameter("chosenCityId"));
		Long companyStateId = Long.parseLong(request.getParameter("chosenStateId"));
		String companyPincode = request.getParameter("companyPincode");

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
		
		AreaMaster companyArea = null;							//After removing the contact info fields
		try {
			companyArea = areaService.findById(companyAreaId);
		} catch (NoSuchAreaException e) {
			exceptionFlag = true;
			model.put("error", CustomException.NO_SUCH_AREA.getCode());
		}
		CityMaster companyCity = null;
		if (exceptionFlag == false) {
			try {
				companyCity = cityService.findById(companyCityId);
			} catch (NoSuchCityException e) {
				exceptionFlag = true;
				model.put("error", CustomException.NO_SUCH_CITY.getCode());
			}
		}
		StateMaster companyState = null;
			try {
				companyState = stateService.findByStateId(companyStateId);
			} catch (BusinessException e) {
				exceptionFlag = true;
				model.put("error", CustomException.NO_SUCH_COUNTRY.getCode());
		}

		/*OutletFeatureControl control = new OutletFeatureControl(
				giftameal, preorder, evite, waittimetracker, splitbill, offer,happyHour,
				promocode, mpay, tableregistration, callForService,polls,buffet,checkIns);*/
		
	     CompanyMaster company = new CompanyMaster(companyName,
				compAgreementStart, compAgreementEnd, agreementFileUlr,
				filename, companyTelephone, companyEmail,
				 companyAddressLine1,
				companyAddressLine2, companyPincode,companyArea, companyCity, companyState
				);
		
		
		
		User admin = new User(loginId, firstName, lastName, RandomCodeGenerator.randomString(8),
				emailId, mobile,true);
		company.setCompanyAdmin(admin);
		try {
			if(companyId!=null)
			{
				company.setCompanyId(companyId);
			}
			companyService.save(company);
			exceptionFlag=false;
		} catch (DuplicateCompanyException | DuplicateLoginException e) {
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
			model.put("company", company);
			return "admin/company/addcompany";
		}
	
		
	}
	
	@RequestMapping(value = "/{url1}/{url2}/RestoreCompany")
	public String restoreCompany(@PathVariable(value = "url1") String url1,
			@PathVariable(value = "url2") String url2,
			@RequestParam(value = "id") Long id,
			HashMap<String, Object> model)
	{
		try {
			companyService.restore(id);
		} catch (NoSuchCompanyException e) {
			e.printStackTrace();
		}
		return "redirect:/" + url1 + "/" + url2;
	}
	
	@RequestMapping("/{url1}/{url2}/DeleteCompany")
	public String deleteCompny(@PathVariable(value = "url1") String url1,
			@PathVariable(value = "url2") String url2,
			@RequestParam(value = "id") Long id,
			Map<String, Object> model)
	{
		try {
			companyService.delete(id);
		} catch (NoSuchCompanyException e) {
			e.printStackTrace();
		}
		return "redirect:/" + url1 + "/" + url2;
	}
	

}
