package com.example.util;

public class ExceptionInfo {
	public String restaurant_name ;
	public int error_level ; 
	public String error_time ;
	public String error_cause ;
	
	public static final String 	RESTAURANT_NAME = "restaurant_name";
	public static final String 	ERROR_LEVEL = "error_level";
	public static final String 	ERROR_TIME = "error_time";
	public static final String 	ERROR_CAUSE = "error_cause";
	
	@Override
	public String toString() {
		return "ExceptionInfo [restaurant_name=" + restaurant_name
				+ ", error_level=" + error_level + ", error_time=" + error_time
				+ ", error_cause=" + error_cause + "]";
	}
}
