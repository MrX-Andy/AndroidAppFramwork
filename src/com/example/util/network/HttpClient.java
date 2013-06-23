package com.example.util.network;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

/**
 * 
 * 包含HTTP访问处理方法的工具类�?
 * 
 * @author <a href="mailto:jiaoshi0531@163.com">焦石</a>
 * @since 1.6
 * @version 1.0.0 2010-11-06
 */
public class HttpClient {
	private static final String ENCODEING = "UTF-8";
    private static final int TIME_OUT = 5000;
    
    private static final int SO_TIME_OUT = 8000;
    
	public static final DefaultHttpClient httpClient;
	
	public static JSONObject getJSON(String url) throws Exception {
		return new JSONObject(getString(url));
	}
	
	public static String getString(String url) throws Exception {
		return EntityUtils.toString(get(url).getEntity());
	}
	
	public static String postString(String url, List list) throws Exception {


		return EntityUtils.toString(post(url, list).getEntity());

	}
	
	public static JSONObject postJSON(String url, List list) throws Exception {
		return new JSONObject(postString(url, list));
	}
	
	public static JSONArray postJSONArray(String url, List list) throws Exception {
		return new JSONArray(postString(url, list));
	}
	
	public static HttpResponse get(String url) throws Exception {
		HttpGet httpget = new HttpGet(url);
		return httpClient.execute(httpget);
	}
	
	public static JSONArray getJSONArray(String url) throws Exception{
	    return new JSONArray(getString(url));
	}
	
	public static HttpResponse post(String url, List list) throws Exception {
		HttpPost httppost = new HttpPost(url);
		UrlEncodedFormEntity urlencodedformentity = new UrlEncodedFormEntity(list, ENCODEING);
		httppost.setEntity(urlencodedformentity);   
		
		
		
		return httpClient.execute(httppost);
	}
	
	public static void setProxy(String proxyHost, int proxyPort){
		HttpHost proxy = new HttpHost(proxyHost, proxyPort); 
		httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
	}
	
	public static void removeProxy(){
		httpClient.getParams().removeParameter(ConnRoutePNames.DEFAULT_PROXY);
	}
	
	public static JSONArray GetArrayWithRetry(String url){

        int retry = 3;

        int count = 0;

        while(count < retry){

            count += 1;

            try{

                JSONArray response = getJSONArray(url);

                //如果成功，则停止循环

                return response;

            }catch(Exception e){
                e.printStackTrace();
                if(count < retry){

                    //继续重试
                }else{
                	
                    //重试3次后请求失败
                    return null;
                    }
            }

        }
        return null;
    }
	
	public static JSONObject GetJSONWithRetry(String url){

        int retry = 3;

        int count = 0;

        while(count < retry){

            count += 1;

            try{

            	JSONObject response = getJSON(url);

                //如果成功，则停止循环

                return response;

            }catch(Exception e){
                e.printStackTrace();
                
                if(count < retry){

                    //继续重试
                }else{
                	
                    //重试3次后请求失败
                    return null;
                    }
            }

        }
        return null;
    }
	
	public static String GetStringWithRetry(String url){

        int retry = 3;

        int count = 0;

        while(count < retry){

            count += 1;

            try{

            	String response = getString(url);

                //如果成功，则停止循环

                return response;

            }catch(Exception e){
                e.printStackTrace();
                if(count < retry){

                    //继续重试
                }else{
                	
                    //重试3次后请求失败
                    return null;
                    }
            }

        }
        return null;
    }
	
	static{
		HttpParams params = new BasicHttpParams();
		params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		params.setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, ENCODEING);
		params.setParameter(CoreProtocolPNames.USER_AGENT, "Apache-HttpClient/Android");
		params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, TIME_OUT);
		params.setParameter(CoreConnectionPNames.SO_TIMEOUT, SO_TIME_OUT);
		params.setParameter(CoreConnectionPNames.STALE_CONNECTION_CHECK, false);
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));//80
		schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
		ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager(params, schemeRegistry);
		httpClient = new DefaultHttpClient(cm, params);
		
//		BasicHttpParams basichttpparams = new BasicHttpParams();
//      HttpProtocolParams.setVersion(basichttpparams, HttpVersion.HTTP_1_1);
//      HttpProtocolParams.setContentCharset(basichttpparams, "UTF-8");
//      //HttpConnectionParams.setStaleCheckingEnabled(basichttpparams, null);
//      HttpConnectionParams.setConnectionTimeout(basichttpparams, 10000);
//      HttpConnectionParams.setSoTimeout(basichttpparams, 10000);//Set the Socket's timeout
//      HttpConnectionParams.setSocketBufferSize(basichttpparams, 8192);
//      //HttpProtocolParams.setUserAgent(basichttpparams, "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.0.15) Gecko/2009101601 Firefox/3.0.15 (.NET CLR 3.5.30729)");
//      SchemeRegistry schemeRegistry = new SchemeRegistry();
//		schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
//		schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
//		ThreadSafeClientConnManager threadsafeclientconnmanager = new ThreadSafeClientConnManager(basichttpparams, schemeRegistry);
//		httpClient = new DefaultHttpClient(threadsafeclientconnmanager, basichttpparams);
		
//		HttpParams params = new BasicHttpParams();
//		HttpProtocolParams.setContentCharset(params, "UTF-8");
//		ConnManagerParams.setMaxTotalConnections(params, 10);
//		ConnManagerParams.setTimeout(params, TIME_OUT);
//		SchemeRegistry schemeRegistry = new SchemeRegistry();
//		schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
//		schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
//		ClientConnectionManager cm = new ThreadSafeClientConnManager(params, schemeRegistry);
//		httpClient = new DefaultHttpClient(cm, params);
	}
}
