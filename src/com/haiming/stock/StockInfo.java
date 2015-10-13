package com.haiming.stock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

public class StockInfo {
	private static final String STOCK_NAME = "name";
	private static final String STOCK_CODE = "code";
	private static final String STOCK_DATE = "date";
	private static final String STOCK_TIME = "time";
	private static final String STOCK_OPENNINGPRICE = "OpenningPrice";
	private static final String STOCK_CLOSINGPRICE = "closingPrice";
	private static final String STOCK_CURRENTPRICE = "currentPrice";
	private static final String STOCK_HPRICE = "hPrice";
	private static final String STOCK_LPRICE = "lPrice";
	private static final String STOCK_COMPETITIVEPRICE = "competitivePrice";
	private static final String STOCK_AUCTIONPRICE = "auctionPrice";
	private static final String STOCK_TOTALNUMBER = "totalNumber";
	private static final String STOCK_TURNOVER = "turnover";
	private static final String STOCK_BUYONE = "buyOne";
	private static final String STOCK_BUYTWO = "buyTwo";
	private static final String STOCK_BUYTHREE = "buyThree";
	private static final String STOCK_BUYFOUR = "buyFour";
	private static final String STOCK_BUYFIVE = "buyFive";
	private static final String STOCK_BUYONEPRICE = "buyOnePrice";
	private static final String STOCK_BUYTWOPRICE = "buyTwoPrice";
	private static final String STOCK_BUYTHREEPRICE = "buyThreePrice";
	private static final String STOCK_BUYFOURPRICE = "buyFourPrice";
	private static final String STOCK_BUYFIVEPRICE = "buyFivePrice";
	private static final String STOCK_SELLONE = "sellOne";
	private static final String STOCK_SELLTWO = "sellTwo";
	private static final String STOCK_SELLTHREE = "sellThree";
	private static final String STOCK_SELLFOUR = "sellFour";
	private static final String STOCK_SELLFIVE = "sellFive";
	private static final String STOCK_SELLONEPRICE = "sellOnePrice";
	private static final String STOCK_SELLTWOPRICE = "sellTwoPrice";
	private static final String STOCK_SELLTHREEPRICE = "sellThreePrice";
	private static final String STOCK_SELLFOURPRICE = "sellFourPrice";
	private static final String STOCK_SELLFIVEPRICE = "sellFivePrice";

	private String mStockName;
	private String mCode;
	private Date mDate;
	private float mOpenningPrice;
	private float mClosingPrice;
	private float mCurrentPrice;
	private float mHighestPrice;
	private float mLowestPrice;
	private float mCompetitivePrice;
	private float mAuctionPrice;
	private float mTurnover;
	private int mTotalNumber;

	private int[] mBuyNumbers = new int[5];
	private float[] mBuyPrices = new float[5];

	private int[] mSellNumbers = new int[5];
	private float[] mSellPrices = new float[5];

	public StockInfo(JSONObject stockInfo) {
		if (stockInfo != null) {
			mStockName = stockInfo.getString(STOCK_NAME);
			mCode = stockInfo.getString(STOCK_CODE);
			String date = stockInfo.getString(STOCK_DATE);
			String time = stockInfo.getString(STOCK_TIME);
			System.out.println("date: " + date);
			System.out.println("time: " + time);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				mDate = sdf.parse(date.replace("\\/", "-") + " " + time);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			mOpenningPrice = Float.parseFloat(stockInfo.getString(STOCK_OPENNINGPRICE));
			mClosingPrice = Float.parseFloat(stockInfo.getString(STOCK_CLOSINGPRICE));
			mCurrentPrice = Float.parseFloat(stockInfo.getString(STOCK_CURRENTPRICE));
			mHighestPrice = Float.parseFloat(stockInfo.getString(STOCK_HPRICE));
			mLowestPrice = Float.parseFloat(stockInfo.getString(STOCK_LPRICE));
			mCompetitivePrice = Float.parseFloat(stockInfo.getString(STOCK_COMPETITIVEPRICE));
			mAuctionPrice = Float.parseFloat(stockInfo.getString(STOCK_AUCTIONPRICE));
			mTotalNumber = Integer.parseInt(stockInfo.getString(STOCK_TOTALNUMBER));
			mTurnover = Float.parseFloat(stockInfo.getString(STOCK_TURNOVER));
			mBuyNumbers[0] = Integer.parseInt(stockInfo.getString(STOCK_BUYONE));
			mBuyNumbers[1] = Integer.parseInt(stockInfo.getString(STOCK_BUYTWO));
			mBuyNumbers[2] = Integer.parseInt(stockInfo.getString(STOCK_BUYTHREE));
			mBuyNumbers[3] = Integer.parseInt(stockInfo.getString(STOCK_BUYFOUR));
			mBuyNumbers[4] = Integer.parseInt(stockInfo.getString(STOCK_BUYFIVE));
			mBuyPrices[0] = Float.parseFloat(stockInfo.getString(STOCK_BUYONEPRICE));
			mBuyPrices[1] = Float.parseFloat(stockInfo.getString(STOCK_BUYTWOPRICE));
			mBuyPrices[2] = Float.parseFloat(stockInfo.getString(STOCK_BUYTHREEPRICE));
			mBuyPrices[3] = Float.parseFloat(stockInfo.getString(STOCK_BUYFOURPRICE));
			mBuyPrices[4] = Float.parseFloat(stockInfo.getString(STOCK_BUYFIVEPRICE));
			mSellNumbers[0] = Integer.parseInt(stockInfo.getString(STOCK_SELLONE));
			mSellNumbers[1] = Integer.parseInt(stockInfo.getString(STOCK_SELLTWO));
			mSellNumbers[1] = Integer.parseInt(stockInfo.getString(STOCK_SELLTHREE));
			mSellNumbers[1] = Integer.parseInt(stockInfo.getString(STOCK_SELLFOUR));
			mSellNumbers[1] = Integer.parseInt(stockInfo.getString(STOCK_SELLFIVE));
			mSellPrices[0] = Float.parseFloat(stockInfo.getString(STOCK_SELLONEPRICE));
			mSellPrices[1] = Float.parseFloat(stockInfo.getString(STOCK_SELLTWOPRICE));
			mSellPrices[2] = Float.parseFloat(stockInfo.getString(STOCK_SELLTHREEPRICE));
			mSellPrices[3] = Float.parseFloat(stockInfo.getString(STOCK_SELLFOURPRICE));
			mSellPrices[4] = Float.parseFloat(stockInfo.getString(STOCK_SELLFIVEPRICE));
		}
	}
	public String getStockName() {
		return mStockName;
	}

	public String getCode() {
		return mCode;
	}

	public Date getDate() {
		return mDate;
	}

	public float getOpenningPrice() {
		return mOpenningPrice;
	}

	public float getClosingPrice() {
		return mClosingPrice;
	}

	public float getCurrentPrice() {
		return mCurrentPrice;
	}

	public float getHighestPrice() {
		return mHighestPrice;
	}

	public float getmLowestPrice() {
		return mLowestPrice;
	}

	public float getCompetitivePrice() {
		return mCompetitivePrice;
	}

	public float getAuctionPrice() {
		return mAuctionPrice;
	}

	public float getmTurnover() {
		return mTurnover;
	}

	public int getTotalNumber() {
		return mTotalNumber;
	}

	public int[] getBuyNumbers() {
		return mBuyNumbers;
	}

	public float[] getBuyPrices() {
		return mBuyPrices;
	}

	public int[] getSellNumbers() {
		return mSellNumbers;
	}

	public float[] getSellPrices() {
		return mSellPrices;
	}

}
