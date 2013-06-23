package com.example.util.network;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * 
 * 包含网络处理方法的工具类
 * 
 * @author <a href="mailto:jiaoshi0531@163.com">焦石</a>
 * @since 1.6
 * @version 1.0.0 2011-3-30
 */
public class NetworkUtil {

	private static final String TAG = NetworkInfo.class.getSimpleName();

	/**
	 * Returns whether the network is available
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			Log.w(TAG, "couldn't get connectivity manager");
		} else {
			NetworkInfo[] infos = connectivity.getAllNetworkInfo();
			if (infos != null) {
				for (int i = 0, count = infos.length; i < count; i++) {
					if (infos[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Returns whether the network is roaming
	 */
	public static boolean isNetworkRoaming(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			Log.w(TAG, "couldn't get connectivity manager");
		} else {
			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (info != null
					&& info.getType() == ConnectivityManager.TYPE_MOBILE) {
				TelephonyManager telManager = (TelephonyManager) context
						.getSystemService(Context.TELEPHONY_SERVICE);
				if (telManager != null && telManager.isNetworkRoaming()) {
					Log.v(TAG, "network is roaming");
					return true;
				} else {
					Log.v(TAG, "network is not roaming");
				}
			} else {
				Log.v(TAG, "not using mobile network");
			}
		}
		return false;
	}

	public static String getLocalIpAddress() { 
		  try { 
		    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) { 
		      NetworkInterface intf = en.nextElement(); 
		      for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) { 
		        InetAddress inetAddress = enumIpAddr.nextElement(); 
		        if (!inetAddress.isLoopbackAddress()) { 
		        return inetAddress.getHostAddress().toString(); 
		      } 
		    } 
		  } 
		  } catch (SocketException ex) { 
			  ex.printStackTrace();
		  } 
		  return ""; 
		} 	
	
	public static void openWifi(Context context){
		WifiManager wifiManager = (WifiManager) context.getSystemService(Service.WIFI_SERVICE);
		try{
			if (!wifiManager.isWifiEnabled()){//wifi 关闭
				wifiManager.setWifiEnabled(true);//打开wifi
				
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public static void connectWifi(Context context,String ssid,String password){
		WifiConfiguration wfc = new WifiConfiguration();
		wfc.SSID = "\"".concat(ssid).concat("\"");
		wfc.status = WifiConfiguration.Status.DISABLED;
		wfc.priority = 40;
		
		wfc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
		wfc.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
		wfc.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
		wfc.allowedAuthAlgorithms.clear();
		wfc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
		wfc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
		wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
		wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
		wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
		wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
		
		
		wfc.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
		wfc.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
		wfc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
		wfc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
		wfc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
		wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
		wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
		wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
		wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
		wfc.preSharedKey = "\"".concat(password).concat("\"");

		WifiManager wfMgr = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		int networkId = wfMgr.addNetwork(wfc);
		if (networkId != -1) {
			wfMgr.enableNetwork(networkId, true);
			wfMgr.saveConfiguration();
		}
	}
}
