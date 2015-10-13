package com.haiming.json;

import java.util.HashMap;


public class Json {
	private HashMap<String, String> mJsonMap = new HashMap<String, String>();

	public Json(String jsonString) {
		String key = "";
		String value = "";
		boolean isKey = true;

		if (jsonString != null) {
			System.out.println(jsonString);
			for (int i = 0; i < jsonString.length(); i++) {
				char temp = jsonString.charAt(i);
				switch (temp) {
				case '"':
				case '{':
				case '}':
				case '[':
				case ']':

					continue;
				case ',':
					if(!mJsonMap.containsKey(key))
						mJsonMap.put(key, value);
					isKey = true;
					key = "";
					value = "";
					break;
				case ':':
					if (!isKey)
						value += temp;
					isKey = false;
					char nextChar = jsonString.charAt(i + 1);
					if (nextChar == '{' || nextChar == '[') {
						isKey = true;
						key = "";
						value = "";
					}
					break;
				default:

					if (isKey) {
						key += temp;
					} else {
						value += temp;
					}
				}
			}
		}
	}

	public void dumpJson() {
		for (String key : mJsonMap.keySet()) {
			System.out.println(key + " : " + mJsonMap.get(key));
		}
	}

	public String get(String key) {
		return mJsonMap.get(key);
	}

}
