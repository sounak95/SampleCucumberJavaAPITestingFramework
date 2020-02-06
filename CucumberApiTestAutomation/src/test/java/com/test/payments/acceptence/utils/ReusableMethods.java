package com.test.payments.acceptence.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ReusableMethods {
	
	public static Logger log  = LogManager.getLogger(ReusableMethods.class.getName());
	private static void copyFileUsingStream(File source, File dest) throws IOException {
		InputStream is = null;
		OutputStream os = null;
		
		try {
			is = new FileInputStream(source);
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;
			
			while((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
			
		} finally {
			is.close();
			os.close();
		}
	}
	
	public static void copyLatestExtentReport() throws IOException {
		Date d = new Date();
		String date = d.toString().replace(":", "_").replace(" ", "_");
		File source = new File(System.getProperty("user.dir") + "\\output\\report.html");
		File dest = new File(System.getProperty("user.dir") + "\\output\\" + date.toString() + ".html");
		copyFileUsingStream(source, dest);
	}
	
	public static void logInfo(String str)
	{
		log.info(str);
	}
	
}
