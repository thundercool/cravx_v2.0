package com.astrika.abg.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.astrika.abg.repository.ImageRepository;


@Component
public class ImageUtil {

	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	HandlerValue propertyValues;
	
	public ArrayList<File> save(byte[] imageData,String fileFormat,String fileName,String locationToSave,boolean isRestaurant,boolean isEvite,boolean isMenu,boolean applyWatermark) throws IOException
	{
		File thumbnailImageFile = null;
		locationToSave=createFilesDir(locationToSave);
		BufferedImage uploadedImage=createImageFromBytes(imageData);
		if(fileFormat.equalsIgnoreCase("png") || fileFormat.equalsIgnoreCase(".png")){
			uploadedImage=changeImageFormat(uploadedImage,"jpg",true);
			fileFormat="jpg";
		}
		BufferedImage originalImage=createImageFromBytes(imageData);
		if(isRestaurant || isMenu)
		{
			if(applyWatermark){
				uploadedImage=addWaterMark(uploadedImage,propertyValues.IMAGE_WATERMARK_TO_APPLY);
			}
			BufferedImage thumbnailImage=convertImageToThumbnail(originalImage,propertyValues.IMAGE_CREATE_THUMBNAIL);
			if(propertyValues.IMAGE_CREATE_THUMBNAIL)
			{
				String thumbNailFileName="";
				if(fileName.contains(".")){
					thumbNailFileName=fileName.substring(0,fileName.indexOf(CharPool.PERIOD))+"Thumbs"+CharPool.PERIOD+fileFormat;
				}
				else{
					thumbNailFileName=fileName+"Thumbs"+CharPool.PERIOD+fileFormat;
				}
				
				thumbnailImageFile=copyImageFile(thumbnailImage, locationToSave, thumbNailFileName);
			}
			uploadedImage=validateAndCompressImage(uploadedImage, propertyValues.IMAGE_VALIDATE_FOR_COMRESS, fileFormat, fileName);
			
		}
		
		else if(isEvite)
		{
			if(applyWatermark){
				uploadedImage=addWaterMark(uploadedImage,propertyValues.IMAGE_WATERMARK_TO_APPLY);
			}
			BufferedImage thumbnailImage=convertImageToThumbnail(originalImage,propertyValues.IMAGE_CREATE_THUMBNAIL);
			if(propertyValues.IMAGE_CREATE_THUMBNAIL)
			{
				thumbnailImageFile=copyImageFile(thumbnailImage, locationToSave, fileName+"Thumbs"+fileFormat);
			}
		}
//		else if(isMenu)
//		{
//			
//		}
		String fileNameToPersist ="filename";
		if(fileName.contains(".")){
			fileNameToPersist = fileName.substring(0,fileName.indexOf('.'));
		}
		else{
			fileNameToPersist = fileName;
		}
	
		String fullFileName=fileNameToPersist+"."+fileFormat;
		
		File location=copyImageFile(uploadedImage,locationToSave,fullFileName);
		ArrayList<File> filesToSend=new ArrayList<File>();
		filesToSend.add(location);
		filesToSend.add(thumbnailImageFile);
		return filesToSend;
		
	}
	
	
	

	public File saveMemberImage(byte[] imageData,String fileFormat,String fileName,String locationToSave) throws IOException
	{
		
		locationToSave=createFilesDir(locationToSave);
		BufferedImage uploadedImage=createImageFromBytes(imageData);
		uploadedImage=validateAndCompressImage(uploadedImage, propertyValues.IMAGE_VALIDATE_FOR_COMRESS, fileFormat, fileName);
		String fileNameToPersist=fileName;
		String fullFileName=fileNameToPersist+"."+fileFormat;
		File location=copyImageFile(uploadedImage,locationToSave,fullFileName);
		return location;
   }

	public File saveMember(byte[] imageData,String fileFormat,String fileName,String saveLocation) throws IOException
	{
		return saveMemberImage(imageData, fileFormat, fileName,saveLocation);
	}
	
	
	
	
	public ArrayList<File> saveInstitution(byte[] imageData,String fileFormat,String fileName,String saveLocation,boolean applyWaterMark) throws IOException
	{
		return save(imageData, fileFormat, fileName,saveLocation, true, false, false,applyWaterMark);
	}
	
	
	
	public ArrayList<File> saveMenu(byte[] imageData,String fileFormat,String fileName,String saveLocation) throws IOException
	{
		return save(imageData, fileFormat, fileName,saveLocation, false, false, true,true);
	}
	
	
	public ArrayList<File> saveEvite(byte[] imageData,String fileFormat,String fileName,String saveLocation) throws IOException
	{
		return save(imageData, fileFormat, fileName,saveLocation, false, true, false,true);
	}
	
	
	public BufferedImage validateAndCompressImage(BufferedImage uploadedImage,boolean isImageSizeToValidate,String fileFormat,String fileName) throws IOException
	{
		if(isImageSizeToValidate){
			long sizeValue=propertyValues.IMAGE_COMPRESS_VALUE;
			String tempFileExtension=fileName.substring(fileName.lastIndexOf('.')+1);
			File temp=File.createTempFile("gourme7FileSize",CharPool.PERIOD+tempFileExtension );
//			Iterator<ImageWriter> writers = ImageIO.getImageWritersBySuffix("png");
//			if (!writers.hasNext())
//			{
//				uploadedImage=changeImageFormat(uploadedImage, "jpeg", true);
//			}
			ImageIO.write(uploadedImage, tempFileExtension, temp);
			long sizeOfImage=temp.length();
			
			if(sizeValue<sizeOfImage)
			{
				uploadedImage=compressImage(uploadedImage,propertyValues.IMAGE_COMPRESS_QUALITY,sizeValue,fileFormat);
				//FileUtils.deleteQuietly(temp);
			}
		}
		
		return uploadedImage;
	}
	
	public int compareAspectRatio(BufferedImage uploadedImage,double aspectValueToCompare)
	{
		aspectValueToCompare=round(aspectValueToCompare,2);
		double aspectRatioImage=round((double)uploadedImage.getHeight()/uploadedImage.getWidth(),2);
		return Double.compare(aspectRatioImage,aspectValueToCompare);
	}
	
	
	public int compareAspectRatio(BufferedImage image1,BufferedImage image2)
	{
		return compareAspectRatio(image1,getAspectRatio(image2));
	}
	
	public double getAspectRatio(BufferedImage uploadedImage)
	{
		return (double)uploadedImage.getHeight()/uploadedImage.getWidth();
	}
	
		public BufferedImage createImageFromBytes(byte[] imageData) {
	    ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
	    try {
	        return ImageIO.read(bais);
	    } catch (IOException e) {
	        throw new RuntimeException(e);
	    }
	}
	

	
	public BufferedImage resizeImage(BufferedImage image,int width,int height, int typeOfImage)
	{
		int type=0;
		if(typeOfImage==0)
			type= image.getType() == 0? BufferedImage.TYPE_INT_ARGB : image.getType();
		else
			type=typeOfImage;
		BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(image, 0, 0, width, height, null);
		g.dispose();
		resizedImage=scale(resizedImage, 1);
		return resizedImage;
	}
	
	
	public BufferedImage changeImageFormat(BufferedImage sourceImage,String fileFormat,boolean changeImageExt) throws IOException
	{
		if(changeImageExt){
	         int type = sourceImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : sourceImage.getType();
	         BufferedImage resizeImagePng = resizeImage(sourceImage,sourceImage.getWidth(),sourceImage.getHeight(),type);
	         
	         File temp=File.createTempFile("gourmet7", fileFormat);
	         
	         ImageIO.write(resizeImagePng,fileFormat,temp);
	         File url = new File(temp.getPath());
	         BufferedImage image = ImageIO.read(url);
	         
	         //FileUtils.deleteQuietly(temp);
	         
			return image;
		}
		return sourceImage;
	}
	
	
	public BufferedImage addWaterMark(BufferedImage image,boolean addWatermark) throws IOException
	{
		if(addWatermark){
			String rootPath = this.getClass().getResource("ImageUtil.class").getPath();
			//check if the system is windows or unix if windows replace the / with \\ and if unix let it remain /
			//rootPath.indexOf(CharPool.FORWARD_SLASH) if the path copntanins / then go next check. File.separator.indexOf(CharPool.FORWARD_SLASH) shud not be 0,a 0 value means its in unix file system.
			if(rootPath.indexOf(CharPool.FORWARD_SLASH)==-1) 
				rootPath=rootPath.replace("\\", "/");
			String fileUrl=URLDecoder.decode(rootPath.toString(),"UTF-8");
			if(fileUrl.contains("file"))
				fileUrl=fileUrl.substring(fileUrl.indexOf(CharPool.FORWARD_SLASH)-1);
			if(fileUrl.indexOf(CharPool.FORWARD_SLASH+"WEB-INF"+CharPool.FORWARD_SLASH)!=-1)
				fileUrl = fileUrl.substring(1, fileUrl.indexOf(CharPool.FORWARD_SLASH+"WEB-INF"+CharPool.FORWARD_SLASH))+CharPool.FORWARD_SLASH+"WEB-INF"+CharPool.FORWARD_SLASH+"classes"+CharPool.FORWARD_SLASH+propertyValues.IMAGE_WATERMARK_VALUE;
			else
			{
				fileUrl = fileUrl.substring(1, fileUrl.indexOf(CharPool.FORWARD_SLASH+"target"))+CharPool.FORWARD_SLASH+"target"+CharPool.FORWARD_SLASH+"test-classes"+CharPool.FORWARD_SLASH+propertyValues.IMAGE_WATERMARK_VALUE;
			}
				
//			fileUrl = fileUrl.replace("/", File.separator);
//			URL url =  new URL(fileUrl);
			Graphics2D g2d = (Graphics2D) image.getGraphics();
			BufferedImage watermarkSource=getImageFromFilePath(fileUrl);
			Graphics2D g1d=(Graphics2D)watermarkSource.getGraphics();
			 BufferedImage waterMarkImage=imageToBufferedImage(makeColorTransparent(watermarkSource,g1d.getBackground()));

			 
	         
	         AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.1f);
	         g2d.setComposite(alphaChannel);
	         waterMarkImage=resizeImage(waterMarkImage, image.getWidth(), image.getHeight(), 0);
	         // calculates the coordinate where the image is painted
	         int topLeftX = (image.getWidth() - waterMarkImage.getWidth()) / 2;
	         int topLeftY = (image.getHeight() - waterMarkImage.getHeight()) / 2;
	  
	         // paints the image watermark
	         g2d.drawImage(waterMarkImage, topLeftX, topLeftY, null);
	
	         //Free graphic resources
	         g2d.dispose();
		}
		return image;
	
	}
	
	 private static BufferedImage imageToBufferedImage(final Image image)
	   {
	      final BufferedImage bufferedImage =
	         new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
	      final Graphics2D g2 = bufferedImage.createGraphics();
	      g2.drawImage(image, 0, 0, null);
	      g2.dispose();
	      return bufferedImage;
	    }
	public static Image makeColorTransparent(final BufferedImage im, final Color color)
	   {
	      final ImageFilter filter = new RGBImageFilter() {
			
	    	  public final int filterRGB(final int x, final int y, final int rgb)
		         {
	    		  int markerRGB = color.getRGB() | 0xFFFFFFFF;
		            if ((rgb | 0xFF000000) == markerRGB)
		            {
		               // Mark the alpha bits as zero - transparent
		               return 0x00FFFFFF & rgb;
		            }
		            else
		            {
		               // nothing to do
		               return rgb;
		            }
		         }
	      		};

	      		final ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
	      	     return Toolkit.getDefaultToolkit().createImage(ip);
	      }
	
	
	
	public BufferedImage changeAspectRatio(BufferedImage image,boolean changeAspectRatio)
	{
		if(changeAspectRatio){
			double aspectValue=getAspecValueFromProp(propertyValues.IMAGE_ASPECT_RATIO_VALUE_CHANGE);
			
			BufferedImage scaledImage=scale(image, aspectValue);
			return scaledImage;
		}
		return image;
	}
	
	
	public double getAspecValueFromProp(String aspectRatio)
	{
		int indexOfSeparator=aspectRatio.indexOf(CharPool.COLON);
		String width=aspectRatio.substring(0,indexOfSeparator).trim();;
		String height=aspectRatio.substring(indexOfSeparator+1).trim();
		double aspectValue=Double.parseDouble(height)/Double.parseDouble(width);
		return aspectValue;
	}
	
	
	public File copyImageFile(BufferedImage image,String destinationLoc,String filename) throws IOException
	{
		Date date=new Date();
		Long dateVal=date.getTime();
		String fileNameToSave=filename.substring(0,filename.indexOf(CharPool.PERIOD)).concat(dateVal.toString()).concat(filename.substring(filename.indexOf(CharPool.PERIOD)));
		File dest=new File(destinationLoc+CharPool.FORWARD_SLASH+fileNameToSave);
		if(!dest.exists())
		{
			dest.createNewFile();
		}
		String fileFormat=filename.substring(filename.lastIndexOf('.')+1);
		ImageIO.write(image, fileFormat, dest);
		
		return dest;
	}
	
	
	
	public BufferedImage convertImageToThumbnail(BufferedImage image,boolean createThumbnail)
	{
		if(createThumbnail){
			String dimention=propertyValues.IMAGE_THUMBNAIL_VALUES;
			int indexOfDimentionSeparator=dimention.indexOf("x");
			if(indexOfDimentionSeparator==-1)
				indexOfDimentionSeparator=dimention.indexOf("X");
			
			String width= dimention.substring(0,indexOfDimentionSeparator).trim();
			String height= dimention.substring(indexOfDimentionSeparator+1).trim();
			
			int typeOfImage=BufferedImage.SCALE_SMOOTH;
				 
			BufferedImage thumbnailImage=resizeImage(image, Integer.parseInt(width), Integer.parseInt(height), typeOfImage);
			
			return thumbnailImage;
		}
		return image;
	}
	
	public BufferedImage scale(BufferedImage source,double ratio) {
		  int w = (int) (source.getWidth() * ratio);
		  int h = (int) (source.getHeight() * ratio);
		  return copy( source, new BufferedImage( w, h, BufferedImage.TYPE_INT_RGB ) );
	}
	  private static BufferedImage copy(BufferedImage source, BufferedImage target) {
	         Graphics2D g2 = target.createGraphics();
	         g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR );
	         double scalex = (double) target.getWidth()/ source.getWidth();
	         double scaley = (double) target.getHeight()/ source.getHeight();
	         AffineTransform at = AffineTransform.getScaleInstance(scalex, scaley);
	         g2.drawRenderedImage(source, at);
	         g2.dispose();
	         return target;
	     }

	
	private BufferedImage getCompatibleImage(int w, int h) {
		  GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		  GraphicsDevice gd = ge.getDefaultScreenDevice();
		  GraphicsConfiguration gc = gd.getDefaultConfiguration();
		  BufferedImage image = gc.createCompatibleImage(w, h);
		  return image;
	}
	
	
	public boolean landscapeImage(BufferedImage image)
	{
		return image.getHeight()<image.getWidth()?true:false;
	}
	
	
	public boolean potraitImage(BufferedImage image)
	{
		return image.getHeight()>image.getWidth()?true:false;
	}
	
	
	public BufferedImage compressImage(BufferedImage imageToCompress,float quality,long sizeToReduce,String fileFormat) throws IOException
	{
		Iterator<ImageWriter> writers = ImageIO.getImageWritersBySuffix(fileFormat);
		if (!writers.hasNext()) throw new IllegalStateException("No writers found");
		ImageWriter writer = (ImageWriter) writers.next();
		// Create the ImageWriteParam to compress the image.
		ImageWriteParam param = writer.getDefaultWriteParam();
		param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		param.setCompressionQuality(quality);
		// The output will be a ByteArrayOutputStream (in memory)
		ByteArrayOutputStream bos = new ByteArrayOutputStream(32768);
		ImageOutputStream ios = ImageIO.createImageOutputStream(bos);
		writer.setOutput(ios);
		writer.write(null, new IIOImage(imageToCompress, null, null), param);
		ios.flush(); // otherwise the buffer size will be zero!
		// From the ByteArrayOutputStream create a RenderedImage.
		ByteArrayInputStream in = new ByteArrayInputStream(bos.toByteArray());
		BufferedImage out = ImageIO.read(in);
		long size = bos.toByteArray().length; 
		
		if(sizeToReduce<size)
		{
			quality=quality-0.05f;
			compressImage(out,quality,sizeToReduce,fileFormat);
		}
		return out;
		
	}
	
	public boolean deleteFile(String pathToFile)
	{
		File fileToDelete=new File(pathToFile);
		return fileToDelete.delete();
	}
	
	
	private double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	
	public String getAbsoluteSaveLocationDir(int...locationDir) throws UnsupportedEncodingException
	{
		String fileLocation=createFilesDir("");
		for(int i:locationDir)
		{
			File fileFolder=new File(fileLocation+File.separator+i);
			if(!fileFolder.exists())
				fileFolder.mkdir();
			fileLocation=fileFolder.getPath();
		}
		return URLDecoder.decode(fileLocation,"UTF-8");
	}
	
	
	public String getAbsoluteSaveLocationDir(String...locationDir) throws UnsupportedEncodingException
	{
		String fileLocation=createFilesDir("");
		for(String i:locationDir)
		{
			File fileFolder=new File(fileLocation+File.separator+i);
			if(!fileFolder.exists())
				fileFolder.mkdirs();
			fileLocation=fileFolder.getPath();
		}
		return URLDecoder.decode(fileLocation,"UTF-8");
	}
	
	
	
	
	public String createFilesDir(String location) throws UnsupportedEncodingException
	{
		System.out.println("*****location:"+location);
		String fileUrl=location;
		if(location==null || location.isEmpty()){
			String rootPath = this.getClass().getResource("ImageUtil.class").getPath();
			//check if the system is windows or unix if windows replace the / with \\ and if unix let it remain /
			//rootPath.indexOf(CharPool.FORWARD_SLASH) if the path copntanins / then go next check. File.separator.indexOf(CharPool.FORWARD_SLASH) shud not be 0,a 0 value means its in unix file system.
			/*if(rootPath.indexOf(CharPool.FORWARD_SLASH)!=-1 && File.separator.indexOf(CharPool.FORWARD_SLASH)!=0)
				rootPath=rootPath.replace("/", File.separator);*/
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
				fileUrl = fileUrl.substring(0, fileUrl.indexOf(CharPool.FORWARD_SLASH+"checkout"+CharPool.FORWARD_SLASH+"target"+CharPool.FORWARD_SLASH+"classes"));
		
			System.out.println("*****fileUrl3:"+fileUrl);
			fileUrl=fileUrl.replace("/ROOT", "/");
			
			if(!fileUrl.contains(propertyValues.IMAGE_SAVE_ROOT_DIR))
				fileUrl=fileUrl+CharPool.FORWARD_SLASH+propertyValues.IMAGE_SAVE_ROOT_DIR;
			File fileFolder = new File(fileUrl);
			if(!fileFolder.exists())
				fileFolder.mkdir();
			
//			location=fileFolder.getAbsolutePath();
		}
//		System.out.println("*****location:"+location);
		/*int i = fileUrl.indexOf("webapps");   
		if(i>0)
		fileUrl = fileUrl.substring(0, i)+"Files/Mice";*/
		return fileUrl;
	}
	
		public String getRelativePath(String path) throws UnsupportedEncodingException
	{
		String folderToSearch=File.separator+propertyValues.IMAGE_SAVE_ROOT_DIR+File.separator;
		return URLDecoder.decode(path.substring(path.lastIndexOf(folderToSearch)),"UTF-8");
	}
	
	
	public String getLocationToFilesDirectory() throws UnsupportedEncodingException
	{
		return createFilesDir("")+File.separator;
	}
	
	private BufferedImage getImageFromFileUrl(URL url) throws IOException
	{
		String fileUrl=URLDecoder.decode(url.toString(),"UTF-8");
		char fileSeparator;
		if(File.separatorChar=='\\' && fileUrl.contains("\\"))
			fileSeparator=File.separatorChar;
		else
			fileSeparator='/';
		if(fileUrl.contains("file"))
			fileUrl=fileUrl.substring(fileUrl.indexOf(fileSeparator));
		
		return ImageIO.read(new File(fileUrl));
	}
	
	private BufferedImage getImageFromFilePath(String url) throws IOException
	{
		return ImageIO.read(new File(url));
	}



	public ArrayList<File> saveAd(byte[] imageInByteArray, String fileFormat,
			String fileName, String locationToSave) throws IOException {
		
		locationToSave=createFilesDir(locationToSave);
		BufferedImage uploadedImage=createImageFromBytes(imageInByteArray);
		String fileNameToPersist=fileName.substring(0,fileName.indexOf('.'));
		String fullFileName=fileNameToPersist+"."+fileFormat;
		
		File location=copyImageFile(uploadedImage,locationToSave,fullFileName);
		ArrayList<File> filesToSend=new ArrayList<File>();
		filesToSend.add(location);
		return filesToSend;
	}



	public ArrayList<File> saveInstructor(byte[] byteArray, String ext,
			String fileName, String locationToSave, boolean applyWatermark) throws IOException {

		return save(byteArray, ext, fileName, locationToSave, true, false, false, applyWatermark);
	}
	
	
	public ArrayList<File> saveEventImage(byte[] imageData,String fileFormat,String fileName,String saveLocation,boolean applyWaterMark) throws IOException
	{
		return save(imageData, fileFormat, fileName,saveLocation, true, false, false,applyWaterMark);
	}

}
