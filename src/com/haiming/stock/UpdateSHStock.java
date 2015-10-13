package com.haiming.stock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class UpdateSHStock {
	private static final String BASE_PATH = "D:/workspace/Stock/raw/";
	private static final String SH_DIR = "SHStockList/";
	private static final String SZ_PATH = BASE_PATH+"SZStockList/stock_companies.xls";
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
	
	public void update(){
		File file = null;
		BufferedReader reader = null;
		StringBuffer content = new StringBuffer();

		for (int i = 0; i < SH_FILES.length; i++) {
			file = new File(BASE_PATH+SH_DIR+SH_FILES[i]);
			if(file.exists()){
				try {
					reader = new BufferedReader(new FileReader(file));
					
					String lineSting = "";
					int line = 0;

					while( (lineSting = reader.readLine()) != null){
						content.append(lineSting);
						line++;
					}
						
				
					System.out.println("content: "+content.toString());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					try {
						reader.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
	}
 
}
