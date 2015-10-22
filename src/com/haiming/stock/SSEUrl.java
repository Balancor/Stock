package com.haiming.stock;

import com.haiming.http.DefaultHttpRequest;
import com.haiming.http.HttpRequest;
import com.haiming.http.HttpResponse;

public class SSEUrl {
	/*
	 * var params1 = {^M
 603         'isPagination' : false,^M
 604         'sqlId' : 'COMMON_SSE_ZQPZ_GP_GPLB_C',^M
 605         'productid' : COMPANY_CODE^M
 606     };^M
 607     var params2 = {^M
 608         'isPagination' : false,^M
 609         'sqlId' : 'COMMON_SSE_ZQPZ_GP_GPLB_AGSSR_C',^M
 610         'productid' : COMPANY_CODE^M
 611     };^M
 612     var params3 = {^M
 613         'isPagination' : false,^M
 614         'sqlId' : 'COMMON_SSE_ZQPZ_GP_GPLB_BGSSR_C',^M
 615         'productid' : COMPANY_CODE^M
 616     };^M
 617     var params4 = {^M
 618         'isPagination' : false,^M
 619         'sqlId' : 'COMMON_SSE_ZQPZ_GP_GPLB_MSXX_C',^M
 620         'productid' : COMPANY_CODE^M
 621     };^M
*/

	private static final int SQLID_COMMON_SSE_ZQPZ_GP_GPLB_C = 0;
	private static final int SQLID_COMMON_SSE_ZQPZ_GP_GPLB_AGSSR_C = 1;
	private static final int SQLID_COMMON_SSE_ZQPZ_GP_GPLB_BGSSR_C = 2;
	private static final int SQLID_COMMON_SSE_ZQPZ_GP_GPLB_MSXX_C = 3;
	private static final String SSE_BASE_URL = "www.sse.com.cn";
	private static final String SSE_BASE_QUERY_URL = "query.sse.com.cn";
	
	
	
	public String getQueryUrl(int productId, int sqlId){
		StringBuffer tempUrl =new StringBuffer();
		tempUrl.append( "/commonQuery.do");
		final String paramSQLIDKey = "sqlId";
		final String paramProductIdKey = "productid";
		switch (sqlId) {
		case SQLID_COMMON_SSE_ZQPZ_GP_GPLB_C:
			tempUrl.append(addParameter(tempUrl.toString(), paramSQLIDKey, "COMMON_SSE_ZQPZ_GP_GPLB_C"));
			break;
		case SQLID_COMMON_SSE_ZQPZ_GP_GPLB_AGSSR_C:
			tempUrl.append(addParameter(tempUrl.toString(), paramSQLIDKey, "COMMON_SSE_ZQPZ_GP_GPLB_AGSSR_C"));
			break;
		case SQLID_COMMON_SSE_ZQPZ_GP_GPLB_BGSSR_C:
			tempUrl.append(addParameter(tempUrl.toString(), paramSQLIDKey, "COMMON_SSE_ZQPZ_GP_GPLB_BGSSR_C"));
			break;
		case SQLID_COMMON_SSE_ZQPZ_GP_GPLB_MSXX_C:
			tempUrl.append(addParameter(tempUrl.toString(), paramSQLIDKey, "COMMON_SSE_ZQPZ_GP_GPLB_MSXX_C"));
			break;
		default:
			break;
		}
		tempUrl.append(addParameter(tempUrl.toString(), paramProductIdKey, ""+productId));
		return tempUrl.toString();
	}
	
	public String addParameter(String baseUrl, String paramKey, String paramValue){
		StringBuffer tempUrl = new StringBuffer();
		if(baseUrl.contains("?")){
			tempUrl.append("&");
		}else {
			tempUrl.append("?");
		}
		tempUrl.append(paramKey+"="+paramValue);
		return tempUrl.toString();
	}
	
	public String getQueryReferUrl(int productId){
		StringBuffer tempUrl =new StringBuffer();
		tempUrl.append(SSE_BASE_URL + "/assortment/stock/list/stockdetails/company/index.shtml");
		tempUrl.append(addParameter(tempUrl.toString(), "COMPANY_CODE", ""+productId));		
		return tempUrl.toString();
	}
	
	
	public String getHostUrl(){
		return SSE_BASE_QUERY_URL;
	}
	
	public boolean isProductIdValid(int productId){
		boolean isValid = false;
		
		return isValid;
	}
	
	public HttpRequest getCompanyHttpRequest(int productId){
		String host = getHostUrl();
		
		String method = "GET";
		HttpRequest request = new DefaultHttpRequest(method,host, getQueryUrl(productId, SQLID_COMMON_SSE_ZQPZ_GP_GPLB_C));
		request.setHeader(HttpRequest.REQUEST_HEADER_HOST, host);
		request.setHeader(HttpRequest.REQUEST_HEADER_REFERER, getQueryReferUrl(productId));

		return request;
	}
}
