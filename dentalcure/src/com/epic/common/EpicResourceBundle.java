package com.epic.common;

import java.util.ResourceBundle;

public class EpicResourceBundle {

	static ResourceBundle bundle;
	static {
		bundle =  ResourceBundle.getBundle("resource.EpicResource");
	}
	
	static ResourceBundle getEpicResourceBundle(){

		return bundle;
	}

	public static String getString(String key){
		return bundle.getString(key);
	}
	
	public static void main(String ...args){
		System.out.println(getString("PatientLabel"));
	}

}
