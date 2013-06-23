package com.example.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * 
 * 包含文件常用操作方法。
 * 
 * @author <a href="mailto:jiaoshi0531@163.com">焦石</a>
 * @since 1.6
 * @version 1.0.0 2011-3-30
 */
public class FileUtil {
	

	
	public static boolean createFile(File file, boolean deleteOnExists) throws IOException {
		
		if (file.exists()) {
			if(deleteOnExists){
				deleteFile(file);
			}else{
				return true;
			}
		}

        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
        	parent.mkdirs();
        }
        return file.createNewFile();
    }
	
	public static boolean deleteFile(File path) {
		boolean result = true;
		if (path.exists()) {
			if (path.isDirectory()) {
				File[] files = path.listFiles();
				for (int i = 0, count = files.length; i < count; i++) {
					result &= deleteFile(files[i]);
				}
				result &= path.delete(); // Delete empty directory.
			} else {
				result &= path.delete();
			}
			return result;
		} else {
			return false;
		}
	}
	
	public static boolean deleteInternalFilr(File path){
	    boolean result = true;
	    if(path.exists()){
	        if(path.isDirectory()){
	               File[] files = path.listFiles();
	                for (int i = 0, count = files.length; i < count; i++) {
	                    result &= deleteFile(files[i]);
	                }
	        }
	    }
	    return result;
	}
	public static boolean installFile(File path,Activity context){
	    boolean result = true;
	    try{
	           Intent intent = new Intent(Intent.ACTION_VIEW); 
	           intent.setDataAndType(Uri.fromFile(path), "application/vnd.android.package-archive"); 
	           context.startActivityForResult(intent,1);
	    }catch(Exception ex){
	        ex.printStackTrace();
	        result = false;
	    }
	    return result;
	}
	
	public static boolean copyFile(String savePath, String sourcePath){
		try{
			FileInputStream fis = new FileInputStream(new File(sourcePath));
			FileOutputStream fos = new FileOutputStream(new File(savePath));
			
			byte[] temp = new byte[1024];
			
			int ch;
			while ((ch=fis.read(temp))!=-1){
				fos.write(temp, 0, ch);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * @author yangxuehui
	 * @param oldNames 
	 * :the file names in sdcard
	 * @param newNames
	 * :the file names from server
	 * @param path	
	 * :the file path in sdcard
	 * @return 
	 * :the names of the files that need to delete
	 */
	public static String[] filesNeedTodelete(String[] oldNames,String[] newNames,String path){
		StringBuffer sb = new StringBuffer();
		boolean isExist = false;
		File file;
		try{
			for(int i = 0; i < oldNames.length ; i++){
				for(int j = 0; j < newNames.length; j++){
					if(oldNames[i].equals(newNames[j])){
						isExist = true;
						
						break;
					}
				}
				if(!isExist){
					file = new File(path + oldNames[i]);
					FileUtil.deleteFile(file);
					sb.append(oldNames[i]+",");
				}
				isExist = false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return sb.toString().split(",");
	}
	public static boolean installFile(File path,Context context){
	    boolean result = true;
	    try{
	           Intent intent = new Intent(Intent.ACTION_VIEW); 
	           intent.setDataAndType(Uri.fromFile(path), "application/vnd.android.package-archive"); 
	           context.startActivity(intent);
	    }catch(Exception ex){
	        ex.printStackTrace();
	        result = false;
	    }
	    return result;
	}
//	/**
//	 * description:this method is used to create a xml file
//	 * @author yangxuehui
//	 * @param restaurantName :as the name defined,it's a restaurant name
//	 * @param level :error level(1.致命错误 2.警告 3.普通)
//	 * @param errorCause :the cause of this error
//	 * @return :if the xml file is created successfully return true,else return false
//	 */
//	public static File createXmlFile(int level,String errorCause){
//		try{
//			 
//			DateFormat df = new SimpleDateFormat(DateUtil.ISO_DATETIME_FORMAT_SORT2);
//			
//			long currentTime = System.currentTimeMillis();  
//			 
//			String crashTime = df.format(new Date(currentTime));
//			
//			File xmlFile = new File(RestaurantConstants.LOG_PATH + crashTime+".xml");
//			File logDir = new File(RestaurantConstants.LOG_PATH);
//			
//			if(!logDir.exists())
//				logDir.mkdirs();
//			if(!xmlFile.exists())
//				xmlFile.createNewFile();
//			
//			FileWriter writer = new FileWriter(xmlFile);
//			
//			XmlSerializer serializer = Xml.newSerializer();
//			
//			serializer.setOutput(writer);
//			
//			serializer.startDocument("UTF-8", true);
//			
//			serializer.startTag("", "exception");
//			
//				serializer.startTag("","restaurant-name");
//				serializer.text("junshunge");
//				serializer.endTag("", "restaurant-name");
//				
//				serializer.startTag("","error-level");
//				serializer.text(level+"");
//				serializer.endTag("", "error-level");
//				
//				serializer.startTag("","error-time");
//				serializer.text(crashTime);
//				serializer.endTag("", "error-time");
//				
//				serializer.startTag("","error-cause");
//				serializer.text(errorCause);
//				serializer.endTag("", "error-cause");
//				
//			serializer.endTag("", "exception");
//			
//			serializer.endDocument();
//			
//			writer.close();
//			
//			return xmlFile;
//			
//		}catch(Exception e){
//			e.printStackTrace();
//			return null;
//		}
//	}
	
	
}
