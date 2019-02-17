package com.astrika.abg.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.astrika.abg.model.CardMaster;
import com.astrika.abg.model.CardType;
import com.astrika.abg.repository.CardMasterRepository;
import com.astrika.abg.service.CardMasterService;
import com.astrika.abg.model.ImageMaster;
import com.astrika.abg.service.ImageService;
import com.astrika.abg.util.RandomCodeGenerator;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;



@Service
public class CardMasterServiceImpl implements CardMasterService{
	
	@Autowired
	private  CardMasterRepository cardMasterRepository;
	
	@Autowired
	private ImageService imageService;

	@Override
	public void generateCard(int cardCount) {
		// TODO Auto-generated method stub
		for(int i=0;i<=cardCount;i++)
		{
		CardMaster card=new CardMaster();
		String cardNumber=RandomCodeGenerator.randomNumber(16);
		card.setCardNumber(Long.parseLong(cardNumber));
		card.setQrImage(qrCode(card.getCardNumber()));
		cardMasterRepository.save(card);
		}
		
	}
	
	public ImageMaster qrCode(Long id){
		ImageMaster qrImage = null;
		try{
		String dir="QrCode";
		String cardNumber=Long.toString(id);		
		ByteArrayOutputStream out=QRCode.from(cardNumber).to(ImageType.PNG).withSize(200, 200).stream();
		byte[] qrbytes=out.toByteArray();
		qrImage=imageService.addEventImage(qrbytes,"png", cardNumber+"_QRCode", true, false, dir);
		qrImage.getImagePath();
		}catch(Exception e){
			e.printStackTrace();
		}
		return qrImage;
	}

	@Override
	public List<CardMaster> findCardByActive(boolean active) {
       List<CardMaster> cardMasterList=cardMasterRepository.findByActive(active);
		return cardMasterList;
	}

	@Override
	public CardMaster updateCard(CardMaster cardMaster) {
		return cardMasterRepository.save(cardMaster);
	}

	@Override
	public long findAssignedCardTypeCount(CardType cardType) {
    Long cardTypeCount=cardMasterRepository.countAssignedCardType(cardType);
    return cardTypeCount;
	}	


	

}
