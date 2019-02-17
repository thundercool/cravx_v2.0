package com.astrika.abg.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.astrika.abg.model.ImageMaster;
import com.astrika.abg.repository.ImageRepository;
import com.astrika.abg.service.ImageService;
import com.astrika.abg.exception.BusinessException;
import com.astrika.abg.exception.FileNameException;
import com.astrika.abg.exception.NoSuchImageException;
import com.astrika.abg.exception.SystemException;
import com.astrika.abg.util.CharPool;
import com.astrika.abg.util.HandlerValue;
import com.astrika.abg.util.ImageUtil;
import com.astrika.abg.util.StringPool;



/**
 * Service implementation for Image.
 * 
 * @author Priyanka
 */
@Service
public class ImageServiceImpl implements ImageService{
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	private ImageUtil imageUtil;
	
	@Autowired
	HandlerValue propertyValues;
	
	
	

	
	public String delete(long imageId) throws UnsupportedEncodingException, NoSuchImageException{
		ImageMaster image= findById(imageId);
		String s = deleteFromPhysicalLocation(image);
		
		return s;
	}
	/**
	 * Delete from physical location
	 * @param image containing the information of the file to delete from physical location
	 * @return the location of the file deleted
	 * @throws UnsupportedEncodingException
	 * @author Debjyoti Nath
	 */
	private String deleteFromPhysicalLocation(ImageMaster image)throws UnsupportedEncodingException
	{
		//delete image file
		String rootPath=imageUtil.getLocationToFilesDirectory();
		if(rootPath.contains(propertyValues.IMAGE_SAVE_ROOT_DIR))
			rootPath=rootPath.substring(0,rootPath.indexOf(propertyValues.IMAGE_SAVE_ROOT_DIR));
		rootPath=rootPath.replace("\\", "/");
		rootPath=rootPath.substring(0,rootPath.lastIndexOf(CharPool.FORWARD_SLASH));
		String filesLocation=rootPath+image.getImagePath();
		String locationToSave=filesLocation.substring(0,filesLocation.lastIndexOf(CharPool.FORWARD_SLASH));
		imageUtil.deleteFile(filesLocation);
		
		//delete thumbnailimage
		
		filesLocation=rootPath+image.getThumbImagePath();
		locationToSave=filesLocation.substring(0,filesLocation.lastIndexOf(CharPool.FORWARD_SLASH));
		imageUtil.deleteFile(filesLocation);
		return locationToSave;
	}
	
	public ImageMaster restore(long imageId){
		ImageMaster image=imageRepository.findOne(imageId);
		image.setActive(true);
		return imageRepository.save(image);
	}
	
	public ImageMaster findById(long imageId) throws NoSuchImageException{
		return imageRepository.findOne(imageId);
	}
	
	public ImageMaster findByImageId(long imageId) throws NoSuchImageException{
		return imageRepository.findByImageId(imageId);
	}
	
	
	

	private ImageMaster saveImage(File file,File thumbnail,String fileName,ImageMaster imageMaster) throws BusinessException, UnsupportedEncodingException
	{
		String fileNameVal=file.getName();
		ImageMaster image=null;
		if(imageMaster!=null && imageMaster.getImageId()>0){
			image=imageMaster;
		}
		else
		{
			image= new ImageMaster();
		}
		
		if (file != null) {
			fileNameVal=validate(fileNameVal, true, file);
			image.setSize(file.length());
		}
		else {
			image.setSize(0);
		}

		image.setImageName(fileNameVal);
		image.setExtension(fileNameVal.substring(fileNameVal.lastIndexOf(StringPool.PERIOD)+1));
		String imagePath=imageUtil.getRelativePath(file.getAbsolutePath()).replace("\\", "/");
		if(thumbnail!=null)
		image.setThumbImagePath(imageUtil.getRelativePath(thumbnail.getAbsolutePath()).replace("\\", "/"));
		image.setImagePath(imagePath);
		fileNameVal=validate(fileNameVal, true, file);
		
		return imageRepository.save(image);
	}
	
	
	private ImageMaster saveImage(File file,File thumbnail,File mobileThumbnailImage,File mobileImageFile,File originalFile,String fileName,ImageMaster imageMaster) throws BusinessException, UnsupportedEncodingException
	{
		String fileNameVal=file.getName();
		ImageMaster image=null;
		if(imageMaster!=null && imageMaster.getImageId()>0){
			image=imageMaster;
		}
		else
		{
			image= new ImageMaster();
		}
		
		if (file != null) {
			fileNameVal=validate(fileNameVal, true, file);
			image.setSize(file.length());
		}
		else {
			image.setSize(0);
		}

		image.setImageName(fileNameVal);
		image.setExtension(fileNameVal.substring(fileNameVal.lastIndexOf(StringPool.PERIOD)+1));
		String imagePath=imageUtil.getRelativePath(file.getAbsolutePath()).replace("\\", "/");
		imagePath = imagePath.replace("/Files", "Files");
		image.setImagePath(imagePath);
		if(thumbnail!=null){
			String thumbnailPath = imageUtil.getRelativePath(thumbnail.getAbsolutePath()).replace("\\", "/");
			thumbnailPath = thumbnailPath.replace("/Files", "Files");
			image.setThumbImagePath(thumbnailPath);
		}
		if(mobileThumbnailImage!=null){
			String mobileThumbnailImagePath = imageUtil.getRelativePath(mobileThumbnailImage.getAbsolutePath()).replace("\\", "/");
			mobileThumbnailImagePath = mobileThumbnailImagePath.replace("/Files", "Files");
			image.setMobileThumbImagePath(mobileThumbnailImagePath);
		}
		if(mobileImageFile!=null){
			String Path = imageUtil.getRelativePath(mobileImageFile.getAbsolutePath()).replace("\\", "/");
			Path = Path.replace("/Files", "Files");
			image.setMobilePath(Path);
		}
		fileNameVal=validate(fileNameVal, true, file);
		if(originalFile!=null){
			String originalFilePath = imageUtil.getRelativePath(originalFile.getAbsolutePath()).replace("\\", "/");
			originalFilePath = originalFilePath.replace("/Files", "Files");
			image.setOriginalImagePath(originalFilePath);
		}
		return imageRepository.save(image);
	}
	
	
	
	public String validate(
			String fileName, boolean validateFileExtension, InputStream is)
		throws BusinessException, SystemException {

		return validate(fileName, validateFileExtension);


//		try {
//			if ((is == null) ||
//				((PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE) > 0) &&
//				 (is.available() >
//					PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE)))) {
//
//				throw new FileSizeException(fileName);
//			}
//		}
//		catch (IOException ioe) {
//			throw new FileSizeException(ioe.getMessage());
//		}
	}
	
	private String validate(String fileName, boolean validateFileExtension, File file) throws BusinessException{

		return validate(fileName, validateFileExtension);

//		if ((PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE) > 0) &&
//			((file == null) ||
//			 (file.length() >
//				PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE)))) {
//
//			throw new FileSizeException(fileName);
//		}
	}
	
	public String validate(String fileName, boolean validateFileExtension) throws BusinessException {

			if (!isValidName(fileName)) {
				throw new FileNameException(fileName);
			}
			if(fileName.contains(" "))
			{
				fileName=fileName.replace(" ", "_");
			}
			
			return fileName;

//			if (validateFileExtension) {
//				boolean validFileExtension = false;
//
//				String[] fileExtensions = PrefsPropsUtil.getStringArray(
//					PropsKeys.DL_FILE_EXTENSIONS, StringPool.COMMA);
//
//				for (int i = 0; i < fileExtensions.length; i++) {
//					if (StringPool.STAR.equals(fileExtensions[i]) ||
//						StringUtil.endsWith(fileName, fileExtensions[i])) {
//
//						validFileExtension = true;
//
//						break;
//					}
//				}
//
//				if (!validFileExtension) {
//					throw new FileExtensionException(fileName);
//				}
//			}
		}

	protected boolean isValidName(String name) {
		if ((name == null) ||
			name.contains("\\") ||
			name.contains("\\\\") ||
			name.contains("//") ||
			name.contains(":") ||
			name.contains("*") ||
			name.contains("?") ||
			name.contains("\"") ||
			name.contains("<") ||
			name.contains(">") ||
			name.contains("|") ||
			name.contains("[") ||
			name.contains("]") ||
			name.contains("../") ||
			name.contains("/..")) {

			return false;
		}

		return true;
	}
	

	@Override
	public ImageMaster addEventImage(byte[] imageInByteArray,
			String fileFormat, String fileName, boolean memberProfileImage,
			boolean applyWaterMark, String... saveDirStructure)
			throws SystemException, BusinessException, IOException {
		if (!isValidName(fileName)) {
			throw new FileNameException(fileName);
		}
			String locationToSave=imageUtil.getAbsoluteSaveLocationDir(saveDirStructure);
			return addOutletImage(imageInByteArray, fileFormat, fileName, locationToSave,applyWaterMark);		
	}
	
	
	@Override
	public ImageMaster addUserProfileImage(byte[] imageInByteArray,
			String fileFormat, String fileName, boolean memberProfileImage,
			boolean applyWaterMark, String... saveDirStructure)
			throws SystemException, BusinessException, IOException {
		if(!isValidName(fileName)){
			throw new FileNameException(fileName);
		}
		String locationToSave=imageUtil.getAbsoluteSaveLocationDir(saveDirStructure);
		return addOutletImage(imageInByteArray, fileFormat, fileName, locationToSave,applyWaterMark);
	}
	
	
	private ImageMaster addOutletImage(byte[] imageInByteArray,String fileFormat,String fileName,String locationToSave,boolean applyWaterMark) throws IOException, BusinessException
	{
		ArrayList<File> file=imageUtil.saveEventImage(imageInByteArray, fileFormat, fileName,locationToSave,applyWaterMark);
		return saveImage(file.get(0),file.get(1),fileName,null);
	}

	@Override
	public ImageMaster save(ImageMaster image) {
		return imageRepository.save(image);
	}

	
	
	
}
