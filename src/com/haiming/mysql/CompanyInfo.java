package com.haiming.mysql;

import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import org.omg.CORBA.PRIVATE_MEMBER;

public final class CompanyInfo {
/*	
	+--------------------+--------------+------+-----+---------+----------------+
	| Field              | Type         | Null | Key | Default | Extra          |
	+--------------------+--------------+------+-----+---------+----------------+
	| id                 | int(11)      | NO   | PRI | NULL    | auto_increment |
	| StockAId           | int(11)      | NO   |     | NULL    |                |
	| StockBId           | int(11)      | YES  |     | NULL    |                |
	| FullName           | varchar(256) | YES  |     | NULL    |                |
	| ProductName        | varchar(128) | YES  |     | NULL    |                |
	| ListingADate       | date         | YES  |     | NULL    |                |
	| ListingBDate       | date         | YES  |     | NULL    |                |
	| Corporate          | varchar(128) | YES  |     | NULL    |                |
	| Email              | varchar(128) | YES  |     | NULL    |                |
	| NetAddress         | varchar(128) | YES  |     | NULL    |                |
	| PhoneNumber        | varchar(20)  | YES  |     | NULL    |                |
	| Trade              | varchar(128) | YES  |     | NULL    |                |
	| City               | varchar(64)  | YES  |     | NULL    |                |
	| isSample180        | tinyint(1)   | YES  |     | NULL    |                |
	| isStockA           | tinyint(1)   | YES  |     | NULL    |                |
	| isStockB           | tinyint(1)   | YES  |     | NULL    |                |
	| isStockedInForeign | tinyint(1)   | YES  |     | NULL    |                |
	| ForeignStockName   | varchar(128) | YES  |     | NULL    |                |
	+--------------------+--------------+------+-----+---------+----------------+
*/

	
	
	private int stockAId;
	private int stockBId;
	private String fullName;
	private String productName;
	private Date listingADate;
	private Date listingBDate;
	private String corporateName;
	private String email;
	private String netAddress;
	private String phoneNumber;
	private String tradeClass;
	private String city;
	private boolean isSample180;
	private boolean isStockA;
	private boolean isStockB;
	private boolean isStockedInForeign;
	private String foreignStockName;
	
	
	/*"result":[{"ENGLISH_ABBR":"SPD BANK","LEGAL_REPRESENTATIVE":"吉晓辉","REPR_PHONE":"-","CSRC_CODE_DESC":"金融业",
	 * "E_MAIL_ADDRESS":"bdo@spdb.com.cn","SSE_CODE_DESC":"综合","WWW_ADDRESS":"http:\/\/www.spdb.com.cn","SECURITY_CODE_A":"600000",
	 * "SECURITY_CODE_B":"-","COMPANY_CODE":"600000","OFFICE_ADDRESS":"上海市中山东一路12号","SECURITY_ABBR_A":"浦发银行",
	 * "COMPANY_ABBR":"浦发银行","AREA_NAME_DESC":"上海","STATE_CODE_A_DESC":"上市","CSRC_MIDDLE_CODE_DESC":"-",
	 * "FULLNAME":"上海浦东发展银行股份有限公司","FOREIGN_LISTING_ADDRESS":"-","COMPANY_ADDRESS":"上海市中山东一路12号",
	 * "STATE_CODE_B_DESC":"-","CSRC_GREAT_CODE_DESC":"货币金融服务","SECURITY_30_DESC":"是","CHANGEABLE_BOND_CODE":"-",
	 * "FOREIGN_LISTING_DESC":"否","CHANGEABLE_BOND_ABBR":"-","SECURITY_CODE_A_SZ":"-",
	 * "FULL_NAME_IN_ENGLISH":"SHANGHAI PUDONG DEVELOPMENT BANK CO., LTD.","OFFICE_ZIP":"200002"}]
	 * */
	
	
	
	private static final String COMPANY_LEGAL_REPRESENTATIVE = "LEGAL_REPRESENTATIVE";
	private static final String COMPANY_REPR_PHONE = "REPR_PHONE";
	private static final String COMPANY_CSRC_CODE_DESC = "CSRC_CODE_DESC";
	private static final String COMPANY_E_MAIL_ADDRESS = "E_MAIL_ADDRESS";
	private static final String COMPANY_SSE_CODE_DESC = "SSE_CODE_DESC";
	private static final String COMPANY_WWW_ADDRESS = "WWW_ADDRESS";
	private static final String COMPANY_SECURITY_CODE_A = "SECURITY_CODE_A";
	private static final String COMPANY_SECURITY_CODE_B = "SECURITY_CODE_B";
	private static final String COMPANY_COMPANY_CODE = "COMPANY_CODE";
	private static final String COMPANY_OFFICE_ADDRESS = "OFFICE_ADDRESS";
	private static final String COMPANY_SECURITY_ABBR_A = "SECURITY_ABBR_A";
	private static final String COMPANY_COMPANY_ABBR = "COMPANY_ABBR";
	private static final String COMPANY_AREA_NAME_DESC = "AREA_NAME_DESC";
	private static final String COMPANY_STATE_CODE_A_DESC = "STATE_CODE_A_DESC";
	private static final String COMPANY_CSRC_MIDDLE_CODE_DESC = "CSRC_MIDDLE_CODE_DESC";
	private static final String COMPANY_FULLNAME = "FULLNAME";
	private static final String COMPANY_FOREIGN_LISTING_ADDRESS = "FOREIGN_LISTING_ADDRESS";
	private static final String COMPANY_COMPANY_ADDRESS = "COMPANY_ADDRESS";
	private static final String COMPANY_STATE_CODE_B_DESC = "STATE_CODE_B_DESC";
	private static final String COMPANY_CSRC_GREAT_CODE_DESC = "CSRC_GREAT_CODE_DESC";
	private static final String COMPANY_SECURITY_30_DESC = "SECURITY_30_DESC";
	private static final String COMPANY_CHANGEABLE_BOND_CODE = "CHANGEABLE_BOND_CODE";
	private static final String COMPANY_FOREIGN_LISTING_DESC = "FOREIGN_LISTING_DESC";
	private static final String COMPANY_CHANGEABLE_BOND_ABBR = "CHANGEABLE_BOND_ABBR";
	private static final String COMPANY_SECURITY_CODE_A_SZ = "SECURITY_CODE_A_SZ";
	private static final String COMPANY_OFFICE_ZIP = "OFFICE_ZIP";
	public CompanyInfo(JSONObject jsonObject) {
		stockAId = Integer.parseInt(jsonObject.getString(COMPANY_SECURITY_CODE_A).trim());
	/*	
		private int stockBId;
		private String fullName;
		private String productName;
		private Date listingADate;
		private Date listingBDate;
		private String corporateName;
		private String email;
		private String netAddress;
		private String phoneNumber;
		private String tradeClass;
		private String city;
		private boolean isSample180;
		private boolean isStockA;
		private boolean isStockB;
		private boolean isStockedInForeign;
		private String foreignStockName;
		*/
	}
	
	public  CompanyInfo() {
		stockAId = -1;
		stockBId = -1;
		fullName = "-";
		productName = "-";
		listingADate = null;
		listingBDate = null;
		corporateName = "-";
		email = "-";
		netAddress = "-";
		phoneNumber = "-";
		tradeClass = "-";
		city = "-";
		isSample180 = false;
		isStockA = false;
		isStockB = false;
		isStockedInForeign = false;
		foreignStockName = "-";
	}
}
