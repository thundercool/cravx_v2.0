package com.astrika.abg.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.astrika.abg.exception.BusinessException;
import com.astrika.abg.exception.DuplicateAreaException;
import com.astrika.abg.exception.DuplicateCityException;
import com.astrika.abg.exception.DuplicateStateException;
import com.astrika.abg.exception.NoSuchAreaException;
import com.astrika.abg.exception.NoSuchCityException;
import com.astrika.abg.exception.SystemException;
import com.astrika.abg.model.AreaMaster;
import com.astrika.abg.model.CityMaster;
import com.astrika.abg.model.StateMaster;
import com.astrika.abg.model.User;
import com.astrika.abg.service.AreaService;
import com.astrika.abg.service.CityService;
import com.astrika.abg.service.StateService;
import com.astrika.abg.service.UserService;
import com.astrika.abg.util.HandlerValue;

@Controller
public class LocationMasterController {
	
	@Autowired
	private AreaService areaService;

	@Autowired
	private CityService cityService;
	
	@Autowired
	private StateService stateService;

	
	@Autowired
	private HandlerValue propsValue;

	@Autowired
	private UserService userService;

	

	// @Autowired
	// PropsValues propertyValues;
	private User getLogedInUser() {
		User user = null;
		Object object = SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		if (object instanceof User)
			user = (User) object;

		return user;
	}

	@ExceptionHandler(BusinessException.class)
	public String handleBusinessException(HttpServletRequest request,
			BusinessException ex) {
		request.setAttribute("errorMsg", ex.getErrorCode());
		return "common/error";
	}

	
	@RequestMapping("/{url1}/{url2}/AddState")
	public String addState(
			@PathVariable("url1") String url1,
			@PathVariable("url2") String url2,
			HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		User user = userService.getLoggedInUser();
		model.put("user",user);
		model.put("state",new StateMaster());
		return "admin/location/addstate";
	}
	
	

	@RequestMapping(value = "/{url1}/{url2}/SaveState")
	public String saveState(
			@PathVariable("url1") String url1,
			@ModelAttribute("state")StateMaster state,
			HttpServletRequest request, HttpServletResponse response,
			HashMap<String, Object> model) 
	       {
		    	stateService.save(state);
		    	model.put("success", true);
		    	return "redirect:/"+url1+"/StateList";
	       }
	
	@RequestMapping("/{url1}/{url2}/EditState")
	public String editState(
			@PathVariable("url1") String url1,
			@PathVariable("url2") String url2,
			@RequestParam(value = "id") Long id,
			@RequestParam(value = "success", required = false, defaultValue = "") String success,
			HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> model) throws BusinessException {
		StateMaster state = stateService.findByStateId(id);
		model.put("state", state);
		return "admin/location/editstate";
	}
	

	@RequestMapping("/*/StateList")
	public String showState(
			@RequestParam(value = "success", required = false, defaultValue = "") String success,
			HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> model) throws BusinessException {		
		List<StateMaster> activeStateList = stateService.findByActive(true);
		List<StateMaster> inActiveStateList = stateService.findByActive(false);
		model.put("activeStateList", activeStateList);
		model.put("inActiveStateList", inActiveStateList);
		model.put("success", true);
		return "admin/location/stategrid";
	}
	
	
	@RequestMapping(value = "/{url1}/{url2}/UpdateState")
	public String UpdateState(
			@PathVariable("url1") String url1,
			@PathVariable("url2") String url2,
			@RequestParam("stateId") Long stateId,
			@RequestParam("stateName") String stateName,
			HttpServletRequest request, HttpServletResponse response,
			HashMap<String, Object> model) throws IOException,
			SystemException, BusinessException, DuplicateStateException{		  
	try {
			stateService.update(stateId, stateName);
		} catch (DuplicateStateException e) {
			StateMaster state = new StateMaster();
			state.setStateId(stateId);
			state.setStateName(stateName);
	    	model.put("state", state);
	    	model.put("error", e.getErrorCode());
			return "admin/superadmin/masters/location/editstate";
		}
		model.put("success", "State updated successfully!!!");
		return "redirect:/"+url1+"/StateList";
	}
	
	

	@RequestMapping(value = "/{url}/RestoreState")
	public String restoreState(
			@PathVariable("url") String url,
			@RequestParam(value = "id", required = false) Long id,
			HttpServletRequest request, HttpServletResponse response,
			HashMap<String, Object> model)  {
		stateService.restore(id);
		model.put("success", "alert.staterestored");
		return "redirect:/"+url+"/StateList";
	}

	@RequestMapping(value = "/{url}/DeleteState")
	public String deleteState(
			@PathVariable("url") String url,
			@RequestParam(value = "id", required = false) Long id,
			HttpServletRequest request, HttpServletResponse response,
			HashMap<String, Object> model) 
			 {
		stateService.delete(id);
		model.put("success", "alert.statedeleted");
		return "redirect:/"+url+"/StateList";
	}

	
	
	@RequestMapping(value = "/{url1}/{url2}/SaveCity")
	public String saveCity(
			@PathVariable("url1") String url1,
			@PathVariable("url2") String url2,
			@RequestParam("cityName") String cityName,
			@RequestParam("chosenStateId") long stateId,
			HttpServletRequest request, HttpServletResponse response,
			HashMap<String, Object> model) throws IOException,
			NoSuchCityException, NoSuchAreaException,
			DuplicateCityException, BusinessException {	
		try {
			cityService.save(cityName, stateId);
			model.put("success", true);
		} catch (DuplicateCityException e) {
			CityMaster cityObj = new CityMaster();
			StateMaster state = stateService.findByStateId(stateId);	
			cityObj.setState(state);
			List<StateMaster> stateList = stateService.findByActive(true);
			model.put("stateList", stateList);
			model.put("city",cityObj);
			model.put("error", e.getErrorCode());
			return "admin/location/addcity";
		}
		model.put("success", true);
		model.put("success", "City Saved Sucessfully");
		return "redirect:/"+url1+"/CityList";
	}
	
	@RequestMapping(value = "/{url1}/{url2}/UpdateCity")
	public String UpdateCity(
			@PathVariable("url1") String url1,
			@PathVariable("url2") String url2,
			@RequestParam(value = "cityId", required = false) Long cityId,
			@RequestParam("cityName") String cityName,
			@RequestParam("chosenstateId") long stateId,
			HttpServletRequest request, HttpServletResponse response,
			HashMap<String, Object> model) throws IOException,
			NoSuchCityException, NoSuchAreaException,BusinessException, 
			DuplicateCityException, SystemException
			 {
		
		try {
			cityService.update(cityId, cityName, stateId);
		} catch (DuplicateCityException e) {
			StateMaster state = stateService.findByStateId(stateId);
			CityMaster city=new CityMaster();
			city.setCityId(cityId);
			city.setCityName(cityName);
			city.setState(state);
			List<StateMaster> stateList = stateService.findByActive(true);
			model.put("stateList", stateList);
			model.put("city",city);
			model.put("error", e.getErrorCode());
			return "admin/superadmin/masters/location/editcity";
		}
		model.put("success", "City updated sucessfully!!!");
		return "redirect:/"+url1+"/CityList";
	}
	
	@RequestMapping("/{url1}/{url2}/AddCity")
	public String addCity(
			@PathVariable("url1") String url1,
			@PathVariable("url2") String url2,
			HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		List<StateMaster> stateList = stateService.findByActive(true);
		model.put("stateList", stateList);
		User user = userService.getLoggedInUser();
		model.put("user",user);
		model.put("city",new CityMaster());
		return "admin/location/addcity";
	}
	
	
	@RequestMapping("/{url1}/{url2}/EditCity")
	public String editCity(
			@PathVariable("url1") String url1,
			@PathVariable("url2") String url2,
			@RequestParam(value = "id") Long id,
			@RequestParam(value = "success", required = false, defaultValue = "") String success,
			HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> model) throws  NoSuchAreaException, NoSuchCityException {
		CityMaster city=cityService.findById(id);
		List<StateMaster> stateList = stateService.findByActive(true);
		model.put("city", city);
		model.put("stateList", stateList);
		return "admin/superadmin/masters/location/editcity";
	}
	


	@RequestMapping(value = "/{url}/DeleteCity")
	public String deleteCity(
			@PathVariable("url") String url,
			@RequestParam(value = "id", required = false) Long id,
			HttpServletRequest request, HttpServletResponse response,
			HashMap<String, Object> model) throws IOException, NoSuchAreaException, NoSuchCityException {
		cityService.delete(id);
		model.put("success", "alert.citydeleted");
		return "redirect:/"+url+"/CityList";
	}

	

	@RequestMapping(value = "/{url}/RestoreCity")
	public String restoreCity(
			@PathVariable("url") String url,
			@RequestParam(value = "id", required = false) Long id,
			HttpServletRequest request, HttpServletResponse response,
			HashMap<String, Object> model) throws NoSuchAreaException, NoSuchCityException {
		cityService.restore(id);
		model.put("success", "alert.cityrestored");
		return "redirect:/"+url+"/CityList";
	}
	


	
	@RequestMapping("/{url}/CityList")
	public String showCities(
			@PathVariable("url") String url,
			@RequestParam(value = "success", required = false, defaultValue = "") String success,
			HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> model) {
		List<CityMaster> cityList = cityService.findByActive(true);
		List<CityMaster> inActiveCityList = cityService.findByActive(false);
		
		
		if (success != null && !success.isEmpty()) {
			model.put("success", success);
		}
		
		model.put("cityList", cityList);
		model.put("inActiveCityList", inActiveCityList);
		model.put("success", true);
		return "admin/location/citygrid";
	}

	
	@RequestMapping(value = "/{url1}/{url2}/SaveArea")
	public String saveCity(
			@PathVariable("url1") String url1,
			@PathVariable("url2") String url2,
			@RequestParam("areaName") String areaName,
			@RequestParam("chosenCityId") long cityId,
			@RequestParam("chosenStateId") long stateId,
			/*@RequestParam("latitude") Double latitude,
			@RequestParam("longitude") Double longitude,*/
			HttpServletRequest request, HttpServletResponse response,
			HashMap<String, Object> model) throws IOException,
			BusinessException
			  {
		StateMaster state = stateService.findByStateId(stateId);
		CityMaster city = cityService.findById(cityId);
		AreaMaster area = new AreaMaster();
		try {
			area = areaService.save(areaName, city.getCityId(),
					stateId, 1.0, 1.0);
		} catch (DuplicateAreaException e) {
			area.setAreaName(areaName);
			area.setCity(city);
			area.setState(state);
			area.setLatitude(1.0);
			area.setLongitude(1.0);
			List<CityMaster> cityList = cityService.findByActive(true);
			List<StateMaster> stateList = stateService.findByActive(true);
			model.put("cityList", cityList);
			model.put("stateList", stateList);
			model.put("area", area);
			model.put("error", e.getErrorCode());
			return "admin/superadmin/masters/location/addarea";
		}
		model.put("success", "alert.areasaved");
		return "redirect:/" + url1 + "/AreaList";
	}

	@RequestMapping(value = "/{url1}/{url2}/UpdateArea")
	public String UpdateArea(@PathVariable("url1") String url1,
			@PathVariable("url2") String url2,
			@RequestParam(value = "areaId", required = false) Long areaId,
			@RequestParam("areaName") String areaName,
			@RequestParam("chosenCityId") long cityId,
			@RequestParam("chosenStateId") long stateId,
			@RequestParam("latitude") Double latitude,
			@RequestParam("longitude") Double longitude,
			HttpServletRequest request, HttpServletResponse response,
			HashMap<String, Object> model) throws IOException,
			BusinessException
	{
		StateMaster state = stateService.findByStateId(stateId);
		CityMaster city = cityService.findById(cityId);
		AreaMaster area = new AreaMaster();
		try {
			areaService.update(areaId, areaName, city.getCityId(),
					 state.getStateId(),latitude, longitude);
		} catch (DuplicateAreaException e) {
			area.setAreaId(areaId);
			area.setAreaName(areaName);
			area.setCity(city);
			area.setState(state);
			area.setLatitude(latitude);
			area.setLongitude(longitude);
			List<CityMaster> cityList = cityService.findByActive(true);
			List<StateMaster> stateList = stateService.findByActive(true);
			model.put("cityList", cityList);
			model.put("stateList", stateList);
			model.put("area", area);
			model.put("error", e.getErrorCode());
			return "admin/superadmin/masters/location/editarea";
		}
		model.put("success", "alert.areaupdated");
		return "redirect:/" + url1 + "/AreaList";
	}

	@RequestMapping("/{url1}/{url2}/AddArea")
	public String addArea(@PathVariable("url1") String url1,
			@PathVariable("url2") String url2, HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		List<CityMaster> cityList = cityService.findByActive(true);
		List<StateMaster> stateList = stateService.findByActive(true);
		model.put("cityList", cityList);
		model.put("stateList", stateList);
		return "admin/location/addarea";
	}
	
	@RequestMapping("/{url1}/AreaList")
	public String area(@PathVariable("url1") String url1,
			 HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		List<AreaMaster> activeAreaList = areaService.findByActive(true);
		List<AreaMaster> inactiveAreaList = areaService.findByActive(false);
		model.put("activeAreaList", activeAreaList);
		model.put("inactiveAreaList", inactiveAreaList);
		model.put("success", true);
		return "admin/location/areagrid";
	}
	
	@RequestMapping("/citybystateid/{stateId}")
	public @ResponseBody
	List<CityMaster> getCityByState(@PathVariable("stateId") Long stateId)
			throws NoSuchCityException {
		List<CityMaster> list =	cityService.findByStateId(stateId);
		return list;
	}
	
	@RequestMapping("/areabycityid/{cityId}")
	public @ResponseBody
	List<AreaMaster> getAreaByCity(@PathVariable("cityId") Long cityId)
			throws NoSuchCityException {
		List<AreaMaster> list =	areaService.findActiveByCityId(cityId);
		return list;
	}

	@RequestMapping("/{url1}/{url2}/EditArea")
	public String editArea(
			@PathVariable("url1") String url1,
			@PathVariable("url2") String url2,
			@RequestParam(value = "id") Long id,
			@RequestParam(value = "success", required = false, defaultValue = "") String success,
			HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> model) throws NoSuchAreaException {
		AreaMaster area = areaService.findById(id);
		List<CityMaster> cityList = cityService.findByActive(true);
		List<StateMaster> stateList = stateService.findByActive(true);
		model.put("area", area);
		model.put("cityList", cityList);
		model.put("stateList", stateList);

		return "admin/superadmin/masters/location/editarea";
	}

	@RequestMapping(value = "/{url}/DeleteArea")
	public String deleteArea(@PathVariable("url") String url,
			@RequestParam(value = "id", required = false) Long id,
			HttpServletRequest request, HttpServletResponse response,
			HashMap<String, Object> model) throws IOException,
			 NoSuchAreaException {
		areaService.delete(id);
		model.put("success", "alert.areadeleted");
		return "redirect:/" + url + "/AreaList";
	}

	@RequestMapping(value = "/{url}/RestoreArea")
	public String restoreArea(@PathVariable("url") String url,
			@RequestParam(value = "id", required = false) Long id,
			HttpServletRequest request, HttpServletResponse response,
			HashMap<String, Object> model) throws NoSuchAreaException {
		areaService.restore(id);
		model.put("success", "alert.arearestored");
		return "redirect:/" + url + "/AreaList";
	}
	

}
