package com.astrika.abg.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.astrika.abg.enums.RoleEnum;
import com.astrika.abg.exception.DuplicateGenericMasterException;
import com.astrika.abg.model.GenericMaster;
import com.astrika.abg.model.GenericMasterType;
import com.astrika.abg.model.Role;
import com.astrika.abg.model.User;
import com.astrika.abg.service.GenericMasterService;
import com.astrika.abg.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private GenericMasterService genericMasterService;

	Logger l = Logger.getLogger(LoginController.class);

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String defaultHandler() {
		System.out.println("Reached Default");
		return "redirect:/login";
	}

	@RequestMapping(value="/{url}/Generic", method = RequestMethod.GET)
	public String showGenerics(
			@PathVariable("url") String url,
			@RequestParam(value = "success", required = false, defaultValue = "") String success,
			@RequestParam(value = "error", required = false, defaultValue = "") String error,
			Map<String, Object> model) {
		if (success != null && !success.isEmpty()) {
			model.put("success", success);
		}
		if (error != null && !error.isEmpty()) {
			model.put("error", error);
		}
		model.put("genericList", genericMasterService.findAllByActive(true));
		model.put("typeList", GenericMasterType.values());
		model.put("inActivegenericList", genericMasterService.findAllByActive(false));
		return "admin/generic/genericmastergrid";
	}
	
	@RequestMapping("/{url1}/{url2}/AddGeneric")
	public String addGeneric(
			@PathVariable("url1") String url1,
			@PathVariable("url2") String url2,
			@RequestParam(value = "id" , required = false) Long id,
			@RequestParam(value = "success", required = false, defaultValue = "") String success,
			HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> model) {
			if(id!=null){
					model.put("generic", genericMasterService.findById(id));
			}
			model.put("typeList", GenericMasterType.values());
		return "admin/generic/addgenericmaster";
	}

	@RequestMapping(value = "/{url}/SaveGeneric")
	public String saveGeneric(
			@PathVariable("url") String url,
			@RequestParam("name")String name,
			@RequestParam("genericMasterType")int genericMasterType,
			HashMap<String, Object> model) {
		GenericMaster generic=new GenericMaster();
		try {
			generic.setName(name);
			generic.setGenericMasterType(GenericMasterType.fromInt(genericMasterType));
			genericMasterService.save(generic);
		} catch (DuplicateGenericMasterException e) {
			model.put("error", e.getErrorCode());
			model.put("generic", generic);
			return "admin/generic/addgenericmaster";
		}
		model.put("success", "true");
		return "redirect:/" + url + "/Generic";
	}

	

	
	@RequestMapping(value = "/{url}/RestoreGeneric")
	public String restoreGeneric(@PathVariable("url") String url,
			@RequestParam(value = "id", required = false) Long id,
			HttpServletRequest request, HttpServletResponse response,
			HashMap<String, Object> model)  {
		genericMasterService.resort(id);
		model.put("success", "alert.genericrestoredsuccessfully");
		return "redirect:/" + url + "/Generic";
	}

	@RequestMapping(value = "/{url}/DeleteGeneric")
	public String deleteGeneric(@PathVariable("url") String url,
			@RequestParam(value = "id", required = false) Long id,
			HttpServletRequest request, HttpServletResponse response,
			HashMap<String, Object> model) {
		genericMasterService.delete(id);
		model.put("success", "alert.genericdeletedsuccessfully");
		return "redirect:/" + url + "/Generic";
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(@RequestParam(value = "error", required = false) String error, HttpServletRequest request,
			HashMap<String, Object> model, String logout) {

		User user = userService.getLoggedInUser();
		if (user != null)
			return "redirect:/Index";

		if (error != null)
			model.put("error", "Invalid Credentials.");

		if (logout != null)
			model.put("message", "You have been logged out successfully.");

		return "common/login";
	}

	@RequestMapping("/Logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();;
		return "redirect:/login";
	}

	@RequestMapping("/Index")
	public String home(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model,RedirectAttributes attributes) {
		User user = userService.getLoggedInUser();
		String userIp = request.getRemoteAddr();
		l.info("LoggedIn Ip :-" + userIp + "  of loginId :-" + user.getLoginId());
		try {
			user.setLastLoggedInIP(userIp);
			user.setLastLoggedInOn(new Date());
			userService.saveUser(user);
		} catch (Exception e) {
			System.out.println("Error in updating user logging Ip :" + userIp);
		}

		String dashboard = null;
		if (user.getRoles() != null) {
			dashboard = redirectToDashBoard(user.getRoles());
		}
		return dashboard;
	}

	public String redirectToDashBoard(List<Role> roleList) {
		if (!roleList.isEmpty())
			for(Role role:roleList) {
				if(role.getName().equalsIgnoreCase(RoleEnum.CRAVX_ADMIN.getName()) || role.getName().equalsIgnoreCase(RoleEnum.SUPER_ADMIN.getName())) {
					return "/admin/superadmin/dashboard";
				}
				if(role.getName().equalsIgnoreCase(RoleEnum.RESTAURANT_ADMIN.getName())) {
					return "";
				}
				if(role.getName().equalsIgnoreCase(RoleEnum.MANAGER.getName())) {
					return "";
				}
				if(role.getName().equalsIgnoreCase(RoleEnum.VAITOR.getName())) {
					return "";
				}
			}
		return "redirect:/Logout";
	}

	@RequestMapping("/Welcome")
	public String welcome(Map<String, Object> model) {
		User user = userService.getLoggedInUser();
		model.put("user",user);
		return "/admin/superadmin/dashboard";
	}

	
}
