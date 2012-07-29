package com.hrm.config;

public final class SystemUtils {
	
	public static final String DEFAULT_ENV = "dev";
	
	public static String getEnvironment(){
		String env = System.getProperty("environment");
		if(isNotEmpty(env)){
			return env;
		}else{
			return DEFAULT_ENV;
		}
	}
	
	private static boolean isNotEmpty(String value){
		if(value != null && !value.isEmpty()){
			return true;
		}
		return false;
	}
}
