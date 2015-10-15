package com.haiming.http;

import java.util.HashMap;

import com.haiming.utils.Log;

public class HttpRequest {

    private static final String REQUEST_HEADER_ACCEPT            = "Accept";
    private static final String REQUEST_HEADER_ACCEPT_CHARSET    = "Accept-Charset";
    private static final String REQUEST_HEADER_ACCEPT_ENCODING   = "Accept-Encoding";
    private static final String REQUEST_HEADER_ACCEPT_LANGUAGE   = "Accept-Language";
    private static final String REQUEST_HEADER_AUTHORIZATION     = "Authorization";
    private static final String REQUEST_HEADER_CONNECTION        = "Connection";
    private static final String REQUEST_HEADER_CONTENT_LENGTH    = "Content-Length";
    private static final String REQUEST_HEADER_COOKIE            = "Cookie";
    private static final String REQUEST_HEADER_FROM              = "From";
    private static final String REQUEST_HEADER_HOST              = "Host";
    private static final String REQUEST_HEADER_IF_MODIFIED_SINCE = "If-Modified-Since";
    private static final String REQUEST_HEADER_PRAGMA            = "Pragma";
    private static final String REQUEST_HEADER_REFERER           = "Referer";
    private static final String REQUEST_HEADER_USER_AGENT        = "User-Agent";
    private static final String REQUEST_HEADER_UA_PIXELS         = "UA-Pixels";
    private static final String REQUEST_HEADER_UA_COLOR          = "UA-Color";
    private static final String REQUEST_HEADER_UA_OS             = "UA-OS";
    private static final String REQUEST_HEADER_UA_CPU            = "UA-CPU";

    private static final String REQUEST_GET     = "GET";
    private static final String REQUEST_POST    = "POST";
    private static final String REQUEST_HEAD    = "HEAD";
    private static final String REQUEST_PUT     = "PUT";
    private static final String REQUEST_DELETE  = "DELETE";
    private static final String REQUEST_OPTIONS = "OPTIONS";
    private static final String REQUEST_TRACE   = "TRACE";
    private static final String REQUEST_CONNECT = "CONNECT";

    private static final String[] AvailableHeaders = {
       REQUEST_HEADER_ACCEPT            ,// "Accept";
       REQUEST_HEADER_ACCEPT_CHARSET    ,// "Accept-Charset";
       REQUEST_HEADER_ACCEPT_ENCODING   ,// "Accept-Encoding";
       REQUEST_HEADER_ACCEPT_LANGUAGE   ,// "Accept-Language";
       REQUEST_HEADER_AUTHORIZATION     ,// "Authorization";
       REQUEST_HEADER_CONNECTION        ,// "Connection";
       REQUEST_HEADER_CONTENT_LENGTH    ,// "Content-Length";
       REQUEST_HEADER_COOKIE            ,// "Cookie";
       REQUEST_HEADER_FROM              ,// "From";
       REQUEST_HEADER_HOST              ,// "Host";
       REQUEST_HEADER_IF_MODIFIED_SINCE ,// "If-Modified-Since";
       REQUEST_HEADER_PRAGMA            ,// "Pragma";
       REQUEST_HEADER_REFERER           ,// "Referer";
       REQUEST_HEADER_USER_AGENT        ,// "User-Agent";
       REQUEST_HEADER_UA_PIXELS         ,// "UA-Pixels";
       REQUEST_HEADER_UA_COLOR          ,// "UA-Color";
       REQUEST_HEADER_UA_OS             ,// "UA-OS";
       REQUEST_HEADER_UA_CPU            // "UA-CPU";
    };

	private String mURL = "";
	private String mHost = "";
	private String mMethod = "";
	private HashMap<String, String> mHeaders = new HashMap<>();
	
	private OnRequestChangedListener mRequestChangedCallback = null;

	public HttpRequest(String method, String url) {
		this.mMethod = method.toUpperCase().trim();
		this.mURL = url.trim();
		this.mHost = mURL.substring(mURL.indexOf("//") +2 , mURL.indexOf("/"));
	}
	
	public void setHeader(String header, String content){
		boolean isAvailableHeader = false;
		for (int i = 0; i < AvailableHeaders.length; i++) {
			if(header.equals(AvailableHeaders[i])){
				isAvailableHeader = true;
				break;
			}
		}
		if(!isAvailableHeader){
			Log.d(header + "is not avalidable");
			return;
		}
		
		mHeaders.put(header, content);
	}
	
	public void setHeaderOverride(String header, String content){
		setHeader(header, content);
	}
	
	public void setHeaderIgnore(String header, String content){
		if(mHeaders.containsKey(header)){
			Log.d(header + " existed. IGNORE");
			return;
		}
		setHeader(header, content);
	}
	public String getURL() {
		return mURL;
	}

	public void setURL(String url) {
		this.mURL = url.trim();
	}

	public void setMethod(String method) {
		this.mMethod = method.toUpperCase().trim();
	}

	public HashMap<String, String> getHeaders() {
		return mHeaders;
	}
	
	public String getHost(){
		return mHost;
	}
	
	public String getRequest(){
		StringBuffer sb = new StringBuffer();
		sb.append(this.mMethod+" /"+this.mURL+" HTTP/1.1\r\n");
		for(String key : mHeaders.keySet()){
			sb.append(key+":"+mHeaders.get(key)+"\r\n");
		}
		sb.append("\r\n");
		return sb.toString();
	}
	
	public byte[] toBytes(){
		return getRequest().getBytes();
	}
	
	public void setonRequestChangedListner(OnRequestChangedListener callback){
		mRequestChangedCallback = callback;
	}
	public void updateRequest(){
		if(mRequestChangedCallback != null){
			mRequestChangedCallback.onRequestChangedListner();
		}
	};
}
