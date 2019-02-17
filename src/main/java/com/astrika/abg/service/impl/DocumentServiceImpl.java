package com.astrika.abg.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.astrika.abg.service.DocumentService;
import com.astrika.abg.util.CharPool;
import com.astrika.abg.util.HandlerValue;

@Service
public class DocumentServiceImpl implements DocumentService{

	
	
	@Autowired
	HandlerValue propertyValues;
	
	FileInputStream fileInputStream;
	/**
	 * This method takes the bytes, converts it to image and perform operations on the image.It also saves the file at a location specified by <code>IMAGE_COPY_DEST_LOCATION</code> in {@link PropsValues} the property file  
	 * @param imageData in bytes
	 * @param fileFormat in string
	 * @param fileName in string
	 * @return {@link File}
	 * @throws IOException
	 */
	public File save(byte[] fileData,String fileFormat,String fileName,String locationToSave) throws IOException
	{
		// byteArray, ext, filename, 1
		System.out.println("***save method***********************************************************");
		System.out.println(fileName+"fileName**************************************************************");
		System.out.println(locationToSave+"locationToSave**************************************************************");
		locationToSave=createFilesDir(locationToSave);
		String fileNameToPersist=fileName.substring(0,fileName.indexOf('.'));
		String fullFileName=fileNameToPersist+fileFormat;
		File location=copyDocumentFile(fileData,locationToSave,fullFileName);
		return location;
		
	}
	
	public File saveFile(byte[] fileData,String fileFormat,String fileName,String locationToSave) throws IOException
	{
		// byteArray, ext, filename, 1
		System.out.println("***save method***********************************************************");
		System.out.println(fileName+"fileName**************************************************************");
		System.out.println(locationToSave+"locationToSave**************************************************************");
		locationToSave=createFilesDir(locationToSave);
		String fullFileName=fileName+fileFormat;
		File location=copyDocumentFile(fileData,locationToSave,fullFileName);
		return location;
		
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * This method writes a image to the destination provided.
	 * 
	 * @param image
	 * @param destinationLoc
	 * @param filename
	 * @return the file that is being created in this method as {@link File}
	 * @throws IOException
	 * @author Debjyoti Nath change by wasim 
	 */
	public File copyDocumentFile(byte[] fileData,String destinationLoc,String filename) throws IOException
	{
		Date date=new Date();
		Long dateVal=date.getTime();
		String fileNameToSave=filename.substring(0,filename.indexOf(CharPool.PERIOD)).concat(dateVal.toString()).concat(filename.substring(filename.indexOf(CharPool.PERIOD)));
		File dest=new File(destinationLoc+File.separator+fileNameToSave);
		
		
		
		System.out.print("**************************File location************************************");
		
		System.out.print(destinationLoc+"destinationLoc******");
		System.out.print(fileNameToSave+"fileNameToSave");
		
		System.out.print("**************************File location************************************");
		if(!dest.exists())
		{
			dest.createNewFile();
		}
		FileOutputStream fileOutputStream=new FileOutputStream(dest);
		String fileFormat=filename.substring(filename.lastIndexOf('.')+1);
		fileOutputStream.write(fileData);
		fileOutputStream.close();
		
		return dest;
	}
	

	
	
	
	
	
	
	
	/**
	 * Deletes a file from the given file location.
	 * @param pathToFile the absolute location of the file
	 * @return boolean if the file is deleted
	 */
	public boolean deleteFile(String pathToFile)
	{
		File fileToDelete=new File(pathToFile);
		return fileToDelete.delete();
	}
	
	
	
	/**
	 * This method makes deirectory inside <code>"Files"</code> folder created in {@link createFilesDir} method.The directory structure is defined by the array passed as integer to this method.
	 * @param locationDir as int array
	 * @return the absolute location of the last created folder/directory
	 * @throws UnsupportedEncodingException 
	 */
	public String getAbsoluteSaveLocationDir(int...locationDir) throws UnsupportedEncodingException
	{
		String fileLocation = createFilesDir("");
		for(int i:locationDir)
		{
			File fileFolder=new File(fileLocation+File.separator+i);
			if(!fileFolder.exists())
				fileFolder.mkdir();
			fileLocation=fileFolder.getPath();
		}
		return URLDecoder.decode(fileLocation,"UTF-8");
	}
	
	/**
	 * This method makes deirectory inside <code>"Files"</code> folder created in {@link createFilesDir} method.The directory structure is defined by the array passed as String to this method.
	 * @param locationDir as String array
	 * @return the absolute location of the last created folder/directory
	 * @throws UnsupportedEncodingException 
	 */
	public String getAbsoluteSaveLocationDir(String...locationDir) throws UnsupportedEncodingException
	{
		String fileLocation=createFilesDir("");
		for(String i:locationDir)
		{
			File fileFolder=new File(fileLocation+File.separator+i);
			if(!fileFolder.exists())
				fileFolder.mkdir();
			fileLocation=fileFolder.getPath();
		}
		return URLDecoder.decode(fileLocation,"UTF-8");
	}
	
	/**
	 * This method creates a directory named <code>"Files"</code>
	 * 
	 * @param location optional parameter,if provided the dir <code>"Files"</code> will be created at this location
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String createFilesDir(String location) throws UnsupportedEncodingException
	{
		String fileUrl=location;
		if(location==null || location.isEmpty()){
			String rootPath = this.getClass().getResource("DocumentServiceImpl.class").getPath();
			//check if the system is windows or unix if windows replace the / with \\ and if unix let it remain /
			//rootPath.indexOf(CharPool.FORWARD_SLASH) if the path copntanins / then go next check. File.separator.indexOf(CharPool.FORWARD_SLASH) shud not be 0,a 0 value means its in unix file system.
System.out.println("*****rootPath:"+rootPath);
			
			if(rootPath.indexOf(CharPool.FORWARD_SLASH)==-1 )
			    rootPath=rootPath.replace("\\", "/");
			fileUrl=URLDecoder.decode(rootPath.toString(),"UTF-8");
			System.out.println("*****fileUrl1:"+fileUrl);
			
			if(fileUrl.contains("file"))
				fileUrl=fileUrl.substring(fileUrl.indexOf(CharPool.FORWARD_SLASH));
			
			System.out.println("*****fileUrl2:"+fileUrl);
			if(fileUrl.contains("file"))
				fileUrl=fileUrl.substring(fileUrl.indexOf(File.separator));
			if(fileUrl.indexOf(CharPool.FORWARD_SLASH+"WEB-INF"+CharPool.FORWARD_SLASH)!=-1)
				fileUrl = fileUrl.substring(0, fileUrl.indexOf(CharPool.FORWARD_SLASH+"WEB-INF"+CharPool.FORWARD_SLASH));
			else
				fileUrl = fileUrl.substring(0, fileUrl.indexOf(CharPool.FORWARD_SLASH+"checkout"+CharPool.FORWARD_SLASH+"target"+CharPool.FORWARD_SLASH+"classes"));
		
			fileUrl=fileUrl.replace("/ROOT", "/");
			File fileFolder=new File(fileUrl+File.separator+propertyValues.IMAGE_SAVE_ROOT_DIR);
			
			System.out.println("***********************createFilesDir**********");
			System.out.println(fileUrl+File.separator+propertyValues.IMAGE_SAVE_ROOT_DIR);
			
			
			
			if(!fileFolder.exists())
				fileFolder.mkdir();
			
			location=fileFolder.getAbsolutePath();
			System.out.println("***********************createFilesDir******location****");
			System.out.println(location);
		}
		return URLDecoder.decode(location,"UTF-8");
	}
	
/*	public String createFilesDir(String location) throws UnsupportedEncodingException
	{
		System.out.println("*****location:"+location);
		String fileUrl=location;
		if(location==null || location.isEmpty()){
			String rootPath = this.getClass().getResource("DocumentServiceImpl.class").getPath();
			//check if the system is windows or unix if windows replace the / with \\ and if unix let it remain /
			//rootPath.indexOf(CharPool.FORWARD_SLASH) if the path copntanins / then go next check. File.separator.indexOf(CharPool.FORWARD_SLASH) shud not be 0,a 0 value means its in unix file system.
			if(rootPath.indexOf(CharPool.FORWARD_SLASH)!=-1 && File.separator.indexOf(CharPool.FORWARD_SLASH)!=0)
				rootPath=rootPath.replace("/", File.separator);
			System.out.println("*****rootPath:"+rootPath);
			
			if(rootPath.indexOf(CharPool.FORWARD_SLASH)==-1 )
			    rootPath=rootPath.replace("\\", "/");
			fileUrl=URLDecoder.decode(rootPath.toString(),"UTF-8");
			System.out.println("*****fileUrl1:"+fileUrl);
			
			if(fileUrl.contains("file"))
				fileUrl=fileUrl.substring(fileUrl.indexOf(CharPool.FORWARD_SLASH));
			
			System.out.println("*****fileUrl2:"+fileUrl);
			
			if(fileUrl.indexOf(CharPool.FORWARD_SLASH+"WEB-INF"+CharPool.FORWARD_SLASH)!=-1)
				fileUrl = fileUrl.substring(0, fileUrl.indexOf(CharPool.FORWARD_SLASH+"WEB-INF"+CharPool.FORWARD_SLASH));
			else
				fileUrl = fileUrl.substring(0, fileUrl.indexOf(CharPool.FORWARD_SLASH+"common"+CharPool.FORWARD_SLASH+"target"+CharPool.FORWARD_SLASH+"classes"));
		
			System.out.println("*****fileUrl3:"+fileUrl);
			
			if(!fileUrl.contains(propertyValues.IMAGE_SAVE_ROOT_DIR))
				fileUrl=fileUrl+CharPool.FORWARD_SLASH+propertyValues.IMAGE_SAVE_ROOT_DIR;
			File fileFolder = new File(fileUrl);
			if(!fileFolder.exists())
				fileFolder.mkdir();
			
//			location=fileFolder.getAbsolutePath();
		}
//		System.out.println("*****location:"+location);
		int i = fileUrl.indexOf("webapps");   
		if(i>0)
		fileUrl = fileUrl.substring(0, i)+"Files/G7";
		return fileUrl;
	}*/
	/**
	 * Gets the relative path of a location i.e the location from the root file folder/dir <code>"Files"</code>.Example if the absolute path is "D:/myFiles/Files/abc/.." this method will return "/Files/abc/..." 
	 * @param path to get the relative path of a location
	 * @return the location from the <code>"Files"</code> folder/dir
	 * @throws UnsupportedEncodingException 
	 */
	public String getRelativePath(String path) throws UnsupportedEncodingException
	{
		String folderToSearch=File.separator+propertyValues.IMAGE_SAVE_ROOT_DIR+File.separator;
		return URLDecoder.decode(path.substring(path.lastIndexOf(folderToSearch)),"UTF-8");
	}
	
	/**
	 * Converts the relative path to absolute path to the <code>"Files"</code> directory.
	 * 
	 * @return absolute path to the <code>"Files"</code> directory
	 * @throws UnsupportedEncodingException
	 */
	public String getLocationToFilesDirectory() throws UnsupportedEncodingException
	{
		return createFilesDir("")+File.separator;
	}
	

	private BufferedImage getImageFromFilePath(String url) throws IOException
	{
		return ImageIO.read(new File(url));
	}
}
