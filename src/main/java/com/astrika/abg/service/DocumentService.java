package com.astrika.abg.service;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


public interface DocumentService {

	
	

	public File save(byte[] fileData,String fileFormat,String fileName,String locationToSave) throws IOException;
	
	public File saveFile(byte[] fileData,String fileFormat,String fileName,String locationToSave) throws IOException;
	
	public File copyDocumentFile(byte[] fileData,String destinationLoc,String filename) throws IOException;
	
	public boolean deleteFile(String pathToFile);
	
	public String getAbsoluteSaveLocationDir(int...locationDir) throws UnsupportedEncodingException;
	
	public String getAbsoluteSaveLocationDir(String...locationDir) throws UnsupportedEncodingException;
	
	public String createFilesDir(String location) throws UnsupportedEncodingException;
	
	public String getRelativePath(String path) throws UnsupportedEncodingException;
	
	public String getLocationToFilesDirectory() throws UnsupportedEncodingException;
	
	
	
}
