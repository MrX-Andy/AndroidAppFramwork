package com.example.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.example.util.network.HttpClient;
/**
 * 
 * @author yangxuehui
 * description:this class is used to handle uncaught exception . record the exception in file and
 * post them to server
 *
 */
public class CrashHandler implements UncaughtExceptionHandler {
	
	public static final String TAG = "CrashHandler";
	public static final boolean DEBUG = true;
	
	
	private static CrashHandler INSTANCE;
	
    public static final String CRASH_REPORT_EXTETION = ".xml"; 
    
    
    public CrashHandler(){
    	
    }
    
    public static CrashHandler getInstance(){
    	if(INSTANCE == null){
    		INSTANCE = new CrashHandler();
    	}
    	return INSTANCE;
    }
    
    public void init(){
    	Thread.setDefaultUncaughtExceptionHandler(this);
    }
    
    @Override
	public void uncaughtException(Thread thread, Throwable ex) {    	
    	handleException(ex);
    	android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(10);
	}
    
    /** 
     * description:handle the exception and collect the exception information and post them to server
     * @param ex 
     * @return true:如果处理了该异常信息;否则返回false 
     */  
    public boolean handleException(Throwable ex) {  
    	 
    	 Writer info = new StringWriter();  //physical node stream
         PrintWriter printWriter = new PrintWriter(info);  //high level handle stream
         ex.printStackTrace(printWriter);  
   
         Throwable cause = ex.getCause();  //get exception cause
         while (cause != null) {  
             cause.printStackTrace(printWriter);  
             cause = cause.getCause();  
         }  
   
         String result = info.toString();  //exception cause
         printWriter.close();  
         
         //create xml file for the exception
        // FileUtil.createXmlFile(1, result);

        return true;  
    }  
  

//    /** 
//     * description:find the exception reporter files and send them to server
//     * @param ctx 
//     */  
//    public boolean sendCrashReportsToServer() {    
//    	try{
//    		String[] crFiles = getCrashReportFiles();     
//            
//            if (crFiles != null && crFiles.length > 0) {  
//
//                for (int i = 0; i < crFiles.length; i++) {  
//              //      File cr = new File(RestaurantConstants.LOG_PATH, crFiles[i]);  
////                    
////                    postReport(cr);  
////                    
////                    cr.delete();// delete the exception reporter file that has been send
//                }  
//            }
//    	}catch(Exception e){
//    		e.printStackTrace();
//    		return false;
//    	}
//         return true; 
//    }  
  
   
//    /**
//     * description:parser the crash xml file first and post a JSONObject to server
//     * @param level :error level
//     * @param errorCause :error cause
//     * @return
//     */
//    public static int postReport(int level,String errorCause){
//    	return postReport(FileUtil.createXmlFile(level, errorCause));
//    }
    
//    
//    /**
//     * description:parser the crash xml file first and post a JSONObject to server
//     * @author yangxuehui
//     * @param file
//     * @return
//     */
//    public  static int postReport(File file) {  
//    	int returnCode = -1;
//    	try{
//    		//new a ExceptionInfo object
//    		ExceptionInfo exception = new ExceptionInfo();
//    
//    		FileInputStream fis = new FileInputStream(file);		
//    		XMLContentHandler xmlReader = new XMLContentHandler();
//    		//parse the crash xml file
//    		exception = xmlReader.readXml(fis);
//    		
//    		JSONObject json = new JSONObject();
//    		json.put(ExceptionInfo.RESTAURANT_NAME,exception.restaurant_name);
//    		json.put(ExceptionInfo.ERROR_LEVEL,exception.error_level+"");
//    		json.put(ExceptionInfo.ERROR_TIME,exception.error_time);
//    		json.put(ExceptionInfo.ERROR_CAUSE,exception.error_cause);
//    		
//    		List<NameValuePair> requestParameter = new ArrayList<NameValuePair>();
//    		
//    		requestParameter.add(new BasicNameValuePair("data",json.toString()));
//    		
//    		
//    		HttpResponse response = HttpClient.post(DataConstants.ADDERROR,requestParameter);
//    		
//    		EntityUtils.toString(response.getEntity());
//    		
//    		
//    	}catch(Exception e){
//    		e.printStackTrace();
//    	}
//    	return returnCode;
//
//    }  
  
    /** 
     * description:get the exception reporter files's name 
     * @param ctx 
     * @return 
     */  
//    public String[] getCrashReportFiles() {  
//        File filesDir = new File(RestaurantConstants.LOG_PATH);  
//        
//        FilenameFilter filter = new FilenameFilter() {  
//            public boolean accept(File dir, String name) {  
//                return name.endsWith(CRASH_REPORT_EXTETION);  
//            }  
//        };  
//        return filesDir.list(filter);  
//    }  

}
