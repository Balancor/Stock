package com.haiming.http;

public class DefaultHttpRequest extends HttpRequest{

	private static final String AGENT = 
			"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36";
	public DefaultHttpRequest(String method, String host, String url) {
		super(method, host, url);
		initHeaders();
	}
	
	private void initHeaders(){
		setHeader(REQUEST_HEADER_ACCEPT, "*/*");
		setHeader(REQUEST_HEADER_HOST, "www.guohaiming.com");
//		setHeader(REQUEST_HEADER_ACCEPT_CHARSET, "utf-8");
		setHeader(REQUEST_HEADER_USER_AGENT, AGENT);
		setHeader(REQUEST_HEADER_CACHE_CONTROL, "max-age=0");
		setHeader(REQUEST_HEADER_ACCEPT_ENCODING, "gzip, deflate, sdch");
		setHeader(REQUEST_HEADER_ACCEPT_LANGUAGE, "en-US,en;q=0.8,ar;q=0.6,fr;q=0.4,ja;q=0.2,pt;q=0.2,zh-CN;q=0.2,zh-TW;q=0.2");
		setHeader(REQUEST_HEADER_CONNECTION, "keep-alive");
	}
	
}
