package com.example.aboutcanada.utils;

import android.content.Context;

import com.google.gson.Gson;

import java.io.InputStream;

/**
 * 封装的是使用Gson解析json的方法
 *
 */
public class GsonUtil {

	/**
	 * 把一个json字符串变成对象
	 * @param json
	 * @param cls
	 * @return
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
