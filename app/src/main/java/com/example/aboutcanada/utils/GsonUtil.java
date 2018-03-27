package com.example.aboutcanada.utils;

import android.content.Context;

import com.google.gson.Gson;

import java.io.InputStream;

public class GsonUtil {

	/**
	 * Converts JSON to an object
	 */
	public static <T> T parseJsonToBean(String json, Class<T> cls) {
		Gson gson = new Gson();
		T t = null;
		try {
			t = gson.fromJson(json, cls);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return t;
	}

	/**
	 * Read assets file
	 */
	public static String readLocalJson(Context context, String fileName){
		String resultString="";
		try {
			InputStream inputStream=context.getResources().getAssets().open(fileName);
			byte[] buffer=new byte[inputStream.available()];
			inputStream.read(buffer);
			resultString=new String(buffer,"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultString;
	}

}
