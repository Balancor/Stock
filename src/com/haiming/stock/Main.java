package com.haiming.stock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

public class Main {
	private static final String baseURL = "http://apis.baidu.com/apistore/stockservice/stock";
	private static String mBaiduHttpArgs = "stockid=sz002230&list=1";
	private static final String myApiKey = "0ff936b883cbb14edbe4434af6328abf";
	
	private static final String m163Url = "http://quotes.money.163.com/service/chddata.html";
	private static final String mFieds = "fields=TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;TURNOVER;VOTURNOVER;VATURNOVER;TCAP;MCAP";
	private static String m163HttpArgs = "code=0600000&start=19991110&end=20151013";
	private static StockFrame mStockFrame;
	
	private static JSONObject mJsonObject;
	public static void main(String[] args) {

		String httpArg = "stockid=sz002230&list=1";
		//String jsonResult = request(baseURL, mBaiduHttpArgs);
		//mStockFrame = new StockFrame(jsonResult);
		//String name = mStockFrame.getStockName();
		//System.out.println("Name: " + jsonResult);
		
		UpdateSHStock updateSHStock = new UpdateSHStock();
		updateSHStock.update();

	}
	
	public static String convert(String utfString){
		StringBuilder sb = new StringBuilder();
		int i = -1;
		int pos = 0;
		
		while((i=utfString.indexOf("\\u", pos)) != -1){
			sb.append(utfString.substring(pos, i));
			if(i+5 < utfString.length()){
				pos = i+6;
				sb.append((char)Integer.parseInt(utfString.substring(i+2, i+6), 16));
			}
		}
		return sb.toString();
	}
	
	public static String request(String httpUrl, String httpArg) {
	    BufferedReader reader = null;
	    String result = null;
	    StringBuffer sbf = new StringBuffer();
	    httpUrl = httpUrl + "?" + httpArg;
	    
	    try {
	        URL url = new URL(httpUrl);
	        HttpURLConnection connection = (HttpURLConnection) url
	                .openConnection();
	        connection.setRequestMethod("GET");
	        
	        connection.setRequestProperty("apikey",  myApiKey);
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
	
	private static void saveToFile(String fileName, StringBuffer sbf){
		File file = new File(fileName);
		FileOutputStream fileOutputStream = null;
		if(file.exists()){
			file.delete();
		}
		try {
			file.createNewFile();
			fileOutputStream = new FileOutputStream(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(fileOutputStream != null){
			try {
				fileOutputStream.write(sbf.toString().getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	
		
	}
}
