package com.haiming.http;

import java.util.HashMap;

import com.haiming.utils.Log;

public class HttpRequest {

    public static final String REQUEST_HEADER_ACCEPT            = "Accept";
    public static final String REQUEST_HEADER_ACCEPT_CHARSET    = "Accept-Charset";
    public static final String REQUEST_HEADER_ACCEPT_ENCODING   = "Accept-Encoding";
    public static final String REQUEST_HEADER_ACCEPT_LANGUAGE   = "Accept-Language";
    public static final String REQUEST_HEADER_AUTHORIZATION     = "Authorization";
    public static final String REQUEST_HEADER_CONNECTION        = "Connection";
    public static final String REQUEST_HEADER_CONTENT_LENGTH    = "Content-Length";
    public static final String REQUEST_HEADER_COOKIE            = "Cookie";
    public static final String REQUEST_HEADER_FROM              = "From";
    public static final String REQUEST_HEADER_HOST              = "Host";
    public static final String REQUEST_HEADER_IF_MODIFIED_SINCE = "If-Modified-Since";
    public static final String REQUEST_HEADER_PRAGMA            = "Pragma";
    public static final String REQUEST_HEADER_REFERER           = "Referer";
    public static final String REQUEST_HEADER_USER_AGENT        = "User-Agent";
    public static final String REQUEST_HEADER_UA_PIXELS         = "UA-Pixels";
    public static final String REQUEST_HEADER_UA_COLOR          = "UA-Color";
    public static final String REQUEST_HEADER_UA_OS             = "UA-OS";
    public static final String REQUEST_HEADER_UA_CPU            = "UA-CPU";
    public static final String REQUEST_HEADER_CACHE_CONTROL     = "Cache-Control";

    public static final String REQUEST_GET     = "GET";
    public static final String REQUEST_POST    = "POST";
    public static final String REQUEST_HEAD    = "HEAD";
    public static final String REQUEST_PUT     = "PUT";
    public static final String REQUEST_DELETE  = "DELETE";
    public static final String REQUEST_OPTIONS = "OPTIONS";
    public static final String REQUEST_TRACE   = "TRACE";
    public static final String REQUEST_CONNECT = "CONNECT";

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
       REQUEST_HEADER_UA_CPU            ,// "UA-CPU";
       REQUEST_HEADER_CACHE_CONTROL
    };

	private String mURL = "";
	private String mHost = "";
	private String mMethod = "";
	private HashMap<String, String> mHeaders = new HashMap<>();
	
	private OnRequestChangedListener mRequestChangedCallback = null;

	public HttpRequest(String method,String host, String url) {
		this.mMethod = method.toUpperCase().trim();
		this.mURL = url.trim();
		this.mHost = host.trim();
	}
	
	public HttpRequest (String method, String host) {
		this(method, host, "/");
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
			Log.d(header + " is not avalidable");
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
		sb.append(this.mMethod+" "+this.mURL+" HTTP/1.1\r\n");
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
