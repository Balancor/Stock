package com.haiming.stock;



import org.json.JSONObject;

public class StockFrame {

	private String mErrorMessage = "";
	private int mErrorCode;
	private StockInfo mStockInfo;

	public StockFrame(String jsonString) {
		if (jsonString != null) {
				
			JSONObject jsonObject = new JSONObject(jsonString);
			mErrorCode = jsonObject.getInt("errNum");
			mErrorMessage = jsonObject.getString("errMsg");
			JSONObject retData = jsonObject.getJSONObject("retData");
			JSONObject stockInfo = retData.getJSONArray("stockinfo").getJSONObject(0);
	
			mStockInfo = new StockInfo(stockInfo);
			
		}
	}
	public String convert(String utfString){
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
	


	
}
