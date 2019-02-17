package com.astrika.abg.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.astrika.abg.model.ImageMaster;
import com.astrika.abg.exception.BusinessException;
import com.astrika.abg.exception.NoSuchImageException;
import com.astrika.abg.exception.SystemException;


public interface ImageService {
	
	
	String delete(long imageId) throws UnsupportedEncodingException, NoSuchImageException;
	
	ImageMaster restore(long imageId);
	
	ImageMaster save(ImageMaster image);
	
	
	ImageMaster findById(long imageId) throws NoSuchImageException;
	
	ImageMaster findByImageId(long imageId) throws NoSuchImageException;
	

	public ImageMaster addEventImage(byte[] imageInByteArray,String fileFormat,String fileName,boolean memberProfileImage,boolean applyWaterMark,String...saveDirStructure) throws SystemException,BusinessException,IOException;
	
	public ImageMaster addUserProfileImage(byte[] imageInByteArray,String fileFormat,String fileName,boolean memberProfileImage,boolean applyWaterMark,String...saveDirStructure) throws SystemException,BusinessException,IOException;
	
}
