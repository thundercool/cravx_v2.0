package com.astrika.abg.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.astrika.abg.enums.RoleEnum;
import com.astrika.abg.exception.DuplicateEmailException;
import com.astrika.abg.exception.DuplicateLoginException;
import com.astrika.abg.exception.NoSuchUserException;
import com.astrika.abg.model.User;
import com.astrika.abg.service.UserService;
import com.astrika.abg.util.HandlerValue;
import com.astrika.abg.util.RandomCodeGenerator;


@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HandlerValue propsValue;
	
	
	@RequestMapping("/*/UserList")
	public String userList(
			@RequestParam(value = "error", required = false, defaultValue = "") String error,
			Map<String, Object> model) {
		if (error != null && !error.isEmpty()) {
			model.put("error", error);
		}
		model.put("user", userService.getLoggedInUser());
        model.put("activeUserList",userService.findAllUser(true));
        model.put("inactiveUserList",userService.findAllUser(false));
		return "admin/user/usermngt";
	}
	
	@RequestMapping("/*/*/AddUser")
	public String addUser(
			@RequestParam(value = "error", required = false, defaultValue = "") String error,
			Map<String, Object> model) {
		if (error != null && !error.isEmpty()) {
			model.put("error", error);
		}
		model.put("user", userService.getLoggedInUser());
		model.put("roleList", RoleEnum.values());
        model.put("objUser", new User());
		return "admin/user/adduser";
	}
	
	@RequestMapping("/*/*/EditUser")
	public String editUser(
			@RequestParam(value = "id") Long id,
			@RequestParam(value = "success", required = false, defaultValue = "") String success,
			Map<String, Object> model) throws NoSuchUserException {
		model.put("user", userService.getLoggedInUser());
		model.put("objUser", userService.findByUserId(id));
		return "admin/user/edituser";
	}
	
	@RequestMapping(value = "/{url1}/{url2}/RestoreUser")
	public String restoreUser(@PathVariable("url1") String url1,
			@PathVariable("url2") String url2,
			@RequestParam(value = "id") Long id, 
			 HashMap<String, Object> model)
			throws NoSuchUserException {
		userService.activateUser(id);
		model.put("success", "User Restored Successfully!!!!");
		return "redirect:/" + url1 + "/" + url2;
	}
	
	@RequestMapping("/{url1}/{url2}/DeleteUser")
	public String deleteUser(@RequestParam(value = "id") Long id,
			@PathVariable("url1") String url1,
			@PathVariable("url2") String url2,
			Map<String, Object> model)
			throws NoSuchUserException {
		userService.inActivateUser(id);
		model.put("success", "User deactivated Successfully!!!!");
		return "redirect:/" + url1 + "/" + url2;
	}
	
	@RequestMapping(value = "/{url1}/{url2}/SaveUser")
	public String saveUser(
			@PathVariable("url1")String url1,
			@PathVariable("url2")String url2,
			HttpServletRequest request,
			HttpServletResponse response, HashMap<String, Object> model)
			throws IOException, DuplicateEmailException,
			DuplicateLoginException {
		
		boolean exceptionFlag = false;
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String emailId = request.getParameter("emailId");
		String mobile = request.getParameter("mobile");
		String loginId = request.getParameter("loginId");
		User user = new User(loginId, firstName, lastName, RandomCodeGenerator.randomString(8),
				 emailId, mobile);
		
		if (exceptionFlag == false) {
			String URL = request.getRequestURL()
					.substring(0,request.getRequestURL().lastIndexOf(propsValue.CONTEXT_URL));
			String resetPasswordURL = URL + propsValue.CONTEXT_URL;
		try{
			User user1 = userService.saveUser(user);
			//userService.sendRegstrationMail(user1, null, resetPasswordURL);
			
			}
		catch(Exception e){
			
			return "redirect:/"+url1+"/"+url2;
		}

			
		}
			model.put("success", "User Saved Sucessfully!!!");
			return "redirect:/"+url1+"/"+url2;
	}

	@RequestMapping(value = "/{url1}/{url2}/UpdateUser")
	public String updateUser(@PathVariable("url1") String url1,
			@PathVariable("url2") String url2,
			@RequestParam("userId") Long userId,
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("emailId") String emailId,
			@RequestParam(value = "loginId", required = false) String loginId,
			@RequestParam("mobile") String mobile, HttpServletRequest request,
			HttpServletResponse response, HashMap<String, Object> model)
			throws IOException, NoSuchUserException, DuplicateLoginException {
		try{
		userService.updateUser(userId, firstName, lastName, emailId, mobile);}
		catch(DuplicateLoginException e){
			User user = new User();
			user = userService.findByUserId(userId);
			loginId = user.getLoginId();
			user.setUserId(userId);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEmailId(emailId);
			user.setMobile(mobile);
			user.setLoginId(loginId);
			model.put("error", e.getErrorCode());
			
			model.put("user", user);
			return "admin/user/edituser";
			
			
		}
		model.put("success", "User Updated Sucessfully!!!");
		return "redirect:/" + url1 + "/" + url2;
	}

}
