package com.astrika.abg.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.astrika.abg.exception.BusinessException;
import com.astrika.abg.exception.CustomException;
import com.astrika.abg.exception.DuplicateCorporateException;
import com.astrika.abg.exception.DuplicateEmailException;
import com.astrika.abg.exception.DuplicateLoginException;
import com.astrika.abg.exception.DuplicateMobileException;
import com.astrika.abg.exception.NoSuchAreaException;
import com.astrika.abg.exception.NoSuchCityException;
import com.astrika.abg.exception.NoSuchCorporateException;
import com.astrika.abg.model.AreaMaster;
import com.astrika.abg.model.CityMaster;
import com.astrika.abg.model.CorporateMaster;
import com.astrika.abg.model.GenericMaster;
import com.astrika.abg.model.GenericMasterType;
import com.astrika.abg.model.StateMaster;
import com.astrika.abg.model.Status;
import com.astrika.abg.model.User;
import com.astrika.abg.service.AreaService;
import com.astrika.abg.service.CityService;
import com.astrika.abg.service.CorporateService;
import com.astrika.abg.service.DocumentService;
import com.astrika.abg.service.GenericMasterService;
import com.astrika.abg.service.StateService;
import com.astrika.abg.service.UserService;
import com.astrika.abg.util.HandlerValue;
import com.astrika.abg.util.RandomCodeGenerator;

@Controller
public class CorporateController {
	
	@Autowired
	private StateService stateService;
	
	@Autowired 
	private CityService cityService;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private CorporateService corporateService;
	
	@Autowired
	private GenericMasterService genericMasterService;
	
	@Autowired
	private DocumentService documentService;
	
	@Autowired
	private HandlerValue propsValue;
	
	@RequestMapping("/*/CorporateList")
	public String showCorporates(
			@RequestParam(value = "success", required = false, defaultValue = "") String success,
			@RequestParam(value = "error", required = false, defaultValue = "") String error,
			Map<String, Object> model) {
		if (success != null && !success.isEmpty()) {
			model.put("success", success);
		} else if (error != null && !error.isEmpty()) {
			model.put("error", error);
		}

		/*model.put("inactiveCorporateList", corporateService.findAllByOrderByCorporateName(false));
		model.put("activeCorporateList", corporateService.findAllByOrderByCorporateName(true));*/
		
		model.put("inactiveCorporateList", corporateService.findAllStatus(Status.INACTIVE));
		model.put("awaitingCorporateList", corporateService.findAllStatus(Status.AWAITING));
		model.put("rejectedCorporateList", corporateService.findAllStatus(Status.REJECTED));
		model.put("activeCorporateList", corporateService.findAllStatus(Status.ACTIVE));
		User user = userService.getLoggedInUser();
		model.put("user", user);
		return "admin/corporate/corporategrid";
	}
	
	@RequestMapping("/*/*/AddCorporate")
	public String addCorporate(
			@RequestParam(value = "error", required = false, defaultValue = "") String error,
			Map<String, Object> model) {
		if (error != null && !error.isEmpty()) {
			model.put("error", error);
		}
		model.put("stateList", stateService.findByActive(true));
		model.put("industryList", genericMasterService.findByGenericMasterType(GenericMasterType.INDUSTRY_TYPE));
		 model.put("user", userService.getLoggedInUser());
		return "admin/corporate/addcorporate";
	}
	
	@RequestMapping("/*/*/EditCorporate")
	public String editCorporate(
			@RequestParam(value = "id") Long id,
			@RequestParam(value = "success", required = false, defaultValue = "") String success,
			Map<String, Object> model) throws NoSuchCorporateException {
		model.put("corporate", corporateService.findById(id));
		 List<CityMaster> cityList = cityService.findByActive(true);
		List<GenericMaster> industryList = genericMasterService.findByGenericMasterType(GenericMasterType.INDUSTRY_TYPE);
	    List<AreaMaster> areaList = areaService.findByActive(true);
		model.put("cityList", cityList);
		model.put("stateList", stateService.findByActive(true));
		model.put("industryList", genericMasterService.findByGenericMasterType(GenericMasterType.INDUSTRY_TYPE));
		model.put("areaList", areaList);
		model.put("industryList", industryList);
		return "admin/corporate/editcorporate";
	}
	
	@RequestMapping(value = "/{url1}/{url2}/SaveCorporate")
	public String addCorporate(
			@PathVariable("url1") String url1,
			@PathVariable("url2") String url2,
			@RequestParam(value = "file", required = false, defaultValue = "") MultipartFile file,
/*			@RequestParam(value = "logo", required = false, defaultValue = "") MultipartFile logo,
*/			@RequestParam("corporateName") String corporateName,
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("emailId") String emailId,
			@RequestParam("mobile") String mobile,
			//@RequestParam("otp") String otp,
			@RequestParam("loginId") String loginId,
/*			@RequestParam("password") String password,
*/			@RequestParam(value = "noOfUsers", required = false, defaultValue = "0") Integer noOfUser,
			@RequestParam(value = "domainName", required = false, defaultValue = " ") String domainName,
			@RequestParam("corporateAgreementStart") String compAgreementStart,
			@RequestParam("corporateAgreementEnd") String compAgreementEnd,
			@RequestParam("corporateTelephone") String corporateTelephone,
			@RequestParam("corporateEmail") String corporateEmail,
			@RequestParam("corporateAddressLine1") String corporateAddressLine1,
			@RequestParam(value = "corporateAddressLine2", required = false, defaultValue = "") String corporateAddressLine2,
			@RequestParam("chosenAreaId") Long corporateAreaId,
			@RequestParam("chosenCityId") Long corporateCityId,
			@RequestParam(value = "industryTypeId", required = false, defaultValue = "") Long industryTypeId,
			@RequestParam("chosenStateId") Long corporateStateId,
			@RequestParam("corporatePincode") String corporatePincode,
			HttpServletRequest request, HttpServletResponse response,
			HashMap<String, Object> model) throws IOException,
			DuplicateMobileException {

		boolean exceptionFlag = false;


		byte[] byteArray = new byte[] {};
		String ext = "";
		String filename = "";
		String agreementFileUlr = "";
		String absolutePath = documentService.getAbsoluteSaveLocationDir(1);
		if (file.getOriginalFilename() != null
				&& file.getOriginalFilename().length() > 0) {
			filename = file.getOriginalFilename();
			filename=filename.replace('/', '_');
			ext = filename.substring(filename.lastIndexOf("."));
			byteArray = file.getBytes();
			
			File uploadedFile = documentService.save(byteArray, ext, filename,
					absolutePath);
			agreementFileUlr = uploadedFile.getAbsolutePath();
			agreementFileUlr = agreementFileUlr
					.split(propsValue.DOCUMENTS_ROOT)[1];
		}
		AreaMaster corporateArea = null;

		try {

			corporateArea = areaService.findById(corporateAreaId);
		} catch (NoSuchAreaException e) {
			exceptionFlag = true;
			model.put("error", CustomException.NO_SUCH_AREA.getCode());
		}

		CityMaster corporateCity = null;

		try {
			corporateCity = cityService.findById(corporateCityId);
		} catch (NoSuchCityException e) {
			exceptionFlag = true;
			model.put("error", CustomException.NO_SUCH_CITY.getCode());
		}

		StateMaster corporateState = null;

		try {
			corporateState = stateService.findByStateId(corporateStateId);
		} catch (BusinessException e) {
			exceptionFlag = true;
			model.put("error", CustomException.NO_SUCH_COUNTRY.getCode());
		}

		User admin = new User(loginId, firstName, lastName, RandomCodeGenerator.randomString(8),
				emailId, mobile,true);
		CorporateMaster corporate = new CorporateMaster(corporateName, true,
				compAgreementStart, compAgreementEnd, agreementFileUlr, corporateTelephone, 
				corporateEmail, corporateAddressLine1, corporateAddressLine2, corporatePincode, corporateArea, 
				corporateCity, corporateState, admin, noOfUser);
		corporate.setStatus(Status.AWAITING);
		corporate.setCorporateAdmin(admin);
/*		corporate.setLogo(logo);
*/		GenericMaster industry = null;
		if(industryTypeId != null){
			industry = new GenericMaster();
			industry.setId(industryTypeId);
		}
		corporate.setIndustryType(industry);
		if (exceptionFlag == false) {
			try {
				corporateService.save(corporate);
			} catch (Exception e) {
				exceptionFlag = true;
				model.put("error", e.getMessage());
			}
		}

		if (exceptionFlag == false) {
			model.put("success", "alert.corporatesaved");
			return "redirect:/" + url1 + "/" + url2;
		} else {
			List<CityMaster> cityList = cityService.findByActive(true);
			List<StateMaster> stateList = stateService.findByActive(true);
			List<AreaMaster> areaList = areaService.findByActive(true);
			List<GenericMaster> industryList = genericMasterService.findByGenericMasterType(GenericMasterType.INDUSTRY_TYPE);
			model.put("cityList", cityList);
			model.put("stateList", stateList);
			model.put("areaList", areaList);
			model.put("industryList", industryList);
			admin.setLoginId(loginId.replace(propsValue.CORPORATE_LOGIN_PREFIX,
					""));
			model.put("user", admin);
			model.put("corporate", corporate);
			return "admin/mngt/corporatemngt/addcorporate";
		}
	}
	
	/*@PreAuthorize("hasAnyRole('GORMET7_ADMIN','GORMET7_EMPLOYEE')")
	@RequestMapping("/{url1}/{url2}/DeleteCorporate")
	public String deleteCorporate(@PathVariable("url1") String url1,
			@PathVariable("url2") String url2,
			@RequestParam(value = "id") Long id, HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model)
			throws NoSuchCorporateException {
		corporateService.delete(id);
		model.put("success", "alert.corporatedeleted");
		return "redirect:/" + url1 + "/" + url2;
	}
	
	@PreAuthorize("hasAnyRole('GORMET7_ADMIN','GORMET7_EMPLOYEE')")
	@RequestMapping("/{url1}/{url2}/RestoreCorporate")
	public String restoreCorporate(
			@PathVariable("url1") String url1,
			@PathVariable("url2") String url2,
			@RequestParam(value = "id") Long id,
			HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> model) throws NoSuchCorporateException {
		corporateService.restore(id);
		model.put("success", "alert.corporaterestored ");
		return "redirect:/"+url1+"/CorporateList";
	}
	*/
	@Transactional
	@RequestMapping(value={"/{url1}/{url2}/AcceptCorporateRequest"}, method = RequestMethod.POST)
	public String acceptCorporaterequest(@PathVariable("url1") String url1,
			@PathVariable("url2") String url2,
			@RequestParam(value = "id") Long id,
			/*@RequestParam(value = "g7AssignedCreditLimt",required=false) BigDecimal g7AssignedCreditLimt,
			@RequestParam(value = "dialAMeal",required=false) boolean dialAMeal,*/
			Map<String, Object> model)
			throws NoSuchCorporateException
	{
		corporateService.acceptAndReject(id, Status.ACTIVE);
		/*CorporateMaster corporate = corporateService.findById(id);
		
		User user = corporate.getCorporateAdmin();
		user.setStatus(Status.ACTIVE);
		userService.save(user);
		userService.sendCorporateAcceptanceAndRejectionMail(user, true);*/
		//model.put("success", "alert.corporateaccepted");
		return "redirect:/" + url1 + "/" + url2;
	}
	
	@RequestMapping(value={"/{url1}/{url2}/RejectCorporateRequest"}, method = RequestMethod.POST)
	public String rejectCorporateRequest(@RequestParam(value = "id") Long id,@PathVariable("url1") String url1,
			@PathVariable("url2") String url2,
			@RequestParam(value = "rejectReason") String rejectReason,
			Map<String, Object> model) throws NoSuchCorporateException {
		corporateService.acceptAndReject(id, Status.REJECTED);
		//CorporateMaster corporate = corporateService.findById(id);
		//User user = corporate.getCorporateAdmin();
		//userService.sendCorporateAcceptanceAndRejectionMail(corporate.getCorporateAdmin(), false);
		//model.put("success", "alert.corporaterejected");
		return "redirect:/" + url1 + "/" + url2;
	}
	
	@RequestMapping("/{url1}/{url2}/DeleteCorporate")
	public String deleteCorporate(@PathVariable("url1") String url1,
			@PathVariable("url2") String url2,
			@RequestParam(value = "id") Long id, HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model)
			throws NoSuchCorporateException {
		corporateService.delete(id);
		model.put("success", true);
		return "redirect:/" + url1 + "/" + url2;
	}
	
	@RequestMapping("/{url1}/{url2}/RestoreCorporate")
	public String restoreCorporate(
			@PathVariable("url1") String url1,
			@PathVariable("url2") String url2,
			@RequestParam(value = "id") Long id,
			HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> model) throws NoSuchCorporateException {
		corporateService.restore(id);
		model.put("success", true);
		return "redirect:/"+url1+"/CorporateList";
	}
	
	
	
	

}
