package com.haiming.ajax;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Ajax {

	public void doGet(String url, String httpArgs){
		service(url, httpArgs, "GET");
	}
	public void doPost(String url, String httpArgs){
		service(url, httpArgs, "POST");

	}
	
	private String service(String httpUrl, String httpArg, String method) {
	    BufferedReader reader = null;
	    String result = null;
	    StringBuffer sbf = new StringBuffer();
	    httpUrl = httpUrl + "?" + httpArg;
	    
	    try {
	        URL url = new URL(httpUrl);
	        HttpURLConnection connection = (HttpURLConnection) url
	                .openConnection();
	        
	        connection.setRequestMethod(method.toUpperCase());

	        
	        connection.connect();
	        InputStream is = connection.getInputStream();
	        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        String strRead = null;
	        while ((strRead = reader.readLine()) != null) {
	            sbf.append(strRead);
	            sbf.append("\r\n");
	        }
	        reader.close();
	        result = sbf.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    //saveToFile("0600000.csv", sbf);
	    return result;
	}
}
