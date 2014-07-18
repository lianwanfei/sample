package com.dingmore.sample;

import android.app.Application;

public class DingmoreApplication extends Application {

	public static String service = "114.215.184.120";
	public static String port = "8080";
	public static String domain = "http://" + service + ":" + port + "/";

	public static String getService() {
		return service;
	}

	public static void setService(String service) {
		DingmoreApplication.service = service;
		domain = "http://" + service + ":" + port + "/";
	}

	public static String getPort() {
		return port;
	}

	public static void setPort(String port) {
		DingmoreApplication.port = port;
		domain = "http://" + service + ":" + port + "/";
	}



	public static String getDomain() {
		return domain;
	}

	public static void setDomain(String domain) {
		DingmoreApplication.domain = domain;
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}
}