package com.astrika.abg.service;

import java.util.List;

import com.astrika.abg.model.CardMaster;
import com.astrika.abg.model.CardType;

public interface CardMasterService {

	void generateCard(int cardCount);
	
	List<CardMaster> findCardByActive(boolean active);
	
	CardMaster updateCard(CardMaster cardMaster);
	
	public long findAssignedCardTypeCount(CardType cardType);
	
}
