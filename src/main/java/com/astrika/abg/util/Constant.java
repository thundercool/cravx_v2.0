package com.astrika.abg.util;

import java.io.File;
import java.util.regex.Pattern;

public class Constant {
	
	public static String ROOT_DIR;
	public static String UPLOADED_FILE_DIR;
	public static String FILE_DIR_NAME = "Files/";
	static {
		ROOT_DIR = Constant.class.getResource("Constant.class").getFile();
		ROOT_DIR = ROOT_DIR.replaceAll("%20", " ");
		ROOT_DIR = ROOT_DIR.substring(0, ROOT_DIR.lastIndexOf("/WEB-INF/classes/com/astrika/abg/util/Constant.class"));
		UPLOADED_FILE_DIR = ROOT_DIR + "/" + FILE_DIR_NAME;

		File file = new File(UPLOADED_FILE_DIR);
		System.out.println(UPLOADED_FILE_DIR);
		boolean exists = file.exists();
		if (!exists) {
			// It returns false if File or directory does not exist
			file.mkdir();
		}
	}

}
