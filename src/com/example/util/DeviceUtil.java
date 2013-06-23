package com.example.util;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * 
 * 包含设备处理方法的工具类
 * 
 * @author <a href="mailto:jiaoshi0531@163.com">焦石</a>
 * @since 1.6
 * @version 1.0.0 2011-3-30
 */
public class DeviceUtil {
	

	public static final int SDK_VERSION_1_5 = 3;

	public static final int SDK_VERSION_1_6 = 4;
	
	public static final int SDK_VERSION_2_0 = 5;
	
	public static final int SDK_VERSION_2_0_1 = 6;
	
	public static final int SDK_VERSION_2_1 = 7;
	
	public static final int SDK_VERSION_2_2 = 8;
	
	/**
	 * 获得设备型号
	 * @return
	 */
	public static String getDeviceModel() {
        return Build.MODEL;
    }
	
	/**
	 * 获得国际移动设备身份码
	 * @param context
	 * @return
	 */
	public static String getIMEI(Context context) {
        return ((TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
    }
	
	/**
	 * 获得国际移动用户识别码
	 * @param context
	 * @return
	 */
	public static String getIMSI(Context context) {
        return ((TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE)).getSubscriberId();
    }
	
	/**
	 * 获得设备屏幕矩形区域范围
	 * @param context
	 * @return
	 */
	public static Rect getScreenRect(Context context) {
        Display display = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int w = display.getWidth();
        int h = display.getHeight();
        return new Rect(0, 0, w, h);
    }
	
	/**
	 * 获得设备屏幕密度
	 */
	public static float getScreenDensity(Context context) {
		DisplayMetrics metrics = context.getApplicationContext().getResources().getDisplayMetrics();
		return metrics.density;
	}
	
	/** 
	 * 获得系统版本
	 */
	public static String getSDKVersion(){
		
		return android.os.Build.VERSION.SDK;
	}
	
	public static int getSDKVersionInt(){
		return NumberUtil.toInt(android.os.Build.VERSION.SDK);
	}
}
