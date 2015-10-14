package com.haiming.stock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.haiming.mysql.MySQLHelper;


public class UpdateSHStock {
	// private static final String BASE_PATH = "D:/workspace/Stock/raw/";
	private static final String BASE_PATH = "/home/haiming/StudyDocument/WEB/Stock/raw/";
	private static final String SH_DIR = "SHStockList/";
	private static final String SZ_PATH = BASE_PATH + "SZStockList/stock_companies.xls";
	private static final String[] SH_FILES = { 
			"commonQuery1.do", 
			"commonQuery2.do", 
			"commonQuery3.do",
			"commonQuery4.do", 
			"commonQuery5.do", 
			"commonQuery6.do", 
			"commonQuery7.do", 
			"commonQuery8.do",
			"commonQuery9.do", 
			"commonQuery10.do", 
			"commonQuery11.do" 
			};

	private ArrayList<CompanyLite> mCompanies = new ArrayList<CompanyLite>();

	public void update() {
		File file = null;
		BufferedReader reader = null;
		StringBuffer content = new StringBuffer();

		for (int i = 0; i < SH_FILES.length; i++) {
			file = new File(BASE_PATH + SH_DIR + SH_FILES[i]);
			if (file.exists()) {
				content.setLength(0);
				try {
					reader = new BufferedReader(new FileReader(file));
					String lineSting = "";
					while ((lineSting = reader.readLine()) != null) {
						content.append(lineSting);
					}
					updateCompanyInfo(content);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("All Company number: " + mCompanies.size());

		 updateBasicInfo();
	}

	private void updateCompanyInfo(StringBuffer content) {
		JSONObject rootJsonObject = new JSONObject(content.toString());
		JSONObject pageHelper = rootJsonObject.getJSONObject("pageHelp");
		JSONArray listCompanies = pageHelper.getJSONArray("data");
		for (int i = 0; i < listCompanies.length(); i++) {
			JSONObject company = listCompanies.getJSONObject(i);
			boolean isRepeated = false;
			String productId = company.getString("PRODUCTID");
			String name = "";
			if(productId.startsWith("900")){
				name = null;
			} else {
				name = company.getString("FULLNAME");
			}
			String productName = company.getString("PRODUCTNAME");

			for (int j = 0; j < mCompanies.size(); j++) {
				CompanyLite temp = mCompanies.get(j);
				if (productId.equals(temp.mProductId)) {
					isRepeated = true;
					break;
				}
			}
			if (!isRepeated) {
				CompanyLite companyLite = new CompanyLite(name, productName, productId);
				mCompanies.add(companyLite);
			}

		}
	}

	public void updateBasicInfo() {
		String baseSQL = "insert into Company(FullName, ProductName, StockAId) values(?, ?,?)";
		MySQLHelper mysql = new MySQLHelper();
		Connection connection = mysql.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(baseSQL);
			for (CompanyLite companyLite : mCompanies) {
				preparedStatement.setString(1, companyLite.mFullName);
				preparedStatement.setString(2, companyLite.mProductName);
				preparedStatement.setInt(3, Integer.parseInt(companyLite.mProductId));
				preparedStatement.addBatch();
			}
			preparedStatement.clearParameters();
			preparedStatement.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	class CompanyLite {
		public String mFullName = "";
		public String mProductName = "";
		public String mProductId = "";

		public CompanyLite(String name, String productName, String productId) {
			this.mFullName = name;
			this.mProductName = productName;
			this.mProductId = productId;
		}
	};

}
