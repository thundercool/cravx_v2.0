package com.astrika.abg.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.astrika.abg.service.CardMasterService;
import com.astrika.abg.model.CardMaster;
import com.astrika.abg.model.CardType;
import com.astrika.abg.model.User;
import com.astrika.abg.service.UserService;

@Controller
public class CardController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CardMasterService cardMasterService;
	
	
	@RequestMapping("/{url1}/{url2}/AddCard")
	public String addCard(
			@PathVariable("url1") String url1,
			@PathVariable("url2") String url2,
			HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		
		User user = userService.getLoggedInUser();
		model.put("user",user);
		return "admin/card/addcard";
	}
	
	@RequestMapping("/{url1}/{url2}/AssignCard")
	public String assignCard(
			@PathVariable("url1") String url1,
			@PathVariable("url2") String url2,
			HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		
		User user = userService.getLoggedInUser();
		model.put("user",user);
		model.put("cardMaster", new CardMaster());
		model.put("cardTypeList", CardType.values());
		return "admin/card/assigncard";
	}
	
	@RequestMapping("/{url1}/{url2}/SaveAssignCard")
	public String saveAssignCard(
			@PathVariable("url1") String url1,
			@PathVariable("url2") String url2,
			@RequestParam("cardType") CardType cardType,
			@RequestParam("cardCount")Integer cardCount,
			HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) throws Exception {
		
		List<CardMaster> activeCardList=cardMasterService.findCardByActive(false);
		if(activeCardList!=null&&activeCardList.size()>cardCount)
		{
			int i=0;
			for(CardMaster card:activeCardList)
			{
				i++;
				if(i<=cardCount)
				{
				card.setCardType(cardType);
				cardMasterService.updateCard(card);
				}else
				{
					break;
				}
			}
		}
		else
		{
			throw new Exception("do not have access");
		}
		User user = userService.getLoggedInUser();
		model.put("user",user);
		model.put("cardMaster", new CardMaster());
		model.put("cardTypeList", CardType.values());
		model.put("success", true);
		model.put("cardCount", cardCount);
		return "admin/card/assigncard";
	}
	
	
	@RequestMapping("/{url1}/{url2}/GenerateCard")
	public String genearteCard(
			@PathVariable("url1") String url1,
			@PathVariable("url2") String url2,
			@RequestParam(value = "cardCount") int cardCount,
			HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		System.out.println("Eneterd card no is" +cardCount);
		User user = userService.getLoggedInUser();
		model.put("user",user);
		cardMasterService.generateCard(cardCount);
		model.put("success", true);
		model.put("cardCount", cardCount);
		return "admin/card/addcard";
	}

}
