package com.haiming.http;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import com.haiming.utils.Log;

public class HttpResponse {
	public static final String RESPONSE_HEADER_ALLOW            = "Allow";
    public static final String RESPONSE_HEADER_DATE             = "Date";
    public static final String RESPONSE_HEADER_EXPIRES          = "Expires";
    public static final String RESPONSE_HEADER_P3P              = "P3P";
    public static final String RESPONSE_HEADER_SET_COOKIE       = "Set-Cookie";
    public static final String RESPONSE_HEADER_ETAG             = "ETag";
    public static final String RESPONSE_HEADER_LAST_MODIFIED    = "Last-Modified";
    public static final String RESPONSE_HEADER_CONTENT_TYPE     = "Content-Type";
    public static final String RESPONSE_HEADER_CONTENT_RANGE    = "Content-Range";
    public static final String RESPONSE_HEADER_CONTENT_LENGTH   = "Content-Length";
    public static final String RESPONSE_HEADER_CONTENT_ENCODING = "Content-Encoding";
    public static final String RESPONSE_HEADER_CONTENT_LANGUAGE = "Content-Language";
    public static final String RESPONSE_HEADER_SERVER           = "Server";
    public static final String RESPONSE_HEADER_X_ASPNET_VERSION = "X-AspNet-Version";
    public static final String RESPONSE_HEADER_X_POWERED_BY     = "X-Powered-By";
    public static final String RESPONSE_HEADER_CONNECTION       = "Connection";
    public static final String RESPONSE_HEADER_LOCATION         = "Location";
    public static final String RESPONSE_HEADER_REFRESH          = "Refresh";
    public static final String RESPONSE_HEADER_WWW_AUTHENTICATE = "WWW-Authenticate";

    private static final String[] mAvaliableResponseHeaders = {
            RESPONSE_HEADER_ALLOW            ,// "Allow";
            RESPONSE_HEADER_DATE             ,// "Date";
            RESPONSE_HEADER_EXPIRES          ,// "Expires";
            RESPONSE_HEADER_P3P              ,// "P3P";
            RESPONSE_HEADER_SET_COOKIE       ,// "Set-Cookie";
            RESPONSE_HEADER_ETAG             ,// "ETag";
            RESPONSE_HEADER_LAST_MODIFIED    ,// "Last-Modified";
            RESPONSE_HEADER_CONTENT_TYPE     ,// "Content-Type";
            RESPONSE_HEADER_CONTENT_RANGE    ,// "Content-Range";
            RESPONSE_HEADER_CONTENT_LENGTH   ,// "Content-Length";
            RESPONSE_HEADER_CONTENT_ENCODING ,// "Content-Encoding";
            RESPONSE_HEADER_CONTENT_LANGUAGE ,// "Content-Language";
            RESPONSE_HEADER_SERVER           ,// "Server";
            RESPONSE_HEADER_X_ASPNET_VERSION ,// "X-AspNet-Version";
            RESPONSE_HEADER_X_POWERED_BY     ,// "X-Powered-By";
            RESPONSE_HEADER_CONNECTION       ,// "Connection";
            RESPONSE_HEADER_LOCATION         ,// "Location";
            RESPONSE_HEADER_REFRESH          ,// "Refresh";
            RESPONSE_HEADER_WWW_AUTHENTICATE ,// "WWW-Authenticate";

    };


	private String mHttpVersion = "";
	private int mStatueCode = 0;
	private String mStatueMsg = "";
	
	private StringBuffer mResponseString = new StringBuffer();
	//the content of header. String
	private String mResponseHeader = "";
	//Parse mResponseHeader to get mResponseHeaders. Hashmap
	private HashMap<String, String> mResponseHeaders = new HashMap<>();
	private byte[] mResponseBody;
	
	public HttpResponse(byte[] buffer) {

		mResponseString.append(new String(buffer));

		mResponseHeader = mResponseString.substring(0, mResponseString.indexOf("\r\n\r\n")+4);
		String firstLine = mResponseHeader.substring(0, mResponseString.indexOf("\r\n"));
		String[] parts = firstLine.split(" ");
		mHttpVersion = parts[0];
		mStatueCode = Integer.parseInt(parts[1]);
		mStatueMsg = parts[2];
		initResponseHeaders();

//		initResponseBody();
	}
	public String getMessageBodyCharset(){
		String tempResponse = getHeader(RESPONSE_HEADER_CONTENT_TYPE).trim();
		String[] parts = null;
		String contentyTypeCharset = "";
		if(tempResponse.contains(";")){
			parts = tempResponse.split(";");
		}
		if (parts != null) {
			for (int i = 0; i < parts.length; i++) {
				if (parts[i].indexOf("charset") != -1) {
					contentyTypeCharset = parts[i];
				}
			}
		}
		String type = "utf-8";
		if(contentyTypeCharset.length() > 0){
			type = contentyTypeCharset.split("=")[1];
		}
		return type;
	}
	
	private void initResponseHeaders(){
		if(mResponseHeader.length() == 0)return;
		String[] parts = new String[2];

		for(String header : mAvaliableResponseHeaders){
			if(!mResponseHeader.contains(header))continue;
			int headerLocation = mResponseHeader.indexOf(header);
			String line = mResponseHeader.substring(headerLocation, mResponseHeader.indexOf("\r\n", headerLocation));
			parts = line.split(":");
			if(parts[1].length() != 0){
				mResponseHeaders.put(header, parts[1]);
			}
			parts = null;
		}
	}
	private void initResponseBody(){
		int bodyLength = Integer.parseInt(mResponseHeaders.get(RESPONSE_HEADER_CONTENT_LENGTH).trim());
		if(bodyLength <= 0)return;
		mResponseBody = new byte[bodyLength];
		String bodyContent = "";

		bodyContent = mResponseString.substring(mResponseString.indexOf("\r\n\r\n"), mResponseString.length());
					


		mResponseBody = bodyContent.getBytes();
		try {
			Log.d("mResponseBody: "+ new String(mResponseBody, getMessageBodyCharset()));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public byte[] getResponseBody(){
		if(mResponseBody == null){
			initResponseBody();
		}
		return mResponseBody;
	}
	
	
	public String getHeader(String header){
		String result = null;
		boolean isAvaliableHeader = false;
		for(String temp : mAvaliableResponseHeaders){
			if(temp.equals(header)){
				isAvaliableHeader = true;
				break;
			}
		}
		if(isAvaliableHeader){
			result = mResponseHeaders.get(header);
		}
		return result.trim();
	}
	
	public String dumpHeaders(){
		StringBuffer sb = new StringBuffer();
		for(String header: mResponseHeaders.keySet()){
			sb.append(header+" : "+mResponseHeaders.get(header)+"\n");
		}
		return sb.toString();
	}

}
